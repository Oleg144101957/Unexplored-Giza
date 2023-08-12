package mycity.airpor.ui.theme

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import io.paperdb.Paper
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mycity.airpor.MyCustomViewClass


@Composable
fun SevenScreen(navHostController: NavHostController){

    var fPCallback: ValueCallback<Array<Uri>>? by remember {
        mutableStateOf(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) {
        fPCallback!!.onReceiveValue(it.toTypedArray())
    }

    var webView: WebView? = null

    AndroidView(factory = {context ->
        MyCustomViewClass(context, fPCallback, launcher).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )

            val listOfLetters = listOf("w", "v")
            val elementToReplace = listOfLetters[0]+listOfLetters[1]
            val elementInstead = ""

            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.userAgentString = settings.userAgentString.replace(elementToReplace, elementInstead)

            webChromeClient = this.wcc()
            webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d("123123", "The url in onPageFinished is: $url")
                }
            }

            val scope = MainScope()
            scope.launch {
                val link = Paper.book().read<String>("link")
                loadUrl(link!!)
            }
        }
    })

}