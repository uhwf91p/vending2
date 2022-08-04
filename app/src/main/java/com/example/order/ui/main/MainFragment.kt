package com.example.order.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.order.R
import com.example.order.app.domain.usecase.*
import com.example.order.databinding.MainFragmentBinding
import com.example.order.viewModel.MainViewModel
import com.foxek.usb_custom_hid_demo.type.Error
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {



    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val questionsAdapter = VariantAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
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



        ledState.setOnCheckedChangeListener { _, isChecked -> viewModel.changeLedButtonPressed(isChecked) }

        connectButton.setOnClickListener {
            viewModel.connectButtonPressed()
        }

        viewModel.buttonState.observe(viewLifecycleOwner, Observer {
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

        viewModel.usbOperationSuccess.observe(this, Observer {
            showMessage(getString(R.string.connection_state_success))
            connectButton.text = getString(R.string.disconnect_hint)
            buttonState.isEnabled = false
            ledState.isEnabled = false
        })




    }



    private fun showMessage(message: String) {
        (activity as MainActivity).showMessage(message)
    }


















    companion object {
        fun newInstance()= MainFragment()
        }
 }




