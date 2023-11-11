package convertor

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
}