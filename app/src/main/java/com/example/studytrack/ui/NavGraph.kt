package com.example.studytrack.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studytrack.MainScreen
import com.example.studytrack.data.StudySession

@Composable
fun NavGraph(viewModel: MainScreenViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        // Main Screen
        composable("MainScreen") {
            MainScreen(
                viewModel = viewModel,
                onNavigateToRegistration = {
                    navController.navigate("RegistrationScreen")
                }
            )
        }

        // Registration Screen
        composable("RegistrationScreen") {
            RegistrationScreen(
                onSave = { date, duration, subject ->
                    viewModel.addSession(
                        StudySession(
                            date = date,
                            duration = duration,
                            subject = subject
                        )
                    )
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
