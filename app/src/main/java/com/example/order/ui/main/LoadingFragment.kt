package com.example.order.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.order.AppState
import com.example.order.R
import com.example.order.Repository.LocalRepository
import com.example.order.Repository.LocalRepositoryImpl
import com.example.order.ViewModel.LoadingViewModel
import com.example.order.app.App
import com.example.order.databinding.LoadingFragmentBinding

class LoadingFragment:Fragment() {
    private var _binding:LoadingFragmentBinding?=null
    private val binding get()=_binding!!
    private val viewModel:LoadingViewModel by lazy { ViewModelProvider(this).get(LoadingViewModel::class.java) }
    private val localRepository1C: LocalRepository = LocalRepositoryImpl(App.get1CDAO())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= LoadingFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding=null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadinglayout.show()
        viewModel.getDataFromServerForDB().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getDataFromServer()

    }



    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                localRepository1C.deleteAllData()
                localRepository1C.putDataFromServer1CToLocalDatabase(data.mainList)
                Toast.makeText(context,"Справочники закгружены успешно",Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.BOTTOM,0,250)
                    show()
                }
                binding.loadinglayout.hide()
                goToMainList(activity?.supportFragmentManager)


            }
            is AppState.Loading -> {
                binding.loadinglayout.show()
            }
            is AppState.Error -> {
                binding.loadinglayout.show()
                Toast.makeText(context,data.error.message,Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.BOTTOM,0,250)
                    show()
                }
                goToMainList(activity?.supportFragmentManager)


            }
        }
    }
    private fun goToMainList(
        manager: FragmentManager?,

    ) {
        manager?.beginTransaction()?.replace(R.id.container, MainFragment.newInstance())
            ?.addToBackStack("")?.commitAllowingStateLoss()
    }
    companion object {
        fun newInstance() = LoadingFragment()

        }
    }






