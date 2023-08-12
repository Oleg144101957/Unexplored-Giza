package mycity.airpor.data

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import mycity.airpor.App
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Apps {
    suspend fun execute(
        context: Context,
        jazz: String,
        blues: Int,
        club: Char
    ): MutableMap<String, Any>? {
        val result = "$jazz and $blues and $club"
        println(result)
        return getMap(context)
    }


    suspend fun getUID(
        context: Context,
        jazz: String,
        blues: Int,
        club: Char
    ): String = suspendCoroutine { cont ->
        val result = AppsFlyerLib.getInstance().getAppsFlyerUID(context).toString()
        cont.resume(result)
    }

    suspend fun getMap(context: Context) : MutableMap<String, Any>? = suspendCoroutine{ continuation ->
        val listenner = ConvListener{
            continuation.resume(it)
        }
        AppsFlyerLib.getInstance().init(App.AF, listenner, context).start(context)
    }
}

class ConvListener(
    private val recievedData: (MutableMap<String, Any>?) -> Unit) :
    AppsFlyerConversionListener {

    override fun onConversionDataSuccess(inputData: MutableMap<String, Any>?) {
        recievedData(inputData)
    }

    override fun onConversionDataFail(inputData: String?) {
        recievedData(null)
    }

    override fun onAppOpenAttribution(inputData: MutableMap<String, String>?) {
        recievedData(null)
    }

    override fun onAttributionFailure(inputData: String?) {
        recievedData(null)
    }
}


