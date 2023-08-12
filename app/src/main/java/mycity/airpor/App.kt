package mycity.airpor

import android.app.Application
import com.onesignal.OneSignal

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initSignal()
    }

    private fun initSignal(){
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONE_SIGNAL)
    }

    companion object{
        const val AF = "sW4m9WB9xdBmnefzPRxMCo"
        const val ONE_SIGNAL = "c328c55a-26da-41cc-8c41-3743093010e2"
        const val FB_ID = "1089529798687794"
        const val FB_SECRET_TOKEN = "4be6f6cff415f6f6f29a92cfeeeb662d"
    }
}