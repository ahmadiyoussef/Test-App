package com.example.testapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
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
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login_screen"
                )  {
                    composable("login_screen") {
                        LoginScreen { username ->
                            navController.navigate("greeting/$username")
                        }
                    }

                    composable("greeting/{username}") { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: "User"
                        MedicineListScreen(username) { medicineName ->
                            navController.navigate("medicine/$medicineName")
                        }
                    }
                    composable("medicine/{name}") { backStackEntry ->
                        val medicineName = backStackEntry.arguments?.getString("name") ?: "Medicine"
                        MedicineDetailScreen(medicineName)
                    }

                }
            }
        }
    }


}

