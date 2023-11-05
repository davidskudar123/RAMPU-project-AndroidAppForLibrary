package convertor

import blueprints.User
import com.google.gson.Gson

//Ova klasa služi za pretvaranje JSON objekata u Objekte u blueprintu, po potrebi se proširava

class JsonConverter {

    public fun JsonToUserListConverter(json:String):List<User>{
        val gson = Gson()
        return gson.fromJson(json,Array<User>::class.java).toList()
    }
    public fun JsonToUserConverter(json:String):List<User>{
        val gson = Gson()
        return gson.fromJson(json,Array<User>::class.java).toList()
    }
}