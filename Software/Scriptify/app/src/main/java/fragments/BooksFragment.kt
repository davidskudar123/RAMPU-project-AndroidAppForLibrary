package fragments

import adapters.BooksAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R

class BooksFragment(private val books: List<Books>) : Fragment(R.layout.books_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.books_fragment, container, false)

        // Initialize and set up your RecyclerView and adapter here
        val recyclerView: RecyclerView = view.findViewById(R.id.booksRecyclerView)
        val booksAdapter = BooksAdapter(books)
        recyclerView.adapter = booksAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}
