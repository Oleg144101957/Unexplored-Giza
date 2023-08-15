package mycity.airpor.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.provider.Settings
import mycity.airpor.App

class ServiceDeveloper : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val status = Settings.Global.getString(contentResolver, Settings.Global.ADB_ENABLED)

        val intentDeveloper = Intent(App.SERVICEDEV)
        intentDeveloper.putExtra(App.STATUS, status)
        sendBroadcast(intentDeveloper)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}