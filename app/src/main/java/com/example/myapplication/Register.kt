package com.example.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class Register : AppCompatActivity() {
    lateinit var db : SQLiteDatabase
    lateinit var rs : Cursor
    lateinit var register : Button
    lateinit var name : TextInputEditText
    lateinit var email : TextInputEditText
    lateinit var pwd : TextInputEditText
    lateinit var cpwd : TextInputEditText
    lateinit var bck: ImageView

    lateinit var progressDialog: ProgressDialog
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        bck = findViewById(R.id.backbtn1)
        name = findViewById(R.id.nme)
        email = findViewById(R.id.email)
        pwd = findViewById(R.id.pwd2)
        cpwd = findViewById(R.id.cpwd2)
        register = findViewById(R.id.register)
        bck.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        val helper = SqlHelper(applicationContext, null)
        db = helper.readableDatabase
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)
        register.setOnClickListener {
            submit()
        }


    }




    private fun submit() {


        var p = Patterns.EMAIL_ADDRESS



        if (TextUtils.isEmpty(name.text.toString())){
            name.setError("Name field is Empty")
            name.requestFocus()

        }
        else if (TextUtils.isEmpty(email.text.toString()))
        {
            email.setError("Email field is Empty")
            email.requestFocus()
        }


        else if (TextUtils.isEmpty(pwd.text.toString()))
        {
            pwd.setError("Password  field is Empty")
            pwd.requestFocus()
        }

        else if (pwd.length() < 8)
        {
            pwd.setError("Password Must be in 8 or more char")
            pwd.requestFocus()
        }
        else if (TextUtils.isEmpty(cpwd.text.toString()))
        {
            cpwd.setError("Confirm Password  field is Empty")
            cpwd.requestFocus()
        }
        else if (cpwd.text.toString().trim() != cpwd.text.toString().trim())
        {
            cpwd.setError("Please provide a same password")
            cpwd.requestFocus()
        }
        else{
            var cv= ContentValues()
            cv.put("Name",name.text.toString())
            cv.put("Email",email.text.toString())
            cv.put("Password",pwd.text.toString())
            db.insert("Abcd",null,cv)
            rs.requery()
            var intent  = Intent(applicationContext,Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}




