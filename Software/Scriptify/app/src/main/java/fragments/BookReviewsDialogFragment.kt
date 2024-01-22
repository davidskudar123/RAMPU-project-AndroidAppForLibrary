package fragments

import adapters.MyBookRecyclerAdapter
import adapters.MyBookReviewsAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Books
import blueprints.Reviews
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class BookReviewsDialogFragment(Id:Int) : DialogFragment(R.layout.book_reviews_dialog) {
    var idBook = Id
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_reviews_dialog, container, false)
        val rv_reviews: RecyclerView = view.findViewById(R.id.rv_reviews_items)
        rv_reviews.layoutManager = LinearLayoutManager(requireContext())
        loadView(idBook, rv_reviews)
        return view
    }
    public fun loadView(Id:Int,recyclerView: RecyclerView) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
            try {
                val jsonConverter: JsonConverter = JsonConverter()
                val httpRequestManager: HttpRequestManager = HttpRequestManager()
                var data = httpRequestManager.getReviewsForBook(Id)

                launch(Dispatchers.Main) {
                    val reviews: List<Reviews>? = jsonConverter.JsonToReviewConverter(data)
                    //kreiramo callback funkciju u adapteru koju ćemo proslijediti i u dialog fragment koji će onda invokat ju po potrebi
                    val adapter = MyBookReviewsAdapter(reviews!!,idBook)
                    recyclerView.adapter = adapter

                }
            }catch (err: IOException){
                Toast.makeText(requireContext(),"Something went wrong", Toast.LENGTH_LONG)
            }


        }


    }
}
