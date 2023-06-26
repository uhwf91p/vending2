package com.example.order

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.order.infrastructure.SerialConnection
import com.example.order.provider.CellProviderImpl
import com.example.order.service.CellServiceImpl
import com.example.order.service.SerialServiceImpl
import com.example.order.ui.MainScreen
import com.example.order.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appConfig = appConfig()
        // TODO: Добавить DI
        setContent {
            MainScreen(
                MainViewModel(
                    SerialServiceImpl(
                        SerialConnection(
                            getSystemService(Context.USB_SERVICE) as UsbManager
                        )
                    ),
                    CellServiceImpl(
                        CellProviderImpl(
                            appConfig
                        )
                    ),
                    appConfig
                )
            )
        }
    }
}