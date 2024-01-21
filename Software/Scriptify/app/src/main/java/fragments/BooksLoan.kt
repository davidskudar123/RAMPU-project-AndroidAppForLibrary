// BooksLoan.kt
package fragments

import adapters.LibraryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import blueprints.Library
import com.example.scriptify.hr.R

class BooksLoan(id: Int) : Fragment(R.layout.book_loan_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_loan_fragment, container, false)

        // Dohvati mock podatke o knjižnicama
        val mockLibraries = getMockLibraries()

        // Prikazi podatke u RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.libraryRecyclerView)
        val libraryAdapter = LibraryAdapter(mockLibraries)
        recyclerView.adapter = libraryAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    // Funkcija za dobivanje mock podataka o knjižnicama
    private fun getMockLibraries(): List<Library> {
        return listOf(
            Library(
                "1",
                "Library A",
                listOf(
                    Books("101", "Book 1", "Author 1", "Description 1"),
                    Books("102", "Book 2", "Author 2", "Description 2")
                )
            ),
            Library(
                "2",
                "Library B",
                listOf(
                    Books("103", "Book 3", "Author 3", "Description 3"),
                    Books("104", "Book 4", "Author 4", "Description 4")
                )
            )
            // Add more libraries as needed
        )
    }
}
