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
    }public fun BookToJsonConverter(idKnjige:Int,naziv_knjige:String,Description:String,Autor:String):String{
        val jsonObject = JSONObject()
        jsonObject.put("idKnjige", idKnjige) // Pretpostavimo da treba poslati i ID korisnika
        jsonObject.put("naziv_knjige", naziv_knjige)
        jsonObject.put("autor", Autor)
        jsonObject.put("Description", Description)

        return  jsonObject.toString()
    }

    public fun JsonToBooksConverter(json:String):List<Books>?{
        if(json == "null"){
            return null
        }else {
            val gson = Gson()
            return gson.fromJson(json, Array<Books>::class.java).toList()
        }
    }
}