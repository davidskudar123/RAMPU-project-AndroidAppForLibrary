package fragments

import adapters.MyBookReviewsAdapter
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blueprints.Reviews
import blueprints.User
import com.example.scriptify.hr.R
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class AddReviewDialogFragment(IdBook: Int,Iduser:Int,val callback: ()->Unit) : DialogFragment(R.layout.add_review_dialog) {
    var IDBook = IdBook
    var IDUser = Iduser
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_review_dialog, container, false)
        var numberPicker: NumberPicker = view.findViewById(R.id.num_picker_review)
        var autor : EditText = view.findViewById(R.id.add_review_dialog_autor)
        var review_text : EditText = view.findViewById(R.id.add_review_dialog_desc)
        var add: Button = view.findViewById(R.id.add_button_reviews)

        numberPicker.minValue = 1
        numberPicker.maxValue = 10
        loadView(IDBook,IDUser,autor)
        add.setOnClickListener {
            var rating = numberPicker.value
            addReview(IDBook,rating,review_text.text.toString(),autor.text.toString())
            callback.invoke()
            dismiss()
        }
        return view
    }
    public fun loadView(Id_book:Int, id_user:Int,autor: EditText) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
            try {
                val jsonConverter: JsonConverter = JsonConverter()
                val httpRequestManager: HttpRequestManager = HttpRequestManager()
                var data = httpRequestManager.getSpecificUserData(id_user)
                var user : List<User>? = jsonConverter.JsonToUserConverter(data)

                launch(Dispatchers.Main) {
                    if (user != null && !user.isNullOrEmpty() && autor != null) {
                        // Assuming user[0].name is the property you want to set in the autor EditText
                        autor.setText(user[0].first_name ?: "Default Text")
                    }


                }
            }catch (err: IOException){
                Toast.makeText(requireContext(),"Something went wrong", Toast.LENGTH_LONG)
            }


        }


    }
    public fun addReview(Id_book: Int,rating:Int,review:String,korisnik: String){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val jsonConverter: JsonConverter = JsonConverter()
            val httpRequestManager: HttpRequestManager = HttpRequestManager()
            var review: Reviews = Reviews(idKnjige = Id_book, review_text = review, rating = rating, korisnik_ime = korisnik, idReview = 0)
            var data = jsonConverter.AddingReviewsJsonConverter(review)
            var success : Boolean = httpRequestManager.addReview(data)

            launch(Dispatchers.Main){
                if(success){

                }
            }


        }
    }
}