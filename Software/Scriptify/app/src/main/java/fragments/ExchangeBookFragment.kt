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
import blueprints.Books
import blueprints.User
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.connection.Exchange
import okhttp3.internal.userAgent
import java.io.IOException


class ExchangeBookFragment(idUser: Int, ID:Int, private val updateCallback: () -> Unit) : DialogFragment(R.layout.dialog_exchange_book) {

    var IDUser:Int = 0
    constructor(idUser:Int, addedCallback:()->Unit) :this(0,0, {}){
        this.IDUser = idUser
        this.addedCallback = addedCallback
    }
    var UserID = idUser
    var IdBook = ID

    lateinit var title: TextView
    lateinit var close: Button
    lateinit var exchange: Button
    lateinit var addedCallback: ()->Unit
    private lateinit var bookForExchange: Spinner

    private lateinit var bookList: List<Books>


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_exchange_book, container, false)

        bookForExchange = view.findViewById(R.id.bookForExhange)
        exchange = view.findViewById(R.id.excBook)
        close = view.findViewById(R.id.excClose)
        title = view.findViewById(R.id.title_dialog_exchange_book)

        title.setText("Welcome to book exchange!")

        fetchBooksFromDatabase()

        close.setOnClickListener {
            dismiss()
        }

        exchange.setOnClickListener {
            val selectedBook = bookForExchange.selectedItem as Books

            if (selectedBook == null) {
                Toast.makeText(
                    requireContext(),
                    "Please select a book",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                Exchange(selectedBook)
                dismiss()
            }
        }

        return view
    }

    fun Exchange(selectedBook:Books){
        var jsonConverter: JsonConverter = JsonConverter()
        var httpRequestManager: HttpRequestManager = HttpRequestManager()


        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){


            var delete1: String = jsonConverter.BookJsonConverter(IdBook)
            var delete2: String = jsonConverter.BookJsonConverter(selectedBook.idKnjige.toInt())

            val data = httpRequestManager.getUserIdOfBook(delete1)

            val userId: Int? = jsonConverter.JsonToUserIdConverter(data)


            var deleted1: Boolean = httpRequestManager.DeleteUserBook(delete1)
            var deleted2: Boolean = httpRequestManager.DeleteUserBook(delete2)

            println("Obrisano 1: $deleted1")
            println("Obrisano 2: $deleted2")

            var connection1: String = jsonConverter.userBooktoJsonConverter(UserID, IdBook)
            val successConnection1: Boolean = httpRequestManager.connectBookUser(connection1)

            println("${connection1}")
            println("${successConnection1}")

            if(userId != null) {
                var connection2: String = jsonConverter.userBooktoJsonConverter(
                    userId,
                    selectedBook.idKnjige.toInt()
                )
                val successConnection2: Boolean =
                    httpRequestManager.connectBookUser(connection2)
                println("${connection2}")
                println("${selectedBook.idKnjige.toInt()}")
            }


            notifyBookUpdated()

            launch(Dispatchers.Main){
                if (successConnection1){
                    Toast.makeText(requireContext(),"Books were exchanged.",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),"Error when exchanging books.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fetchBooksFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getUserBooks(UserID)

                launch(Dispatchers.Main) {
                    bookList = jsonConverter.JsonToBooksConverter(data) ?: emptyList()

                    val bookArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bookList)
                    bookArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    bookForExchange.adapter = bookArrayAdapter
                }
            } catch (err: IOException) {
                Toast.makeText(requireContext(), "Error fetching books from the database", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun notifyBookUpdated() {
        updateCallback.invoke()
    }

}