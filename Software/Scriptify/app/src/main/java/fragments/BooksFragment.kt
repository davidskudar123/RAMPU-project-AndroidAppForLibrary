package fragments

import adapters.BooksAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksFragment(private val libraryId: Int) : Fragment(R.layout.books_fragment) {

    private val httpRequestManager = HttpRequestManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.books_fragment, container, false)

        // Initialize and set up your RecyclerView and adapter here
        val recyclerView: RecyclerView = view.findViewById(R.id.booksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Call the function to fetch books for the selected library
                val books: List<Books>? = httpRequestManager.getLibraryBooks(libraryId)

                // Check for null and update the UI on the main thread
                launch(Dispatchers.Main) {
                    if (books != null) {
                        val booksAdapter = BooksAdapter(books)
                        recyclerView.adapter = booksAdapter

                    } else {
                        // Handle case when books are null
                        // You might want to show an error message or handle it appropriately
                    }
                }
            } catch (e: Exception) {
                // Handle exception, log error, or show an error message
                // Note: You may want to handle errors appropriately
            }
        }

        return view
    }
}
