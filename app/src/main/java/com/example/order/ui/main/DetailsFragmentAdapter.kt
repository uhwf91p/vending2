package com.example.order.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.order.Data.MainList

import com.example.order.databinding.DetailsItemBinding
import com.example.order.databinding.DetailsItemBinding.inflate

class DetailsFragmentAdapter:RecyclerView.Adapter<DetailsFragmentAdapter.MainViewHolder> (){
private var detailsListData:List<MainList> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setMainlist(data:List<MainList>){
        detailsListData=data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val binding=DetailsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
           return MainViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(detailsListData[position])
    }

    override fun getItemCount(): Int {
        return detailsListData.size
    }
    inner class MainViewHolder(val binding: DetailsItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(mainList: MainList){
            binding.detailsItem.text=mainList.name
        }


    }
}