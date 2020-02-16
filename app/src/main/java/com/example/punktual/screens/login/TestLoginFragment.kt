package com.example.punktual.screens.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.punktual.R
import com.example.punktual.helpers.Store
import kotlinx.android.synthetic.main.fragment_test_login.*
import kotlinx.android.synthetic.main.fragment_test_login.view.*


/**
 * A simple [Fragment] subclass.
 */
class TestLoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: View = inflater.inflate(R.layout.fragment_test_login, container, false)

        binding.getValueButton.setOnClickListener{ onGetValues() }

        return binding.rootView
    }

    private fun onGetValues() {
        Log.i("carl", "onGetValue")
        username.setText(Store.getString("username"))
        token.setText(Store.getString("push_token"))
    }
}
