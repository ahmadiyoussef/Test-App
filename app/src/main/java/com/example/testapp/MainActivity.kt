package com.example.testapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.testapp.login.LoginScreen
import com.example.testapp.medicineDetail.MedicineDetailScreen
import com.example.testapp.medicineList.MedicineListScreen
import com.example.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                AppNavigation()

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        NavHost(navController, startDestination = "login_screen") {
            composable("login_screen") {
                LoginScreen { username ->
                    navController.navigate("greeting/$username")
                }
            }

            composable("greeting/{username}") { backStackEntry ->
                val username = backStackEntry.arguments?.getString("username") ?: "User"
                MedicineListScreen(username) { medicine ->
                    navController.navigate("medicine/${medicine.name}/${medicine.dose}/${medicine.strength}")
                }
            }

            composable("medicine/{name}/{dose}/{strength}") { backStackEntry ->
                val medicineName = backStackEntry.arguments?.getString("name") ?: "Unknown"
                val dose = backStackEntry.arguments?.getString("dose") ?: "Unknown"
                val strength = backStackEntry.arguments?.getString("strength") ?: "Unknown"

                MedicineDetailScreen(medicineName, dose, strength)
            }
        }
    }


}

