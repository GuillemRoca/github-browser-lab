package dev.guillem.githubbrowserlab.presentation.main

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.guillem.githubbrowserlab.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun checkContainerMain() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.container)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerview_repositories)).check(matches(isDisplayed()))
    }

    @Test
    fun checkLongPressFirstElement() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.recyclerview_repositories)).check(matches(isDisplayed()))

        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerview_repositories)).perform(
            actionOnItemAtPosition<RepositoryAdapter.RepositoryViewHolder>(
                0,
                longClick()
            )
        )
    }

    @Test
    fun checkScrollToElementTen() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.recyclerview_repositories))
            .check(matches(isDisplayed()))

        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerview_repositories)).perform(
            actionOnItemAtPosition<RepositoryAdapter.RepositoryViewHolder>(
                10,
                scrollTo()
            )
        )
    }

    @Test
    fun checkDialogDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerview_repositories)).perform(
            actionOnItemAtPosition<RepositoryAdapter.RepositoryViewHolder>(
                0,
                longClick()
            )
        )

        onView(withText(R.string.dialog_repository_title)).check(matches(isDisplayed()))
        onView(withText(R.string.dialog_repository_description)).check(matches(isDisplayed()))
    }
}