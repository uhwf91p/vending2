package com.example.order.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order.AppState
import com.example.order.Data.MainList
import com.example.order.Data.Order
import com.example.order.R
import com.example.order.Repository.Repository
import com.example.order.databinding.MainFragmentBinding

class MainFragment : Fragment() {




    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = MainFragmentAdapter()


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)  }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      /*  adapter.setOnItemClickListener(object: OnItemClickListener{
            override fun onItemClick(mainList: MainList) {mainList->
                activity?.supportFragmentManager?.apply {
                    beginTransaction().add(R.id.container,)
                }

            }

        })*/



        val observer=Observer<AppState>{ renderData(it)}
        binding.mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainFragmentRecyclerView.adapter=adapter
        viewModel.getData().observe(viewLifecycleOwner,observer)
        viewModel.getMainListViewModel()
    }

    private fun renderData(data: AppState) {
        when (data){
            is AppState.loadMainList->{
                adapter.setMainList(data.mainList)

            }

        }


    }
    interface OnItemClickListener {
        fun onItemClick(mainList: MainList)
    }
   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}