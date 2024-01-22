// LibraryAdapter.kt
package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blueprints.Library
import com.example.scriptify.hr.R


class LibraryAdapter(private val libraries: List<Library>) : RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_library, parent, false)
        return LibraryViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val library = libraries[position]
        holder.bind(library)
    }

    override fun getItemCount(): Int = libraries.size

    class LibraryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val libraryNameTextView: TextView = itemView.findViewById(R.id.libraryNameTextView)

        fun bind(library: Library) {
            libraryNameTextView.text = library.ime
            // Bind other views with data if needed
        }
    }
}


/*class LibraryAdapter(private val libraries: List<Library>,
                 //    private val onLibraryClickListener: OnLibraryClickListener
) : RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    interface OnLibraryClickListener {
        fun onLibraryClick(library: Library)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_library, parent, false)
        return LibraryViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val library = libraries[position]
        holder.bind(library)
        holder.itemView.setOnClickListener {
            //onLibraryClickListener.onLibraryClick(library)
        }
    }

    override fun getItemCount(): Int = libraries.size

    class LibraryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val libraryNameTextView: TextView = itemView.findViewById(R.id.libraryNameTextView)

        fun bind(library: Library) {
            libraryNameTextView.text = library.nazivKnjiznice
            // Bind other views with data if needed
        }
    }
}
*/