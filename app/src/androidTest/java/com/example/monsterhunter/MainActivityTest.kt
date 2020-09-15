package com.example.monsterhunter

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun orientationChange() {
        UITestHelper.changeOrientation(activityRule)
    }
    @Test
    fun recyclerViewItemClickTest() {
        UITestHelper.recyclerTest(activityRule)
    }
}