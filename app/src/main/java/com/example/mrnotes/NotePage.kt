package com.example.mrnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrnotes.RecycleView.recycleAdapter
import com.example.mrnotes.RoomData.NoteDataBase
import com.example.mrnotes.databinding.ActivityNotePageBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class NotePage : AppCompatActivity() {
    private lateinit var binding: ActivityNotePageBinding
    private lateinit var adapter: recycleAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityNotePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id: String? = intent.getStringExtra("id")
        var user = FirebaseAuth.getInstance().currentUser
        var email = user?.email
        binding.user.setText("$email")


        binding.addbtn.setOnClickListener {

            var intent = Intent(this, AddNotesPage::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }

        binding.Logout.setOnClickListener {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@NotePage, Login_Page::class.java))
            finish()
        }

        binding.rec.layoutManager = LinearLayoutManager(this)
        fetchdata(id!!)

    }


    override fun onResume() {
        super.onResume()

        //Firebase current user id
        var user = FirebaseAuth.getInstance().currentUser?.uid
        //method to fetch the data....
        fetchdata(user!!)


    }

    fun fetchdata(id: String) {
        var db = NoteDataBase.getInstances(applicationContext)
        var notedao = db!!.NoteDao()

        GlobalScope.launch {
             var list = notedao.getAll(id!!)

            Log.d("mukesh", "$list")


            //
            withContext(Dispatchers.Main)
            {
                if(!list.isEmpty()) {
                    binding.result.visibility= View.GONE
                    binding.rec.visibility=View.VISIBLE
                    var adapter1 = recycleAdapter(list)
                    Log.d("mukeshad", "$adapter1")
                    binding.rec.adapter = adapter1
                }else
                {

                    binding.rec.visibility=View.GONE
                    binding.result.visibility=View.VISIBLE
                    Log.d("mukesh","no")
                   // binding.result.setText("Hey Make Your Notes")
                }

            }



        }

    }

}