package com.android.myanimelist.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.myanimelist.R
import com.android.myanimelist.databinding.ChildRecyclerViewItemBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeTopEntity


class ChildRecyclerViewAdapter( private val list: MutableList<AnimeTopEntity>?) :
    RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder>() {


    private lateinit var binding: ChildRecyclerViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.child_recycler_view_item, parent, false);
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind()
    }



    override fun getItemCount(): Int {
        return list?.size!!
    }


    inner class MyViewHolder(binding: ChildRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var animeTopEntity: AnimeTopEntity

        fun onBind(){
            animeTopEntity = list?.get(absoluteAdapterPosition)!!
            binding.animeTopEntity = animeTopEntity
        }
    }



}