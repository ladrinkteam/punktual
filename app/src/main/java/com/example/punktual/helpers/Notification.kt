package com.example.punktual.helpers

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.punktual.MainActivity
import com.example.punktual.R

class Notification {
    companion object {
        private var id = 1

        fun buildNotification(title: String, content: String){
            val intent = Intent(Store.getContext(), MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            val pendingIntent = PendingIntent.getActivity(Store.getContext(), 0, intent, 0)

            val notification = with(NotificationCompat.Builder(Store.getContext(), "Punktual")) {
                setContentText(content)
                setSmallIcon(R.drawable.ic_draw_alarm_clock_)
                setContentIntent(pendingIntent)
                setAutoCancel(true)
                setContentTitle(title)
                build()
            }

            NotificationManagerCompat
                .from(Store.getContext())
                .notify(id++, notification)
        }

    }
}