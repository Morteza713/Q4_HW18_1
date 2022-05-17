package com.example.q4_hw18_1.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.datasource.model.UserAndHobie
import com.example.q4_hw18_1.databinding.CardSaveUserBinding


class SaveAdapter:ListAdapter<UserAndHobie,SaveAdapter.SaveViewHolder>(DiffUtils()) {

    inner class SaveViewHolder(private var binding: CardSaveUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(userHobie: UserAndHobie) {
            binding.tvFirst.text = userHobie.user.firstName
            binding.tvLast.text = userHobie.user.lastName
            binding.tvCode.text = userHobie.user.nationalCode
            binding.tvHobie.text = userHobie.hobie.map { it.name }.toString()
        }
    }
    class DiffUtils : DiffUtil.ItemCallback<UserAndHobie>() {
        override fun areItemsTheSame(oldItem: UserAndHobie, newItem: UserAndHobie) = oldItem.user.id == newItem.user.id
        override fun areContentsTheSame(oldItem: UserAndHobie, newItem: UserAndHobie) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        return SaveViewHolder(CardSaveUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun itemsSwipe(id: Int): User {
        return getItem(id).user
    }
}