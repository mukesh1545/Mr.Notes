        package com.example.mrnotes.Activites

        import NotesRepository
        import NotesViewModel
        import android.annotation.SuppressLint
        import android.content.Intent
        import androidx.appcompat.app.AppCompatActivity
        import android.os.Bundle
        import android.util.Log
        import android.view.DragEvent
        import android.view.Menu
        import android.view.MenuItem
        import android.view.MotionEvent
        import android.view.View
        import android.view.ViewGroup
        import android.widget.PopupMenu
        import android.widget.RelativeLayout
        import android.widget.Toast
        import androidx.appcompat.app.AlertDialog
        import androidx.constraintlayout.widget.ConstraintLayout
        import androidx.lifecycle.Observer
        import androidx.lifecycle.ViewModelProvider
        import androidx.recyclerview.widget.LinearLayoutManager
        import com.example.mrnotes.R
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
//            private var offsetX = 0f
//            private var offsetY = 0f

            @SuppressLint("ClickableViewAccessibility")
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityNotePageBinding.inflate(layoutInflater)
                setContentView(binding.root)




//                binding.swipingbtn.setOnLongClickListener {
//
//                    Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
//
////                    val popupMenu = PopupMenu(it.context, it)
////                    popupMenu.menuInflater.inflate(R.menu.swipbtn, popupMenu.menu)
////                    popupMenu.show()
//
//                }

//                binding.swipingbtn.setOnClickListener {
//                    Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
//                }




                // Set the long click listener to start dragging
//               binding.imageButton.setOnLongClickListener {
//                   val value = View.DragShadowBuilder(it)
//                   it.startDragAndDrop(null, value, it, 0)
//                   it.visibility = View.VISIBLE
//                   true
//                }

                //set the onTouch ..it will set the imagebtn when we change it....
//                binding.swipingbtn.setOnTouchListener { v,event->
//
//                    binding.swipingbtn.setOnLongClickListener {
//
//                        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
//
//                        return@setOnLongClickListener  true
//
//                    }
//                    var chheck=0;
//                    // v->it will give the View....
//                    // event ->it will give the event....
//
//                    val layoutParams = v.layoutParams as ConstraintLayout.LayoutParams
//                    when (event.action) {
//
//
//                        //used to clacluate the initnal poistion of the imagebtn....
//                        MotionEvent.ACTION_DOWN -> {
//                            chheck = 1
//                            offsetX = event.rawX - layoutParams.leftMargin
//                            offsetY = event.rawY - layoutParams.topMargin
//
//                        }
//
//
//                        //used to calucate the curent position of the image btn
//                        MotionEvent.ACTION_MOVE -> {
//
//                            val newX = event.rawX - offsetX
//                            val newY = event.rawY - offsetY
//                            chheck = 1
//                            // Prevent dragging the view outside the parent layout
//                            if (newX >= 0 && newX <= (v.parent as ViewGroup).width - v.width &&
//                                newY >= 0 && newY <= (v.parent as ViewGroup).height - v.height
//                            ) {
//                                layoutParams.leftMargin = newX.toInt()
//                                layoutParams.topMargin = newY.toInt()
//                                v.layoutParams = layoutParams
//                            }
//                        }
//
//                        //visible the imagebtn...
//                        MotionEvent.ACTION_UP -> {
//                            v.visibility = View.VISIBLE
//
//                        }
//                    }
//                 Log.d("mukesh","$chheck")
//
//                        when(chheck) {
//
//                            0 -> {v.performLongClick()}
//                        }
//                    true
//                }
//
//
//
//
//                binding.swipingbtn.setOnDragListener { v, event ->
//                    when (event.action) {
//                        DragEvent.ACTION_DRAG_STARTED -> true
//                        DragEvent.ACTION_DRAG_ENTERED -> true
//                        DragEvent.ACTION_DRAG_LOCATION -> true
//                        DragEvent.ACTION_DRAG_EXITED -> true
//                        DragEvent.ACTION_DROP -> {
//                            // Get the new position based on drag event coordinates
//                            val x = event.x - offsetX
//                            val y = event.y - offsetY
//
//                            // Set new layout parameters for the ImageButton
//                            val layoutParams = v.layoutParams as ViewGroup.MarginLayoutParams
//                            layoutParams.leftMargin = x.toInt()
//                            layoutParams.topMargin = y.toInt()
//
//                            // Update the layout parameters
//                            v.layoutParams = layoutParams
//                            true
//                        }
//                        DragEvent.ACTION_DRAG_ENDED -> {
//                          //  Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
//
//                            // Make the ImageButton visible after dragging
//                            v.visibility = View.VISIBLE
//                            true
//                        }
//                        else -> {
//                           // Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
//                            false
//                        }
//                    }
//                }

                //Displaying the Email id

                val user = FirebaseAuth.getInstance().currentUser
                val email = user?.email
                binding.user.setText("$email")
                val uid = FirebaseAuth.getInstance().currentUser?.uid

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
                            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                            FirebaseAuth.getInstance().signOut()
                            startActivity(Intent(this@NotePage, Login_Page::class.java))
                            finish()
                        }
                        .setNegativeButton("No", null)
                        .show()
                }
                var db=NoteDataBase.getInstances(this)
                var Dao=db!!.NoteDao()
                binding.rec.layoutManager = LinearLayoutManager(this)
                repo = NotesRepository(Dao)
                notesviewModel = ViewModelProvider(this,NotesRepFactory(repo)).get(NotesViewModel::class.java)
                notesviewModel.getAll(uid!!).observe(this, Observer { notesList ->
                    if (notesList.isNullOrEmpty()) {
                        // If the list is empty, show a message
                        binding.result.visibility = View.VISIBLE
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

            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                return super.onCreateOptionsMenu(menu)
            }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                return super.onOptionsItemSelected(item)
            }
            fun click(view: View)
            {
                Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
                Log.d("mukesh","clicked")
            }
        }
