package com.res.jobjob.common.chanel

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.res.jobjob.R

class NotificationHelper(base: Context?) : ContextWrapper(base) {

    val notificationManager: NotificationManager
        get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val CHANEL_ID = "com.res.jobjob"
        const val CHANEL_NAME = "Jobjob"
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createChanel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChanel() {
        val notificationManager = NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
            enableLights(true)
            enableVibration(true)
            lightColor = Color.GRAY
            lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        }
        this.notificationManager.createNotificationChannel(notificationManager)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotification(title: String, body: String, intent: PendingIntent, action: Notification.Action): Notification.Builder {
        return Notification.Builder(applicationContext, CHANEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(intent)
                .addAction(action)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(Notification.BigTextStyle().bigText(body).setBigContentTitle(title))
    }

    fun getNotificationOldApi(title: String, body: String, intent: PendingIntent, soundUri: Uri, action: NotificationCompat.Action): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, CHANEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(intent)
                .setSound(soundUri)
                .addAction(action)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body).setBigContentTitle(title))
    }
}