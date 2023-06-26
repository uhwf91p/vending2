package com.example.order.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.order.config.AppConfig
import com.example.order.service.CellService
import com.example.order.service.SerialService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

actual class MainViewModel(
    private val serialService: SerialService,
    private val cellService: CellService,
    private val appConfig: AppConfig
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainState())
    actual val uiState: StateFlow<MainState> = _uiState

    actual fun onConnect() {
        viewModelScope.launch(Dispatchers.IO) {
            serialService.connect()
        }
    }

    actual fun onQrInputChange(value: String) {
        _uiState.value = _uiState.value.copy(
            qrInput = value,
        )
        if (_uiState.value.qrInput.contains(appConfig.eofSymbol)) {
            val code = _uiState.value.qrInput
            val opened = !_uiState.value.opened
            viewModelScope.launch(Dispatchers.IO) {
                cellService.openCellByCode(
                    code = code,
                    opened = opened
                )
            }
            _uiState.value = _uiState.value.copy(
                qrInput = "",
                opened = opened
            )
        }
    }

    actual fun onInputChange(value: String) {
        _uiState.value = _uiState.value.copy(
            input = value
        )
    }

    actual fun onOpen() {
        when (_uiState.value.connectionState) {
            ConnectionState.Disconnected -> {

            }
            ConnectionState.Connected -> {
                viewModelScope.launch(Dispatchers.IO) {
                    serialService.writeString(_uiState.value.input)
                }
            }
        }
    }
}