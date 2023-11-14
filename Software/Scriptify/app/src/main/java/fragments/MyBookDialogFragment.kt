package fragments

import adapters.MyBookRecyclerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.io.IOException
import kotlin.random.Random

class MyBookDialogFragment(ID:Int,Naziv:String,Desc:String,autor:String,private val updateCallback: () -> Unit) : DialogFragment(R.layout.dialog_my_books) {

    var IDUser:Int = 0
    constructor(idUser:Int) :this(0,"","","",{}){
        this.IDUser = idUser
    }

    var IdBook = ID
    var Name = Naziv
    var Desc = Desc
    var Autor = autor
    lateinit var add_title: TextView
    lateinit var bookName : EditText
    lateinit var title: TextView
    lateinit var bookDesc : EditText
    lateinit var close: Button
    lateinit var update: Button
    lateinit var delete: Button
    lateinit var autor :EditText
    lateinit var add: Button
    lateinit var recyclerView: RecyclerView
    lateinit var sc_notice : TextView
    lateinit var sc_sign: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_my_books,container,false)
        val view2 = inflater.inflate(R.layout.my_books_fragment,container,false)
        var settableBookId = generateBookId()

        bookDesc = view.findViewById(R.id.my_book_dialog_desc)
        bookName = view.findViewById(R.id.my_book_dialog_name)
        title = view.findViewById(R.id.title_dialog_mybooks)
        close = view.findViewById(R.id.mybooks_close_dialog)
        update = view.findViewById(R.id.my_book_update)
        delete = view.findViewById(R.id.mybooks_delete_dialog)
        autor = view.findViewById(R.id.my_book_dialog_autor)
        recyclerView = view2.findViewById(R.id.my_books_rv)
        add = view.findViewById(R.id.Add_my_book)
        add_title = view.findViewById(R.id.title_dialog_mybooks_add)
        sc_notice = view.findViewById(R.id.sc_notice_add)
        sc_sign = view.findViewById(R.id.sc_sign_add)
        title.setText("${Name}")
        bookName.setText("${Name}")
        bookDesc.setText("${Desc}")
        autor.setText("${Autor}")

        close.setOnClickListener {
            dismiss()
        }
        if(IdBook == 0){
            update.setVisibility(View.INVISIBLE)
            delete.setVisibility(View.INVISIBLE)
            add.setVisibility(View.VISIBLE)
            add_title.setVisibility(View.VISIBLE)
            sc_sign.setVisibility(View.VISIBLE)
            sc_notice.setVisibility(View.VISIBLE)
        }
        update.setOnClickListener {
            saveBookData(IdBook,bookName,bookDesc,autor)
            notifyBookUpdated()
            dismiss()
        }
        delete.setOnClickListener {
            deleteBookData(IdBook)
            notifyBookUpdated()
            dismiss()
        }
        add.setOnClickListener {
            Toast.makeText(requireContext(),"You have added a new book!",Toast.LENGTH_SHORT).show()

            addBook(IDUser,settableBookId,bookName,bookDesc,autor)
            dismiss()
        }



        return view
    }
    fun addBook(userID:Int,BookID:Int,Naziv: EditText,Desc:EditText,Autor:EditText){
        Log.i("USEUR","User:${userID}")
        Log.d("Book","Book:${BookID}")
    }
    fun generateBookId():Int{
        return Random.nextInt(1,523523525)

    }
    fun notifyBookUpdated() {
        updateCallback.invoke()
    }
    fun deleteBookData(bookID: Int){
        val httpRequestManager = HttpRequestManager()
        var success: Boolean = false
        notifyBookUpdated()
        try {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default){
                success = httpRequestManager.deleteBook(bookID)
            }

        }catch (IO:IOException){
            IO.printStackTrace()
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
            if(success){
                Toast.makeText(requireContext(),"Book has been deleted!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Error deleting book",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun saveBookData(bookID:Int,Naziv: EditText,Desc:EditText,Autor:EditText){
        val updatedNaziv = Naziv.text.toString()
        val updatedDesc = Desc.text.toString()
        val updatedAutor = Autor.text.toString()
        val jsonConverter: JsonConverter = JsonConverter()

        notifyBookUpdated()
        var success: Boolean = false
        val httpRequestManager: HttpRequestManager = HttpRequestManager()
        val jsonBody = jsonConverter.BookToJsonConverter(bookID,updatedNaziv,updatedDesc,updatedAutor)
        val courutine =  viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            try {
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()

                success = httpRequestManager.updateBookData(jsonBody,bookID)


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            if (success) {
                Toast.makeText(requireContext(), "Books have been updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Error updating books", Toast.LENGTH_SHORT).show()
            }
        }
    }
}