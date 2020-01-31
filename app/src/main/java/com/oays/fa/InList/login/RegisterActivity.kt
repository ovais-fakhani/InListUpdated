package com.oays.fa.InList.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.oays.fa.InList.HomeActivity
import com.oays.fa.InList.MainActivity
import com.oays.fa.InList.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        btn_login_h.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        btn_register.setOnClickListener {
              signUpUser()


        }
    }

   private fun signUpUser() {
       if (email_r.text.toString().isEmpty()) {
           email_r.error = getString(R.string.please_enter_Email)
           email_r.requestFocus()
           return
       }
       
       if (password_r.text.toString().isEmpty()) {
           password_r.error = getString(R.string.please_enter_password)
           password_r.requestFocus()
           return
       }

       auth.createUserWithEmailAndPassword(email_r.text.toString(), password_r.text.toString())
               .addOnCompleteListener(this) { task ->
                   if (task.isSuccessful) {
                       startActivity(Intent(this, HomeActivity::class.java))
                       finish()
                   } else {
                       // If sign in fails, display a message to the user.

                       Toast.makeText(baseContext, getString(R.string.Sign_up_failed),
                               Toast.LENGTH_SHORT).show()

                   }


               }


   }
}
