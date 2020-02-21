package com.example.punktual

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.punktual.helpers.Store
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.i("carl", task.exception.toString() + "getInstanceId failed")
                    return@OnCompleteListener
                }

                task.result?.let {token ->
                    val msg = token.token
                    Log.i("carl","Firebase Token: $msg")
                    //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    Store.putString("push_token", msg)
                }
            })
    }
}
