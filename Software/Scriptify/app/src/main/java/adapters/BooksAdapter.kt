package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R

class BooksAdapter(private val books: List<Books>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    // Add onItemClickListener property
    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)

        // Set click listener on the item view
        holder.itemView.setOnClickListener {
            // Invoke the onItemClickListener callback with the clicked position
            onItemClickListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = books.size


    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookTitleTextView: TextView = itemView.findViewById(R.id.bookTitleTextView)
        private val bookAuthorTextView: TextView = itemView.findViewById(R.id.bookAuthorTextView)
        private val bookDescriptionTextView: TextView = itemView.findViewById(R.id.bookDescriptionTextView)

        fun bind(book: Books) {
            bookTitleTextView.text = book.naziv_knjige
            bookAuthorTextView.text = "Author: ${book.autor}"
            bookDescriptionTextView.text = "Description: ${book.Description}"
        }
    }
}
