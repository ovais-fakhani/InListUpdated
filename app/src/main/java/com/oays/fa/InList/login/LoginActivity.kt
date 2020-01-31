package com.oays.fa.InList.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.oays.fa.InList.HomeActivity
import com.oays.fa.InList.MainActivity
import com.oays.fa.InList.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        btn_sign_ub.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

         btn_login.setOnClickListener {
             doLogin()
         }

    }

    private fun doLogin() {

        if (email_n.text.toString().isEmpty()){
            email_n.error = getString(R.string.please_enter_Email)
            email_n.requestFocus()
            return
        }

        if (password_n.text.toString().isEmpty()){
            password_n.error = getString(R.string.please_enter_password)
            password_n.requestFocus()
            return
        }


        auth.signInWithEmailAndPassword(email_n.text.toString(), password_n.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails,

                        updateUI(null)
                    }

                }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

     private fun updateUI (currentUser : FirebaseUser?){
        if (currentUser!= null){
            startActivity(Intent(this,HomeActivity::class.java))
        }
         else {
            Toast.makeText(baseContext, getString(R.string.Login_failed),
                    Toast.LENGTH_SHORT).show()
        }
    }
}
