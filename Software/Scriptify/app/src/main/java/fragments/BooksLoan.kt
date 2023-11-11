package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scriptify.hr.R

// Za implementaciju potrebno je dodati Book blueprint, dodati konekcije na server u HttpRequestManageru, i onda u JsonConverteru da ih možemo loadati, uz to i recyclerview
//VRLO BITNO- Pošto radimo sa http requestovima potrebno je koristiti courutines, u ostalim fragmentima može se pronaći implementacija koja se može iskopirati i doraditi po potrebi
class BooksLoan(id:Int): Fragment(R.layout.book_loan_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_loan_fragment,container,false)

        return view
    }
}