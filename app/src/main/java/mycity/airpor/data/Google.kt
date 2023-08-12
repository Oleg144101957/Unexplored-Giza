package mycity.airpor.data

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Google() {

    val one: String = "one"
    val two: String = "two"
    val three: String = "three"

    suspend fun execute(context: Context): String{
        return getGoogle(context)
    }

    private suspend fun getGoogle(context: Context) : String = withContext(Dispatchers.IO){
        AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
    }
}