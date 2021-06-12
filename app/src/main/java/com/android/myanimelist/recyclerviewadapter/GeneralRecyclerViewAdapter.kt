package com.android.myanimelist.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.databinding.SearchRecyclerViewItemBinding
import com.android.myanimelist.model.base.types.AnimeGeneralEntity

class GeneralRecyclerViewAdapter(
    private val listener: ChildRvListener
) :
    PagingDataAdapter<AnimeGeneralEntity, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    private lateinit var binding: SearchRecyclerViewItemBinding

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<AnimeGeneralEntity>() {
            override fun areItemsTheSame(
                oldItem: AnimeGeneralEntity,
                newItem: AnimeGeneralEntity
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AnimeGeneralEntity,
                newItem: AnimeGeneralEntity
            ): Boolean =
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
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MyViewHolder)?.onBind()
    }

    inner class MyViewHolder(private val binding: SearchRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var animeSearchSubEntity: AnimeGeneralEntity

        fun onBind() {

            animeSearchSubEntity = getItem(absoluteAdapterPosition)!!
            binding.animeSearchSubEntity = animeSearchSubEntity
            binding.root.setOnClickListener {
                listener.onClick(animeSearchSubEntity.malId)
            }
        }
    }
}