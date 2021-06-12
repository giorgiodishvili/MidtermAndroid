package com.android.myanimelist.recyclerviewadapter

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.callback.ParentRvListener
import com.android.myanimelist.databinding.ParentRecyclerViewItemBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeTopEntity


class ParentRecyclerViewAdapter(
    private var parents: MutableList<TopSubtype>,
    private val children: MutableMap<Int, MutableList<AnimeTopEntity>>,
    private val listener: ChildRvListener,
    val parentListener: ParentRvListener
) :
    RecyclerView.Adapter<ParentRecyclerViewAdapter.ChildRecyclerViewHolder>() {
    private lateinit var binding: ParentRecyclerViewItemBinding
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ChildRecyclerViewHolder(binding: ParentRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val recyclerView: RecyclerView = binding.ChildRV
        fun onBind() {
            setParentRecycler()

            val childLayoutManager = LinearLayoutManager(
                recyclerView.context, LinearLayoutManager.HORIZONTAL, false
            )
            childLayoutManager.initialPrefetchItemCount = 4
            recyclerView.apply {
                layoutManager = childLayoutManager
                i("children", children[absoluteAdapterPosition].toString())
                adapter = ChildRecyclerViewAdapter(children[absoluteAdapterPosition], listener)
                setRecycledViewPool(viewPool)
            }
        }

        private fun setParentRecycler() {
            var name = parents[absoluteAdapterPosition].name
            if (name == "NONE") {
                name = "TOP ANIME"
            }
            binding.setAnimeCategory(name)
            binding.seeAll.setOnClickListener {
                parentListener.onSeeAllClickListener(parents[absoluteAdapterPosition])
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