package com.example.monsterhunter

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.monsterhunter.Views.MainActivity
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