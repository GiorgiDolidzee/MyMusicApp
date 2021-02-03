package com.example.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class EminemActivity : AppCompatActivity() {

    private lateinit var backEminem: ImageView
    private lateinit var moreInfoEminem: ImageButton
    private lateinit var backeminemtext: TextView

    private lateinit var killer: ImageView
    private lateinit var loseyourself: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eminem)
        supportActionBar?.hide()

        backEminem = findViewById(R.id.backFromEminem)
        backEminem.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        backeminemtext = findViewById(R.id.backFromEminemText)
        backeminemtext.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        moreInfoEminem = findViewById(R.id.eminemMoreInfo)
        moreInfoEminem.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Marshall Bruce Mathers")
            builder.setMessage("Eminem-ის ნამდვილი სახელია Marshall Bruce Mathers, რომელიც დაიბადა 1972 წლის 17 ოქტომბერს. იგი არის ამერიკელი რეპერი, კომპოზიტორი და ჩამწერი პროდიუსერი. \n  \n ის ყველა დროის საუკეთესო რეპერად ითვლება, რომელმაც უამრავ წარმატებას მიაღწია.  \n" +
                    "  \n მას 10 ზე მეტი ალბომი აქვს გამოშვებული, რომლებიცაა: Infinite, The Slim Shady, The Marshall Mathers და სხვა")
            builder.setPositiveButton("გასაგებია") { dialog, i ->
                dialog.dismiss()
            }
            builder.show()
        }

        var musicname = ""

        killer = findViewById(R.id.eminemKiller)
        killer.setOnClickListener {
            musicname = "killer"
            val myActivity = (Intent(this, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            this.startActivity(myActivity)
        }

        loseyourself = findViewById(R.id.loseyourselfEminem)
        loseyourself.setOnClickListener {
            musicname = "loseyourself"
            val myActivity = (Intent(this, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            this.startActivity(myActivity)
        }

    }
}