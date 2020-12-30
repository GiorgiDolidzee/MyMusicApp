package com.example.mymusicapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mymusicapp.EminemActivity
import com.example.mymusicapp.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var navController: NavController
    private lateinit var eminemImageButton: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        navController = Navigation.findNavController(view)

        eminemImageButton = view.findViewById(R.id.eminemImageButton)

        eminemImageButton.setOnClickListener {
            val intent = Intent (activity, EminemActivity::class.java)
            activity?.startActivity(intent)
        }

    }
}