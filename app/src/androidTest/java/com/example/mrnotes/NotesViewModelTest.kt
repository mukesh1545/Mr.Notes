package com.example.mrnotes

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
    fun displayAllNotes_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        val all=Dao.getAllByList()
        assertThat(all).contains(NoteApp(1,"Id1","Test1","content1"))
    }
    @Test
    fun insert_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        val all=Dao.getdetails(1)
        assertThat(all).isEqualTo(NoteApp(1,"Id1","Test1","content1"))
    }
    @Test
    fun update_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        Dao.Update(NoteApp(1,"Id2","Test1","content1"))
        val all=Dao.getdetails(1)
        assertThat(all).isEqualTo(NoteApp(1,"Id2","Test1","content1"))
    }
    @Test
    fun DeleteIdOfList_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        Dao.insert(NoteApp(2,"Id1","Test1","content1"))
        Dao.insert(NoteApp(3,"Id1","Test1","content1"))

        val items= mutableListOf<Int>()
        items.add(1)
        items.add(2)
        items.add(3)

        Dao.multipleDelete(items)
        val all=Dao.getdetails(1)
        assertThat(all).isNull();
        val all1=Dao.getdetails(2)
        assertThat(all1).isNull();
        val all2=Dao.getdetails(3)
        assertThat(all2).isNull();
    }
    @Test
    fun displayNull_theNotes()= runBlocking {
        val all=Dao.getAllByList()
        assertThat(all).isEmpty()
    }
    @Test
    fun DisplayNotesBy_id()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        val all=Dao.getdetails(1)
        assertThat(all).isEqualTo(NoteApp(1,"Id1","Test1","content1"))

    }

    @Test
    fun Check_DoesNotContains_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        val all=Dao.getAllByList()
        assertThat(all).doesNotContain(NoteApp(2,"id1","Test1","content1"))

    }
    @Test
    fun check_DisplayNotesBy_id()= runBlocking {
        Dao.insert(NoteApp(2,"Id1","Test1","Content1"))
        val all=Dao.getdetails(1)
        assertThat(all).isNotEqualTo(NoteApp(1,"Id1","Test1","Content1"))

    }
    @Test
    fun check_doesInsert_theNotes()= runBlocking {
        val all=Dao.getdetails(1)
        assertThat(all).isNotEqualTo(NoteApp(1,"Id1","Test","content1"))
    }
    @Test
    fun check_doesNoteUpdate_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        Dao.Update(NoteApp(1,"Id","Test1","content1"))
        val all=Dao.getdetails(1)
        assertThat(all).isNotEqualTo(NoteApp(1,"Id1","Test1","content1"))
    }
    @Test
    fun check_doesNotDisplayNull_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","Content1"))
        val all=Dao.getAllByList()
        assertThat(all).isNotEmpty()

    }
    fun check_ListOfDeleteId_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"Id1","Test1","content1"))
        Dao.insert(NoteApp(2,"Id2","Test2","content2"))
        Dao.insert(NoteApp(3,"Id3","Test3","content3"))

        val items= mutableListOf<Int>()
        items.add(3)
        items.add(4)
        items.add(5)

        Dao.multipleDelete(items)
        val all=Dao.getdetails(1)
        assertThat(all).isNotNull()
        val all1=Dao.getdetails(2)
        assertThat(all1).isNotNull()
        val all2=Dao.getdetails(2)
        assertThat(all2).isNotNull()
    }
    fun check_displayAllNotes_theNotes()= runBlocking {
        Dao.insert(NoteApp(1,"id1","Test1","content1"))
        Dao.insert(NoteApp(2,"id2","Test2","content2"))
        val all=Dao.getAllByList()
        assertThat(all).contains(NoteApp(1,"id1","harish","content1"))
    }







}