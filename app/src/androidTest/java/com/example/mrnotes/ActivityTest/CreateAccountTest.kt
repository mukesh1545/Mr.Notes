package com.example.mrnotes.ActivityTest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mrnotes.Activites.Login_Page
import com.example.mrnotes.Activites.splash
import com.example.mrnotes.R
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test

class CreateAccountTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(splash::class.java)
    @Test
    fun CreatePage_Display() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.Loginbtn)).check(matches(isDisplayed()))
        onView(withId(R.id.hello)).check(matches(isDisplayed()))
        onView(withId(R.id.image)).check(matches(isDisplayed()))
        onView(withId(R.id.ProgrssBar)).check(matches(not(isDisplayed())))


    }
    @Test
    fun CreatePage_InputCheck() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.Loginbtn)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshkumar.ee19@bitsathy.ac.in"),
           closeSoftKeyboard()
       )
        onView(withId(R.id.TextView_Password)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.Loginbtn)).perform(click())
        onView(withId(R.id.Login)).check(matches(isDisplayed()))



    }
    @Test
    fun CreatePage_MissmatchPasswordCheck() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.Loginbtn)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshkumar.ee19@bitsathy.ac.in"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_Password)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("123457"), closeSoftKeyboard())
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }
    @Test
    fun CreatePage_InvalidEmailIdCheck() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.Loginbtn)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshkumar.ee19bitsathy.ac.in"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_Password)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.TextView_Email_id)).check(matches(hasErrorText("Enter the valid Email id")))
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }
    @Test
    fun CreatePage_NullInput_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }

    @Test
    fun CreatePage_missmatchPasswordLength_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshkumar.ee19@bitsathy.ac.in"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_Password)).perform(typeText("12345"), closeSoftKeyboard())
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("12345"), closeSoftKeyboard())
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.TextView_Password)).check(matches(hasErrorText("Enter the valid Password")))
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }
    @Test
    fun CreatePage_missmatchconfirmPassword_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshkumar.ee19@bitsathy.ac.in"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_Password)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("12345"), closeSoftKeyboard())
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(hasErrorText("Confirm Password Not matched")))
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }

    @Test
    fun CreatePage_NewUser_Check(){
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))

        onView(withId(R.id.TextView_Email_id)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshharish3@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_Password)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.ProgrssBar)).check(matches((isDisplayed())))
        onView(withId(R.id.Create_Account_btn)).check(matches(not(isDisplayed())))
        Thread.sleep(2000)
        onView(withId(R.id.Create_Account_btn)).check(matches(isDisplayed()))
        Thread.sleep(9000)
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).check(matches(withText("")))
        onView(withId(R.id.TextView_Password)).check(matches(withText("")))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(withText("")))
    }

    @Test
    fun CreatePage_Invalid_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }

    @Test
    fun CreatePage_InvalidPassword_check() {
        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_Email_id)).perform(typeText("mukeshharish3@gmail.com"),closeSoftKeyboard())
        onView(withId(R.id.TextView_Password)).check(matches(isDisplayed()))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.TextView_Password)).check(matches(hasErrorText("Enter the valid Password")))
        Thread.sleep(1000)
        onView(withId(R.id.create_Activity)).check(matches(isDisplayed()))
    }

    @Test
    fun testEmailPasswordInput() {

        var activityRule = ActivityScenario.launch(splash::class.java)
        var activityRule1 = ActivityScenario.launch(Login_Page::class.java)
        onView(withId(R.id.SignUp_btn)).check(matches(isDisplayed())).perform(ViewActions.click())
        // Type email and password into the respective TextViews
        onView(withId(R.id.TextView_Email_id)).perform(typeText("test@example.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_Password)).perform(typeText("123"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.TextView_ConfrimPassword)).perform(typeText("123"),
            closeSoftKeyboard()
        )


        // Perform an action that triggers your validation logic
        onView(withId(R.id.Create_Account_btn)).perform(click())
        onView(withId(R.id.ProgrssBar)).check(matches(not(isDisplayed())))

        // Check if the email, password, and confirm password fields contain the expected text
        onView(withId(R.id.TextView_Email_id)).check(matches(withText("test@example.com")))
        onView(withId(R.id.TextView_Password)).check(matches(withText("123")))
        onView(withId(R.id.TextView_ConfrimPassword)).check(matches(withText("123")))
    }

}