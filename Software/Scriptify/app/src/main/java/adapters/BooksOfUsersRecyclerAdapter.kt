package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import com.example.scriptify.hr.R
import fragments.BooksOfUsersDialogFragment
import fragments.ExchangeBookFragment
import fragments.MyBookDialogFragment

class BooksOfUsersRecyclerAdapter(val data:List<Books>, Id:Int, val updateCallback: () -> Unit) : RecyclerView.Adapter<BooksOfUsersRecyclerAdapter.ViewHolder>() {
    var Id = Id
    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var name: TextView = item.findViewById(R.id.book_name_books_of_users)
        var desc: TextView = item.findViewById(R.id.description_books_of_users)
        var autor: TextView = item.findViewById(R.id.Autor_books_of_users)
        var btnBuy: Button = item.findViewById(R.id.btn_buy)
        var btnExchange: Button = item.findViewById(R.id.btn_exchange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_of_users_item,parent,false)
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

        holder.btnBuy.setOnClickListener {
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val booksOfUsersDialogFragment = BooksOfUsersDialogFragment(
                Id,
                items.idKnjige.toInt(),
                items.naziv_knjige,
                items.Description,
                items.autor,
                items.cijena_knjige,
                updateCallback
            )
            booksOfUsersDialogFragment.show(fragmentManager, "BooksOfUsersDialogFragment")
        }

        holder.btnExchange.setOnClickListener{
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val exchangeBookFragment = ExchangeBookFragment(
                Id,
                items.idKnjige.toInt(),
                updateCallback
            )
            exchangeBookFragment.show(fragmentManager, "ExchangeBookFragment")
        }

    }

}