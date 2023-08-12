package mycity.airpor

import android.content.Context
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.compose.ManagedActivityResultLauncher

class MyCustomViewClass(
    private val context: Context,
    var fPCallback: ValueCallback<Array<Uri>>?,
    val launcher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>
) : WebView(context){


    fun wcc(): WebChromeClient{
        return object : WebChromeClient(){
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                fPCallback = filePathCallback
                launcher.launch("image/*")
                return true
            }
        }
    }

}