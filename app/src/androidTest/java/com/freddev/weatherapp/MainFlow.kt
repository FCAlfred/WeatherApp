package com.freddev.weatherapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFlow {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private val cityName =
        InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.morelia_city)
    private val searchButtonText =
        InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.search_button)
    private val locationText =
       "Location: \nMorelia, Mexico"

    @Test
    fun mainFlow() {
        onView(withId(R.id.edit_text)).perform(replaceText(cityName), closeSoftKeyboard())

        onView(withId(R.id.edit_text)).check(matches(withText(cityName)))
            .perform(pressImeActionButton())

        onView(withId(R.id.button_map)).check(matches(withText(searchButtonText))).perform(click())

        onView(withId(R.id.text_view_city)).check(matches(withText(locationText)))
    }
}
