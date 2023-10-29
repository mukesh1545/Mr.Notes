package com.example.mrnotes.RoomData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteApp::class], version = 2, exportSchema = false)
abstract  class NoteDataBase :RoomDatabase() {

    companion object{
        private var INSTANCE:NoteDataBase?=null

        fun getInstances(context: Context):NoteDataBase?
        {
            if(INSTANCE==null)
            {
                synchronized(NoteDataBase::class.java)
                {
                    INSTANCE= Room.databaseBuilder(
                        context,NoteDataBase::class.java,"MyDatase"
                    ).build()

                }
                return INSTANCE
            }

            return INSTANCE

        }




    }

    abstract  fun NoteDao():NoteDao





}