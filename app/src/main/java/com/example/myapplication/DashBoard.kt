package com.example.myapplication

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class DashBoard : AppCompatActivity() {

    lateinit var frameLayout: FrameLayout
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerlayout : DrawerLayout
    lateinit var naview : NavigationView
    lateinit var theader :TextView
    lateinit var theadere :TextView
    lateinit var db : SQLiteDatabase



    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        replaceFragment(Home())
      var helper = SqlHelper(this,null)
        db = helper.readableDatabase
       var preferences:SharedPreferences = getSharedPreferences("mypre", MODE_PRIVATE)
        var string = preferences.getString("email","").toString()
        naview = findViewById(R.id.nav_view)
        var a = naview.inflateHeaderView(R.layout.nsv_hrder)
        theader = a.findViewById(R.id.txtname)
        theadere = a.findViewById(R.id.txtemail)
        theader.text=string
        var args = listOf<String>(string).toTypedArray()
        var rs =db.rawQuery("SELECT name FROM Abcd WHERE email = ? LIMIT 1",args)
        rs.moveToNext()

        theader.text=rs.getString(rs.getColumnIndex("name"))


        drawerlayout = findViewById(R.id.drawerlayout)


        toggle = ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        naview.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId)
            {
                R.id.nav_payment-> changeFragment(PaymentFragment(),it.title.toString())
                R.id.nav_address-> changeFragment(AddressFragment(),it.title.toString())
                R.id.nav_password-> changeFragment(Passwordfragment(),it.title.toString())
                R.id.nav_household-> changeFragment(Householdfragment(),it.title.toString())

            }
            true
        }






        bottomNavigationView = findViewById(R.id.btmnavigationview)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(Home())
                R.id.vendor->replaceFragment(Vendor())
                R.id.list->replaceFragment(list())
                R.id.category->replaceFragment(Category())
                R.id.more->replaceFragment(More())

                else ->{

                }
            }
             true
        }

    }

     private fun changeFragment(fragment: Fragment,tile: String)
     {
         val fragmentManager = supportFragmentManager
         val fragmentTransaction = fragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.framelayout,fragment)
         fragmentTransaction.commit()
         drawerlayout.closeDrawers()
         setTitle(tile)
     }
 private fun replaceFragment(fragment: Fragment){
     val fragmentManager = supportFragmentManager
     val fragmentTransaction = fragmentManager.beginTransaction()
     fragmentTransaction.replace(R.id.framelayout,fragment)
     fragmentTransaction.commit()

        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }




    }


