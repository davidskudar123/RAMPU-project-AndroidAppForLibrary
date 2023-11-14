package fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
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
import org.json.JSONObject
import java.io.IOException

// Za implementaciju potrebno je dodati Book blueprint, dodati konekcije na server u HttpRequestManageru, i onda u JsonConverteru da ih možemo loadati, uz to i recyclerview
//VRLO BITNO- Pošto radimo sa http requestovima potrebno je koristiti courutines, u ostalim fragmentima može se pronaći implementacija koja se može iskopirati i doraditi po potrebi
class ProfileFragment(id:Int) : Fragment(R.layout.profile_activity) {

    private val Id: Int = id
    lateinit var first_name: EditText
    lateinit var last_name: EditText
    lateinit var address:EditText
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var mail: EditText
    lateinit var profile_button: Button
    ////novci
    lateinit var money: TextView
    lateinit var openMoneyDialog: Button
    lateinit var add_money: Button
    lateinit var Close: Button
    lateinit var input_money_amount: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.profile_activity, container, false)
        first_name = view.findViewById(R.id.first_name_profile)
        last_name = view.findViewById(R.id.last_name_profile)
        address = view.findViewById(R.id.address_profile)
        username = view.findViewById(R.id.username_profile)
        password = view.findViewById(R.id.password_profile)
        mail = view.findViewById(R.id.mail_profile)
        profile_button = view.findViewById(R.id.profileUpdate)
        //dodavanje novaca
        money = view.findViewById(R.id.money_profile)
        openMoneyDialog = view.findViewById(R.id.btn_openMoneyDialog)
        add_money = view.findViewById(R.id.btn_add_money)
        Close = view.findViewById(R.id.btn_Close)
        input_money_amount = view.findViewById(R.id.input_money_amount)

        loadData(first_name, last_name, address, mail,username,password, money)

        profile_button.setOnClickListener{
            saveUserData(first_name, last_name, address, username, password, mail);
        }
        openMoneyDialog.setOnClickListener{
            showDialogAddMoney()
        }
        return view
    }

    public fun loadData(first_name: EditText, last_name: EditText, address: EditText, mail: EditText,username: EditText, password: EditText, money: TextView){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default){
            try {

                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getSpecificUserData(Id)

                launch(Dispatchers.Main) {
                    val user: List<User>? = jsonConverter.JsonToUserConverter(data)
                    if(user != null){
                        first_name.setText(user[0].first_name)
                        last_name.setText(user[0].last_name)
                        address.setText(user[0].address)
                        mail.setText(user[0].email)
                        password.setText(user[0].password)
                        username.setText(user[0].username)
                        money.setText(user[0].money.toString())
                    }

                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle the exception, show an error message, etc.
            }
        }
    }

    private fun saveUserData(first_name: EditText, last_name: EditText, address: EditText, username : EditText,password:EditText,  mail : EditText) {
        val updatedFirstName = first_name.text.toString()
        val updatedLastName = last_name.text.toString()
        val updatedAddress = address.text.toString()
        val updatedUsername = username.text.toString()
        val updatedPassword = password.text.toString()
        val updatedMail = mail.text.toString()
        val jsonConverter: JsonConverter = JsonConverter()
        //val updatedAddress = adress_profile.text.toString()

        val jsonBody = jsonConverter.UserToJsonConverter(Id,updatedFirstName,updatedLastName,updatedAddress,updatedUsername,updatedPassword,updatedMail)

       val courutine =  viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            try {
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()

                val success = httpRequestManager.updateUserData(jsonBody, Id)

                launch(Dispatchers.Main) {
                    if (success) {
                        Toast.makeText(requireContext(), "Podaci ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
    private fun showDialogAddMoney(){
        val dialog_add_money = view?.findViewById<FrameLayout>(R.id.dialog_add_money)
        dialog_add_money?.visibility = View.VISIBLE

        add_money.setOnClickListener{

            if(!TextUtils.isEmpty(input_money_amount.text)){

                var updatedMoney:Int
                var moneyOfUser = money.text.toString()
                var inputMoney = input_money_amount.text.toString()
                if(moneyOfUser.isNotEmpty() && inputMoney.isNotEmpty()){
                    val courutine =  viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
                        try {
                            var moneyOfUserInt = moneyOfUser.toInt()
                            var inputMoneyInt = inputMoney.toInt()

                            updatedMoney = moneyOfUserInt + inputMoneyInt
                            val jsonConverter = JsonConverter()
                            val jsonBody = jsonConverter.UserMoneyToJsonConverter(Id, updatedMoney)

                            val httpRequestManager = HttpRequestManager()

                            val success = httpRequestManager.updateUserMoney(jsonBody, Id)
                            launch(Dispatchers.Main) {
                                if (success) {
                                    dialog_add_money?.visibility = View.GONE
                                    Toast.makeText(
                                        requireContext(),
                                        "Podaci ažurirani",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Greška pri ažuriranju podataka",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                requireContext(),
                                "Unos nije ispravan broj",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                else{
                    Toast.makeText(requireContext(), "Niste unjeli iznos!", Toast.LENGTH_SHORT).show()
                }
            }

            else{
                Toast.makeText(requireContext(), "Unesite ispravan iznos novca!", Toast.LENGTH_SHORT).show()
            }
        }
        Close.setOnClickListener{
            dialog_add_money?.visibility = View.GONE
        }
    }
}