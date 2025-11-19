package fr.eseo.b3.mlbcpg.ourblindtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.MainScreen
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.QuizScreen
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.ResultScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(onStartQuiz = {
                navController.navigate("quiz")
            })
        }
        composable("quiz") {
            QuizScreen(
                onFinish = {
                    navController.navigate("results")
                }
            )
        }
        composable("results") {
            ResultScreen(
                onRestart = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}
