package com.example.scriptify.hr

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import blueprints.User
import connectors.HttpRequestManager
import convertor.JsonConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var address: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var registerButton: Button

    private lateinit var httpRequestManager: HttpRequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        username = findViewById(R.id.Username_register)
        password = findViewById(R.id.Password_register)
        email = findViewById(R.id.Email_register)
        address = findViewById(R.id.Address_register)
        firstName = findViewById(R.id.FirstName_register)
        lastName = findViewById(R.id.LastName_register)
        registerButton = findViewById(R.id.Register)

        registerButton.setOnClickListener {
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()
            val enteredEmail = email.text.toString()
            val enteredAddress = address.text.toString()
            val enteredFirstName = firstName.text.toString()
            val enteredLastName = lastName.text.toString()

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredEmail.isEmpty() ||
                enteredAddress.isEmpty() || enteredFirstName.isEmpty() || enteredLastName.isEmpty()
            ) {
                Toast.makeText(
                    applicationContext,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                registerUser(
                    enteredUsername,
                    enteredPassword,
                    enteredEmail,
                    enteredAddress,
                    enteredFirstName,
                    enteredLastName
                )
            }
        }
    }

    private fun registerUser(
        username: String,
        password: String,
        email: String,
        address: String,
        firstName: String,
        lastName: String
    ) {
        lifecycleScope.launch(Dispatchers.Default) {
            try {
                val registrationSuccessful = httpRequestManager.registerUser(
                    username,
                    password,
                    email,
                    address,
                    firstName,
                    lastName
                )

                launch(Dispatchers.Main) {
                    if (registrationSuccessful) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration successful",
                            Toast.LENGTH_LONG
                        ).show()

                        // Navigate back to the LoginActivity
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish() //Close Register
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@RegisterActivity,
                    "Something went wrong",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
