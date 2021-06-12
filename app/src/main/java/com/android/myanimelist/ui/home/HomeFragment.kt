package com.android.myanimelist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myanimelist.MainViewModel
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRVListener
import com.android.myanimelist.databinding.FragmentHomeBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeTopEntity
import com.android.myanimelist.recyclerviewadapter.ParentRecyclerViewAdapter

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private val viewModel: HomeViewModel by viewModels()
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
        adapter = ParentRecyclerViewAdapter(TopSubtype.values().toMutableList(), child,
            object : ChildRVListener {
                override fun onClick(malId: Int) {
                    findNavController().navigate(
                        R.id.action_navigation_home_to_animeFragment,
                        bundleOf("malId" to malId)
                    )
                }
            }
        )
        binding!!.ParentRecyclerView.layoutManager =
            LinearLayoutManager(context)
        binding!!.ParentRecyclerView.adapter = adapter
    }

    private fun observe() {
        mainViewModel._fetchedAnimesByCategory.observe(viewLifecycleOwner, {
            binding!!.progressCircular.visibility = GONE
            initRecycler(it)
        })
    }
}