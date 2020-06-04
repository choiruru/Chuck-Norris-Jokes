package com.example.chucknorrisjokes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.chucknorrisjokes.ui.main.MainActivity
import com.example.chucknorrisjokes.ui.main.MainCategoryAdapter
import com.example.chucknorrisjokes.ui.search.SearchJokeAdapter
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class TestMainActivity : TestBase() {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLaunchMainFragment(){

        //check main Fragment visible
        onView(withId(R.id.motion_base)).check(matches(isDisplayed()))

        //click button (I WANT ANOTHER JOKE)
        onView(withId(R.id.btnNext)).perform(click())

        //swipe up category
        onView(withId(R.id.motion_base)).perform(swipeUp())

        //check list category visible
        onView(allOf(withId(R.id.rvCategory))).check(matches(isDisplayed()))

        //select category in list
        onView(withId(R.id.rvCategory)).perform(RecyclerViewActions
            .actionOnItemAtPosition<MainCategoryAdapter.CategoryViewHolder>(2, click()))

        //click search icon
        onView(withId(R.id.actionSearch)).perform(click())

        //type in searchView
        onView(withId(R.id.searchView)).perform(click())
        onView(withId(R.id.searchView)).perform(typeText("sun"))

        //select joke in list and goto detail joke
        onView(withId(R.id.rvJokes)).check(matches(isDisplayed()))
        onView(withId(R.id.rvJokes)).perform(RecyclerViewActions
            .actionOnItemAtPosition<SearchJokeAdapter.JokeViewHolder>(2, click()))

        //back to search fragment
        pressBack()

        //back to main fragment
        pressBack()

        //click button (SHARE THIS JOKE)
        onView(withId(R.id.btnShare)).perform(click())

    }
}