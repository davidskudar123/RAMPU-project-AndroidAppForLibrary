package fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scriptify.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import connectors.HttpRequestManager
import java.io.IOException
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import blueprints.User
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
class MyBooksFragment : Fragment(R.layout.my_books_fragment) {

    lateinit var fab: FloatingActionButton
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.my_books_fragment, container, false)
        fab = view.findViewById(R.id.floatingActionButton)

        fab.setOnClickListener {
           getUserData()
        }

        return view
    }
    public fun getUserData(){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default){
            try {
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getUserData()

                launch(Dispatchers.Main) {
                    val userlist: List<User> = jsonConverter.JsonToUserListConverter(data)
                    var FirstName = "john_doe"
                    for (user in userlist.indices){
                        if(userlist[user].username == FirstName){
                            Toast.makeText(requireContext(),userlist[user].username,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle the exception, show an error message, etc.
            }
        }
    }
}

