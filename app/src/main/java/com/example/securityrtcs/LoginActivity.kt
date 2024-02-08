package com.example.securityrtcs

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern.compile


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var email: TextView
    lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email_txt)
        password = findViewById(R.id.password_txt)
        auth = FirebaseAuth.getInstance()

        // Проверка на авторизованного пользователя
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this , BottomNavActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }

    fun next_activity(view: View) {
        if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
        {
            if(EmailValid(email.text.toString()))
            {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    })
            }
            else
            {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("У вас неправильный e-mail")
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }
        }
        else
        {
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("У вас есть незаполненные поля")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }

    }

    val pattern = ("[a-z0-9]{1,50}"+
            "\\@"+
            "[a-z]{1,10}" +
            "\\." +
            "[a-z]{1,3}")

    fun EmailValid(email: String):Boolean{
        return compile (pattern).matcher(email).matches()
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            val intent = Intent(this@LoginActivity, BottomNavActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this@LoginActivity, "Something Error", Toast.LENGTH_SHORT).show()
        }
    }
}