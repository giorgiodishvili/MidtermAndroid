package com.android.myanimelist.ui.categorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.databinding.TopAnimeCategoryListFragmentBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.pagination.LoaderStateAdapter
import com.android.myanimelist.recyclerviewadapter.GeneralRecyclerViewAdapter
import kotlinx.coroutines.launch
import java.util.*

class TopAnimeCategoryListFragment : Fragment() {

    private val viewModel: TopAnimeCategoryListViewModel by viewModels()
    private lateinit var binding: TopAnimeCategoryListFragmentBinding
    private lateinit var adapter: GeneralRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.top_anime_category_list_fragment,
            container,
            false
        )
        init()
        return binding.root
    }

    private fun init() {
        binding.progrssBar.visibility = View.VISIBLE
        initRecycler()
        var category = arguments?.getString("topSubtype")!!
        if (category == TopSubtype.NONE.name.lowercase(Locale.getDefault())) {
            category = ""
        }
        viewModel.getTopAnime(category).observe(viewLifecycleOwner, {
            binding.progrssBar.visibility = View.GONE
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private fun initRecycler() {
        adapter = GeneralRecyclerViewAdapter(
            object : ChildRvListener {
                override fun onClick(malId: Int) {
                    findNavController().navigate(
                        R.id.action_topAnimeCategoryListFragment_to_animeFragment,
                        bundleOf("malId" to malId)
                    )
                }
            }
        )
        binding.searchRv.layoutManager = GridLayoutManager(context, 2)
        binding.searchRv.adapter = adapter.withLoadStateFooter(LoaderStateAdapter())
    }

}