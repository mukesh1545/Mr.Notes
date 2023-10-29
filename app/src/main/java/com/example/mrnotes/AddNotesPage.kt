package com.example.mrnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.RoomData.NoteDataBase
import com.example.mrnotes.databinding.ActivityAddNotesPageBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNotesPage : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var uid = FirebaseAuth.getInstance().currentUser!!.uid


        ///checking whether the intent is coming or not
        var Id = intent.getIntExtra("id123", -1)

        if (intent.hasExtra("id123")) {
            binding.AddNote.setText("Edit Note")
            var db = NoteDataBase.getInstances(this)
            var dao = db!!.NoteDao()

            GlobalScope.launch {
                var List = dao.getdetails(Id)
                Log.d("mukesh List", "${List}")
                withContext(Dispatchers.Main)
                {
                    var tittle = List
                    binding.TittleBar.setText("${List.Name}")
                    binding.contentBar.setText("${List.content}")
                }


                //when the user enter the or edit the text for updating the notes
                binding.saveButton.setOnClickListener {
                    var newTittle = binding.TittleBar.text.toString()
                    var newContent = binding.contentBar.text.toString()
                    if(newTittle .isEmpty() && newContent.isEmpty())
                    {
                        Toast.makeText(this@AddNotesPage,"Enter the valid fields",Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    var List = NoteApp(Id, uid, newTittle, newContent)
                    GlobalScope.launch(Dispatchers.IO)
                    {
                        dao.Update(List)

                        withContext(Dispatchers.Main)
                        {
                            Toast.makeText(this@AddNotesPage, "changes Saved", Toast.LENGTH_SHORT)
                                .show()
                            finish()

                        }


                    }


                }
            }
        }

        ///
        var Id1 = intent.getIntExtra("Delete", -1)

        if (intent.hasExtra("Delete")) {
            binding.saveButton.visibility = View.GONE
            binding.deletebtn1.visibility = View.VISIBLE


            binding.AddNote.setText("Delete Note")
            var db = NoteDataBase.getInstances(this)
            var dao = db!!.NoteDao()

            GlobalScope.launch {
                var List = dao.getdetails(Id1)
                Log.d("mukesh List", "${List}")
                withContext(Dispatchers.Main)
                {
                    var tittle = List
                    binding.TittleBar.setText("${List.Name}")
                    binding.contentBar.setText("${List.content}")
                }
                binding.deletebtn1.setOnClickListener {

                    GlobalScope.launch {
                        dao.Delete(Id1)

                        withContext(Dispatchers.Main)
                        {
                            Toast.makeText(this@AddNotesPage, "Deleted", Toast.LENGTH_SHORT).show()
                            finish()

                        }
                    }
                }
            }
        }


            ////createing the notes
            binding.saveButton.setOnClickListener {
                //Intsance the database
                var id: String? = intent.getStringExtra("id")
                var tittle = binding.TittleBar.text.toString()
                var content = binding.contentBar.text.toString()



                if(tittle.isNullOrBlank() || content.isNullOrBlank())
                {
                    Toast.makeText(this@AddNotesPage,"Enter the valid fields",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Log.d("mukesh", "$content")


                var databse = NoteDataBase.getInstances(this)

                var NotesDao = databse?.NoteDao()

                val data = NoteApp(0, id!!, tittle, content)

                Log.d("mukesh", "$data")

                GlobalScope.launch {
                    NotesDao?.insert(data!!)
                    withContext(Dispatchers.Main)
                    {

                        Toast.makeText(this@AddNotesPage, " Notes Saved ", Toast.LENGTH_SHORT)
                            .show()
                        finish()

                    }
                }
            }


            ///checking whether the intent is coming or not


        }
    }
