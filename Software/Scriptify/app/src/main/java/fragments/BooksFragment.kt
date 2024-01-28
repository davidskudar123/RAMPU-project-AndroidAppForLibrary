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
import fragments.BooksOfUsersDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksFragment(private val libraryId: Int, private val Id: Int) : Fragment(R.layout.books_fragment) {

    private val httpRequestManager = HttpRequestManager()
    private val id = Id
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.books_fragment, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.booksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        updateUIAfterBookPurchase(recyclerView)

        return view
    }

    private fun showBookDetails(selectedBook: Books, recyclerView: RecyclerView) {

        val bookId = selectedBook.idKnjige.toIntOrNull() ?: -1
        val dialogFragment = BooksOfUsersDialogFragment(

            Id,
            bookId,
            selectedBook.naziv_knjige,
            selectedBook.Description,
            selectedBook.autor,
            selectedBook.cijena_knjige
        ) {
            updateUIAfterBookPurchase(recyclerView)
        }

        dialogFragment.show(parentFragmentManager, "BooksOfUsersDialogFragment")
    }


    private fun updateUIAfterBookPurchase(recyclerView:RecyclerView) {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val books: List<Books>? = httpRequestManager.getLibraryBooks(libraryId, id)

                launch(Dispatchers.Main) {
                    if (books != null) {
                        val booksAdapter = BooksAdapter(books)
                        recyclerView.adapter = booksAdapter

                        booksAdapter.onItemClickListener = { position ->
                            val selectedBook = books[position]
                            showBookDetails(selectedBook,recyclerView)
                        }
                    } else {
                    }
                }
            } catch (e: Exception) {
            }
        }

        }
    }
