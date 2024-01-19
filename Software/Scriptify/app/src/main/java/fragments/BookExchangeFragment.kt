package fragments

import adapters.MyBookRecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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

//Fragment za book exchange
// Za implementaciju potrebno je dodati Book blueprint, dodati konekcije na server u HttpRequestManageru, i onda u JsonConverteru da ih možemo loadati, uz to i recyclerview
//VRLO BITNO- Pošto radimo sa http requestovima potrebno je koristiti courutines, u ostalim fragmentima može se pronaći implementacija koja se može iskopirati i doraditi po potrebi
class BookExchangeFragment(id:Int): Fragment(R.layout.book_exchange_fragment) {

    lateinit var recycler: RecyclerView;
    private var Id = id;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_exchange_fragment,container,false)
        recycler = view.findViewById(R.id.books_of_users)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        loadBooks(Id, recycler)
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
        recycler.startAnimation(animation)

        return view
    }

    public fun loadBooks(Id:Int, recycler: RecyclerView){

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
            try {
                val jsonConverter: JsonConverter = JsonConverter()
                val httpRequestManager: HttpRequestManager = HttpRequestManager()
                var data = httpRequestManager.getUserBooks(Id)

                launch(Dispatchers.Main) {
                    val books: List<Books>? = jsonConverter.JsonToBooksConverter(data)
                    val adapter = MyBookRecyclerAdapter(books!!,Id){

                        loadBooks(Id,recycler)
                        recycler.startAnimation(animation)

                    }
                    recycler.adapter = adapter

                }
            }catch (err: IOException){
                Toast.makeText(requireContext(),"Something went wrong", Toast.LENGTH_LONG)
            }


        }
    }
}