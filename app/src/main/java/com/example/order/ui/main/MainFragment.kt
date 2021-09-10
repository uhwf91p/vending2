package com.example.order.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order.AppState
import com.example.order.Data.MainList
import com.example.order.R
import com.example.order.Data.Keys
import com.example.order.Data.Keys.count
import com.example.order.Repository.RepositoryUpload
import com.example.order.Repository.RepositoryUploadImpl
import com.example.order.databinding.MainFragmentBinding


class MainFragment : Fragment() {

 var repositoryUpload:RepositoryUpload=RepositoryUploadImpl()


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
        adapter.removeOnItemViewClickListener()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener(object: OnItemViewClickListener {
            override fun onItemViewClick(mainList: MainList) {
                if (count == Keys.KEY_FOR_INFLATE_MAIN_LIST) {
                    Keys.LIST_KEY = mainList.id2
                    count = count +1
                    val manager = activity?.supportFragmentManager
                    makeDetails(manager, mainList)
                } else {

                    count = Keys.KEY_FOR_INFLATE_MAIN_LIST;
                    Keys.LIST_KEY = Keys.DEFAULT_VALUE
                    val manager = activity?.supportFragmentManager
                    repositoryUpload.rememberMainList(mainList)
                    makeDetails(manager, mainList)

                }
            }

        })



        val observer=Observer<AppState>{ renderData(it)}
        binding.mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainFragmentRecyclerView.adapter=adapter
        viewModel.getData().observe(viewLifecycleOwner,observer)

        viewModel.getMainListViewModel()
    }

    private fun makeDetails(
        manager: FragmentManager?,
        mainList: MainList
    ) {
        if (manager != null) {
            val bundle = Bundle()

            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, mainList)
            manager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    private fun renderData(data: AppState) {
        when (data){
            is AppState.loadMainList->{
                adapter.setMainList(data.mainList)

            }

        }


    }
    interface OnItemViewClickListener {
        fun onItemViewClick(mainList: MainList)
    }

}