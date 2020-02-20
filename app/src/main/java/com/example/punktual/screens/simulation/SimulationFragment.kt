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
import com.example.punktual.interfaces.PunktualService
import com.example.punktual.models.User
import com.example.punktual.models.UserToConnect
import kotlinx.android.synthetic.main.fragment_simulation.*
import kotlinx.android.synthetic.main.fragment_simulation.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class SimulationFragment : Fragment() {
    private val url = "http://d3cima.tech:8080/"

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

//    private fun onGetValues() {
//        Log.i("carl", "onGetValue")
//        username.setText(Store.getString("username"))
//        token.setText(Store.getString("push_token"))
//
//        ApiAccess.postSign("/login","azerty", "token_20200220_1914")
//
//        ApiAccess.postSign("/register","azerty", "token_20200220_1914")
//    }

    private fun onGetValues() {
        Log.i("carl", "Je passe ici")
        username.setText(Store.getString("username"))
        token.setText(Store.getString("push_token"))

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(PunktualService::class.java)

        val userToConnect: UserToConnect = UserToConnect("azerty", "mon_pushtoken")
        val userRequest = service.login(userToConnect)

        userRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                if (user != null) {
                    Log.i("carl", "${user.id}")
                    Log.i("carl", "${user.username}")
                    Log.i("carl", "${user.pushToken}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                error("KO")
            }
        })
    }
}

