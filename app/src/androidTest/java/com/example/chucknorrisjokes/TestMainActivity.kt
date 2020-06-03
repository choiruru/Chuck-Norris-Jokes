package com.example.chucknorrisjokes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.example.chucknorrisjokes.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestMainActivity{

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldBeAbleToLaunchMainScreen() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
    }

//    @Test
//    fun shouldBeAbleToLoadMovies() {
//        onView(withId(R.id.list)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToScrollViewMovieDetails() {
//        onView(withId(R.id.list)).perform(RecyclerViewActions
//                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
//        onView(withText(R.string.summary)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToDisplayTrailerLabel() {
//        onView(withId(R.id.list)).perform(RecyclerViewActions
//                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
//        onView(withText(R.string.trailers)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToDisplayCastLabel() {
//        onView(withId(R.id.list)).perform(RecyclerViewActions
//                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
//        onView(withText(R.string.cast)).perform(nestedScrollTo()).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToDisplayCast() {
//        onView(withId(R.id.list)).perform(RecyclerViewActions
//                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
//        onView(withId(R.id.cast_list)).perform(nestedScrollTo()).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToDisplayPersonDetail() {
//        onView(withId(R.id.list)).perform(RecyclerViewActions
//                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
//        onView(withId(R.id.cast_list)).perform(nestedScrollTo()).perform(RecyclerViewActions
//                .actionOnItemAtPosition<CastAdapter.CastViewHolder>(2, click()))
//        onView(withText(R.string.biography)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToChangeTabAndViewDetails() {
//        onView(withId(R.id.action_upcoming)).perform(click())
//        onView(withId(R.id.list)).perform(RecyclerViewActions
//                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
//        onView(withText(R.string.summary)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldBeAbleToSearchItem() {
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java))
//                .perform(typeText("Ironman"),
//                        pressImeActionButton())
//        onView(withId(R.id.list)).check(matches(isDisplayed()))
//    }
}
