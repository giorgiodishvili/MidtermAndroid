package com.android.myanimelist.recyclerviewadapter

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myanimelist.R
import com.android.myanimelist.databinding.ParentRecyclerViewItemBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeTopEntity


class ParentRecyclerViewAdapter(
    private var parents: MutableList<TopSubtype>,
    private val children: MutableMap<Int, MutableList<AnimeTopEntity>>
) :
    RecyclerView.Adapter<ParentRecyclerViewAdapter.ChildRecyclerViewHolder>() {
    private lateinit var binding: ParentRecyclerViewItemBinding
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ChildRecyclerViewHolder(binding: ParentRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val recyclerView: RecyclerView = binding.ChildRV
        fun onBind() {
            var name = parents[absoluteAdapterPosition].name
            if (name == "NONE") {
                name = "TOP ANIME"
            }
            binding.setAnimeCategory(name)
            val childLayoutManager = LinearLayoutManager(
                recyclerView.context, LinearLayoutManager.HORIZONTAL, false
            )
            childLayoutManager.initialPrefetchItemCount = 4

            recyclerView.apply {
                layoutManager = childLayoutManager
                //TODO set data to childRecyclerView
                i("children", children[absoluteAdapterPosition].toString())
                adapter = ChildRecyclerViewAdapter(children[absoluteAdapterPosition])
                setRecycledViewPool(viewPool)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRecyclerViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.parent_recycler_view_item, parent, false
        )
        return ChildRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ChildRecyclerViewHolder, position: Int) {
        holder.onBind()
    }

}