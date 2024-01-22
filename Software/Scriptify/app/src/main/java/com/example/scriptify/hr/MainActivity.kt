package com.example.scriptify.hr

import BooksLoan
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.BookExchangeFragment
import fragments.MyBooksFragment
import fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomAppBar: BottomNavigationView
    @SuppressLint("MissingInflatedId")
    //Bottom app bar je napravljen ovaj file ne dirati
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val id: Int = intent.getStringExtra("id")!!.toInt()
        val profileFragment : ProfileFragment = ProfileFragment(id)
        val myBooksFragment: MyBooksFragment = MyBooksFragment(id)
        val bookExchangeFragment : BookExchangeFragment = BookExchangeFragment(id)
        val bookLoanFramgent: BooksLoan = BooksLoan(id)
        bottomAppBar = findViewById(R.id.bottom_navigation)
        bottomAppBar.selectedItemId = R.id.book_exchange
        setCurrentFragment(myBooksFragment)

        bottomAppBar.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.profile -> { setCurrentFragment(profileFragment)
                true}
                R.id.my_books -> { setCurrentFragment(myBooksFragment)
                true}
                R.id.book_exchange -> { setCurrentFragment(bookExchangeFragment)
                    true}
                R.id.book_loan -> { setCurrentFragment(bookLoanFramgent)
                    true}
                else -> false
            }

        }



    }
    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()




        // Replace the fragment
        transaction.replace(R.id.flLayout, fragment)

        // Commit the transaction
        transaction.commit()
    }

}