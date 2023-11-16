package com.example.mrnotes.ActivityTest

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mrnotes.Activites.Login_Page
import com.example.mrnotes.Activites.splash
import com.example.mrnotes.R
import com.example.mrnotes.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before

import org.junit.Rule
import org.junit.Test


class MainActivityTest {
        @get:Rule
        var activityRule = ActivityScenarioRule(splash::class.java)

        @Before
        fun before() {
            //variables
            var activityRule = ActivityScenario.launch(splash::class.java)
            var activityRule1 = ActivityScenario.launch(Login_Page::class.java)

            //check whether the Email..Password..LoginBtn ..All are displayed or not
            onView(withId(R.id.Login_Email_id)).check(matches(isDisplayed()))
            onView(withId(R.id.Login_Password)).check(matches(isDisplayed()))

            //check with the input
            onView(withId(R.id.Login_Email_id)).perform(
                typeText("mukeshkumar.ee19@bitsathy.ac.in"),
                closeSoftKeyboard()
            )
            onView(withId(R.id.Login_Password)).perform(typeText("1234567"), closeSoftKeyboard())
            onView(withId(R.id.Login_btn)).perform(click())
            Thread.sleep(9000)

            //After the sucessfull Login it will go to Note Page && check the whether All are dispplayed are not
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
//            onView(withId(R.id.result)).check(matches(isDisplayed()))
            onView(withId(R.id.user)).check(matches(isDisplayed()))

            //It will check the current user Email is displayed Correctly or not
            onView(withId(R.id.user)).check(matches(withText("mukeshkumar.ee19@bitsathy.ac.in")))
            onView(withId(R.id.DeleteAllbtn)).check(matches(isDisplayed()))
        }

        @Test
        fun NotePage_IsVisible() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))

        }

        @Test
        fun DisplayTheAddNotesbutton() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))


        }

        @Test
        fun DisplayTheRecycleView() {
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())
            onView(withId(R.id.rec)).check(matches(isDisplayed()))
            onView(withId(R.id.rec)).check(matches(isDisplayed()))


        }

        @Test
        fun AddNotesActivity_TittleBar_isVisible() {

            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.Tittle)).check(matches(isDisplayed()))
        }

        @Test
        fun AddNotesActivity_ContentBar_isVisible() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.content)).check(matches(isDisplayed()))
        }

        @Test
        fun AddNotesPage_isVisible() {

            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
        }

        @Test
        fun AddTittle_Check() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())

        }

        @Test
        fun logout_NavToLoginPage() {

            onView(withId(R.id.Logout)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.Login)).check(matches(isDisplayed()))
        }

        @Test
        fun logout_Check_No() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.Logout)).check(matches(isDisplayed()))
            onView(withId(R.id.Logout)).check(matches(isDisplayed()))
            onView(withId(R.id.Logout)).perform(click())
            onView(withText("No"))
        }

        @Test
        fun AddContent_Check() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())

        }

        @Test
        fun Result_Check() {
            onView(withId(R.id.Note)).check(matches((isDisplayed())))
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.result)).check(matches(isDisplayed()))
            onView(withId(R.id.rec)).check(matches(not(isDisplayed())))
        }

        @Test
        fun AddNotesPage_saveBtn_Check() {

            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())

        }

        @Test
        fun Addnotes_InvalidForTittle() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches((isDisplayed())))

        }

        @Test
        fun Addnotes_InvalidForContent() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches((isDisplayed())))

        }

        @Test
        fun Addnotes_EmptyInput_check() {

            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.saveButton)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches((isDisplayed())))

        }

        @Test
        fun AddNotes_ValidInput_Check() {
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText("content"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
        }

        @Test
        fun countOfItem_null() {

            var expectedCount = null
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.result)).check(matches(isDisplayed()))
            onView(withId(R.id.Note)).check(matches(isDisplayed()))
            onView(withId(R.id.rec))
                .check(RecyclerViewItemCountAssertion(expectedCount))

        }

        @Test
        fun countOfItem() {
            var expectedCount = 2
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.result)).check(matches(isDisplayed()))
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
                .check(RecyclerViewItemCountAssertion(expectedCount))
        }


        @Test
        fun MutlipleDeleteItems_check() {
            //check AddNotesPage
            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
            onView(withId(R.id.Note)).check(matches(isDisplayed()))

            //check AddNotesPage
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

            //Check the input
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.Tittle)).perform(typeText("Tiitle1"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText("Content1"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())

            //check whether it for LongClick....
            onView(withId(R.id.rec))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                        0,
                        ViewActions.longClick()
                    )
                )
            //check whether it Click or  not ....
            onView(withId(R.id.rec))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                        1,
                        ViewActions.click()
                    )
                )
            //check whether it will unclick the each which is in position "0"
            onView(withId(R.id.rec)).perform(
                (RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    ViewActions.click()
                ))
            )
            ///when the MutliItems are Selected it will check whether the Multiple DekketeBtn is Visible Or not
            onView(withId(R.id.Mutlidelete)).check(matches(isDisplayed()))
            onView(withId(R.id.Mutlidelete)).perform(click())
        }

        @Test
        fun Viewing_matches() {
            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.result)).check(matches(isDisplayed()))
            onView(withId(R.id.Note)).check(matches(isDisplayed()))

            //AddNotesPage
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

            //check the input
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
            onView(
                allOf(
                    isDescendantOfA(
                        allOf(
                            withId(R.id.relative),
                            hasDescendant(
                                allOf(
                                    withId(R.id.TittleBar),
                                    withText("Tittle")
                                )
                            )
                        )
                    ),

                    withId(R.id.deletebtn)
                )
            ).check(matches(isDisplayed())).perform(click())
            onView(withText("Yes")).perform(click())

            onView(withId(R.id.result)).check(matches(isDisplayed()))

        }

        @Test
        fun EditPage_BtnCheck() {
            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
            onView(withId(R.id.Note)).check(matches(isDisplayed()))

            //AddNotes
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

            //check the Input
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
        fun EditPage_TittleAndcontent() {
            ///Check the AddBtn ....Go to Addotes Page
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

            ///Check the Input
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
            onView(
                allOf(
                    isDescendantOfA(
                        allOf(
                            withId(R.id.relative),
                            hasDescendant(
                                allOf(
                                    withId(R.id.TittleBar),
                                    withText("Tittle")
                                )
                            )
                        )
                    ),
                    withId(R.id.updateBtn)
                )
            ).check(matches(isDisplayed())).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.Tittle)).perform(typeText(" Tittle1"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText(" Content1"), closeSoftKeyboard())
            onView(withId(R.id.EditBtn)).perform(click())

        }

        @Test
        fun EditPage_checkTittleAndcontent() {


            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))

            //check the delete All Btn it will open the Dailog Buider and click the " Yes " anf it will delete All the existing Notes...
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())

            //it will check whether the result it means the content will displayed when their is not items or Notes in the RecyclerView
            onView(withId(R.id.result)).check(matches(isDisplayed()))

            //When we click it ..it will go to AddNotes Page...
            onView(withId(R.id.addbtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

            //Check the input ...
            onView(withId(R.id.Tittle)).perform(typeText("Tittle"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText("Content"), closeSoftKeyboard())
            onView(withId(R.id.saveButton)).perform(click())

            //It will check the RecyclerView in postion "0"...it will go to Edit Notes Page
            onView(withId(R.id.rec))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                        0,
                        ViewActions.click()
                    )
                )
            Thread.sleep(2000)

            ///It will check whether the EditNotes Page it diaplyed Or Not
            onView(withId(R.id.EditBtn)).check(matches(isDisplayed())).perform(click())
            Thread.sleep(2000)

            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
            Thread.sleep(2000)
            //It will check the Parent relative of the items... using relative has a Parent ..it will search the child with Id...check it will displayed Or Not
            onView(
                allOf(
                    isDescendantOfA(
                        allOf(
                            withId(R.id.relative),
                            hasDescendant(
                                allOf(
                                    withId(R.id.TittleBar),
                                    withText("Tittle")
                                )
                            )
                        )
                    ),
                    ///It will check whether the EditNotes Page it diaplyed Or Not
                    withId(R.id.updateBtn)
                )
            ).check(matches(isDisplayed())).perform(click())

            //Go Edit Note Page
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

            //Edit the Input
            onView(withId(R.id.Tittle)).perform(typeText(" EditTittle"), closeSoftKeyboard())
            onView(withId(R.id.content)).perform(typeText(" EditContent"), closeSoftKeyboard())
            onView(withId(R.id.EditBtn)).perform(click())

            //It will check the EditTittle and EditContent is correct or not...
            onView(
                allOf(
                    isDescendantOfA(
                        allOf(
                            withId(R.id.relative),
                            hasDescendant(
                                allOf(
                                    withId(R.id.Tittle),
                                    withText("Tittle EditTittle")
                                )
                            )
                        )
                    ),
                    //
                    withId(R.id.content),
                    withText("Content EditContent")
                )
            )

        }

        @Test
        fun DeleteAllNotesBtn_Check() {
            //AddNotes
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

            //Delete AllBtn....
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.result)).check(matches(isDisplayed()))
        }

        @Test
        fun EditPage_TittleAndcontentNull() {
            onView(withId(R.id.addbtn)).check(matches(isDisplayed()))
            onView(withId(R.id.Note)).check(matches(isDisplayed()))

            //DeleteAll Btn...
            onView(withId(R.id.DeleteAllbtn)).perform(click())
            onView(withText("Yes")).perform(click())
            onView(withId(R.id.result)).check(matches(isDisplayed()))

            //AddNotes Btn
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
            onView(
                allOf(
                    isDescendantOfA(
                        allOf(
                            withId(R.id.relative),
                            hasDescendant(
                                allOf(
                                    withId(R.id.TittleBar),
                                    withText("Tittle")
                                )
                            )
                        )
                    ),
                    withId(R.id.updateBtn)
                )
            ).check(matches(isDisplayed())).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.saveButton)).check(matches(not(isDisplayed())))
            onView(withId(R.id.AddNote)).check(matches(withText("Edit Note")))
            onView(withId(R.id.Tittle)).perform(clearText())
            onView(withId(R.id.content)).perform(clearText())
            onView(withId(R.id.EditBtn)).perform(click())
            onView(withId(R.id.NewNotes)).check(matches(isDisplayed()))

        }



}

