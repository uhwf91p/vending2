package com.example.order.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.order.Data.MainList
import com.example.order.Repository.Keys
import com.example.order.databinding.MainItemBinding

class MainFragmentAdapter:RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var mainListData: List<MainList> = listOf()
    private var onItemViewClickListener: MainFragment.OnItemViewClickListener? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setMainList(data: List<MainList>) {
        mainListData = data
        notifyDataSetChanged()

    }

    fun setOnItemViewClickListener(onItemViewClickListener: MainFragment.OnItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun removeOnItemViewClickListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mainListData[position])
    }

    override fun getItemCount() = mainListData.size
    inner class MainViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(mainList: MainList) {
            var textForItem:String=""
            textForItem = if (Keys.LIST_KEY == 0) {
                mainList.name+mainList.value
            } else{
                mainList.name
            }

            binding.apply {

                mainItemRecyclerTextView.text = textForItem

                binding.root.setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(mainList)
                }
            }

        }


    }
}