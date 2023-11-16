//package com.example.mrnotes.ActivityTest
//
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.action.ViewActions.typeText
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import com.example.mrnotes.R
//import com.example.mrnotes.databinding.EachtemsBinding
//import org.junit.Rule
//import org.junit.Test
//
//class YourActivityTest {
//
////    @get:Rule
//////    val activityRule = ActivityScenarioRule(testEachItemBinding()::class.java)
//
//    @Test
//    fun testEachItemBinding() {
//        // Inflate the EachtemsBinding layout
////        val binding = EachtemsBinding.inflate(activityRule.scenario)
//
//        // Check if the TittleBar TextView is displayed
//        onView(withId(binding.TittleBar.id)).check(matches(isDisplayed()))
//
//        // Perform an action on the delete button
//        onView(withId(binding.deletebtn.id)).perform(click())
//
//        // Perform an action on the update button
//        onView(withId(binding.updateBtn.id)).perform(click())
//
//        // Enter text into the contentBar TextView
//        onView(withId(binding.contentBar.id)).perform(typeText("Test Content"))
//
//        // Check if the contentBar TextView displays the entered text
//        onView(withId(binding.contentBar.id)).check(matches(withText("Test Content")))
//    }
//}
