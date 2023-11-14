//import androidx.lifecycle.Observer
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.mrnotes.RoomData.NoteApp
//import com.google.common.base.Verify.verify
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//
//@RunWith(AndroidJUnit4::class)
//class NotesViewModelTest {
//
//   @get:Rule
//   val rule = InstantTaskExecutorRule()
//   @Mock
//   private lateinit var repository: FakeNotesRepository
//
//   private lateinit var viewModel: NotesViewModel
//
//   @Before
//   fun setUp() {
//      MockitoAnnotations.initMocks(this)
//      viewModel = NotesViewModel(repository)
//   }
//
//   @Test
//   fun testGetAllNotes() {
//      val user = "testUser"
//      val observer = mock<Observer<List<NoteApp>>>()
//      viewModel.getAll(user).observeForever(observer)
//
//      // Add some fake notes to the repository
//      val fakeNotes = listOf(
//         NoteApp(1, "Title 1", "Content 1", user),
//         NoteApp(2, "Title 2", "Content 2", user)
//      )
//      when(repository.getAllNotes(user)).thenReturn((fakeNotes))
//
//      // Trigger LiveData observation
//      viewModel.getAll(user)
//
//      // Verify that the observer is updated
//      verify(observer).onChanged(fakeNotes)
//   }
//
//   // Similar tests for other ViewModel methods...
//}
