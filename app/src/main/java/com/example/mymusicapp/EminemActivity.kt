package com.example.mymusicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EminemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eminem)

        supportActionBar?.hide()
    }
}