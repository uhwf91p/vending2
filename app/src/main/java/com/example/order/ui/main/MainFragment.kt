package com.example.order.ui.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order.AppState
import com.example.order.Data.Keys
import com.example.order.Data.Keys.KEY_FOR_INFLATE_MAIN_LIST
import com.example.order.Data.Keys.count
import com.example.order.Data.MainList
import com.example.order.MainActivity
import com.example.order.R
import com.example.order.Repository.RepositoryMakeResult
import com.example.order.Repository.RepositoryMskeResultImpl
import com.example.order.ViewModel.MainViewModel
import com.example.order.databinding.MainFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*


class MainFragment : Fragment() {

    var repositoryUpload: RepositoryMakeResult = RepositoryMskeResultImpl()
    private lateinit var bottomSheetBehavor: BottomSheetBehavior<ConstraintLayout>
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val textView     = binding.inputEditTextDate

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        input_date_layout.setEndIconOnClickListener {

            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
                val month=month+1

                textView.setText("${addZeroToMonthAndDay(dayOfMonth)}.${addZeroToMonthAndDay(month)}.$year")
            }, year, month, day)
            dpd.show()

        }

        setBottomSheetBehavor(view.findViewById(R.id.bottom_sheet_container))
        setBottomAppBar(view)
        hideAndShowDate()
         adapter.setOnItemViewClickListener(object : OnItemViewClickListener {
            override fun onItemViewClick(mainList: MainList) {
                if (count == KEY_FOR_INFLATE_MAIN_LIST) {
                    binding.inputEditTextDate.hide()
                    Keys.LIST_KEY = mainList.id2
                    count += 1
                    val manager = activity?.supportFragmentManager
                    makeDetails(manager, mainList)
                } else {
                    binding.inputEditTextDate.show()

                    count = KEY_FOR_INFLATE_MAIN_LIST;
                    Keys.LIST_KEY = Keys.DEFAULT_VALUE
                    val manager = activity?.supportFragmentManager
                    repositoryUpload.rememberMainList(mainList)
                    makeDetails(manager, mainList)

                }
            }

        })

        binding.mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainFragmentRecyclerView.adapter = adapter
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
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

    private fun setBottomSheetBehavor(bottomSheet: ConstraintLayout) {
        bottomSheetBehavor = BottomSheetBehavior.from(bottomSheet)
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
               adapter.setMainList(data.mainList)

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
    private fun addZeroToMonthAndDay(dayOrMonth:Int):String{

        return if (dayOrMonth <10) {
            "0$dayOrMonth"

        } else{
            dayOrMonth.toString()
        }

    }
    fun hideAndShowDate(){
        if (count!= KEY_FOR_INFLATE_MAIN_LIST) {
            //второй экран
            binding.inputEditTextDate.isGone=true
            binding.inputDateLayout.endIconMode=TextInputLayout.END_ICON_NONE
            val params = binding.mainFragmentRecyclerView.layoutParams as ConstraintLayout.LayoutParams
            params.topToBottom=binding.inputLayout.id


        } else {

            binding.inputDateLayout.isGone=false


        }
    }





    companion object {
        fun newInstance()= MainFragment()
        }
    }



