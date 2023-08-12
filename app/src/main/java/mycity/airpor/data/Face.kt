package mycity.airpor.data

import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import mycity.airpor.App
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Face() {
    suspend fun execute(context: Context, trash: String, trash2: Long): String{
        FacebookSdk.setApplicationId(App.FB_ID)
        FacebookSdk.setClientToken(App.FB_SECRET_TOKEN)
        FacebookSdk.sdkInitialize(context)
        val result = "$trash + $trash2"
        result.length
        return provideFace(context)
    }

    private suspend fun provideFace(context: Context): String = suspendCoroutine{ cont ->

        val music = collectData()
        music.replace("u", "i")

        AppLinkData.fetchDeferredAppLinkData(context){
            cont.resume(it?.targetUri.toString())
        }
    }

    private fun collectData() : String{
        return "Blues"
    }
}