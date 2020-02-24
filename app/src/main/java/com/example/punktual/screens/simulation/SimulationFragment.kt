package com.example.punktual.screens.simulation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.punktual.R
import com.example.punktual.enums.LocationType
import com.example.punktual.helpers.Notification
import com.example.punktual.helpers.Store
import com.example.punktual.interfaces.PunktualService
import com.example.punktual.models.Position
import kotlinx.android.synthetic.main.fragment_simulation.*
import kotlinx.android.synthetic.main.fragment_simulation.view.*
import okhttp3.ResponseBody
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
        binding.campusButton.setOnClickListener{ onCampusButton() }
        binding.papeterieButton.setOnClickListener{ onPapeterieButton() }

        return binding.rootView
    }

    override fun onStart() {
        super.onStart()
        usernameEdit.setText(Store.getString("username"))
        idEdit.setText(Store.getString("id"))
        tokenEdit.setText(Store.getString("push_token"))
    }

    private fun onCampusButton() {
        latitudeEdit.setText(getString(R.string.latitude_campus))
        longitudeEdit.setText(getString(R.string.longitude_campus))

        onPositionResgister(LocationType.CAMPUS_NUMERIQUE)
    }

    private fun onPapeterieButton() {
        latitudeEdit.setText(getString(R.string.parking_latitude))
        longitudeEdit.setText(getString(R.string.parking_longitude))

        onPositionResgister(LocationType.PAPETERIE)
    }

    private fun onPositionResgister(locationType: LocationType) {
        Log.i("carl","onPositionRegister")

        val idRequest = Store.getString("id")
        val pushtokenRequest = Store.getString("push_token")
        val latitudeRequest = latitudeEdit.text.toString()
        val longitudeRequest = longitudeEdit.text.toString()

        if (idRequest != null && idRequest != "" && pushtokenRequest != null && pushtokenRequest != "" && latitudeRequest != "" && longitudeRequest != "") {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create(PunktualService::class.java)

            val position = Position(latitudeRequest.toDouble(), longitudeRequest.toDouble())
            val request = service.positionRegister(position, locationType.toString(), idRequest)

            request.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (202 == response.code()) {
                        val message = response.body()?.string().toString()
                        Notification.buildNotification("You sent a message:", message)
                        Log.i("carl", message)
//                        Toast.makeText(Store.getContext(), message, Toast.LENGTH_LONG).show()
                    }
                    else if (404 == response.code()) {
                        val message = "(${response.code().toString()}) " + getString(R.string.code_404_position_register)
                        Log.e("carl", message)
//                        Toast.makeText(Store.getContext(), message, Toast.LENGTH_SHORT).show()
                    }
                    else if (400 == response.code()) {
                        val message = "(${response.code().toString()}) " + getString(R.string.code_400)
                        Log.e("carl", message)
//                        Toast.makeText(Store.getContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    error("KO")
                }
            })
        }
        else {
            if (latitudeRequest ==  "") {
                val message = getString(R.string.error_latitude)
                Log.w("carl", message)
                Toast.makeText(Store.getContext(), message, Toast.LENGTH_SHORT).show()
            }
            if (longitudeRequest == "") {
                val message = getString(R.string.error_longitude)
                Log.w("carl", message)
                Toast.makeText(Store.getContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

