package com.res.jobjob.common.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.res.jobjob.InformacionActivity
import com.res.jobjob.R
import com.res.jobjob.common.chanel.NotificationHelper

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessaging : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val data: Map<String, String> = p0.data
        val title: String? = data["title"]
        val body: String? = data["body"]
        val key: String? = data["key_1"]
        if (title != null && body != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) show(title, body)
            else showOld(title, body)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun show(vararg text: String) {
        val intent: PendingIntent = PendingIntent.getActivities(baseContext, 0, arrayOf(Intent()), PendingIntent.FLAG_ONE_SHOT)
        val notification = NotificationHelper(baseContext)
        val builder = notification.getNotification(text[0], text[1], intent, notificationAction())
        notification.notificationManager.notify(1, builder.build())
    }

    private fun showOld(vararg text: String) {
        val intent: PendingIntent = PendingIntent.getActivities(baseContext, 0, arrayOf(Intent()), PendingIntent.FLAG_ONE_SHOT)
        val sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationHelper(baseContext)
        val builder = notification.getNotificationOldApi(text[0], text[1], intent, sound, notificationActionOl())
        notification.notificationManager.notify(1, builder.build())
    }

    private fun notificationAction(): Notification.Action {
        val informacion = Intent(this, InformacionActivity::class.java)
        val intent: PendingIntent = PendingIntent.getActivities(baseContext, 0, arrayOf(informacion), PendingIntent.FLAG_UPDATE_CURRENT)
        return Notification.Action.Builder(R.mipmap.ic_launcher, "Ver", intent).build()
    }

    private fun notificationActionOl(): NotificationCompat.Action {
        val informacion = Intent(this, InformacionActivity::class.java)
        val intent: PendingIntent = PendingIntent.getActivities(baseContext, 0, arrayOf(informacion), PendingIntent.FLAG_UPDATE_CURRENT)
        return NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Ver", intent).build()
    }
}