package com.android.myanimelist.ui.authentification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.myanimelist.R
import com.android.myanimelist.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginBtn.setOnClickListener{
            login()
        }
        binding.signupBtn.setOnClickListener{
            register()
        }
        return binding.root
    }


    fun register() {
        findNavController().navigate(R.id.action_loginFragment_to_registerAccountFragment)
    }

    fun login() {
        val email = binding.emailEdtText.text.trim()
        val pass = binding.passEdtText.text.trim()
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(requireContext(), "Please Fill in all the fields", Toast.LENGTH_SHORT)
                .show()
        } else {
            Log.i("register", "inelse")
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.toString(), pass.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser? = task.result!!.user
                        Toast.makeText(
                            requireContext(),
                            "Signed In sucesffully",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }
    }
}