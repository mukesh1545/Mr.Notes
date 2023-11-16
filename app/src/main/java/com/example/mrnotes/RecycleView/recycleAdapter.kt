package com.example.mrnotes.RecycleView

import NotesViewModel
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mrnotes.Activites.AddNotesPage
import com.example.mrnotes.R
import com.example.mrnotes.RoomData.NoteApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
var mu= mutableListOf<Int>()
class recycleAdapter(var list1:List<NoteApp>,private var viewModel:NotesViewModel,var mulipleData:MulipleData):RecyclerView.Adapter<recycleAdapter.ViewItems>() {


    class ViewItems(view: View,var mulipleData:MulipleData) : RecyclerView.ViewHolder(view) {

        var tittle: TextView = view.findViewById(R.id.TittleBar)
        var content: TextView = view.findViewById(R.id.contentBar)
        var cardView: RelativeLayout = view.findViewById(R.id.Relative)
        var update: ImageButton = view.findViewById(R.id.updateBtn)
        var delete: ImageButton = view.findViewById(R.id.deletebtn)
        var view2:CardView = view.findViewById(R.id.relative)
        var colcor:LinearLayout=view.findViewById(R.id.lin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItems {

        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.eachtems, parent, false)

        return ViewItems(v,mulipleData)

    }

    override fun getItemCount(): Int {

        return list1.size
    }

    override fun onBindViewHolder(holder: ViewItems, position: Int) {
        val note = list1[position]
        holder.tittle.text = note.Name.toString()
        holder.content.text = note.content.toString()
        //holder.content.text=note.content.toString()
        val color = getColorForItem(position)

        holder.itemView.setBackgroundColor(color)

        holder.update.setOnClickListener {
            var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                var id = list1[position].id
                putExtra("update", note.id)
                Log.d("mukesh id", "${id}")
            }
            holder.itemView.context.startActivity(intent)
        }


        holder.delete.setOnClickListener {
            Log.d("mukesh", "deleted")
            var id = list1[position].id
            createAlertDialog(it, id)

        }

        holder.cardView.setOnLongClickListener {
            if (mu.isEmpty()) {

                holder.update.visibility = View.GONE
                holder.delete.visibility = View.GONE
                Log.d("mukesh ADD before", "${list1[holder.adapterPosition].Name}")
                mu.clear()
                mu.add(holder.adapterPosition)
                mulipleData.getselectedItems(mu)

                Log.d(" Mukesh Add", "${mu.size}")

                holder.view2.setBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.Error
                    )
                )

            }
            true
        }


        holder.cardView.setOnClickListener {

            if (mu.isEmpty()) {
                var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                    var id = list1[position].id
                    putExtra("View", note.id)
                    Log.d("mukesh id kkk", "${id}")
                }
                holder.itemView.context.startActivity(intent)
            } else {

                holder.update.visibility = View.GONE
                holder.delete.visibility = View.GONE

                if (mu.contains(holder.adapterPosition)) {

                    holder.update.visibility = View.VISIBLE
                    holder.delete.visibility = View.VISIBLE
                    Log.d("mukesh remove before", "$mu")
                    mu.remove(holder.adapterPosition)
                    mulipleData.getselectedItems(mu)


                    Log.d("mukesh Remove", "$mu")
                        holder.itemView.setBackgroundColor(getColorForItem(position))


                } else {
                    mu.add(holder.adapterPosition)
                    mulipleData.getselectedItems(mu)
                    Log.d("data", "$mu")

                    holder.view2.setBackgroundColor(
                        ContextCompat.getColor(
                            it.context,
                            R.color.Error
                        )
                    )
                    Log.d("mukesh_Data", "$mu")
                }
            }
        }
    }

    private fun getColorForItem(position: Int): Int {
        // Customize this logic to provide different colors based on your criteria
        return if (position % 2 == 0) {
            Color.parseColor("#FFC0CB") // Example color: Light Pink
        } else {
            Color.parseColor("#87CEFA") // Example color: Light Sky Blue
        }
    }


    fun createAlertDialog(view: View, id: Int) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to delete this item?")

        builder.setPositiveButton("Yes") { dialog, which ->

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.delete(id)

                withContext(Dispatchers.Main) {
                    Toast.makeText(view.context, "Note Deleted", Toast.LENGTH_LONG).show()

                }
                //Toast.makeText(view.context, "Note Deleted", Toast.LENGTH_LONG).show()
                // Handle the deletion logic here
                //
            }
        }

        builder.setNegativeButton("No",null)

        val dialog = builder.create()
        dialog.show()
    }

//

    interface MulipleData
    {

          fun getselectedItems(mu:MutableList<Int>)

    }
    fun set()
    {
        mu.clear()
    }



}

