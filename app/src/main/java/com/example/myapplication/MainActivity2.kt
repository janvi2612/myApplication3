package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    private lateinit var loginbtn: Button
    private lateinit var signupbtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        loginbtn = findViewById(R.id.signin)
        signupbtn = findViewById(R.id.registerbtn)


        loginbtn.setOnClickListener{
            val  intent = Intent(applicationContext,Login::class.java)
            startActivity(intent)
        }


        signupbtn.setOnClickListener{
            val intent = Intent(applicationContext,Register::class.java)
            startActivity(intent)
        }
    }
}