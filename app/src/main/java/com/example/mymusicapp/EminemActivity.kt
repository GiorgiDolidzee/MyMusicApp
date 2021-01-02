package com.example.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EminemActivity : AppCompatActivity() {

    private lateinit var backEminem: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eminem)

        supportActionBar?.hide()

        backEminem = findViewById(R.id.backFromEminem)
        backEminem.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }


    }
}