package com.example.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class TwopacActivity : AppCompatActivity() {

    private lateinit var backTwopac: ImageView
    private lateinit var moreInfoTwopac: ImageButton
    private lateinit var backtwopactext: TextView

    private lateinit var alleyezonme: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twopac)
        supportActionBar?.hide()

        backTwopac = findViewById(R.id.backFromTwopac)
        backTwopac.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        backtwopactext = findViewById(R.id.backFromTwopacText)
        backtwopactext.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        moreInfoTwopac = findViewById(R.id.twopacMoreInfo)
        moreInfoTwopac.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Tupac Amaru Shakur")
            builder.setMessage("2Pac-ის ნამდვილი სახელია Tupac Amaru Shakur, რომელიც დაიბადა 1971 წლის 16 ივნისს. ამერიკელი რეპერი, მსახიობი, აქტივისტი და მწერალი. \n \n 2007 წლისთვის გაიყიდა მისი 75 მილიონზე მეტი ალბომი, რითაც ტუპაკი იქცა ყველა დროის ერთ-ერთ გაყიდვად შემსრულებლად")
            builder.setPositiveButton("გასაგებია") { dialog, i ->
                dialog.dismiss()
            }
            builder.show()
        }

        var musicname = ""

        alleyezonme = findViewById(R.id.twopacAlleyezonme)
        alleyezonme.setOnClickListener {
            musicname = "alleyezonme"
            val myActivity = (Intent(this, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            this.startActivity(myActivity)
        }

    }
}