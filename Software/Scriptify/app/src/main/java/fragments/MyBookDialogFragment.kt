package fragments

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.scriptify.hr.R

class MyBookDialogFragment(ID:Int,Naziv:String,Desc:String) : DialogFragment(R.layout.dialog_my_books) {
    var IdBook = ID
    var Name = Naziv
    var Desc = Desc
    lateinit var bookName : EditText
    lateinit var title: TextView
    lateinit var bookDesc : EditText
    lateinit var close: Button
    lateinit var update: Button
    lateinit var delete: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_my_books,container,false)
        bookDesc = view.findViewById(R.id.my_book_dialog_desc)
        bookName = view.findViewById(R.id.my_book_dialog_name)
        title = view.findViewById(R.id.title_dialog_mybooks)
        close = view.findViewById(R.id.mybooks_close_dialog)
        update = view.findViewById(R.id.my_book_update)
        delete = view.findViewById(R.id.mybooks_delete_dialog)
        title.setText("${Name}")
        bookName.setText("${Name}")
        bookDesc.setText("${Desc}")
        close.setOnClickListener {
            dismiss()
        }



        return view
    }
}