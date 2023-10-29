package com.example.mrnotes.RecycleView

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mrnotes.AddNotesPage
import com.example.mrnotes.R
import com.example.mrnotes.RoomData.NoteApp

class recycleAdapter(var list1:List<NoteApp>):RecyclerView.Adapter<recycleAdapter.ViewItems>() {

    class ViewItems(view: View):RecyclerView.ViewHolder(view)  {
        var tittle: TextView = view.findViewById(R.id.TittleBar)
        var content: TextView = view.findViewById(R.id.contentBar)
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

    override fun  onBindViewHolder(holder: ViewItems, position: Int,) {
        val note = list1[position]
        holder.tittle.text = note.Name.toString()
        holder.content.text = note.content.toString()
        //holder.content.text=note.content.toString()

        holder.update.setOnClickListener {
            var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                var id = list1[position].id
                putExtra("id123", note.id)
                Log.d("mukesh id", "${id}")
            }

            holder.itemView.context.startActivity(intent)
       }



        holder.delete.setOnClickListener {
            var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                var id = list1[position].id
                putExtra("Delete", note.id)
                Log.d("mukesh id", "${id}")
            }

            holder.itemView.context.startActivity(intent)
        }
    }



    }
