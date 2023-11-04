package com.example.mrnotes.Activites

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrnotes.RecycleView.recycleAdapter
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.RoomData.NoteDataBase
import com.example.mrnotes.ViewModel.NotesViewModel
import com.example.mrnotes.databinding.ActivityNotePageBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class NotePage : AppCompatActivity() , recycleAdapter.MulipleData {


    //intilaizing
    private lateinit var binding: ActivityNotePageBinding
    private var adapter: recycleAdapter? = null
    private lateinit var notesviewModel: NotesViewModel
    lateinit var Userid: List<NoteApp>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Displaying the Email id
        var user = FirebaseAuth.getInstance().currentUser
        var email = user?.email
        binding.user.setText("$email")
        var uid = FirebaseAuth.getInstance().currentUser?.uid

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
                .setNegativeButton("No", null)
                .show()
        }

        binding.rec.layoutManager = LinearLayoutManager(this)

        notesviewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        notesviewModel.getAll(binding.rec, uid!!).observe(this, Observer { notesList ->
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
                var list = recycleAdapter(notesList, notesviewModel, this@NotePage)

//                list.getSelectedItems().observe(this, Observer {
//
//
//                    Log.d("mukeshselcteditems","$it")
//
//                         val distinctSelectedItems = it.toSet().toList()
//                         items.addAll(distinctSelectedItems.filter { !items.contains(it) ||items1.contains(it)})
//                    Log.d("mukesh list","$items")
//
//                })
//                var items1= mutableListOf<Int>()
//                binding.Mutlidelete.setOnClickListener {
//                    Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
//                    notesviewModel.deletebyIds(applicationContext,items)
//                    items1.addAll(items)
//                    items.clear()
//                    Log.d("mukesh","$items")
//                }
//                Log.d("mukeshafterclerar","$items")


                binding.rec.adapter = list
            }
        })
    }

    override fun getselectedItems(mu: MutableList<Int>) {

        Log.d("mukeshinterface", "$mu")
        var Userid: List<NoteApp>
        var count=0
        var items = mutableListOf<Int>()
        if(!mu.isEmpty()|| !(mu.size==0))
        {
            binding.Mutlidelete.visibility=View.VISIBLE
        }
        else{
            binding.Mutlidelete.visibility=View.GONE
        }

        CoroutineScope(Dispatchers.IO).launch {
            // Inside the coroutine, you can access the context using the coroutineContext // Gets the CoroutineContext's Job element
            // Now you can use 'context' as your context variable
            Userid = NoteDataBase.getInstances(applicationContext)?.NoteDao()?.getAllByList()
                ?: emptyList()

            if (!mu.isEmpty()) {
                for (i in mu) {
                    if (i in 0 until Userid.size) {
                        Log.d("mukesh","$i")
                        items.add(Userid[i].id)
                        Log.d("mukeshidname","${Userid[i].Name}")
                    }
                }
                withContext(Dispatchers.Main) {

                    binding.Mutlidelete.setOnClickListener {
                        Toast.makeText(this@NotePage, "deleted", Toast.LENGTH_SHORT).show()
                        notesviewModel.deletebyIds(applicationContext, items)
                        count=1;
                        runOnUiThread {
                            binding.Mutlidelete.visibility = View.GONE
                        }
                        mu.clear()
                        adapter?.notifyDataSetChanged()
                    }
                    }
                        Log.d("mukesh", "$items")

                    //items.clear()
                }
            }

    }
}
