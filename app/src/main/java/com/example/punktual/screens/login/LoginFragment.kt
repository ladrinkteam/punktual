package com.example.punktual.screens.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.punktual.R
import com.example.punktual.helpers.Store
import com.example.punktual.interfaces.PunktualService
import com.example.punktual.models.User
import com.example.punktual.models.UserToConnect
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.usernameEdit
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_simulation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private val url = "http://d3cima.tech:8080"

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
        Store.putString("username", usernameEdit.text.toString())

        val usernameRequest = Store.getString("username")
        val pushtokenRequest = Store.getString("push_token")

        if (usernameRequest != null && usernameRequest != "" && pushtokenRequest != null && pushtokenRequest != "") {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create(PunktualService::class.java)

            val userToConnect: UserToConnect = UserToConnect(usernameRequest, pushtokenRequest)
            val userRequest = service.login(userToConnect)

            userRequest.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (200 == response.code()) {
                        val user = response.body()
                        if (user != null) {
                            Store.putString("id", user.id)
                            Log.i("carl", getString(R.string.code_200_login))
                            Toast.makeText(Store.getContext(), getString(R.string.code_200_login), Toast.LENGTH_LONG).show()
                            goToTestLoginFragment(view)
                        }
                    }
                    else if (404 == response.code()) {
                        Log.e("carl", "(${response.code().toString()}) " + getString(R.string.code_404_login))
                        Toast.makeText(Store.getContext(), getString(R.string.code_404_login), Toast.LENGTH_SHORT).show()
                    }
                    else if (400 == response.code()) {
                        Log.e("carl", "(${response.code().toString()}) " + getString(R.string.code_400))
                        Toast.makeText(Store.getContext(), getString(R.string.code_400), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    error("KO")
                }
            })
        }
        else {
            if (usernameRequest == null || usernameRequest == "") {
                Log.w("carl", getString(R.string.error_username))
                Toast.makeText(Store.getContext(), getString(R.string.error_username), Toast.LENGTH_SHORT).show()
            }
            if (pushtokenRequest == null || pushtokenRequest == "") {
                Log.w("carl", getString(R.string.error_pushtoken))
                Toast.makeText(Store.getContext(), getString(R.string.error_pushtoken) + "\nPlease restart Punktual", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onSignUp(view: View) {
        Log.i("carl","onSignUp")
        Store.putString("username", usernameEdit.text.toString())

        val usernameRequest = Store.getString("username")
        val pushtokenRequest = Store.getString("push_token")

        if (usernameRequest != null && usernameRequest != "" && pushtokenRequest != null && pushtokenRequest != "") {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create(PunktualService::class.java)

            val userToConnect: UserToConnect = UserToConnect(usernameRequest, pushtokenRequest)
            val userRequest = service.register(userToConnect)

            userRequest.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (200 == response.code()) {
                        val user = response.body()
                        if (user != null) {
                            Store.putString("id", user.id)
                            Log.i("carl", getString(R.string.code_200_register))
                            Toast.makeText(Store.getContext(), getString(R.string.code_200_register), Toast.LENGTH_LONG).show()
                            goToTestLoginFragment(view)
                        }
                    }
                    else if (409 == response.code()) {
                        Log.e("carl", "(${response.code().toString()}) " + getString(R.string.code_409_register))
                        Toast.makeText(Store.getContext(), getString(R.string.code_409_register), Toast.LENGTH_SHORT).show()
                    }
                    else if (400 == response.code()) {
                        Log.e("carl", "(${response.code().toString()}) " + getString(R.string.code_400))
                        Toast.makeText(Store.getContext(), getString(R.string.code_400), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    error("KO")
                }
            })
        }
        else {
            if (usernameRequest == null || usernameRequest == "") {
                Log.w("carl", getString(R.string.error_username))
                Toast.makeText(Store.getContext(), getString(R.string.error_username), Toast.LENGTH_SHORT).show()
            }
            if (pushtokenRequest == null || pushtokenRequest == "") {
                Log.w("carl", getString(R.string.error_pushtoken))
                Toast.makeText(Store.getContext(), getString(R.string.error_pushtoken) + "\nPlease restart Punktual", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToTestLoginFragment(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_simulationFragment)
    }
}
