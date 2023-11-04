package com.example.mrnotes.ViewModel


import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.RoomData.NoteDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


class NotesViewModel: ViewModel() {


    fun getAll(view: View,User:String):LiveData<List<NoteApp>>
    {
        val db:LiveData<List<NoteApp>> = NoteDataBase.getInstances(view.context)!!.NoteDao()!!.getAll(User)
        return db

    }

    fun insert(view: View,list:NoteApp)
    {
        CoroutineScope(Dispatchers.IO).launch {
            NoteDataBase.getInstances(view.context)!!.NoteDao().insert(list)
        }
    }
    fun delete(view: View,id:Int)
    {
        CoroutineScope(Dispatchers.IO).launch {
            NoteDataBase.getInstances(view.context)!!.NoteDao().Delete(id)
        }
    }
    fun update(view: View,list:NoteApp)
    {
        CoroutineScope(Dispatchers.IO).launch {
            NoteDataBase.getInstances(view.context)!!.NoteDao().Update(list)
        }

    }
    fun getDeatilsById(context:Context,Id: Int): NoteApp {

             val db = NoteDataBase.getInstances(context)!!.NoteDao().getdetails(Id)

        return db

    }
    fun deletebyIds(context: Context,UserId:List<Int>)
    {
        Log.d("mukeshdeleted","$UserId")
        CoroutineScope(Dispatchers.IO).launch {
            NoteDataBase.getInstances(context)!!.NoteDao().multipleDelete(UserId)
        }
    }
//    fun getAllByList():List<NoteApp>
//    {
//
//    }

}