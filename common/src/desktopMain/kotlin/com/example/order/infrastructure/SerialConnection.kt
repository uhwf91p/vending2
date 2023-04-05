package com.example.order.infrastructure

import com.fazecast.jSerialComm.SerialPort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.logging.Logger

actual class SerialConnection {
    private var serialPort: SerialPort? = null
    private val logger = Logger.getLogger(this::class.qualifiedName)

    actual suspend fun connect(): Boolean = withContext(Dispatchers.IO) {
        if (serialPort == null) {
            val availablePorts = SerialPort.getCommPorts().apply {
                val systemPortNames = this.map { it.systemPortName }
                logger.info("Available ports by SystemPortName: $systemPortNames")
            }
            val ttySerialPorts = availablePorts.filter { port -> SERIAL_PORT_IDENTIFIERS.any { port.systemPortName.contains(it) } }.apply {
                val systemPortNames = this.map { it.systemPortName }
                logger.info("ttySerialPorts by SystemPortName: $systemPortNames")
            }
            ttySerialPorts.firstOrNull()?.let {
                it.setComPortParameters(9600, 8, 1, 0)
                serialPort = it
            }
            logger.info("Serial port initialization. Serial port = $serialPort")
        }
        serialPort?.openPort(20).apply {
            logger.info("Serial port = ${serialPort}. Result = $this")
        } ?: false
    }

    actual suspend fun disconnect() = serialPort?.closePort().apply {
        logger.info("Disconnection from serialPort = ${serialPort}. Result = $this")
    } ?: false

    actual suspend fun write(data: ByteArray) = serialPort?.writeBytes(data, data.size.toLong()).apply {
        logger.info("Write bytes result. Result = ${this}. Serial port = ${serialPort}. Data array = $data")
    } ?: -1

    companion object {
        private val SERIAL_PORT_IDENTIFIERS = listOf(
            "ttyACM",
            "ttyUSB"
        )
    }
}