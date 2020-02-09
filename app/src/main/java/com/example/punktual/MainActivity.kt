package com.example.punktual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.w( task.exception, "getInstanceId failed")
                    return@OnCompleteListener
                }

                task.result?.let {token ->
                    // Log and toast
                    val msg = token.token
                    Timber.d("Firebase Token: $msg")
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
