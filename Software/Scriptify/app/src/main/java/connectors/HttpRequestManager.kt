package connectors


import blueprints.Books
import blueprints.Library
import convertor.JsonConverter
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
    private val address:String ="http://192.168.1.112"
    private val url: String = "${address}:4000/"
    private var urlSpecific: String ="${address}:4000/loginuser"
    private var registrationUrl: String = "${address}:4000/register"
    private var urlUpdate: String ="${address}:4000/updateUserData"
    private var urlUpdateMoney: String ="${address}:4000/updateUserMoney"
    private var urlUpdateBook: String ="${address}:4000/updateBook"
    private var urlBooks: String = "${address}:4000/myBooks"
    private var urlDeleteBook: String = "${address}:4000/deleteBook"
    private var addBook: String = "${address}:4000/makeBook"
    private var connectBook: String = "${address}:4000/userBookCon"
    private var UpdateConnectBook: String = "${address}:4000/updateUserBookCon"
    private var urlBooksOfUsers: String = "${address}:4000/BooksOfUsers"
    private var buyBook: String = "${address}:4000/buyBook"
    private var updateMoney: String = "${address}:4000/updateMoney"
    private var urlMoneyInfo: String = "${address}:4000/urlMoneyInfo"
    private var libraries: String = "${address}:4000/libraries"
    private var BooksOfLibrary: String = "${address}:4000/BooksOfLibrary"

    private var reviews: String = "${address}:4000/review"

    private val client = OkHttpClient()
//dohvaćanje
    fun getLibraryBooks(libraryId: Int): List<Books>? {
        val request = Request.Builder().url("${BooksOfLibrary}/${libraryId}").build()

        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    return responseBody?.let {
                        JsonConverter().JsonToLibraryBookListConverter(it)
                    }
                } else {
                    println("Unexpected code ${response.code}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


    fun getLibraries(): List<Library>? {
        val request = Request.Builder().url("${libraries}").build()

        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    return responseBody?.let {
                        JsonConverter().JsonToLibraryListConverter(it)
                    }
                } else {
                    println("Unexpected code ${response.code}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }





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
    fun getReviewsForBook(id:Int): String  {
        val request = Request.Builder().url("${reviews}/${id}").build()
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


    fun getUserMoneyInfo(jsonBody : String): String  {
        val request = Request.Builder()
            .url("${urlMoneyInfo}")
            .post(jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()
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

    fun getBooksOfUsers(id:Int):String{
        val request = Request.Builder().url("${urlBooksOfUsers}/${id}").build()
        var res: String? = ""
        try {
            val response = client.newCall(request).execute()
            if(response.isSuccessful){
                res = response.body?.string() ?: "Empty response body"
            }
            else{
                res = "Unexpected code ${response.code}"
            }
        }
        catch (e: IOException){
            e.printStackTrace()
            res = null;
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

    fun UpdateMoney(jsonBody: String):Boolean{
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${updateMoney}")
            .post(jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
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

    fun buyBook(jsonBook:String):Boolean{
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${buyBook}")
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
    fun UpdateConnectBookUser(jsonBody: String):Boolean{
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("${UpdateConnectBook}")
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
            val jsonMediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

            // Use the updated registration endpoint URL


            // Create JSON request body using the JsonConverter
            val requestBody = JsonConverter().registrationRequestJson(
                username,
                password,
                email,
                address,
                firstName,
                lastName
            )

            // Create the request
            val request = Request.Builder()
                .url(registrationUrl)
                .post(requestBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
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
