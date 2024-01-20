package fragments
import android.util.Log

import adapters.BooksOfUsersRecyclerAdapter
import adapters.MyBookRecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class BookExchangeFragment(private val userId: Int) : Fragment(R.layout.book_exchange_fragment) {

    private lateinit var recycler: RecyclerView
    lateinit var buy: Button
    lateinit var exchange: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_exchange_fragment, container, false)
        val view2 = inflater.inflate(R.layout.books_of_users_item, container, false)

        buy = view2.findViewById(R.id.btn_buy)
        exchange = view2.findViewById(R.id.btnChange)
        recycler = view.findViewById(R.id.books_of_users)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        loadBooks(userId, recycler)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.animation)
        recycler.startAnimation(animation)

        return view
    }

    private fun loadBooks(userId: Int, recyclerView: RecyclerView) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
            try {
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getBooksOfUsers(userId)

                launch(Dispatchers.Main) {
                    val books: List<Books>? = jsonConverter.JsonToBooksConverter(data)
                    val adapter = BooksOfUsersRecyclerAdapter(books!!,userId){

                        loadBooks(userId,recycler)
                        recycler.startAnimation(animation)

                    }
                    recyclerView.adapter = adapter
                }
            } catch (err: IOException) {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }
}
