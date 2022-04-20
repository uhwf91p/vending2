package com.example.order.ui.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order.Data.ItemForSearchTextStorage
import com.example.order.app.domain.AppState
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.GlobalConstAndVars.KEY_FOR_INFLATE_MAIN_LIST
import com.example.order.Data.GlobalConstAndVars.count
import com.example.order.Data.ItemOfList
import com.example.order.MainActivity
import com.example.order.R
import com.example.order.Repository.*
import com.example.order.ViewModel.MainViewModel
import com.example.order.app.App
import com.example.order.databinding.MainFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.*
import java.util.*


class MainFragment : Fragment() {


   /* var repositoryResult: RepositoryMakeResult = RepositoryMakeResultImpl()*/
    private val localRepository1C: LocalRepository = LocalRepositoryImpl(App.get1CDAO())
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = MainFragmentAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
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
        val workedOut=binding.inputEditTextWorked
        val etSearchBar=binding.inputEditText
        val textView     = binding.inputEditTextDate
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        textView.setText(GlobalConstAndVars.DATE_OF_ORDER)
        input_date_layout.setEndIconOnClickListener {
            val dpd = DatePickerDialog(requireContext(), { _, year, _, dayOfMonth ->
                val month=month+1
                GlobalConstAndVars.DATE_OF_ORDER="$year.${addZeroToMonthAndDay(month)}.${addZeroToMonthAndDay(dayOfMonth)}"
                textView.setText(GlobalConstAndVars.DATE_OF_ORDER)
            }, year, month, day)
            dpd.show()
        }
        workedOut.setText(GlobalConstAndVars.WORKED_OUT)
        workedOut.setOnClickListener {
            GlobalConstAndVars.WORKED_OUT=workedOut.text.toString()
            workedOut.setText(GlobalConstAndVars.WORKED_OUT)
        }

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        setBottomAppBar(view)
        hideAndShowViews()
         adapter.setOnItemViewClickListener(object : OnItemViewClickListener {
            override fun onItemViewClick(itemOfList: ItemOfList) {
                if (count == KEY_FOR_INFLATE_MAIN_LIST) {
                    binding.inputEditTextDate.hide()
                    GlobalConstAndVars.LIST_KEY = itemOfList.id2
                    count += 1
                    val manager = activity?.supportFragmentManager
                    makeDetails(manager, itemOfList)

                } else {
                    binding.inputEditTextDate.show()
                    count = KEY_FOR_INFLATE_MAIN_LIST
                    GlobalConstAndVars.LIST_KEY = GlobalConstAndVars.DEFAULT_VALUE
                    val manager = activity?.supportFragmentManager
                    viewModel.rememberListOfChosenItemsVM(itemOfList)
                    makeDetails(manager, itemOfList)
                }
            }

        })

        binding.mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainFragmentRecyclerView.adapter = adapter
        viewModel.getData().observe(viewLifecycleOwner, { renderList(it) })
        viewModel.getMainListViewModel()
        etSearchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateSearch()
            }
        })

        workedOut.setOnFocusChangeListener{ _: View, b: Boolean ->
            if (!b){
                GlobalConstAndVars.WORKED_OUT= workedOut.text.toString()
                workedOut.setText(GlobalConstAndVars.WORKED_OUT)
            }

        }

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

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.send_main_bottom_bar) {

            if (GlobalConstAndVars.DATE_OF_ORDER!= "") {
               val dateFromCalendar= ItemOfList("date","date",GlobalConstAndVars.DATE_OF_ORDER,"") // убрать хардкод из этой строки
                viewModel.rememberListOfChosenItemsVM(dateFromCalendar)//<-----!!!!Изменил!!!! раньше запрашивлось из репозитория результатов
            }
            if (GlobalConstAndVars.WORKED_OUT!= "") {
                val worked= ItemOfList("Фактически отработано в натуре",GlobalConstAndVars.WORKED_OUT,GlobalConstAndVars.WORKED_OUT,GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST) // убрать хардкод из этой строки
                viewModel.rememberListOfChosenItemsVM(worked)//<-----!!!!Изменил!!!! раньше запрашивлось из репозитория результатов
            }

            if (viewModel.checkCompleteness(GlobalConstAndVars.listForFirstScreen,GlobalConstAndVars.listOfChosenItemOfLists,GlobalConstAndVars.DATE_OF_ORDER,GlobalConstAndVars.WORKED_OUT)=="Данные наряда заполнены не полностью"){
                toast("Данные наряда заполнены не полностью")
            }
            else {
                localRepository1C.putDataToResultDB(GlobalConstAndVars.listOfChosenItemOfLists)
                toast("данные записаны успешно")
                val workedOut=binding.inputEditTextWorked
                viewModel.pullDataToServer(localRepository1C.getAllDataDBResultEntityToMainList())
                viewModel.getData().observe(viewLifecycleOwner, { isDataUploadedToServer(it) })

                GlobalConstAndVars.listForFirstScreen = mutableListOf()
                GlobalConstAndVars.listOfChosenItemOfLists= mutableListOf()
                GlobalConstAndVars.DATE_OF_ORDER= ""
                GlobalConstAndVars.LIST_KEY=GlobalConstAndVars.DEFAULT_VALUE
                GlobalConstAndVars.WORKED_OUT= ""
                workedOut.setText(GlobalConstAndVars.WORKED_OUT)
                goToSaveFragment(activity?.supportFragmentManager)


            }
        }
        if (item.itemId == R.id.refresh) {
            viewModel.pullDataToServer(localRepository1C.getAllDataDBResultEntityToMainList())
            viewModel.getData().observe(viewLifecycleOwner, { isDataUploadedToServer(it) })



        }

        return super.onOptionsItemSelected(item)
    }

    private fun makeDetails(
        manager: FragmentManager?,
        itemOfList: ItemOfList
    ) {
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, itemOfList)
            manager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
    private fun updateSearch() {
        val etSearchBar=binding.inputEditText
        val s = etSearchBar.text
        if (s?.length == 0) {
            adapter.setMainList(viewModel.convertArrayListItemToMainList(ItemForSearchTextStorage.list))
        } else {
            adapter.setMainList( viewModel.convertArrayListItemToMainList(ItemForSearchTextStorage.list).filter {
                 it.name.contains(s.toString(), true)
            } )
        }

    }

    private fun renderList(data: AppState) {
        when (data) {
            is AppState.Success -> {
               adapter.setMainList(data.itemOfList)
                ItemForSearchTextStorage.list=viewModel.convertMainListToArrayListItem(data.itemOfList)
            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
                toast(data.error.message)

            }

        }

    }
    private fun isDataUploadedToServer(data: AppState) {
        when (data) {
            is AppState.Success -> {
             toast("Выгрузка прошла успешно")
            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
                toast("Данные не загружены:${data.error.message}")


            }

        }

    }

    interface OnItemViewClickListener {
        fun onItemViewClick(itemOfList: ItemOfList)
    }

   private fun Fragment.toast(string: String?) {
     fun handleError(error: Throwable){}
       val fragmentCoroutineScope = CoroutineScope(
           Dispatchers.Main+ SupervisorJob() + CoroutineExceptionHandler { _, throwable -> handleError(throwable)  })

        fragmentCoroutineScope.launch { Toast.makeText(context, string, Toast.LENGTH_LONG).
        show() }

    }
    private fun addZeroToMonthAndDay(dayOrMonth:Int):String{
        return if (dayOrMonth <10) {
            "0$dayOrMonth"

        } else{
            dayOrMonth.toString()
        }

    }
    private fun hideAndShowViews(){
        if (count!= KEY_FOR_INFLATE_MAIN_LIST) {
            //второй экран
            binding.inputEditTextDate.isGone=true
            binding.bottomBarMain.isGone=true
            binding.inputLayout.isGone=false
            binding.inputEditTextWorked.isGone=true
            binding.inputDateLayout.endIconMode=TextInputLayout.END_ICON_NONE
            val params = binding.mainFragmentRecyclerView.layoutParams as ConstraintLayout.LayoutParams
            params.topToBottom=binding.inputLayout.id

        } else {
            //первый экран
            binding.inputDateLayout.isGone=false
            binding.inputLayout.isGone=true
            binding.bottomBarMain.isGone=false
            binding.inputEditTextWorked.isGone=false
        }
    }
    private fun goToSaveFragment(
        manager: FragmentManager?,

        ) {
        manager?.beginTransaction()?.replace(R.id.container, SaveFragment.newInstance())
            ?.addToBackStack("")?.commitAllowingStateLoss()
    }





    companion object {
        fun newInstance()= MainFragment()
        }
 }




