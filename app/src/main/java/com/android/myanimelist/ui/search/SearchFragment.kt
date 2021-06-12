package com.android.myanimelist.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.databinding.FragmentSearchBinding
import com.android.myanimelist.pagination.LoaderStateAdapter
import com.android.myanimelist.recyclerviewadapter.GeneralRecyclerViewAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: GeneralRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.btnSearch.setOnClickListener {
            binding.loadSearch.visibility = VISIBLE
            val searchWord = checkInput()
            initRecycler()
            if (searchWord == null) {
                binding.loadSearch.visibility = GONE
                return@setOnClickListener
            }
            fetchAnime(searchWord)
        }
    }

    private fun initRecycler() {
        adapter = GeneralRecyclerViewAdapter(
            object : ChildRvListener {
                override fun onClick(malId: Int) {
                    findNavController().navigate(
                        R.id.action_navigation_search_to_animeFragment,
                        bundleOf("malId" to malId)
                    )
                }
            }
        )
        binding.searchRv.layoutManager = GridLayoutManager(context, 3)
        binding.searchRv.adapter = adapter.withLoadStateFooter(LoaderStateAdapter())
    }

    private fun fetchAnime(searchWord: String) {
        searchViewModel.searchAnime(searchWord).observe(viewLifecycleOwner, {
            binding.loadSearch.visibility = GONE
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private fun checkInput(): String? {
        val searchWord = binding.etSearch.text.toString().trim()
        if (searchWord.isBlank()) {
            Toast.makeText(requireContext(), "Please input search word", Toast.LENGTH_SHORT).show()
            return null
        }
        return searchWord
    }

}
