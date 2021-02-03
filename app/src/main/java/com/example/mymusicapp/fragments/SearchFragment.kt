package com.example.mymusicapp.fragments

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mymusicapp.MusicPlayerActivity
import com.example.mymusicapp.R

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var gangstaparadise: ImageButton
    private lateinit var killer: ImageButton
    private lateinit var californialove: ImageButton
    private lateinit var stilldre: ImageButton
    private lateinit var farshevangi: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        gangstaparadise = view.findViewById(R.id.gangstaparadise)
        killer = view.findViewById(R.id.killer)
        californialove = view.findViewById(R.id.californialove)
        stilldre = view.findViewById(R.id.stilldre)

        var musicname = ""

        gangstaparadise.setOnClickListener {
            musicname = "gangstaparadise"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        killer.setOnClickListener {
            musicname = "killer"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        californialove.setOnClickListener {
            musicname = "californialove"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        stilldre.setOnClickListener {
            musicname = "stilldre"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }

        farshevangi = view.findViewById(R.id.farshevangisearch)
        farshevangi.setOnClickListener {
            musicname = "farshevangi"
            val myActivity = (Intent(activity, MusicPlayerActivity::class.java))
            myActivity.putExtra("value", musicname)
            activity?.startActivity(myActivity)
        }



    }
}