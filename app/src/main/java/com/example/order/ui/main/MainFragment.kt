package com.example.order.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order.AppState
import com.example.order.Data.Keys
import com.example.order.Data.Keys.count
import com.example.order.Data.MainList
import com.example.order.MainActivity
import com.example.order.R
import com.example.order.Repository.*
import com.example.order.ViewModel.Converters
import com.example.order.ViewModel.MainViewModel
import com.example.order.app.App
import com.example.order.databinding.MainFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainFragment : Fragment() {

    var repositoryUpload: RepositoryMakeResult = RepositoryMskeResultImpl()
    private lateinit var bottomSheetBehavor: BottomSheetBehavior<ConstraintLayout>
    private val repository: RepositoryGetMainList = RepositoryGetMainListImpl()


    companion object {
        fun newInstance() = MainFragment()
    }

    val converters: Converters = Converters()
    private val localRepository1C: LocalRepository1C = LocalRepository1CImpl(App.get1CDAO())


    /*override fun onActivityCreate(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }*/


    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = MainFragmentAdapter()


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


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
        adapter.removeOnItemViewClickListener()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val observer= Observer<AppState> {renderData(it)  }

        viewModel.getData()
            .observe(viewLifecycleOwner, observer)


        setBottomSheetBehavor(view.findViewById(R.id.bottom_sheet_container))
        setBottomAppBar(view)

        adapter.setOnItemViewClickListener(object : OnItemViewClickListener {
            override fun onItemViewClick(mainList: MainList) {
                if (count == Keys.KEY_FOR_INFLATE_MAIN_LIST) {
                    Keys.LIST_KEY = mainList.id2
                    count += 1
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
        //val observer=Observer<AppState>{ renderData2(it)}
        /* viewModel.getData()
             .observe(viewLifecycleOwner, { renderData(it) })*/
        binding.mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainFragmentRecyclerView.adapter = adapter
        /*viewModel.getData().observe(viewLifecycleOwner,observer)*/
        /*viewModel.getMainListViewModel()*/
        val observerForRender = Observer<AppState> { renderData2(it) }
        viewModel.getDataForDBRequest().observe(viewLifecycleOwner, observerForRender)
        viewModel.getMainListViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_botom_bar, menu)
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_bar_main))
        setHasOptionsMenu(true)

    }

    private fun setBottomSheetBehavor(bottomSeet: ConstraintLayout) {
        bottomSheetBehavor = BottomSheetBehavior.from(bottomSeet)
        bottomSheetBehavor.state = BottomSheetBehavior.STATE_COLLAPSED

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return super.onOptionsItemSelected(item)
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
        when (data) {
            is AppState.Success -> {
                val serverResponseData = data.serverResponseData
                localRepository1C.deleteAllData()
                localRepository1C.putDataFromServer1CToLocalDatabase(serverResponseData)

            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {

                toast(data.error.message)



            }

        }

    }


    private fun renderData2(data: AppState) {
        when (data) {
            is AppState.Success -> {
                adapter.setMainList(data.serverResponseData)
            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {

                toast(data.error.message)
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(mainList: MainList)
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

}