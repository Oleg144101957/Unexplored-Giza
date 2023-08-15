package mycity.airpor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableSharedFlow
import io.paperdb.Paper
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mycity.airpor.data.DataChecker
import mycity.airpor.service.ServiceDeveloper
import mycity.airpor.ui.theme.NavigationFile
import mycity.airpor.ui.theme.UnexploredGizaTheme

class MainActivity : ComponentActivity() {

    private lateinit var broadDev: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sp = getSharedPreferences(App.FB_ID, Context.MODE_PRIVATE)
        val currentStatus = sp.getString(App.ONE_SIGNAL, "once_upon_a_time") ?: "once_upon_a_time"
        val dataChecker = DataChecker(this)

        Log.d("123123", "onCreate")


        broadDev = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val devBrodcastResponce = intent?.getStringExtra(App.STATUS) ?: "null"


                Log.d("123123", "devBrodcastResponce is $devBrodcastResponce")



                if (devBrodcastResponce == "0"){
                    lifecycleScope.launch {
                        Paper.init(applicationContext)

                        if (currentStatus == "once_upon_a_time"){
                            dataChecker.initialProcess()
                        } else if(currentStatus == App.TOXIC){
                            transmitter.emit(App.TOXIC)
                        } else {
                            transmitter.emit(App.FRIEND)
                        }
                    }
                } else {
                    lifecycleScope.launch {
                        transmitter.emit(App.TOXIC)
                    }
                }

            }
        }
        startDevServiceAndRegisterReciever(300, 22, 14)


        setContent {
            UnexploredGizaTheme {
                // A surface container using the 'background' color from the theme
                NavigationFile(){
                    handleBackPress(it)
                }
            }
        }
    }


    private fun startDevServiceAndRegisterReciever(a: Int, b:Int, c:Int){
        val intent = Intent(this, ServiceDeveloper::class.java)
        startService(intent)
        val intentDevBroadcast = IntentFilter(App.BROADCASTDEV)
        registerReceiver(broadDev, intentDevBroadcast)
        val result = a+b+c
        intent.putExtra("dd", result)
    }

    private fun handleBackPress(w: WebView){
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (w.canGoBack()){
                        w.goBack()
                    } else {
                        //Do something else
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadDev)
    }

    companion object{
        val transmitter = MutableSharedFlow<String>()
    }
}

