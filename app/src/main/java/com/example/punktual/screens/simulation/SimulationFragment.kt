package com.example.punktual.screens.simulation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.punktual.R
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
        binding.getUserButton.setOnClickListener{ onGetUser() }

        return binding.rootView
    }

    private fun onGetUser() {

        val usernameRequest = Store.getString("username")
        val pushtokenRequest = Store.getString("push_token")

        if (usernameRequest != null && pushtokenRequest != null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create(PunktualService::class.java)

            val userToConnect: UserToConnect = UserToConnect(usernameRequest, pushtokenRequest)
            val userRequest = service.login(userToConnect)

            userRequest.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    if (user != null) {
                        usernameEdit.setText(user.username)
                        idEdit.setText(user.id)
                        tokenEdit.setText(user.pushToken)
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
}

