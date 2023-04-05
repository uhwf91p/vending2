package com.example.order.infrastructure

import android.hardware.usb.UsbManager
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import com.hoho.android.usbserial.util.SerialInputOutputManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

actual class SerialConnection(
    private val usbManager: UsbManager
) {

    private var serialPort: UsbSerialPort? = null
    private var inputOutputManager: SerialInputOutputManager? = null
    private val isOpen = AtomicBoolean(false)

    actual suspend fun connect() = withContext(Dispatchers.IO) {
        val deviceList = usbManager.deviceList
        if (deviceList.isNotEmpty()) {
            deviceList.values.forEach { usbDevice ->
                val driver = UsbSerialProber.getDefaultProber().probeDevice(usbDevice)
                val usbConnection = usbManager.openDevice(usbDevice)
                if (driver != null) {
                    driver.ports.firstOrNull()?.let { serialPort ->
                        val opened = try {
                            serialPort.open(usbConnection)
                            true
                        } catch (exception: IOException) {
                            false
                        }
                        if (opened) {
                            serialPort.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
                            isOpen.set(true)
                            inputOutputManager = SerialInputOutputManager(serialPort)
                            Executors.newSingleThreadExecutor().submit(inputOutputManager)
                        }
                    }
                }
            }
        }
        isOpen.get()
    }

    actual suspend fun disconnect() = withContext(Dispatchers.IO) {
        if (isOpen.get()) {
            try {
                inputOutputManager?.stop()
                serialPort?.close()
                isOpen.set(false)
            } finally {
                serialPort = null
                inputOutputManager = null
                !isOpen.get()
            }
        }
        !isOpen.get()
    }

    actual suspend fun write(data: ByteArray) = withContext(Dispatchers.IO) {
        if (isOpen.get()) {
            try {
                serialPort?.let {
                    it.write(data, 1000)
                    1
                } ?: -1
            } catch (e: IOException) {
                -1
            }
        } else {
            -1
        }
    }
}