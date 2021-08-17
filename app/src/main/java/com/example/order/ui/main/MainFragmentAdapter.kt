package com.example.order.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.order.Data.MainList
import com.example.order.databinding.MainItemBinding

class MainFragmentAdapter:RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var mainListData:List<MainList> = listOf()
    private var onItemClickListener: MainFragment.OnItemClickListener? =null
    fun setOnItemClickListener (onItemClickListener: MainFragment.OnItemClickListener){
        this.onItemClickListener=onItemClickListener
    }
    fun setMainList(data:List<MainList>){
        mainListData=data
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
       val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mainListData[position])
    }

    override fun getItemCount()=mainListData.size
   inner class MainViewHolder(private val binding: MainItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(mainList: MainList){
            binding.apply {
                mainItemRecyclerTextView.text=mainList.name
                onItemClickListener?.onItemClick(mainList)
            }

        }


    }
}