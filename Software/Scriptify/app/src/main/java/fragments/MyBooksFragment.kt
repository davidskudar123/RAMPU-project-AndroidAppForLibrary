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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyBooksFragment : Fragment(R.layout.my_books_fragment) {

    lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.my_books_fragment, container, false)
        fab = view.findViewById(R.id.floatingActionButton)

        fab.setOnClickListener {
            // Use lifecycleScope to launch a coroutine within the fragment's lifecycle
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val httpRequestManager = HttpRequestManager()
                    val data = httpRequestManager.getUserData()

                    launch(Dispatchers.Main) {
                        Toast.makeText(requireContext(), data, Toast.LENGTH_LONG).show()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    // Handle the exception, show an error message, etc.
                }
            }
        }

        return view
    }
}

