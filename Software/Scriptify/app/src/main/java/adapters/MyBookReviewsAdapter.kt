package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import blueprints.Reviews
import com.example.scriptify.hr.R
import fragments.MyBookDialogFragment

class MyBookReviewsAdapter(val data: List<Reviews>,Id:Int): RecyclerView.Adapter<MyBookReviewsAdapter.ViewHolder>() {
    var Id = Id
    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var name: TextView = item.findViewById(R.id.book_name_reviews)
        var desc: TextView = item.findViewById(R.id.description_reviews)
        var ocjena: TextView = item.findViewById(R.id.Autor_reviews)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_review,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = data[position]
        holder.name.text = items.idKnjige.toString()
        holder.desc.text = items.review_text
        holder.ocjena.text = items.rating.toString()
        holder.itemView.setOnClickListener {

        }

    }

}