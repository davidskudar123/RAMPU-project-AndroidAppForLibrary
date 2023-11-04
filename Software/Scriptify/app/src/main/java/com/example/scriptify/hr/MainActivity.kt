package com.example.scriptify.hr

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.BookExchangeFragment
import fragments.BooksLoan
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
        val bookExchangeFragment : BookExchangeFragment = BookExchangeFragment()
        val bookLoanFramgent: BooksLoan = BooksLoan()
        bottomAppBar = findViewById(R.id.bottom_navigation)
        bottomAppBar.selectedItemId = R.id.book_exchange
        setCurrentFragment(bookExchangeFragment)

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