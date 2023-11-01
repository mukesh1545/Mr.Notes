package com.example.mrnotes.RecycleView

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mrnotes.Activites.AddNotesPage
import com.example.mrnotes.R
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.ViewModel.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class recycleAdapter(var list1:List<NoteApp>,private var viewModel:NotesViewModel):RecyclerView.Adapter<recycleAdapter.ViewItems>() {

    class ViewItems(view: View) : RecyclerView.ViewHolder(view) {
        var tittle: TextView = view.findViewById(R.id.TittleBar)
        var content: TextView = view.findViewById(R.id.contentBar)
        var view: CardView = view.findViewById(R.id.view)
        var update: ImageButton = view.findViewById(R.id.updateBtn)
        var delete: ImageButton = view.findViewById(R.id.deletebtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItems {

        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.eachtems, parent, false)
        return ViewItems(v)

    }

    override fun getItemCount(): Int {

        return list1.size
    }

    override fun onBindViewHolder(holder: ViewItems, position: Int ) {
        val note = list1[position]
        holder.tittle.text = note.Name.toString()
        holder.content.text = note.content.toString()
        //holder.content.text=note.content.toString()

        holder.update.setOnClickListener {
            var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                var id = list1[position].id
                putExtra("update", note.id)
                Log.d("mukesh id", "${id}")
            }
            holder.itemView.context.startActivity(intent)
        }


        holder.delete.setOnClickListener {
            Log.d("mukesh","deleted")
            var id = list1[position].id
            createAlertDialog(it, id)

        }

        ///view the notes
        holder.view.setOnClickListener {
            var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                var id = list1[position].id
                putExtra("View", note.id)
                Log.d("mukesh id kkk", "${id}")
            }

            holder.itemView.context.startActivity(intent)
        }


    }

    fun createAlertDialog(view: View, id: Int) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to delete this item?")

        builder.setPositiveButton("Yes") { dialog, which ->

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.delete(view,id)

                withContext(Dispatchers.Main) {
                    Toast.makeText(view.context, "Note Deleted", Toast.LENGTH_LONG).show()

                }
               //Toast.makeText(view.context, "Note Deleted", Toast.LENGTH_LONG).show()
                // Handle the deletion logic here
                //
            }
        }

        builder.setNegativeButton("No") { dialog, which ->
            // Do nothing or handle the cancellation
        }

        val dialog = builder.create()
        dialog.show()
    }
}
