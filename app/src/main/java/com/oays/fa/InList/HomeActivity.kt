package com.oays.fa.InList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.oays.fa.InList.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.coroutines.Continuation

class HomeActivity : AppCompatActivity() {

    lateinit var mBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


          gotoListsBtn.setOnClickListener {
              startActivity(Intent(this,MainActivity::class.java))
          }







        LogOut_btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,LoginActivity::class.java))

        }

        mBtn = findViewById(R.id.lng_Btn)

        mBtn.setOnClickListener {

            showChangeLang()

        }

    }

    private fun showChangeLang() {
        val listItmes = arrayOf("English", "Française", "Deutsch","عربي","Türkçe")
        val mBuilder = AlertDialog.Builder(this@HomeActivity)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->

            if (which == 0) {
                setLocate("en")
                recreate()
            } else if (which == 1) {
                setLocate("fr")
                recreate()
            }    else if (which == 2) {
            setLocate("de")
            recreate()
            }   else if (which == 3) {
                setLocate("ar")
                recreate()
            } else if (which == 4) {
                setLocate("tr")
                recreate()
            }
            dialog.dismiss()

        }
        val mDialog = mBuilder.create()

        mDialog.show()

    }
    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language)
    }


}
