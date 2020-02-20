package com.example.punktual.screens.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.punktual.R
import com.example.punktual.helpers.ApiAccess
import com.example.punktual.helpers.Store
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import okhttp3.OkHttpClient

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

        binding.signInButton.setOnClickListener{ view: View -> onSignIn(view) }
        binding.signUpButton.setOnClickListener{ view: View -> onSignUp(view) }

        return binding.rootView
    }

    private fun onSignIn(view: View) {
        Log.i("carl","onSignIn")
        saveUsername()
        goToTestLoginFragment(view)
    }

    private fun onSignUp(view: View) {
        Log.i("carl","onSignUp")
        saveUsername()
        goToTestLoginFragment(view)
    }

    private fun saveUsername() {
        Store.putString("username", username.text.toString())
    }

    private fun goToTestLoginFragment(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_simulationFragment)
    }

}
