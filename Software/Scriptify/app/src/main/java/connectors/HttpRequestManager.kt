package connectors


import com.android.volley.toolbox.JsonObjectRequest
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HttpRequestManager {
    var url: String = "http://192.168.1.113:4000/"
    val client  = OkHttpClient()
    public fun getData():String{

        val request = Request.Builder().url(url).build()
        var res: String = ""
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun  onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    res = response.toString()
                }
            }
        })
        return res
    }
}