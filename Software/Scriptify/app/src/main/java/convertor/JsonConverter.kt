package convertor

import blueprints.Books
import blueprints.User
import com.google.gson.Gson
import org.json.JSONObject
//Ova klasa služi za pretvaranje JSON objekata u Objekte u blueprintu, po potrebi se proširava

class JsonConverter {

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

    public fun BookToJsonConverter(idKnjige:Int,naziv_knjige:String,Description:String,autor:String):String{
        val jsonObject = JSONObject()
        jsonObject.put("idKnjige", idKnjige) // Pretpostavimo da treba poslati i ID korisnika
        jsonObject.put("naziv_knjige", naziv_knjige)
        jsonObject.put("autor", autor)
        jsonObject.put("Description", Description)

        return  jsonObject.toString()
    }

    public fun PurchasedBookToJsonConverter(idBook:Int, idUser:Int, Name:String, Autor:String, Description: String):String{
        val jsonObject = JSONObject()
        jsonObject.put("idKnjige", idBook)
        jsonObject.put("id_user", idUser)
        jsonObject.put("naziv_knjige", Name)
        jsonObject.put("autor", Autor)
        jsonObject.put("Description", Description)

        return  jsonObject.toString()
    }
    public fun userBooktoJsonConverter(idUser:Int,idBook:Int): String{
        val jsonObject = JSONObject()
        jsonObject.put("user_id_user",idUser)
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