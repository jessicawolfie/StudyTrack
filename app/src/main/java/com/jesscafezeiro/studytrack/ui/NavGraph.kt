package com.jesscafezeiro.studytrack.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesscafezeiro.studytrack.MainScreen
import com.jesscafezeiro.studytrack.data.StudySession

@Composable
fun NavGraph(viewModel: MainScreenViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "SplashScreen"
    ) {
        // Splash Screen
        composable("SplashScreen") {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate("MainScreen") {
                        // Limpa a splash do histórico para não voltar ao clicar em "back"
                        popUpTo("SplashScreen") { inclusive = true }
                    }
                }
            )
        }

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
