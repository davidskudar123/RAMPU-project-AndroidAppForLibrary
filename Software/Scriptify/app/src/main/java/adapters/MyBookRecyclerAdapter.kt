package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R
import fragments.MyBookDialogFragment

class MyBookRecyclerAdapter(val data:List<Books>,Id:Int,val updateCallback: () -> Unit) : RecyclerView.Adapter<MyBookRecyclerAdapter.ViewHolder>() {
        var Id = Id
    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item){
            var name: TextView = item.findViewById(R.id.book_name_my_books)
            var desc: TextView = item.findViewById(R.id.description_my_books)
            var autor: TextView = item.findViewById(R.id.Autor_mybooks)

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
        holder.autor.text = items.autor
        holder.itemView.setOnClickListener {
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val myBookDialogFragment = MyBookDialogFragment(Id,items.idKnjige.toInt(),items.naziv_knjige,items.Description,items.autor,updateCallback)
            myBookDialogFragment.show(fragmentManager, "MyBookDialogFragment")
        }

    }

}