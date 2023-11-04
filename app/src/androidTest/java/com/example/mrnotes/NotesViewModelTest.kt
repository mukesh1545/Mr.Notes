package com.example.mrnotes

import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.RoomData.NoteDao
import com.example.mrnotes.RoomData.NoteDataBase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesViewModelTest {


    private lateinit var Database:NoteDataBase
    private lateinit var Dao:NoteDao

    @Before
    fun setup()
    {
        Database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDataBase::class.java
        ).build()
        Dao=Database.NoteDao()
    }
    @After
    fun Trun()
    {
        Database.close()
    }



    @Test
    fun insert()= runBlocking {
        Dao.insert(NoteApp(1,"bejbievb","mukesh","content"))
        var all=Dao.getdetails(1)
        assertThat(all).isEqualTo(NoteApp(1,"bejbievb","mukesh","content"))
    }
    @Test
    fun display()= runBlocking {
        Dao.insert(NoteApp(1,"id1","harish","content1"))
        var all=Dao.getAllByList()
        assertThat(all).contains(NoteApp(1,"id1","harish","content1"))


    }
    @Test
    fun Delete()= runBlocking {
        Dao.insert(NoteApp(1,"bejbievb","mukesh","content"))
        Dao.Delete(1)
        var all=Dao.getdetails(1)
        assertThat(all).isNull()

    }
    @Test
    fun update ()= runBlocking {
        Dao.insert(NoteApp(1,"kumar","harish","Good boy"))
        Dao.Update(NoteApp(1,"mukesh","harish","Good boy"))
        var all=Dao.getdetails(1)
        assertThat(all).isEqualTo(NoteApp(1,"mukesh","harish","Good boy"))
    }
    @Test
    fun ListOfDeleteId()= runBlocking {
        Dao.insert(NoteApp(1,"kumar","harish","Good boy1"))
        Dao.insert(NoteApp(2,"kum","mukesh","Good boy2"))
        Dao.insert(NoteApp(3,"ku","sathish","Good boy3"))

        var items= mutableListOf<Int>()
        items.add(1)
        items.add(2)
        items.add(3)

        Dao.multipleDelete(items)
        var all=Dao.getdetails(1)
        assertThat(all).isNull();
        var all1=Dao.getdetails(2)
        assertThat(all1).isNull();
        var all2=Dao.getdetails(2)
        assertThat(all2).isNull();
    }
    @Test
    fun displayNullNotes()= runBlocking {
        var all=Dao.getAllByList()
        assertThat(all).isEmpty()
    }







}