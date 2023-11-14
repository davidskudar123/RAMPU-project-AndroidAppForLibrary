package com.example.scriptify.hr

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import blueprints.User
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        username = findViewById(R.id.Username_login)
        password = findViewById(R.id.Password_login)
        loginButton = findViewById(R.id.LogIn)

        loginButton.setOnClickListener {
            getUserData(username.text.toString(),password.text.toString())
        }
    }
    public fun getUserData(username:String, Password: String){
        lifecycleScope.launch(Dispatchers.Default){
            try {
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                val jsonConverter = JsonConverter()
                val httpRequestManager = HttpRequestManager()
                val data = httpRequestManager.getUserData()

                launch(Dispatchers.Main) {
                    try{
                        val userlist: List<User>? = jsonConverter.JsonToUserListConverter(data)
                        if(userlist != null){
                        for (user in userlist.indices) {
                            if (userlist[user].username == username && userlist[user].password == Password) {

                                intent.putExtra("id", userlist[user].id_user.toString())
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Welcome ${userlist[user].username}",
                                    Toast.LENGTH_LONG
                                ).show()
                                startActivity(intent)
                                finish()
                            } else {

                            }
                        }
                    }else{
                            Toast.makeText(this@LoginActivity,"Something went wrong",Toast.LENGTH_LONG).show()
                        }

                    }catch (e:IOException){
                        e.printStackTrace()
                        Toast.makeText(this@LoginActivity,"Something went wrong",Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this@LoginActivity,"Something went wrong",Toast.LENGTH_LONG).show()
            }
        }
    }
}