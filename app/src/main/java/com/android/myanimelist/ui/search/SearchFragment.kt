package com.android.myanimelist.ui.search

import android.app.Dialog
import android.os.Bundle
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.databinding.FragmentSearchBinding
import com.android.myanimelist.databinding.NoItemsFoundBinding
import com.android.myanimelist.extension.setUp
import com.android.myanimelist.pagination.LoaderStateAdapter
import com.android.myanimelist.recyclerviewadapter.GeneralRecyclerViewAdapter
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
        initRecycler()
        binding.btnSearch.setOnClickListener {
            binding.show = true
            val searchWord = checkInput()
            i("binding", binding.show.toString())
            if (searchWord == null) {
                binding.show = false
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
        adapter.addLoadStateListener {
            binding.show = it.refresh is LoadState.Loading
            if (it.refresh is LoadState.Error) {
                val error = (it.refresh as LoadState.Error).error
                if (error is NullPointerException) {
                    showError("No Anime With Such Name")
                }
                if (error is HttpException) {
                    showError(error.toString())
                }
            }
        }
    }

    private fun fetchAnime(searchWord: String) {
        searchViewModel.searchAnime(searchWord).observe(viewLifecycleOwner, {
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

    private fun showError(errorMessage: String) {
        val dialog = Dialog(requireContext())
        val dialogBinding = NoItemsFoundBinding.inflate(LayoutInflater.from(requireContext()))
        dialogBinding.error.text = errorMessage
        dialog.setUp(
            dialogBinding,
            R.color.design_default_color_error,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.closeButton.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()
    }

}
