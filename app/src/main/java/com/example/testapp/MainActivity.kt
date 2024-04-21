package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testapp.login.LoginScreen
import com.example.testapp.ui.theme.TestAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                LoginScreen(onLogin = ::onLoginSuccess)
            }
        }
    }

    private fun onLoginSuccess(username: String) {
        // Handle the login success event
        println("User logged in: $username")
    }
}

