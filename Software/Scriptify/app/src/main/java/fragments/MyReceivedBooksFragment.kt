package fragments

import adapters.MyBookRecyclerAdapter
import adapters.MyReceivedBookRecyclerAdapter
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
import blueprints.ReceivedBook
import blueprints.User
import com.android.volley.toolbox.HttpClientStack.HttpPatch
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.delay

class MyReceivedBooksFragment(Id:Int) : Fragment(R.layout.my_received_books_fragment) {

    lateinit var recycler: RecyclerView
    private var Id = Id
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.my_received_books_fragment, container, false)
        recycler = view.findViewById(R.id.my_received_books_rv)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        loadView(Id,recycler)
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
        recycler.startAnimation(animation)

        return view
    }


    public fun loadView(Id:Int,recyclerView: RecyclerView) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
            try {
                val jsonConverter: JsonConverter = JsonConverter()
                val httpRequestManager: HttpRequestManager = HttpRequestManager()
                var data = httpRequestManager.getUserReceivedBooks(Id)

                launch(Dispatchers.Main) {
                    val books: List<ReceivedBook>? = jsonConverter.JsonToReceivedBooksConverter(data)
                    val adapter = MyReceivedBookRecyclerAdapter(books!!,Id){

                        loadView(Id,recycler)
                        recycler.startAnimation(animation)

                    }
                    recyclerView.adapter = adapter

                }
            }catch (err:IOException){
                Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_LONG)
            }


        }


    }


}

