package com.android.myanimelist.recyclerviewadapter

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.databinding.ChildRecyclerViewItemBinding
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import com.android.myanimelist.ui.activity.MainViewModel


class ChildRecyclerViewAdapter(
    private val list: MutableList<AnimeGeneralEntity>?,
    val listener: ChildRvListener
) :
    RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder>() {
    private lateinit var mainViewModel: MainViewModel


    private lateinit var binding: ChildRecyclerViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.child_recycler_view_item,
                parent,
                false
            );
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind()
    }


    override fun getItemCount(): Int {
        return list?.size!!
    }


    inner class MyViewHolder(binding: ChildRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var animeGeneralEntity: AnimeGeneralEntity

        fun onBind() {
            animeGeneralEntity = list?.get(absoluteAdapterPosition)!!
            binding.animeTopEntity = animeGeneralEntity
            i("onClikkk", "bla")

            binding.root.setOnClickListener {
                i("onClikkk", "onBindinit()")
                listener.onClick(animeGeneralEntity.malId)
            }
        }
    }


}