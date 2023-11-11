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
import connectors.HttpRequestManager
import java.io.IOException
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import blueprints.User
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
// Za implementaciju potrebno je dodati Book blueprint, dodati konekcije na server u HttpRequestManageru, i onda u JsonConverteru da ih možemo loadati, uz to i recyclerview
//VRLO BITNO- Pošto radimo sa http requestovima potrebno je koristiti courutines, u ostalim fragmentima može se pronaći implementacija koja se može iskopirati i doraditi po potrebi
class MyBooksFragment(id:Int) : Fragment(R.layout.my_books_fragment) {

    lateinit var fab: FloatingActionButton
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.my_books_fragment, container, false)
        fab = view.findViewById(R.id.floatingActionButton)


        fab.setOnClickListener {

        }

        return view
    }

}

