package com.android.myanimelist.ui.anime

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.myanimelist.R
import com.android.myanimelist.databinding.FragmentAnimeBinding


class AnimeFragment : Fragment() {

    private var binding: FragmentAnimeBinding? = null
    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime, container, false)
        init()
        return binding?.root!!
    }

    private fun init() {
        viewModel.getAnime(arguments?.getInt("malId")!!)
        binding!!.animeDescription.movementMethod = ScrollingMovementMethod();
        observeAnimeList()
    }


    private fun observeAnimeList() {
        viewModel._fetchedfetchedAnime.observe(viewLifecycleOwner, {
            i("logging", it.toString())
            binding!!.animeLoad.visibility = View.GONE
            binding!!.anime = it
        })
    }
}