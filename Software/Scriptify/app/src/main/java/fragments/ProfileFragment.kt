package fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import blueprints.User
import com.example.scriptify.hr.MainActivity
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

// Za implementaciju potrebno je dodati Book blueprint, dodati konekcije na server u HttpRequestManageru, i onda u JsonConverteru da ih možemo loadati, uz to i recyclerview
//VRLO BITNO- Pošto radimo sa http requestovima potrebno je koristiti courutines, u ostalim fragmentima može se pronaći implementacija koja se može iskopirati i doraditi po potrebi
class ProfileFragment(id:Int) : Fragment(R.layout.profile_activity) {

    private val Id: Int = id
    lateinit var username: EditText
    lateinit var mail: EditText
    lateinit var profile_button: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.profile_activity, container, false)
        username = view.findViewById(R.id.username_profile)
        mail = view.findViewById(R.id.mail_profile)
        profile_button = view.findViewById(R.id.profileUpdate)
        loadData(mail,username)
        return view
    }

    public fun loadData(mail: EditText,username: EditText){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default){
            try {

                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getSpecificUserData(Id)

                launch(Dispatchers.Main) {
                    val user: List<User> = jsonConverter.JsonToUserConverter(data)
                    mail.setText(user[0].email)
                    username.setText(user[0].username)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle the exception, show an error message, etc.
            }
        }
    }

}