package fragments

import adapters.BooksOfUsersRecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
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

class BookExchangeFragment(private val userId: Int) : Fragment(R.layout.book_exchange_fragment) {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_exchange_fragment, container, false)
        recycler = view.findViewById(R.id.books_of_users)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.animation)
        recycler.startAnimation(animation)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadBooks(userId)
    }

    private fun loadBooks(userId: Int) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getBooksOfUsers(userId)

                launch(Dispatchers.Main) {
                    val books: List<Books>? = jsonConverter.JsonToBooksConverter(data)
                    val adapter = BooksOfUsersRecyclerAdapter(books ?: emptyList(), userId) {
                        loadBooks(userId)
                        recycler.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.animation))
                    }
                    recycler.adapter = adapter
                }
            } catch (err: IOException) {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }
}
