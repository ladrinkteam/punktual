package com.example.punktual.screens.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.example.punktual.R
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: View = inflater.inflate(R.layout.fragment_login, container, false)

        binding.signUpButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_testLoginFragment)}

        return binding
    }


}
