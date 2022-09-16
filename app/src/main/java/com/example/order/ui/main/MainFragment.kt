package com.example.order.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.order.R
import com.example.order.app.domain.model.ListItem
import com.example.order.app.domain.usecase.*
import com.example.order.core.GlobalConstAndVars
import com.example.order.databinding.MainFragmentBinding
import com.example.order.viewModel.MainViewModel
import com.foxek.usb_custom_hid_demo.type.Error
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.*


class MainFragment : Fragment() {



    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val questionsAdapter = VariantAdapter()
    private lateinit var viewModel: MainViewModel
   private val fireBase:FireBaseCase=FirebaseCaseImpl()
    private val load:LoadDataFrom1CCase=LoadDataFrom1CCaseImpl()
    private val list:CreateListOfAllItemsFrom1CDBCase=CreateListOfAllItemsFrom1CDBCaseImpl()
    private val storage = Firebase.storage
    private val storageRef = storage.reference




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root





    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]




        ledState.setOnCheckedChangeListener { _, isChecked -> viewModel.changeLedButtonPressed(isChecked) }

        connectButton.setOnClickListener {
            viewModel.connectButtonPressed()
        }

        viewModel.answerFromUsbState.observe(viewLifecycleOwner, Observer {
            buttonState.isChecked = true
        })

        viewModel.usbOperationError.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Error.NoDeviceFoundError -> showMessage(getString(R.string.error_no_device))
                is Error.UsbConnectionError -> showMessage(getString(R.string.error_no_connection))
                is Error.ClaimInterfaceError -> showMessage(getString(R.string.error_claim_interface))
                is Error.ReadReportError -> showMessage(getString(R.string.error_read_report))
                /* handle other error */
            }
            connectButton.text = getString(R.string.connect_hint)
            buttonState.isEnabled = false
            ledState.isEnabled = false
        })

        viewModel.usbOperationSuccess.observe(viewLifecycleOwner, Observer {
            showMessage(getString(R.string.connection_state_success))
            connectButton.text = getString(R.string.disconnect_hint)
            buttonState.isEnabled = false
            ledState.isEnabled = false
        })
        binding.getOrder.setOnClickListener {
         appCoroutineScope.launch {  viewModel.processOpeningCells(GlobalConstAndVars.ORDERS_NUMBER) }


           /* val report = ByteArray(1)
            report[0] = 1*/
            viewModel.openCellButtonIsPressed(GlobalConstAndVars.CELLS_LIST)
                //заглушка

            showMessage("ячейки ${GlobalConstAndVars.CELLS_STRING} открыты")
        }


/*
        binding.lock1.setOnClickListener {
            val report = ByteArray(1)
            report[0] = 1
            viewModel.openCell(report)
        }


        binding.lock2.setOnClickListener {
            val report = ByteArray(1)
            report[0] = 2
            viewModel.openCell(report)
        }
        binding.lock3.setOnClickListener {
            val report = ByteArray(1)
            report[0] = 3
            viewModel.openCell(report)
        }*/

        isEditingGetOrderFinished()
        //заглушка - переписать после подключения сканера штрих кодов
        isEditingLoadCellFinished()



    }
    private val appCoroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + CoroutineExceptionHandler { _, _ ->
            handleError()
        })
    private fun handleError() {}
    private fun isEditingGetOrderFinished() {
        binding.inputEditTextOrderNumber.setOnFocusChangeListener { _: View, b: Boolean ->
            if (!b) {
                GlobalConstAndVars.ORDERS_NUMBER = binding.inputEditTextOrderNumber.text.toString()
                binding.inputEditTextOrderNumber.setText(GlobalConstAndVars.ORDERS_NUMBER)
            }
        }

    }
    private fun isEditingLoadCellFinished() {
        binding.goodArticleInputEditText.setOnFocusChangeListener { _: View, b: Boolean ->
            if (!b) {
                GlobalConstAndVars.GOOD_TO_LOAD = binding.inputEditTextOrderNumber.text.toString()
                binding.inputEditTextOrderNumber.setText(GlobalConstAndVars.GOOD_TO_LOAD)
            }
        }
    }





        private fun showMessage(message: String) {
            (activity as MainActivity).showMessage(message)
        }



















    companion object {
        fun newInstance()= MainFragment()
        }
    interface OnItemViewClickListener {
        fun onItemViewClick(listItem: ListItem)
    }

}




