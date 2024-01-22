import adapters.LibraryAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Library
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import fragments.BooksFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksLoan(Id: Int) : Fragment(R.layout.book_loan_fragment) {

    private val httpRequestManager = HttpRequestManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_loan_fragment, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.libraryRecyclerView)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val libraries: List<Library>? = httpRequestManager.getLibraries()

                if (libraries != null) {
                    // Use LibraryAdapter to display libraries
                    launch(Dispatchers.Main) {
                        val libraryAdapter = LibraryAdapter(libraries)
                        recyclerView.adapter = libraryAdapter
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())

                        // Set onItemClickListener for reacting to library selection
                        libraryAdapter.onItemClickListener = { position ->
                            val selectedLibraryId = libraries[position].idKnjizara
                            showBooksForLibrary(selectedLibraryId)
                        }
                    }

                } else {
                    // Handle case when libraries are null or empty
                    Log.e("BooksLoan", "Libraries are null or empty")
                }
            } catch (e: Exception) {
                // Handle exception, log error, or show an error message
                Log.e("BooksLoan", "Error fetching libraries", e)
            }
        }

        return view
    }

    private fun showBooksForLibrary(libraryId: Int) {
        // Call the function to fetch books for the selected library
        // Example:
        //val books = httpRequestManager.getLibraryBooks(libraryId) ?: emptyList()

        // Create and show a new fragment with the retrieved books
        val booksFragment = BooksFragment(libraryId)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.flLayout, booksFragment)
            .addToBackStack(null)
            .commit()
    }
}