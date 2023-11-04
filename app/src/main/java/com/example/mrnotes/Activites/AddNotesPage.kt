package com.example.mrnotes.Activites


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.ViewModel.NotesViewModel
import com.example.mrnotes.databinding.ActivityAddNotesPageBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNotesPage : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesPageBinding
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var uid = FirebaseAuth.getInstance().currentUser!!.uid
        var newtittle = binding.TittleBar.text.toString()
        var newcontent = binding.contentBar.text.toString()
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        if (intent.hasExtra("update")) {
            var id = intent.getIntExtra("update", -1)
            if (id == -1) {
                finish()
                return
            }
            binding.saveButton.visibility = View.GONE
            binding.EditBtn.visibility = View.VISIBLE
            binding.AddNote.setText("Edit Note")
            CoroutineScope(Dispatchers.IO).launch {
                val list = viewModel.getDeatilsById(applicationContext, id)
                withContext(Dispatchers.Main) {
                    binding.TittleBar.setText("${list.Name}")
                    binding.contentBar.setText("${list.content}")
                }
                binding.EditBtn.setOnClickListener {
                    var details1 = NoteApp(
                        id,
                        uid,
                        binding.TittleBar.text.toString(),
                        binding.contentBar.text.toString()
                    )
                    Log.d("mukesh up", "$details1")
                    viewModel.update(it, details1)
                    finish()
                }
            }
        } else if (intent.hasExtra("View")) {
            var id = intent.getIntExtra("View", -1)
            if (id == -1) {
                finish()
                return
            }
            binding.saveButton.visibility = View.GONE
            binding.EditBtn.visibility = View.VISIBLE
            binding.AddNote.setText("View Note")
            CoroutineScope(Dispatchers.IO).launch {
                val list = viewModel.getDeatilsById(applicationContext, id)
                withContext(Dispatchers.Main) {
                    binding.TittleBar.setText("${list.Name}")
                    binding.contentBar.setText("${list.content}")
                }
                binding.EditBtn.setOnClickListener {
                    finish()
                }
            }
        } else {
            // save button
            binding.saveButton.setOnClickListener {
                val details = NoteApp(
                    0,
                    uid,
                    binding.TittleBar.text.toString(),
                    binding.contentBar.text.toString()
                )
                Log.d("mukesh insert", "$details")
                viewModel.insert(it, details)
                finish()
            }
        }
    }
}
