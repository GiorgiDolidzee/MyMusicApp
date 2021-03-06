package com.example.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputConfirmPassword: EditText
    private lateinit var registerButton: TextView
    private lateinit var loginInstead: TextView

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        inputEmail = findViewById(R.id.signUpEmailEditText)
        inputPassword = findViewById(R.id.signUpPasswordEditText)
        inputConfirmPassword = findViewById(R.id.signUpConfirmPasswordEditText)
        registerButton = findViewById(R.id.signUpButton)

        registerButton.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val confirmPassowrd = inputConfirmPassword.text.toString()

            if (email.isEmpty() && password.isEmpty() && confirmPassowrd.isEmpty() && (password != confirmPassowrd)) {
                Toast.makeText(this, "შეიყვანეთ ყველა ველი სწორად !", Toast.LENGTH_LONG).show()
            } else {
                mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val builder = AlertDialog.Builder(this)
                            builder.setTitle("! ! !")
                            builder.setMessage("თქვენი პერსონალური ინფორმაცია შენახულია და დაცულია ბაზაში")
                            builder.setPositiveButton("გასაგებია") { dialog, i ->
                                dialog.dismiss()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                            builder.show().setCanceledOnTouchOutside(false)
                        } else {
                            Toast.makeText(this, "შეამოწმეთ ყველა ველი", Toast.LENGTH_LONG).show()
                        }
                    }
            }

        }

        loginInstead = findViewById(R.id.loginInstead)
        loginInstead.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}