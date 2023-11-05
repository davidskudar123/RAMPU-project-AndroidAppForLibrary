package convertor

import blueprints.User
import com.google.gson.Gson

class JsonConverter {

    public fun JsonToUserListConverter(json:String):List<User>{
        val gson = Gson()
        return gson.fromJson(json,Array<User>::class.java).toList()
    }
}