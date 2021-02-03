package com.example.mymusicapp.fragments

import android.content.Intent
import android.os.Bundle
import android.renderscript.Sampler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mymusicapp.MainActivity
import com.example.mymusicapp.Personinfo
import com.example.mymusicapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var inputName: EditText
    private lateinit var inputImageUrl: EditText
    private lateinit var saveButton: ImageButton
    private lateinit var nameTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var logoutButton: ImageButton

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")

        inputName = view.findViewById(R.id.nameEditText)
        inputImageUrl = view.findViewById(R.id.imageUrlEditText)
        saveButton = view.findViewById(R.id.saveButton)
        nameTextView = view.findViewById(R.id.nameTextView)
        imageView = view.findViewById(R.id.profileView)
        logoutButton = view.findViewById(R.id.logoutButton)

        logoutButton.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(activity, MainActivity::class.java))
        }

        saveButton.setOnClickListener {
            val name = inputName.text.toString()
            val url = inputImageUrl.text.toString()

            val p = Personinfo(name, url)

            if (mAuth.currentUser?.uid != null) {
                db.child(mAuth.currentUser?.uid!!).setValue(p).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "წარმატებით შეინახა", Toast.LENGTH_SHORT).show()
                        inputName.text = null
                        inputImageUrl.text = null
                    } else {
                        Toast.makeText(activity, "დაფიქსირდა შეცდომა", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        if (mAuth.currentUser?.uid != null) {
            db.child(mAuth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "დაფიქსირდა შეცდომა", Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (getActivity() == null) {
                        return
                    }

                    val p = snapshot.getValue(Personinfo::class.java)
                    if (p != null) {
                        // Name
                        nameTextView.text = p.name

                        // Profile Photo
                        Glide.with(this@ProfileFragment)
                                .load(p.imageUrl)
                                .centerCrop()
                                .placeholder(R.drawable.profilephotoframe)
                                .into(imageView)

                    }
                }

            })

        }



    }
}