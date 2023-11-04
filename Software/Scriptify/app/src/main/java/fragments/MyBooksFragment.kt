package fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scriptify.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyBooksFragment(): Fragment(R.layout.my_books_fragment) {

    lateinit var fab: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.my_books_fragment,container,false)
        fab = view.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener{
            Toast.makeText(requireContext(),"Napravio sam knjigu",Toast.LENGTH_LONG).show()
        }
        return view
    }
}