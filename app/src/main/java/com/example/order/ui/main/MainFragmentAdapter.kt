package com.example.order.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.ItemOfList
import com.example.order.databinding.MainItemBinding

class MainFragmentAdapter:RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var itemOfListData: List<ItemOfList> = listOf()
    private var onItemViewClickListener: MainFragment.OnItemViewClickListener? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setMainList(data: List<ItemOfList>) {
        itemOfListData = data
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
        holder.bind(itemOfListData[position])
    }

    override fun getItemCount() = itemOfListData.size
    inner class MainViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(itemOfList: ItemOfList) {
            val textForItem:String = if (GlobalConstAndVars.LIST_KEY == "0") {

                itemOfList.name+": "+itemOfList.value
            } else{
                itemOfList.name
            }


            binding.apply {

                mainItemRecyclerTextView.text = textForItem

                binding.mainItemRecyclerTextView.setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(itemOfList)
                }
            }

        }


    }
}