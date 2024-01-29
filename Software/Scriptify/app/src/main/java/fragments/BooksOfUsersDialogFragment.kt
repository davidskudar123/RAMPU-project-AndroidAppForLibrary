package fragments

import android.util.Log
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import blueprints.User
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import java.io.IOException

class BooksOfUsersDialogFragment(idUser: Int, ID:Int, Naziv:String, Desc:String, autor:String, cijena_knjige:Int, private val updateCallback: () -> Unit) : DialogFragment(R.layout.dialog_buy_book) {

    var IDUser:Int = 0
    constructor(idUser:Int, addedCallback:()->Unit) :this(0,0,"","","",0, {}){
        this.IDUser = idUser
        this.addedCallback = addedCallback
    }

    var UserID = idUser
    var IdBook = ID
    var Name = Naziv
    var Desc = Desc
    var Autor = autor
    var Cijena = cijena_knjige
    lateinit var add_title: TextView
    lateinit var bookName : TextView
    lateinit var title: TextView
    lateinit var bookDesc : TextView
    lateinit var cijena: TextView
    lateinit var close: Button
    lateinit var buy: Button
    lateinit var autor : TextView
    lateinit var recyclerView: RecyclerView
    lateinit var sc_notice : TextView
    lateinit var sc_sign: TextView
    lateinit var addedCallback: ()->Unit
    lateinit var money: TextView
    private lateinit var paymentMethodSpinner: Spinner


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_buy_book, container, false)
        val view2 = inflater.inflate(R.layout.book_exchange_fragment, container, false)

        paymentMethodSpinner = view.findViewById(R.id.paymentMethodSpinner)
        bookDesc = view.findViewById(R.id.desc)
        bookName = view.findViewById(R.id.name)
        title = view.findViewById(R.id.title_dialog_buy_book)
        close = view.findViewById(R.id.buy_book_close_dialog)
        buy = view.findViewById(R.id.buyBook)
        autor = view.findViewById(R.id.author)
        recyclerView = view2.findViewById(R.id.books_of_users)
        add_title = view.findViewById(R.id.title_dialog_buy_book_buy)
        sc_notice = view.findViewById(R.id.sc_notice_add)
        sc_sign = view.findViewById(R.id.sc_sign_add)
        money = view.findViewById(R.id.moneyShow)
        cijena = view.findViewById(R.id.price)
        title.setText("Buying a book")
        bookName.setText("${Name}")
        bookDesc.setText("${Desc}")
        autor.setText("${Autor}")
        cijena.setText("${Cijena}")

        val paymentOptions = arrayOf("Cash on collection", "My wallet")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paymentOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paymentMethodSpinner.adapter = adapter

        LoadMoneyOfUser(UserID)

        close.setOnClickListener {
            dismiss()
        }

        buy.setOnClickListener {
            val selectedPaymentMethod = paymentMethodSpinner.selectedItem

            if (selectedPaymentMethod == null) {
                Toast.makeText(
                    requireContext(),
                    "Please select a payment method!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                var spinnerValue = selectedPaymentMethod.toString()
                BuyABook(IdBook, UserID, Name, Desc, Autor, Cijena, spinnerValue)
                dismiss()
            }

        }

        return view
    }

    private fun LoadMoneyOfUser(IDuser: Int) {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {

            var value = 0
            var iduser:Int = IDuser

            val jsonConverter = JsonConverter()
            val httpRequestManager = HttpRequestManager()
            var ForMoneyOfUser: String = jsonConverter.userToJsonConverter(iduser)

            val data = httpRequestManager.getUserMoneyInfo(ForMoneyOfUser)
            val user: List<User>? = jsonConverter.JsonToUserConverter(data)

            if (user != null) {
                money.setText(user[0].money.toString())
            }

        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun BuyABook(IDbook:Int, IDuser:Int, Name: String, Desc:String, Autor:String, Cijena:Int, spinnerValue: String){
        var idbook:Int = IDbook
        var iduser:Int = IDuser
        var name:String = Name
        var desc:String = Desc
        var autor:String = Autor
        var cijena:Int = Cijena
        var spinnerValue = spinnerValue
        var jsonConverter: JsonConverter = JsonConverter()
        var httpRequestManager: HttpRequestManager = HttpRequestManager()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
            var purchasedBookInJson: String = jsonConverter.PurchasedBookToJsonConverter(idbook, iduser, name, desc, autor, cijena)
            var connection: String = jsonConverter.userBooktoJsonConverter(iduser,idbook)

            var status = "Pending"
            var receivedBookInJson: String = jsonConverter.ReceivedBookToJsonConverter(iduser, idbook, name, desc, autor, cijena, status)

            if(spinnerValue == "Cash on collection"){
                val successBuy:Boolean = httpRequestManager.buyBook(purchasedBookInJson)
                val successConnection: Boolean = httpRequestManager.UpdateConnectBookUser(connection)

                notifyBookUpdated()

                launch(Dispatchers.Main){
                    if (successBuy && successConnection){
                        Toast.makeText(requireContext(),"The book has been purchased, payment is cash on delivery.",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(),"Error when buying a book.",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if(spinnerValue == "My wallet"){

                var value:Int  = 0

                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                var ForMoneyOfUser: String = jsonConverter.userToJsonConverter(iduser)

                val data = httpRequestManager.getUserMoneyInfo(ForMoneyOfUser)
                val user: List<User>? = jsonConverter.JsonToUserConverter(data)

                if(user != null){
                    value = user[0].money
                }


                Log.d("Tag", "Poruka s varijablom: $value")
                if(value >= Cijena){
                    val successBuy:Boolean = httpRequestManager.buyBook(purchasedBookInJson)
                    val successConnection: Boolean = httpRequestManager.UpdateConnectBookUser(connection)
                    value -= Cijena
                    val jsonBody = jsonConverter.UserMoneyToJsonConverter(UserID, value)
                    val success = httpRequestManager.updateUserMoney(jsonBody, UserID)
                    notifyBookUpdated()

                    if (successBuy && successConnection && success) {
                        launch(Dispatchers.Main){
                            Toast.makeText(requireContext(),"The book has been purchased.",Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    launch(Dispatchers.Main){
                        Toast.makeText(requireContext(),"You don't have enough money on your account.",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun notifyBookUpdated() {
        updateCallback.invoke()
    }

}