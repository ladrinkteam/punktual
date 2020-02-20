package com.example.punktual.screens.simulation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.punktual.R
import com.example.punktual.helpers.ApiAccess
import com.example.punktual.helpers.Store
import kotlinx.android.synthetic.main.fragment_simulation.*
import kotlinx.android.synthetic.main.fragment_simulation.view.*

/**
 * A simple [Fragment] subclass.
 */
class SimulationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: View = inflater.inflate(R.layout.fragment_simulation, container, false)
        Log.i("carl", "SimulationFragment")
        binding.getValueButton.setOnClickListener{ onGetValues() }

        return binding.rootView
    }

    private fun onGetValues() {
        Log.i("carl", "onGetValue")
        username.setText(Store.getString("username"))
        token.setText(Store.getString("push_token"))

        ApiAccess.postSign("/login","azerty", "token_20200220_1914")

        ApiAccess.postSign("/register","azerty", "token_20200220_1914")
    }
}

