package com.example.mrnotes.ActivityTest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mrnotes.Activites.Login_Page
import com.example.mrnotes.Activites.splash
import com.example.mrnotes.R
import org.junit.Rule
import org.junit.Test


class LoginPageTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(splash::class.java)

    @Test
    fun Login_Display() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)

        onView(withId(R.id.Login_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.Login_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.Login_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.hello)).check(matches(isDisplayed()))
        onView(withId(R.id.image)).check(matches(isDisplayed()))


    }

    @Test
    fun Login_Check() {

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
    }

    @Test
    fun LoginPage_SignUp_Check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

    }

    @Test
    fun LoginPage_IvalidEmail_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.Login_Email_id)).perform(typeText("mukeshkumar.ee19bitsathy.ac.in"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.Login_Password)).perform(typeText("1234567"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.Login_btn)).perform(click())
        onView(withId(R.id.Login_Email_id)).check(matches(ViewMatchers.hasErrorText("Email Id Invalid")))
    }

    @Test
    fun LoginPage_IvalidEmailAndPassword_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
//        onView(withId(R.id.Login_Email_id)).perform(typeText("mukeshkumar.ee19bitsathy.ac.in"),
//            closeSoftKeyboard()
//        )
//        onView(withId(R.id.Login_Password)).perform(typeText("1234567"),
//            closeSoftKeyboard()
//        )
        onView(withId(R.id.Login_btn)).perform(click())
        Thread.sleep(2000)
//        onView(withId(R.id.Login_Email_id)).check(matches(ViewMatchers.hasErrorText("Email Id Invalid")))
    }

    @Test
    fun LoginPage_InvalidEmailIdVerification_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.Login_Email_id)).perform(typeText("mukeshharish3@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.Login_Password)).perform(typeText("1234567"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.Login_btn)).perform(click())
        Thread.sleep(4000)
    }

    @Test
    fun LoginPage_InvalidEmailIdAndPassword_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.Login_Email_id)).perform(typeText("mukeshharish@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.Login_Password)).perform(typeText("1234567"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.Login_btn)).perform(click())
        Thread.sleep(5000)
    }


}


