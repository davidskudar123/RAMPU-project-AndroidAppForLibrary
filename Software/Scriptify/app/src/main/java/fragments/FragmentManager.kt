package fragments

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.scriptify.hr.R

class FragmentManager: AppCompatActivity() {

    public fun setCurrentFragment(fragment: Fragment){
        val supportFragmentManager =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flLayout,fragment)
            commit()
        }
    }

}