        package com.example.mrnotes.Activites

        import NotesRepository
        import NotesViewModel
        import android.content.Intent
        import androidx.appcompat.app.AppCompatActivity
        import android.os.Bundle
        import android.util.Log
        import android.view.View
        import android.widget.Toast
        import androidx.appcompat.app.AlertDialog
        import androidx.lifecycle.Observer
        import androidx.lifecycle.ViewModelProvider
        import androidx.recyclerview.widget.LinearLayoutManager
        import com.example.mrnotes.RecycleView.recycleAdapter
        import com.example.mrnotes.RoomData.NoteApp
        import com.example.mrnotes.RoomData.NoteDao
        import com.example.mrnotes.RoomData.NoteDataBase
        import com.example.mrnotes.ViewModel.NotesRepFactory
        import com.example.mrnotes.databinding.ActivityNotePageBinding
        import com.google.firebase.auth.FirebaseAuth
        import kotlinx.coroutines.CoroutineScope
        import kotlinx.coroutines.Dispatchers
        import kotlinx.coroutines.GlobalScope
        import kotlinx.coroutines.launch
        import kotlinx.coroutines.runBlocking
        import kotlinx.coroutines.withContext

        class NotePage : AppCompatActivity() , recycleAdapter.MulipleData {


            //intilaizing
            private lateinit var binding: ActivityNotePageBinding
            private var adapter: recycleAdapter? = null
            private lateinit var db:NoteDataBase
            private lateinit var notesviewModel: NotesViewModel
            private lateinit var repo:NotesRepository

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityNotePageBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //Displaying the Email id binding.user.setText("$email"
                /// notes adding function
                binding.addbtn.setOnClickListener {

                    val intent = Intent(this, AddNotesPage::class.java)
                    startActivity(intent)
                }
                //logout button
                binding.Logout.setOnClickListener {

                    val alert = AlertDialog.Builder(this)
                        .setTitle("Log out")
                        .setMessage("Do you want to Logout ?")
                        .setPositiveButton("Yes")
                        { p0, p1 ->
//                            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
//                            FirebaseAuth.getInstance().signOut()
//                            startActivity(Intent(this@NotePage, Login_Page::class.java))
                        //    finish()
                        }
                        .setNegativeButton("No", null)
                        .show()
                }
                var db=NoteDataBase.getInstances(this)
                var Dao=db!!.NoteDao()
                binding.rec.layoutManager = LinearLayoutManager(this)
                repo = NotesRepository(Dao)
                notesviewModel = ViewModelProvider(this,NotesRepFactory(repo)).get(NotesViewModel::class.java)
                notesviewModel.getAll("0").observe(this, Observer { notesList ->
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
                        val list = recycleAdapter(notesList, notesviewModel, this@NotePage)

                        binding.rec.adapter = list
                    }
                })

                binding.DeleteAllbtn.setOnClickListener {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Confirmation")
                        .setMessage("Are you sure to delete All the Notes")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes") { _, _ ->
                            GlobalScope.launch {
                                notesviewModel.DeleteAll()
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(this@NotePage, "All Notes Deleted", Toast.LENGTH_SHORT).show()
                                    builder.create().dismiss() // Dismiss the dialog
                                }
                            }
                        }
                        .show()
                }

            }



            override fun getselectedItems(mu: MutableList<Int>) {

                Log.d("mukeshinterface", "$mu")
                var Userid: List<NoteApp>
                var count=0
                val items = mutableListOf<Int>()
                if(!mu.isEmpty()|| !(mu.size==0))
                {
                    binding.Mutlidelete.visibility=View.VISIBLE
                }
                else{
                    binding.Mutlidelete.visibility=View.GONE
                }

                CoroutineScope(Dispatchers.IO).launch {
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

                            binding.Mutlidelete.setOnClickListener{
                                Toast.makeText(this@NotePage, "deleted", Toast.LENGTH_SHORT).show()
                                notesviewModel.deleteByIds(items)
                                count=1;

                                runOnUiThread{
                                    binding.Mutlidelete.visibility = View.GONE
                                }

                                mu.clear()
                                adapter?.notifyDataSetChanged()
                            }
                            }
                                Log.d("mukesh", "$items")
                        }
                    }

            }
        }
