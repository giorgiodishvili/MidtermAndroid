package com.android.myanimelist.ui.authentification

import android.os.Bundle
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.myanimelist.R
import com.android.myanimelist.databinding.FragmentRegisterAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterAccountFragment : Fragment() {
    private lateinit var binding: FragmentRegisterAccountBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register_account, container, false)

        binding.register.setOnClickListener{
            register()
        }

        return binding.root
    }


    private fun register() {
        i("register", "inRegister")

        val email = binding.etEmail.text.trim()
        val fullName = binding.etFullName.text.trim()
        val pass = binding.etPassword.text.trim()
        val cnfPass = binding.etConfirmPassword.text.trim()
        val phone = binding.etPhoneNumber.text.trim()
        if (email.isEmpty() || fullName.isEmpty() || cnfPass.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Please Fill in all the fields", Toast.LENGTH_SHORT)
                .show()
        } else if (pass != cnfPass) {
            Toast.makeText(requireContext(), "Passwords Does not Match", Toast.LENGTH_SHORT).show()
        } else {
            i("register", "inelse")
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.toString(), pass.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser? = task.result!!.user
                        Toast.makeText(
                            requireContext(),
                            "You have registered successfully",
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