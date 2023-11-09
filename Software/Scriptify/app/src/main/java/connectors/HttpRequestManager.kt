package connectors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlinx.coroutines.*

//Ova klasa služi za dohvaćanje svih zahtjeva sa Servera, po potrebi se proširava

class HttpRequestManager {
    private val url: String = "http://172.20.10.2:4000/"
    private var urlSpecific: String ="http://172.20.10.2:4000/loginuser"
    private val client = OkHttpClient()

     fun getUserData(): String  {
        val request = Request.Builder().url(url).build()
        var res: String? = ""

        try {

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                res = response.body?.string() ?: "Empty response body"
            } else {
                // Handle unsuccessful response if needed
                res = "Unexpected code ${response.code}"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            res = null
        }

        return res.toString()
    }
    fun getSpecificUserData(id:Int): String  {
        val request = Request.Builder().url("${urlSpecific}/${id}").build()
        var res: String? = ""

        try {

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                res = response.body?.string() ?: "Empty response body"
            } else {
                // Handle unsuccessful response if needed
                res = "Unexpected code ${response.code}"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            res = null
        }

        return res.toString()
    }
}
