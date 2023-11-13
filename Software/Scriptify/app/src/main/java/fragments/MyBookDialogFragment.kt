package fragments

import adapters.MyBookRecyclerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
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
import java.io.IOException

class MyBookDialogFragment(ID:Int,Naziv:String,Desc:String,autor:String,private val updateCallback: () -> Unit) : DialogFragment(R.layout.dialog_my_books) {
    var IdBook = ID
    var Name = Naziv
    var Desc = Desc
    var Autor = autor

    lateinit var bookName : EditText
    lateinit var title: TextView
    lateinit var bookDesc : EditText
    lateinit var close: Button
    lateinit var update: Button
    lateinit var delete: Button
    lateinit var autor :EditText
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_my_books,container,false)
        val view2 = inflater.inflate(R.layout.my_books_fragment,container,false)
        bookDesc = view.findViewById(R.id.my_book_dialog_desc)
        bookName = view.findViewById(R.id.my_book_dialog_name)
        title = view.findViewById(R.id.title_dialog_mybooks)
        close = view.findViewById(R.id.mybooks_close_dialog)
        update = view.findViewById(R.id.my_book_update)
        delete = view.findViewById(R.id.mybooks_delete_dialog)
        autor = view.findViewById(R.id.my_book_dialog_autor)
        recyclerView = view2.findViewById(R.id.my_books_rv)

        title.setText("${Name}")
        bookName.setText("${Name}")
        bookDesc.setText("${Desc}")
        autor.setText("${Autor}")
        close.setOnClickListener {
            dismiss()
        }
        update.setOnClickListener {
            saveBookData(IdBook,bookName,bookDesc,autor)
            notifyBookUpdated()
            dismiss()
        }
        delete.setOnClickListener {
            Toast.makeText(requireContext(),"Your book has been deleted!",Toast.LENGTH_SHORT).show()
            dismiss()
        }



        return view
    }
    fun notifyBookUpdated() {
        updateCallback.invoke()
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