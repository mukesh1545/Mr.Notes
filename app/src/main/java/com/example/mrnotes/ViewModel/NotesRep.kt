import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mrnotes.RoomData.NoteApp
import com.example.mrnotes.RoomData.NoteDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRep(private val context: Context) {

    private val noteDao = NoteDataBase.getInstances(context)!!.NoteDao()

    fun getAllNotes(userId: String): LiveData<List<NoteApp>> {
        return noteDao.getAll(userId)
    }

    fun insertNote(note: NoteApp) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }
    }

    fun deleteNoteById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.Delete(id)
        }
    }

    fun updateNote(note: NoteApp) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.Update(note)
        }
    }

    fun getNoteDetailsById(id: Int): NoteApp {
        return noteDao.getdetails(id)
    }

    fun deleteNotesByIds(userIds: List<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.multipleDelete(userIds)
        }
    }
}
