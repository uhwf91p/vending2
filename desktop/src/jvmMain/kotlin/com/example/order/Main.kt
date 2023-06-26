package com.example.order

import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.example.order.config.AppConfig
import com.example.order.infrastructure.SerialConnection
import com.example.order.provider.CellProviderImpl
import com.example.order.service.CellServiceImpl
import com.example.order.service.SerialServiceImpl
import com.example.order.ui.MainScreen
import com.example.order.ui.MainViewModel

fun main() = singleWindowApplication(
    title = "Vending",
    state = WindowState(width = 800.dp, height = 480.dp), // Разрешение 7 дюймового тачскрина
    icon = BitmapPainter(useResource("ic_launcher.png", ::loadImageBitmap)),
) {
    val config = appConfig()
    MainScreen(
        MainViewModel(
            SerialServiceImpl(
                SerialConnection()
            ),
            CellServiceImpl(
                CellProviderImpl(config)
            ),
            config
        )
    )
}
