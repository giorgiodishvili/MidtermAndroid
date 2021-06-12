package com.android.myanimelist.recyclerviewadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRVListener
import com.android.myanimelist.databinding.SearchRecyclerViewItemBinding
import com.android.myanimelist.model.base.types.AnimeSearchSubEntity

class SearchRecyclerViewAdapter(
    private val listener: ChildRVListener
) :
    PagingDataAdapter<AnimeSearchSubEntity, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    private lateinit var binding: SearchRecyclerViewItemBinding

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<AnimeSearchSubEntity>() {
            override fun areItemsTheSame(oldItem: AnimeSearchSubEntity, newItem: AnimeSearchSubEntity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: AnimeSearchSubEntity, newItem: AnimeSearchSubEntity): Boolean =
                oldItem.malId == newItem.malId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_recycler_view_item,
                parent,
                false
            );
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MyViewHolder)?.onBind()
    }

    inner class MyViewHolder(private val binding: SearchRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var animeSearchSubEntity: AnimeSearchSubEntity

        fun onBind() {
            animeSearchSubEntity = getItem(absoluteAdapterPosition)!!
            Log.i("onClikkk", "onBindSearch")
            binding.animeSearchSubEntity = animeSearchSubEntity
            binding.root.setOnClickListener {
                Log.i("onClikkk", "onBindinit()")
                listener.onClick(animeSearchSubEntity.malId)
            }
        }
    }
}