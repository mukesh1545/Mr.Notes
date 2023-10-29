package com.example.mrnotes.RoomData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")
data class NoteApp(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var Uid:String,
    var Name:String,
    var content:String
)
