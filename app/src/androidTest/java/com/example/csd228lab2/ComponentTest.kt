package com.example.csd228lab2

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onSiblings
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.csd228lab2.ui.screens.ConvoListScreen
import com.example.csd228lab2.ui.screens.CreateUserScreen
import com.example.csd228lab2.ui.theme.CSD228Lab2Theme
import com.example.csd228lab2.ui.viewmodels.ConvoViewModel
import com.example.csd228lab2.ui.viewmodels.CreateUserViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentTest {

    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun testCreateUserScreen() {
        composeRule.setContent {
            CreateUserScreen(cb = {})
        }
        composeRule.onNodeWithTag("createUserScreen").assertExists()
        composeRule.onNodeWithText("Create User")
    }
}

