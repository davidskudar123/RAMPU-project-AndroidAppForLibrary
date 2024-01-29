package adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import blueprints.ReceivedBook
import blueprints.User
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import fragments.MyBookDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyReceivedBookRecyclerAdapter(val data:List<ReceivedBook>,Id:Int,val updateCallback: () -> Unit) : RecyclerView.Adapter<MyReceivedBookRecyclerAdapter.ViewHolder>() {
    var Id = Id
    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        var name: TextView = item.findViewById(R.id.book_name_my_received_books)
        var desc: TextView = item.findViewById(R.id.description_my_received_books)
        var autor: TextView = item.findViewById(R.id.Autor_myreceivedbooks)
        var status: TextView = item.findViewById(R.id.status_my_received_books)
        var btnReceived: Button = item.findViewById(R.id.btn_Received)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_received_book_item,parent,false)
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
        holder.status.text = items.status
        holder.btnReceived.setOnClickListener{


        }

    }

}