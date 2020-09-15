package com.example.monsterhunter

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Test

class UITestHelper {
    companion object {

        fun <T : Activity> changeOrientation(activityRule: ActivityTestRule<T>) {
            activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            assert(activityRule.activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            assert(activityRule.activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }

        fun <T : Activity> recyclerTest(activityRule: ActivityTestRule<T>) {
            fun getRVcount(): Int {
                val recyclerView = activityRule.getActivity()
                    .findViewById(R.id.recycler_view) as RecyclerView
                return recyclerView.adapter!!.itemCount
            }
            if (getRVcount() > 0) {
                Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        1,
                        ViewActions.click()
                    )
                )
            }



        }
    }
}