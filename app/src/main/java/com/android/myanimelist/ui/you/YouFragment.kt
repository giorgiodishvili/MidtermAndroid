package com.android.myanimelist.ui.you

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.myanimelist.R
import com.android.myanimelist.databinding.FragmentYouBinding

class YouFragment : Fragment() {

    private val youViewModel: YouViewModel by viewModels()
    private lateinit var binding: FragmentYouBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_you, container, false)



        return binding.root
    }
}