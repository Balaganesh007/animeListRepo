package com.example.animelistrepo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animelistrepo.databinding.ItemRecyclerViewBinding
import com.example.animelistrepo.domain.DataModel

class AnimeAdapter : ListAdapter<DataModel,AnimeAdapter.AnimeViewHolder>(DataDiffCallBack()){

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        Log.v("bala3","onbindholder")
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        Log.v("bala1","oncreate")
        return AnimeViewHolder.from(parent)
    }

    class AnimeViewHolder private constructor(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: DataModel
        ) {
            Log.v("bala4",item.toString())
            binding.data = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): AnimeViewHolder {
                Log.v("bala2","view holder")
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return AnimeViewHolder(binding)
            }
        }
    }
}
class DataDiffCallBack : DiffUtil.ItemCallback<DataModel>(){

    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem.tittle == newItem.tittle
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

}