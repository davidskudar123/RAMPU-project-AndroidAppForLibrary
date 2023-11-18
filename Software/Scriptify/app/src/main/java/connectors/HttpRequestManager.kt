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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Properties



//Ova klasa služi za dohvaćanje svih zahtjeva sa Servera, po potrebi se proširava

class HttpRequestManager {
    val properties = Properties()
    private val address:String ="http://192.168.1.113"
    private val url: String = "${address}:4000/"
    private var urlSpecific: String ="${address}:4000/loginuser"
    private var registrationUrl: String = "$address:4000/register"
    private var urlUpdate: String ="${address}:4000/updateUserData"
    private var urlUpdateMoney: String ="${address}:4000/updateUserMoney"
    private var urlUpdateBook: String ="${address}:4000/updateBook"
    private var urlBooks: String = "${address}:4000/myBooks"
    private var urlDeleteBook: String = "${address}:4000/deleteBook"
    private var addBook: String = "${address}:4000/makeBook"
    private var connectBook: String = "${address}:4000/userBookCon"
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

    fun getUserBooks(id:Int):String{
        val request = Request.Builder().url("${urlBooks}/${id}").build()
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
    fun updateUserData(jsonBody : String, id:Int): Boolean {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${urlUpdate}/${id}")
            .post(jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()

        val response = client.newCall(request).execute()

        return response.isSuccessful
    }
    fun updateUserMoney(jsonBody : String, id:Int): Boolean {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${urlUpdateMoney}/${id}")
            .post(RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), jsonBody))
            .build()

        val response = client.newCall(request).execute()

        return response.isSuccessful
    }
    fun updateBookData(jsonBody : String, id:Int): Boolean {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${urlUpdateBook}/${id}")
            .post(jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()

        val response = client.newCall(request).execute()

        return response.isSuccessful
    }
    fun makeBook(jsonBook:String):Boolean{

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("${addBook}")
                .post(jsonBook.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
                .build()


        val response = client.newCall(request).execute()
        return response.isSuccessful
    }
    fun connectBookUser(jsonBody: String):Boolean{
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${connectBook}")
            .post(jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()
        val response = client.newCall(request).execute()
        return response.isSuccessful
    }

    fun deleteBook(IdBook:Int):Boolean{
        val request = Request.Builder().url("${urlDeleteBook}/${IdBook}").build()
        var res: String? = ""
        var succ: Boolean = false
        try {

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                res = response.body?.string() ?: "Empty response body"
                succ = response.isSuccessful
            } else {
                // Handle unsuccessful response if needed
                res = "Unexpected code ${response.code}"
                succ = response.isSuccessful
            }
        } catch (e: IOException) {
            e.printStackTrace()
            res = null
        }

        return succ
    }
    suspend fun registerUser(
        username: String,
        password: String,
        email: String,
        address: String,
        firstName: String,
        lastName: String
    ): Boolean {
        try {
            val jsonMediaType = "application/json".toMediaTypeOrNull()

            // Create JSON request body
            val requestBody = """
            {
                "username": "$username",
                "password": "$password",
                "email": "$email",
                "address": "$address",
                "firstName": "$firstName",
                "lastName": "$lastName"
            }
        """.trimIndent().toRequestBody(jsonMediaType)

            // Use the updated registration endpoint URL
            val registrationUrl = "$address:4000/register"

            // Create the request
            val request = Request.Builder()
                .url(registrationUrl)
                .post(requestBody)
                .build()

            // Make the request
            val response: Response = client.newCall(request).execute()

            // Check if registration was successful based on the HTTP status code
            val registrationSuccessful = response.isSuccessful

            return registrationSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}
