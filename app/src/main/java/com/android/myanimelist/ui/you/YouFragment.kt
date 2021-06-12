package com.android.myanimelist.ui.you

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.myanimelist.R
import com.android.myanimelist.databinding.FragmentYouBinding
import com.google.firebase.auth.FirebaseAuth

class YouFragment : Fragment() {

    private val youViewModel: YouViewModel by viewModels()
    private lateinit var binding: FragmentYouBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_you, container, false)
        init()
        getData()

        return binding.root
    }

    private fun init(){
        binding.setOnLoginClicked {
            handleClick()
        }
        binding.setOnRegisterClicked {
            register()
        }
        binding.setOnLogOutClick {
            logout()
        }
    }

    private fun handleClick() {
        findNavController().navigate(R.id.action_navigation_you_to_loginFragment)
    }

    private fun register() {
        findNavController().navigate(R.id.action_navigation_you_to_registerAccountFragment)
    }

    fun logout() {

        FirebaseAuth.getInstance().signOut()
        binding.email.text = ""
        binding.tvUid.text = ""
        binding.btnLogin.visibility = VISIBLE
        binding.btnRegister.visibility = VISIBLE
        binding.btnLogout.visibility = GONE

    }


    private fun getData() {
        val email = FirebaseAuth.getInstance().currentUser?.email
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (!email.isNullOrBlank()) {
            binding.email.text = email
            binding.tvUid.text = userId
            binding.btnLogin.visibility = GONE
            binding.btnRegister.visibility = GONE
            binding.btnLogout.visibility = VISIBLE
        }
    }


}