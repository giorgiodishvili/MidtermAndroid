package com.android.myanimelist.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myanimelist.MainViewModel
import com.android.myanimelist.R
import com.android.myanimelist.databinding.FragmentHomeBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeTopEntity
import com.android.myanimelist.recyclerviewadapter.ParentRecyclerViewAdapter

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: ParentRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        observe()

    }

    private fun initRecycler(child: MutableMap<Int, MutableList<AnimeTopEntity>>) {
        adapter = ParentRecyclerViewAdapter(TopSubtype.values().toMutableList(), child)
        binding!!.ParentRecyclerView.layoutManager =
            LinearLayoutManager(context)
        binding!!.ParentRecyclerView.adapter = adapter
    }

    private fun observe() {
        mainViewModel._fetchedFields.observe(viewLifecycleOwner, {
            Log.i("children", it.toString())
            initRecycler(it)
        })
    }


}