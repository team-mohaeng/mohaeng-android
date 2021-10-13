package org.journey.android.pushalarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.journey.android.R
import org.journey.android.frame.MainActivity

//class JourneyMessagingService : FirebaseMessagingService() {
//    override fun onNewToken(token: String) {
//        Log.d(TAG, "new Token: $token")
//        sendRegistrationToServer(token)
//    }
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        if (remoteMessage != null) {
//            Log.d(TAG, "From: " + remoteMessage.data)
//            Log.i("notice 바디: ", remoteMessage.notification?.title.toString())
//            Log.i("notice 타이틀: ", remoteMessage.notification?.body.toString())
//            sendMainNotification(remoteMessage)
//            sendNotification(remoteMessage)
//        } else {
//            Log.i("notice 수신에러: ", "data가 비어있습니다. 메시지를 수신하지 못했습니다.")
//            Log.i("notice data 값: ", remoteMessage.data.toString())
//        }
//    }
//
//    private fun sendMainNotification(remoteMessage: RemoteMessage) {
//        val uniId = remoteMessage.sentTime.toInt()
//
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("isOpenFromPushAlarm", true)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(
//            this, uniId, intent, PendingIntent.FLAG_ONE_SHOT
//        )
//
//        val channelId = "Notification Message"
//
//        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//        val notificationBuilder =
//            NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.drawable.mohaeng_char)
//                .setContentTitle(remoteMessage.notification?.title.toString())
//                .setContentText(remoteMessage.notification?.body.toString())
//                .setAutoCancel(true)
//                .setSound(soundUri)
//                .setContentIntent(pendingIntent)
//
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(channel)
//        }
//        notificationManager.notify(uniId, notificationBuilder.build())
//    }
//
//
//    private fun sendNotification(remoteMessage: RemoteMessage) {
//        val uniId = remoteMessage.sentTime.toInt()
//
//        val intent = Intent(this, FirebaseMessagingService::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(
//            this, uniId, intent, PendingIntent.FLAG_ONE_SHOT
//        )
//
//        val channelId = "노티피케이션 메시지"
//
//        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//        val notificationBuilder =
//            NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(remoteMessage.notification?.title.toString())
//                .setContentText(remoteMessage.notification?.body.toString())
//                .setAutoCancel(true)
//                .setSound(soundUri)
//                .setContentIntent(pendingIntent)
//
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(channel)
//        }
//        notificationManager.notify(uniId, notificationBuilder.build())
//    }
//
//    private fun sendRegistrationToServer(token: String) {
//        Log.d(TAG, "sendRegistrationTokenToServer($token)")
//    }
//
//    companion object {
//        const val TAG = "FirebasePractice"
//    }
//
//}