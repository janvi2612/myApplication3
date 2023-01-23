package com.example.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var bck: ImageView
    private lateinit var eemail: TextInputEditText
    private lateinit var epwd: TextInputEditText
    private lateinit var login: Button
    private lateinit var noaccount: Button
    private lateinit var progressDialog: ProgressDialog
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var helper = SqlHelper(applicationContext, null)
        db = helper.readableDatabase
        bck = findViewById(R.id.backbtn)
        eemail = findViewById(R.id.email1)
        epwd = findViewById(R.id.pwd1)
        login = findViewById(R.id.loginbtn)
        noaccount = findViewById(R.id.noaccountlgn)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)
        bck.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        noaccount.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
        login.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        var p = Patterns.EMAIL_ADDRESS
        if (TextUtils.isEmpty(eemail.text.toString()))
        {
            eemail.setError("Enter email")
            eemail.requestFocus()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(eemail.text.toString()).matches()){

            eemail.setError("Enter email in proper format")
            eemail.requestFocus()
        }
        else if(TextUtils.isEmpty(epwd.text.toString()))
        {
            epwd.setError("Enter the password")
            epwd.requestFocus()
        }


        else {
            var args = listOf<String>(eemail.text.toString(),epwd.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT * FROM Abcd WHERE email = ? AND password =?",args)
            if(rs.moveToNext())
            {
                var preferences:SharedPreferences=getSharedPreferences("mypre", Context.MODE_PRIVATE)
                var editor:SharedPreferences.Editor=preferences.edit()
                editor.putString("email",eemail.text.toString())
                editor.apply()
                editor.commit()
                Toast.makeText(applicationContext, "Valid Credentials", Toast.LENGTH_LONG).show()

                var intent =  Intent(applicationContext,DashBoard::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_LONG).show()

            }
        }


    }
}
















