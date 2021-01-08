package com.example.mymusicapp.fragments

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mymusicapp.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var navController: NavController

    private lateinit var eminemImageButton: ImageButton
    private lateinit var twopacImageButton: ImageButton

    private lateinit var loseYourSelf: ImageButton
    private lateinit var taste: ImageButton
    private lateinit var dropwtheworld: ImageButton
    private lateinit var alleyezonme: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        navController = Navigation.findNavController(view)

        var musicname = ""

        // • TOP Artist List •
        eminemImageButton = view.findViewById(R.id.eminemImageButton)
        eminemImageButton.setOnClickListener {
            startActivity(Intent(activity, EminemActivity::class.java))
        }

        twopacImageButton = view.findViewById(R.id.twopacImageButton)
        twopacImageButton.setOnClickListener {
            startActivity(Intent(activity, EminemActivity::class.java))
        }


        // • Music List •
        loseYourSelf = view.findViewById(R.id.eminemLoveYourself)
        loseYourSelf.setOnClickListener {
            musicname = "loseyourself"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        taste = view.findViewById(R.id.tygaTaste)
        taste.setOnClickListener {
            musicname = "taste"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        dropwtheworld = view.findViewById(R.id.lilwayneDropTheWorld)
        dropwtheworld.setOnClickListener {
            musicname = "droptheworld"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        alleyezonme = view.findViewById(R.id.twopacAllEyez)
        alleyezonme.setOnClickListener {
            musicname = "alleyezonme"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

    }

}