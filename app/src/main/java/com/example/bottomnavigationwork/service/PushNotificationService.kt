package com.example.bottomnavigationwork.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.bottomnavigationwork.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Heads Up Notification",
            NotificationManager.IMPORTANCE_HIGH
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, CHANNEL_ID)
        notification.setSmallIcon(R.mipmap.ic_launcher)
            .setContentText(remoteMessage.notification?.body)
            .setContentTitle(remoteMessage.notification?.title)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    companion object {
        val CHANNEL_ID = "channel_iddd"
    }
}