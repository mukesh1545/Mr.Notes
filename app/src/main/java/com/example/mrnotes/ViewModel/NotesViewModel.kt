import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mrnotes.RoomData.NoteApp

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {

    fun getAll(user: String): LiveData<List<NoteApp>> {
        return repository.getAllNotes(user)
    }

    fun insert(note: NoteApp) {
        repository.insertNote(note)
    }

    fun delete(id: Int) {
        repository.deleteNoteById(id)
    }

    fun update(note: NoteApp) {
        repository.updateNote(note)
    }
    fun DeleteAll() {
        repository.deleteAllNotes()
    }

    fun getDetailsById(id: Int): NoteApp {
        return repository.getNoteDetailsById(id)
    }

    fun deleteByIds(userIds: List<Int>) {
        repository.deleteNotesByIds(userIds)
    }
}
