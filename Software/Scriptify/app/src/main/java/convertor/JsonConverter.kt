package convertor

import blueprints.Books

import blueprints.Library

import blueprints.Reviews

import blueprints.User
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
//Ova klasa služi za pretvaranje JSON objekata u Objekte u blueprintu, po potrebi se proširava

class JsonConverter {

    fun JsonToLibraryListConverter(json: String): List<Library>? {
        if (json == "null") {
            return null
        } else {
            val gson = Gson()
            return gson.fromJson(json, Array<Library>::class.java).toList()
        }
    }

    fun JsonToLibraryBookListConverter(json: String): List<Books>? {
        if (json == "null") {
            return null
        } else {
            val gson = Gson()
            return gson.fromJson(json, Array<Books>::class.java).toList()
        }
    }
    public fun JsonToUserListConverter(json:String):List<User>?{
        if(json == "null"){
            return null
        }else{
            val gson = Gson()
            return gson.fromJson(json,Array<User>::class.java).toList()
        }

    }
    public fun JsonToUserConverter(json:String):List<User>?{
        if(json == "null"){
            return null
        }else {
            val gson = Gson()
            return gson.fromJson(json, Array<User>::class.java).toList()
        }
    }
    data class UserResponse(val user_id_user: Int)
    public fun JsonToUserIdConverter(json: String): Int? {
        if (json == "null") {
            return null
        } else {
            val gson = Gson()
            val userArray = gson.fromJson(json, Array<UserResponse>::class.java)

            // Ako imate samo jedan rezultat, možete uzeti prvi element niza
            val userResponse = userArray.firstOrNull()

            // Ako je objekt dostupan, dobijte user_id_user
            return userResponse?.user_id_user
        }
    }
    public fun JsonToReviewConverter(json:String):List<Reviews>?{
        if(json == "null"){
            return null
        }else {
            val gson = Gson()
            return gson.fromJson(json, Array<Reviews>::class.java).toList()
        }
    }
    public fun UserToJsonConverter(id:Int,updatedFirstName:String,updatedLastName:String,updatedAddress:String,updatedUsername:String,updatedPassword:String,updatedMail:String):String{
        val jsonObject = JSONObject()
        jsonObject.put("id_user", id) // Pretpostavimo da treba poslati i ID korisnika
        jsonObject.put("first_name", updatedFirstName)
        jsonObject.put("last_name", updatedLastName)
        jsonObject.put("address", updatedAddress)
        jsonObject.put("username", updatedUsername)
        jsonObject.put("password", updatedPassword)
        jsonObject.put("email", updatedMail)
        return  jsonObject.toString()
    }
    public fun UserMoneyToJsonConverter(id:Int, updatedMoney:Int):String{
        val jsonObject = JSONObject()
        jsonObject.put("id_user", id)
        jsonObject.put("money", updatedMoney)
        return  jsonObject.toString()
    }

    public fun userToJsonConverter(id:Int):String{
        val jsonObject = JSONObject()
        jsonObject.put("user_id_user", id)
        return  jsonObject.toString()
    }

    public fun BookToJsonConverter(idKnjige:Int,naziv_knjige:String,Description:String,autor:String):String{
        val jsonObject = JSONObject()
        jsonObject.put("idKnjige", idKnjige) // Pretpostavimo da treba poslati i ID korisnika
        jsonObject.put("naziv_knjige", naziv_knjige)
        jsonObject.put("autor", autor)
        jsonObject.put("Description", Description)

        return  jsonObject.toString()
    }

    public fun PurchasedBookToJsonConverter(idBook:Int, idUser:Int, Name:String, Autor:String, Description: String, Cijena:Int):String{
        val jsonObject = JSONObject()
        jsonObject.put("idKnjige", idBook)
        jsonObject.put("id_user", idUser)
        jsonObject.put("naziv_knjige", Name)
        jsonObject.put("autor", Autor)
        jsonObject.put("Description", Description)
        jsonObject.put("cijena_knjige", Cijena)

        return  jsonObject.toString()
    }
    public fun UpdateMoneyToJson(idUser: Int, money: Int):String{
        val jsonObject = JSONObject()
        jsonObject.put("id_user", idUser)
        jsonObject.put("money", money)

        return  jsonObject.toString()
    }

    public fun userBooktoJsonConverter(idUser:Int,idBook:Int): String{
        val jsonObject = JSONObject()
        jsonObject.put("user_id_user",idUser)
        jsonObject.put("Knjige_idKnjige",idBook)
        return jsonObject.toString()
    }
    public fun BooktoBooktoJsonConverter(idBookGdje:Int,idBookKoja:Int): String{
        val jsonObject = JSONObject()
        jsonObject.put("WhereBook",idBookGdje)
        jsonObject.put("Book",idBookKoja)
        return jsonObject.toString()
    }

    public fun BookJsonConverter(idBook:Int): String{
        val jsonObject = JSONObject()
        jsonObject.put("Knjige_idKnjige",idBook)
        return jsonObject.toString()
    }
    public fun JsonToBooksConverter(json:String):List<Books>?{
        if(json == "null"){
            return null
        }else {
            val gson = Gson()
            return gson.fromJson(json, Array<Books>::class.java).toList()
        }
    }

    public fun AddingReviewsJsonConverter(reviews: Reviews):String{
        val jsonObject = JSONObject()
        jsonObject.put("idKnjige",reviews.idKnjige)
        jsonObject.put("review_text",reviews.review_text)
        jsonObject.put("rating",reviews.rating)
        jsonObject.put("korisnik_ime",reviews.korisnik_ime)
        return jsonObject.toString()
    }

//tijelo zahtjeva za registraciju
    public fun registrationRequestJson(
        username: String,
        password: String,
        email: String,
        address: String,
        firstName: String,
        lastName: String
    ): String {
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("email", email)
        jsonObject.put("address", address)
        jsonObject.put("first_name", firstName)
        jsonObject.put("last_name", lastName)

        return jsonObject.toString()
    }
}