package com.example.mrnotes.Activites




import NotesRepository
import NotesViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.RoomData.NoteDataBase
import com.example.mrnotes.ViewModel.NotesRepFactory
import com.example.mrnotes.databinding.ActivityAddNotesPageBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNotesPage : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesPageBinding
    private lateinit var viewModel: NotesViewModel
    private lateinit var repo:NotesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var uid = FirebaseAuth.getInstance().currentUser!!.uid
        var newtittle = binding.Tittle.text.toString()
        var newcontent = binding.content.text.toString()
        val noteDao = NoteDataBase.getInstances(applicationContext)!!.NoteDao()
         repo = NotesRepository(noteDao)
        viewModel = ViewModelProvider(this,NotesRepFactory(repo)).get(NotesViewModel::class.java)

        if (intent.hasExtra("update")) {
            var id = intent.getIntExtra("update", -1)
//            if (id == -1) {
//                finish()
//                return
//            }
            binding.saveButton.visibility = View.GONE
            binding.EditBtn.visibility = View.VISIBLE
            binding.AddNote.setText("Edit Note")
            CoroutineScope(Dispatchers.IO).launch {
                val list = viewModel.getDetailsById(id)
                withContext(Dispatchers.Main) {
                    binding.Tittle.setText("${list.Name}")
                    binding.content.setText("${list.content}")
                }
                binding.EditBtn.setOnClickListener {
                    if (binding.Tittle.text.toString()
                            .isNotBlank() && binding.content.text.toString().isNotBlank()
                    ) {
                        var details1 = NoteApp(
                            id,
                            uid,
                            binding.Tittle.text.toString(),
                            binding.content.text.toString()
                        )
                        Log.d("mukesh up", "$details1")
                        viewModel.update(details1)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@AddNotesPage,"Enter the valid ones",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        } else if (intent.hasExtra("View")) {
            var id = intent.getIntExtra("View", -1)
//            if (id == -1) {
//                finish()
//                return
//            }
            binding.saveButton.visibility = View.GONE
            binding.EditBtn.visibility = View.VISIBLE
            binding.AddNote.setText("View Note")
            CoroutineScope(Dispatchers.IO).launch {
                val list = viewModel.getDetailsById(id)
                withContext(Dispatchers.Main) {
                    binding.Tittle.setText("${list.Name}")
                    binding.content.setText("${list.content}")
                }
                binding.EditBtn.setOnClickListener {
                    finish()
                }
            }
        }
        else
        {
            // save button
            binding.saveButton.setOnClickListener {


                if (binding.Tittle.text.toString()
                        .isNotBlank() && binding.content.text.toString().isNotBlank()
                ) {
                    val details = NoteApp(
                        0,
                        uid,
                        binding.Tittle.text.toString(),
                        binding.content.text.toString()
                    )
                    Log.d("mukesh insert", "$details")
                    viewModel.insert(details)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Enter the valid Fields",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
