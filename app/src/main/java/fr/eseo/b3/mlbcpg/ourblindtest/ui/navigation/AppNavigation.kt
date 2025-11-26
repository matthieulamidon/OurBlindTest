package fr.eseo.b3.mlbcpg.ourblindtest.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoriesRoomImp
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.MainScreen
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.QuizScreen
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.ResultScreen
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as Application
    val ourBlindTestVM: OurBlindTestViewModel = viewModel(
        factory = OurBlindTestViewModelFactory(OurBlindTestRepositoryListImpl())
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(onStartQuiz = {
                navController.navigate("quiz")
            },ourBlindTestVM)
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
