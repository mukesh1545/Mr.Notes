//import android.provider.ContactsContract.CommonDataKinds.Note
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.SmallTest
//import com.example.mrnotes.RecycleView.recycleAdapter
//import com.example.mrnotes.RoomData.NoteApp
//import com.example.mrnotes.RoomData.NoteDao
//import com.example.mrnotes.RoomData.NoteDataBase
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.runBlocking
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mockito.mock
//
//@RunWith(AndroidJUnit4::class)
//@SmallTest
//class MyViewModelTest {
//
//    private  lateinit var rep:NotesRepository
//    private lateinit var  Notedao:NoteDao
//    private lateinit var db:NoteDataBase
//
//    @Before
//    fun setup()
//    {
//        db= Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),NoteDataBase::class.java).build()
//       Notedao=db.NoteDao()
//        rep=NotesRepository(Notedao)
//    }
//
//    @After
//    fun clear()
//    {
//        db.close()
//    }
//    @Test
//    fun display(){
//
//            var details = rep.getNoteDetailsById(1)
//            assertThat(details).isNull()
//
//    }
//    @Test
//    fun insert()= runBlocking{
//
//            rep.insertNote(NoteApp(1, "Id1", "Tittle", "Content"))
//            var Details = rep.getNoteDetailsById(1)
//            assertThat(Details).isEqualTo(NoteApp(1, "Id1", "Tittle", "Content"))
//
//    }
//
//    @Test
//    fun testDeleteNotesByIds() {
//        val userDao = mock(NoteDao::class.java)
//        val userRepository = NotesRepository(userDao) // Replace with your actual UserRepository implementation
//
//        val userIdsToDelete = listOf(1, 2, 3)
//        userRepository.deleteNotesByIds(userIdsToDelete)
//        val value=userRepository.getNoteDetailsById(1)
//        assertThat(value).isNull()
//
//        // Add your assertions or verifications here
//    }
//
//    @Test
//    fun update()= runBlocking{
//
//        rep.insertNote(NoteApp(1, "Id1", "Tittle", "Content"))
//        rep.updateNote(NoteApp(1,"Id2","Tittle","Content"))
//        var Details = rep.getNoteDetailsById(1)
//        assertThat(Details).isEqualTo(NoteApp(1, "Id2", "Tittle", "Content"))
//
//    }
//    @Test
//    fun MulipleList_deletion() {
//
//        rep.insertNote(NoteApp(1, "Id1", "Tittle", "Content"))
//        rep.insertNote(NoteApp(2, "Id1", "Tittle", "Content"))
//        rep.insertNote(NoteApp(3, "Id1", "Tittle", "Content"))
//        var list = mutableListOf<Int>()
//        list.add(1)
//        list.add(2)
//        rep.deleteNotesByIds(list)
//        var Detail = rep.getNoteDetailsById(1)
//        assertThat(Detail).isNull()
//        var details1=rep.getNoteDetailsById(2)
//        assertThat(details1).isNull()
////        var details2=rep.getNoteDetailsById(3)
////        assertThat(details2).isNull()
//
//    }
//    @Test
//    fun DONotMulipleList_deletion(){
//
//        rep.insertNote(NoteApp(1, "Id1", "Tittle", "Content"))
//        rep.insertNote(NoteApp(2, "Id1", "Tittle", "Content"))
//        rep.insertNote(NoteApp(3, "Id1", "Tittle", "Content"))
//        rep.updateNote(NoteApp(1,"Id2","Tittle","Content"))
//        var list:List<Int> = listOf(4,5,6)
//        rep.deleteNotesByIds(list)
//        var Details = rep.getNoteDetailsById(1)
//        assertThat(Details).isNotNull()
//        var details1=rep.getNoteDetailsById(2)
//        assertThat(details1).isNotNull()
//        var details2=rep.getNoteDetailsById(3)
//        assertThat(details2).isNotNull()
//
//    }
//}