package com.example.q4_hw18_1.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.q4_hw18_1.data.network.model.UserItemList
import com.example.q4_hw18_1.databinding.CardUserBinding

class UserAdapter:ListAdapter<UserItemList ,UserAdapter.UserViewHolder >(DiffUser()) {


    inner class UserViewHolder(private var binding:CardUserBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: UserItemList){
            binding.tvFirst.text = item.firstName
            binding.tvLast.text = item.lastName
            binding.tvCode.text = item.nationalCode
        }
    }

    class DiffUser:DiffUtil.ItemCallback<UserItemList>(){
        override fun areItemsTheSame(oldItem: UserItemList, newItem: UserItemList): Boolean {
           return oldItem._id === newItem._id
        }

        override fun areContentsTheSame(oldItem: UserItemList, newItem: UserItemList): Boolean {
           return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
      return UserViewHolder(CardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun addInData(id: Int): UserItemList {
        return getItem(id)
    }
}