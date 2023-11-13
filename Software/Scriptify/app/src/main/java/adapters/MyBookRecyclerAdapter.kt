package adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import blueprints.Books
import com.example.scriptify.hr.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fragments.BooksLoan
import fragments.MyBookDialogFragment

class MyBookRecyclerAdapter(val data:List<Books>) : RecyclerView.Adapter<MyBookRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item){
            var name: TextView = item.findViewById(R.id.book_name_my_books)
            var desc: TextView = item.findViewById(R.id.description_my_books)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.my_book_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = data[position]
        holder.name.text = items.naziv_knjige
        holder.desc.text = items.Description
        holder.itemView.setOnClickListener {
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val myBookDialogFragment = MyBookDialogFragment()
            myBookDialogFragment.show(fragmentManager, "MyBookDialogFragment")
        }

    }
}