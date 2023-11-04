package com.example.mrnotes.RecycleView

import android.app.AlertDialog
import android.content.Intent
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
import androidx.compose.runtime.simulateHotReload
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mrnotes.Activites.AddNotesPage
import com.example.mrnotes.R
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.ViewModel.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
var mu= mutableListOf<Int>()
class recycleAdapter(var list1:List<NoteApp>,private var viewModel:NotesViewModel,var mulipleData:MulipleData):RecyclerView.Adapter<recycleAdapter.ViewItems>() {


    class ViewItems(view: View,var mulipleData:MulipleData) : RecyclerView.ViewHolder(view) {

        var tittle: TextView = view.findViewById(R.id.TittleBar)
        var content: TextView = view.findViewById(R.id.contentBar)
        var cardView: CardView = view.findViewById(R.id.relative)
        var update: ImageButton = view.findViewById(R.id.updateBtn)
        var delete: ImageButton = view.findViewById(R.id.deletebtn)
        var view2: LinearLayout = view.findViewById(R.id.line)

        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItems {

        var v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.eachtems, parent, false)

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


        // view the notes
        holder.view2.setOnClickListener {
            if (!(holder.adapterPosition in mu)) {
                var intent = Intent(holder.itemView.context, AddNotesPage::class.java).apply {
                    var id = list1[position].id
                    putExtra("View", note.id)
                    Log.d("mukesh id kkk", "${id}")
                }
                holder.itemView.context.startActivity(intent)
                true
            }
        }

        holder.cardView.setOnLongClickListener {
            if (mu.size == 0) {

                holder.update.visibility = View.GONE
                holder.delete.visibility = View.GONE
                Log.d("mukesh ADD before", "${list1[holder.adapterPosition].Name}")
                mu.clear()
                mu.add(holder.adapterPosition)
                mulipleData.getselectedItems(mu)

                Log.d(" Mukesh Add", "${mu.size}")

                holder.cardView.setBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.sucess
                    )
                )

            }
            true
        }


        holder.cardView.setOnClickListener {
            if (!mu.isEmpty()) {

                holder.update.visibility=View.GONE
                holder. delete.visibility=View.GONE

                if ( mu.contains(holder.adapterPosition)) {

                    holder.update.visibility=View.VISIBLE
                    holder. delete.visibility=View.VISIBLE
                    Log.d("mukesh remove before","$mu")
                    mu.remove(holder.adapterPosition)
                    mulipleData.getselectedItems(mu)


                    Log.d("mukesh Remove","$mu")

                    holder.cardView.setBackgroundColor(
                        ContextCompat.getColor(
                            it.context,
                            R.color.white
                        )
                    )

                }
                else
                {
                    mu.add(holder.adapterPosition)
                    mulipleData.getselectedItems(mu)
                    Log.d("data","$mu")

                    holder.cardView.setBackgroundColor(
                        ContextCompat.getColor(
                            it.context,
                            R.color.sucess
                        )
                    )
                    Log.d("mukesh_Data","$mu")
                }
            }
        }
    }


    fun createAlertDialog(view: View, id: Int) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to delete this item?")

        builder.setPositiveButton("Yes") { dialog, which ->

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.delete(view, id)

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

//

    interface MulipleData
    {
        fun getselectedItems(mu:MutableList<Int>)
        {

        }

    }
    fun set()
    {
        mu.clear()
        Log.d("mukeshset","$mu")
    }



}

