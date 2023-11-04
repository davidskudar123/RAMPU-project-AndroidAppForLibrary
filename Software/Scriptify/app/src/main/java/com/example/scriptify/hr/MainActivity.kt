package com.example.scriptify.hr

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.MyBooksFragment
import fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomAppBar: BottomNavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val profileFragment : ProfileFragment = ProfileFragment()
        val myBooksFragment: MyBooksFragment = MyBooksFragment()

        bottomAppBar = findViewById(R.id.bottom_navigation)
        setCurrentFragment(profileFragment)

        bottomAppBar.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.profile -> { setCurrentFragment(profileFragment)
                true}
                R.id.my_books -> { setCurrentFragment(myBooksFragment)
                    true}
                else -> false
            }

        }


    }
    public fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flLayout,fragment)
            commit()
        }

}