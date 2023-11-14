package com.example.mrnotes

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isSystemAlertWindow
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mrnotes.Activites.NotePage
import com.example.mrnotes.Activites.splash
import com.example.mrnotes.RecycleView.recycleAdapter
import com.example.mrnotes.RoomData.NoteDao
import com.example.mrnotes.RoomData.NoteDataBase
import com.example.mrnotes.databinding.ActivityLoginPageBinding
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.not

import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(splash::class.java)

   var  Database :NoteDataBase= Room.inMemoryDatabaseBuilder(
    ApplicationProvider.getApplicationContext(),
    NoteDataBase::class.java
    ).build()
     var Dao:NoteDao=Database.NoteDao()

    @Test
    fun NotePage() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))


    }
   // @Test

//    fun Splash()
//    {
//        var activityRule = ActivityScenario.launch(splash::class.java)
//        // Check if the ProgressBar is displayed
//        onView(withId(R.id.Bar)).check(matches(isDisplayed()))
//
//        // Check if the TextView with ID "Notes" is displayed and has the correct text
//        onView(withId(R.id.Notes)).check(matches(isDisplayed()))
//        onView(withId(R.id.Notes)).check(matches(withText("Your Notes")))
//
//        // You can perform other actions or assertions based on your UI
//        // For example, click on a view
//        onView(withId(R.id.Notes)).perform(click())
//    }

    @Test
    fun DisplayTheAddNotesbutton() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).check(matches(isDisplayed()))


    }

    @Test
    fun DisplayTheRecycleView() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec)).check(matches(isDisplayed()))

        onView(withId(R.id.rec)).check(matches(isDisplayed()))


    }

    //    @Test
//    fun DisplayTheTest()
//    {
//        var activityRule = ActivityScenario.launch(splash::class.java)
//        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
//        onView(withId(R.id.user)).check(matches(isDisplayed()))
//
//
//    }
//    @Test
//    fun DisplayTheResult()
//    {
//        var activityRule = ActivityScenario.launch(splash::class.java)
//        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
//        onView(withId(R.id.result)).check(matches(isDisplayed()))
//    }
    @Test
    fun addButtonIsClicked_navToNotesActivity() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        val activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.addbtn)).perform(click())
    }

    @Test
    fun NotesActivity_addButton_isVisible() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

    }

    @Test
    fun AddNotesActivity_TittleBar_isVisible() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).check(matches(isDisplayed()))
    }

    @Test
    fun AddNotesActivity_ContentBar_isVisible() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.content)).check(matches(isDisplayed()))
    }
    @Test
    fun AddNotesActivity_isVisible() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
    }

    @Test
    fun AddTittle_Check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())

    }
    @Test
    fun logout()
    {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.Logout)).check(matches(isDisplayed()))
        onView(withId(R.id.Logout)).perform(click())
        onView(withText("Yes"))
    }
    @Test
    fun logout_No()
    {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.Logout)).check(matches(isDisplayed()))
        onView(withId(R.id.Logout)).perform(click())
        onView(withText("No"))
    }

    @Test
    fun AddContent_Check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())

    }
    @Test
    fun Result_Check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches((isDisplayed())))
        onView(withId(R.id.DeleteAllbtn)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withId(R.id.result)).check(matches(isDisplayed()))
        onView(withId(R.id.rec)).check(matches(not(isDisplayed())))


    }

    @Test
    fun AddTittle_Content_Check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())

    }

    @Test
    fun Addnotes_InvalidForTittle_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())

    }

    @Test
    fun Addnotes_InvalidForContent_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())

    }

    //    @Test
//    fun Addnotes_InvalidForContent_checkToast()
//    {
//        var activityRule=ActivityScenario.launch(splash::class.java)
//        var activity2=ActivityScenario.launch(NotePage::class.java)
//        onView(withId(R.id.Note)).check(matches(isDisplayed()))
//        onView(withId(R.id.addbtn)).perform(click())
//        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
//        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
//        onView(withId(R.id.saveButton)).perform(click())
//        onView(withText("Enter the valid Fields")).inRoot(isSystemAlertWindow()).check(matches(
//            isDisplayed()
//        ))
//
//    }
    @Test
    fun AddList_DisplayingtheList_visible() {

        var activityRule = ActivityScenario.launch(splash::class.java)
        var activity2 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
    }

    @Test
    fun updateBtn_Item() {
        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())

        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.Note)).check(matches(isDisplayed()))


        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
    }
//    @Test
//    fun countOfItem_null()
//   {
//
//        var expectedCount=null
//        var activity=ActivityScenario.launch(splash::class.java)
//        var activity1=ActivityScenario.launch(NotePage::class.java)
//        onView(withId(R.id.DeleteAllbtn)).perform(click())
//        onView(withText("Yes")).perform(click())
//        onView(withId(R.id.result)).check(matches(isDisplayed()))
//        onView(withId(R.id.Note)).check(matches(isDisplayed()))
//        onView(withId(R.id.rec))
//            .check(RecyclerViewItemCountAssertion(expectedCount))
//
//    }
//    @Test
//    fun countOfItem()
//    {
//        var expectedCount=2
//        var activity=ActivityScenario.launch(splash::class.java)
//        var activity1=ActivityScenario.launch(NotePage::class.java)
//        onView(withId(R.id.DeleteAllbtn)).perform(click())
//        onView(withText("Yes")).perform(click())
//        onView(withId(R.id.result)).check(matches(isDisplayed()))
//        onView(withId(R.id.Note)).check(matches(isDisplayed()))
//        onView(withId(R.id.addbtn)).perform(click())
//        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
//        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
//        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
//        onView(withId(R.id.saveButton)).perform(click())
//        onView(withId(R.id.addbtn)).perform(click())
//        onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
//        onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())
//        onView(withId(R.id.saveButton)).perform(click())
//        onView(withId(R.id.Note)).check(matches(isDisplayed()))
//        onView(withId(R.id.rec))
//            .check(RecyclerViewItemCountAssertion(expectedCount))
//
//    }

    @Test
    fun checkDeleteButtonForAllItems() {
        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.Note)).check(matches(isDisplayed()))

        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    1,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTittle_matches() {
        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.longClick()
                )
            )

        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    1,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.rec)).perform(
            (RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                0,
                ViewActions.click()
            ))
        )

        onView(withId(R.id.Mutlidelete)).check(matches(isDisplayed()))
        onView(withId(R.id.Mutlidelete)).perform(click())
    }


    @Test
    fun Viewing_matches() {
//        Dao.DeleteAll()
        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.DeleteAllbtn)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withId(R.id.result)).check(matches(isDisplayed()))
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        onView(withId(R.id.EditBtn)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(
            isDescendantOfA(allOf(
                withId(R.id.relative),
                hasDescendant(allOf(
                    withId(R.id.TittleBar),
                    withText("Tittle")
                ))
            )),

            withId(R.id.deletebtn)
        )).check(matches(isDisplayed())).perform(click())
        onView(withText("Yes")).perform(click())

        onView(withId(R.id.result)).check(matches(isDisplayed()))

    }
    @Test
    fun Editbtn_Eachitems() {

        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        onView(withId(R.id.EditBtn)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.addbtn)).check(matches(isDisplayed()))

    }
    @Test
    fun Editbtn_TittleAndcontent() {

        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.DeleteAllbtn)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withId(R.id.result)).check(matches(isDisplayed()))

        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        onView(withId(R.id.EditBtn)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(allOf(
            isDescendantOfA(allOf(
                withId(R.id.relative),
                hasDescendant(allOf(
                    withId(R.id.TittleBar),
                    withText("Tittle")
                ))
            )),
            withId(R.id.updateBtn)
        )).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText( " Tittle1"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText(" Content1"), closeSoftKeyboard())
        onView(withId(R.id.EditBtn)).perform(click())

    }
    @Test
    fun Editbtn_checkTittleAndcontent() {

        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.DeleteAllbtn)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withId(R.id.result)).check(matches(isDisplayed()))

        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        onView(withId(R.id.EditBtn)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(allOf(
            isDescendantOfA(allOf(
                withId(R.id.relative),
                hasDescendant(allOf(
                    withId(R.id.TittleBar),
                    withText("Tittle")
                ))
            )),
            withId(R.id.updateBtn)
        )).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText( " EditTittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText(" EditContent"), closeSoftKeyboard())
        onView(withId(R.id.EditBtn)).perform(click())
        onView(allOf(
            isDescendantOfA(allOf(
                withId(R.id.relative),
                hasDescendant(allOf(
                    withId(R.id.Tittle),
                    withText("Tittle EditTittle")
                ))
            )), withId(R.id.content),
                withText("Content EditContent")
        ))

    }
    @Test
    fun DeleteAll()
    {
        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())

        onView(withId(R.id.DeleteAllbtn)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withId(R.id.result)).check(matches(isDisplayed()))
    }
    @Test
    fun Editbtn_TittleAndcontentNull() {

        var activity = ActivityScenario.launch(splash::class.java)
        var activity1 = ActivityScenario.launch(NotePage::class.java)
        onView(withId(R.id.Note)).check(matches(isDisplayed()))
        onView(withId(R.id.DeleteAllbtn)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withId(R.id.result)).check(matches(isDisplayed()))
        onView(withId(R.id.addbtn)).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
        onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.rec))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        onView(withId(R.id.EditBtn)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(allOf(
            isDescendantOfA(allOf(
                withId(R.id.relative),
                hasDescendant(allOf(
                    withId(R.id.TittleBar),
                    withText("Tittle")
                ))
            )),
            withId(R.id.updateBtn)
        )).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.saveButton)).check(matches(not(isDisplayed())))
        onView(withId(R.id.AddNote)).check(matches(withText("Edit Note")))
        onView(withId(R.id.Tittle)).perform(clearText())
        onView(withId(R.id.content)).perform(clearText())
        onView(withId(R.id.EditBtn)).perform(click())

    }


}

