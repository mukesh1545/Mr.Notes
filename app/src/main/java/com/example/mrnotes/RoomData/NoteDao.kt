package com.example.mrnotes.RoomData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDao {

    @Insert
    fun insert(note:NoteApp)
    @Update
    fun Update(note: NoteApp)

    @Query("Delete from Notes where id like:userId")
    fun Delete(userId:Int)

    @Query("select *  from notes where id like :Id")
    fun getdetails(Id:Int):NoteApp

    @Query("SELECT * FROM  notes  where Uid like :User  ORDER BY id DESC")
    fun getAll(User:String):LiveData<List<NoteApp>>


}


