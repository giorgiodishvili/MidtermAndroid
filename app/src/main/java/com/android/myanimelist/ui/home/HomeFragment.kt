package com.android.myanimelist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myanimelist.ui.activity.MainViewModel
import com.android.myanimelist.R
import com.android.myanimelist.callback.ChildRvListener
import com.android.myanimelist.callback.ParentRvListener
import com.android.myanimelist.databinding.FragmentHomeBinding
import com.android.myanimelist.model.TopSubtype
import com.android.myanimelist.model.base.types.AnimeGeneralEntity
import com.android.myanimelist.recyclerviewadapter.ParentRecyclerViewAdapter
import java.util.*

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

    private fun initRecycler(child: MutableMap<Int, MutableList<AnimeGeneralEntity>>) {
        adapter = ParentRecyclerViewAdapter(TopSubtype.values().toMutableList(), child,
            object : ChildRvListener {
                override fun onClick(malId: Int) {
                    findNavController().navigate(
                        R.id.action_navigation_home_to_animeFragment,
                        bundleOf("malId" to malId)
                    )
                }

            }, object : ParentRvListener {
                override fun onSeeAllClickListener(topSubtype: TopSubtype) {
                    findNavController().navigate(
                        R.id.action_navigation_home_to_topAnimeCategoryListFragment,
                        bundleOf("topSubtype" to topSubtype.name.lowercase(Locale.getDefault()))
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
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            initRecycler(it)
        })
    }
}