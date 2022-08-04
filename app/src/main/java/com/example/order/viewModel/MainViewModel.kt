package com.example.order.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.app.domain.model.SearchItem
import com.example.order.core.GlobalConstAndVars
import com.example.order.app.domain.model.ListItem
import com.example.order.datasource.Server.Retrofit1C
import com.example.order.datasource.Server.ServerResponseData
import com.example.order.app.domain.usecase.AppState
import com.example.order.app.domain.usecase.*
import com.foxek.usb_custom_hid_demo.device.CustomDeviceImpl
import com.foxek.usb_custom_hid_demo.hardware.UsbHelperImpl
import com.foxek.usb_custom_hid_demo.type.Empty
import com.foxek.usb_custom_hid_demo.type.Error
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainViewModel(
    private val createLists: CreateListsForFirstAndSecondScreensCase = CreateListsForFirstAndSecondScreensCaseImpl(),
    private val makeResultCase: GetSelectionResultCase = GetSelectionResultCaseImpl(),
            application: Application




) : ViewModel() {
    private val customDevice =
        CustomDeviceImpl(UsbHelperImpl(application.applicationContext))

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val retrofit1C: Retrofit1C = Retrofit1C()
    private val converters: Converters = Converters()
    private val createListOfOrdersAndStartListItem: CreateListOfAllItemsFrom1CDBCase =
        CreateListOfAllItemsFrom1CDBCaseImpl()
    var ticket:List<ListItem> = listOf()
    var questions: String = "1"
    var variants: List<ListItem> = listOf()

    private val disposable = CompositeDisposable()

    val buttonState = MutableLiveData<Boolean>()
    val usbOperationError = MutableLiveData<Error>()
    val usbOperationSuccess = MutableLiveData<Empty>()

    fun changeLedButtonPressed(state: Boolean) {
        customDevice.setLedState(state).handle(::handleError, ::handleChangeLed)
    }

    fun connectButtonPressed() {
        if (customDevice.isConnected().isSuccess)
            customDevice.disconnect()
        else {
            customDevice.connect().handle(::handleError, ::handleConnect)
        }
    }

    private fun handleError(error: Error) {
        usbOperationError.postValue(error)
    }

    private fun handleChangeLed(success: Empty){
        usbOperationSuccess.postValue(success)
    }

    private fun handleConnect(success: Empty){
        observeUsbRequest()
        usbOperationSuccess.postValue(success)
    }

    private fun handleReport(report: ByteArray){
        when (report[0]) {
            CustomDeviceImpl.BUTTON_REPORT_ID.toByte() -> handleButtonResponse(report)
            /* handle other report ID*/
        }
    }

    private fun observeUsbRequest() {
        disposable.add(
            customDevice.receive()
                .observeOn(Schedulers.computation())
                .repeat()
                .subscribe({
                    it.handle(::handleError, ::handleReport)
                }, {
                    usbOperationError.postValue(Error.ReadReportError)
                })
        )
    }

    private fun handleButtonResponse(response: ByteArray) {
        if (response[1] == 0.toByte())
            buttonState.postValue(false)
        else
            buttonState.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        customDevice.disconnect()
    }




        fun makeOrdersFinishedVM() {
            makeResultCase.makeOrderFinished()

        }




        fun pullDataToServer(resultListItem: List<ListItem>) {
            liveDataToObserve.value = AppState.Loading(null)
            val apiKey: String = "BuildConfig.APIKEY_FROM_1C"
            if (apiKey.isBlank()) {
                AppState.Error(Throwable("You need API key"))
            } else {
                retrofit1C.getRetrofit().pullDataTo1C(resultListItem).enqueue(object :
                    Callback<List<ServerResponseData>> {
                    override fun onResponse(
                        call: Call<List<ServerResponseData>>,
                        response: Response<List<ServerResponseData>>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDataToObserve.value = AppState.Success(resultListItem)
                            GlobalConstAndVars.LIST_OF_FINISHED_ORDERS = response.body()!!
                            makeOrdersFinishedVM()

                        } else {
                            val message = response.message()
                            if (message.isNullOrEmpty()) {
                                liveDataToObserve.value =
                                    AppState.Error(Throwable("Unidentified error"))
                            } else {
                                liveDataToObserve.value =
                                    AppState.Error(Throwable(message))
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<ServerResponseData>>, t: Throwable) {
                        liveDataToObserve.value = AppState.Error(t)
                    }
                })
            }

        }





    }




