package fragments

import adapters.MyBookRecyclerAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.scriptify.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import connectors.HttpRequestManager
import java.io.IOException
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import blueprints.User
import com.android.volley.toolbox.HttpClientStack.HttpPatch
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.delay
// Za implementaciju potrebno je dodati Book blueprint, dodati konekcije na server u HttpRequestManageru, i onda u JsonConverteru da ih možemo loadati, uz to i recyclerview
//VRLO BITNO- Pošto radimo sa http requestovima potrebno je koristiti courutines, u ostalim fragmentima može se pronaći implementacija koja se može iskopirati i doraditi po potrebi

class MyBooksFragment(Id:Int) : Fragment(R.layout.my_books_fragment) {

    lateinit var fab: FloatingActionButton
    lateinit var recycler: RecyclerView
    private var Id = Id
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.my_books_fragment, container, false)
        fab = view.findViewById(R.id.floatingActionButton)
        recycler = view.findViewById(R.id.my_books_rv)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        loadView(Id,recycler)
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
        recycler.startAnimation(animation)
        fab.setOnClickListener {
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val myBookDialogFragment = MyBookDialogFragment()
            myBookDialogFragment.show(fragmentManager,"MyBookDialogFragment")
        }

        return view
    }
    

   public fun loadView(Id:Int,recyclerView: RecyclerView) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val jsonConverter: JsonConverter = JsonConverter()
                    val httpRequestManager: HttpRequestManager = HttpRequestManager()
                    var data = httpRequestManager.getUserBooks(Id)

                    launch(Dispatchers.Main) {
                        val books: List<Books>? = jsonConverter.JsonToBooksConverter(data)
                        //kreiramo callback funkciju u adapteru koju ćemo proslijediti i u dialog fragment koji će onda invokat ju po potrebi
                        val adapter = MyBookRecyclerAdapter(books!!,Id){

                            loadView(Id,recycler)
                        }
                        recyclerView.adapter = adapter

                    }
                }catch (err:IOException){
                    Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_LONG)
                }


            }


    }


}

