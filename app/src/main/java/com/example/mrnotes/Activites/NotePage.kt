package com.example.mrnotes.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mrnotes.RecycleView.recycleAdapter
import com.example.mrnotes.RoomData.NoteDataBase
import com.example.mrnotes.ViewModel.NotesViewModel
import com.example.mrnotes.databinding.ActivityNotePageBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class NotePage : AppCompatActivity() {


     //intilaizing
    private lateinit var binding: ActivityNotePageBinding
    private lateinit var adapter: recycleAdapter
    private lateinit var notesviewModel:NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityNotePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Displaying the Email id
        var user = FirebaseAuth.getInstance().currentUser
        var email = user?.email
        binding.user.setText("$email")
        var uid=FirebaseAuth.getInstance().currentUser?.uid

       /// notes adding function
        binding.addbtn.setOnClickListener {

            var intent = Intent(this, AddNotesPage::class.java)
            startActivity(intent)
        }


        //logout button
        binding.Logout.setOnClickListener {

            val alert = AlertDialog.Builder(this)
                .setTitle("Log out")
                .setMessage("Do you want to Logout ?")
                .setPositiveButton("Yes")
                { p0, p1 ->
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this@NotePage, Login_Page::class.java))
                    finish()
                }
                .setNegativeButton("No",null)
                .show()
        }

        binding.rec.layoutManager = LinearLayoutManager(this)

        notesviewModel=ViewModelProvider(this).get(NotesViewModel::class.java)


        notesviewModel.getAll(binding.rec,uid!!).observe(this, Observer { notesList ->
            if (notesList.isNullOrEmpty()) {
                // If the list is empty, show a message
                binding.result.visibility = View.VISIBLE
                binding.result.text = "There are no notes available"
                binding.rec.visibility = View.GONE
            } else {
                // If there are notes, hide the message and show the RecyclerView
                binding.result.visibility = View.GONE
                binding.rec.visibility = View.VISIBLE

                // Create and set the adapter
                var list = recycleAdapter(notesList, notesviewModel)
                Log.d("mukesh11", "$list")
                binding.rec.adapter = list
            }
        })





    }
}