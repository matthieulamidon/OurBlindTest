package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.R.attr.onClick
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import fr.eseo.b3.mlbcpg.ourblindtest.R
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepository
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoriesRoomImp
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepository
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.OurBlindTestAppBar
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModelFactory
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModelFactory
import java.time.LocalDateTime

@Composable
fun MainScreen(onStartQuiz: () -> Unit,
   InGameVm: InGameViewModel,
   OurBlindTestVm : OurBlindTestViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold (
            topBar = {
                OurBlindTestAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    MainScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onStartQuiz = onStartQuiz,
                        InGameVm =  InGameVm,
                        OurBlindTestVm = OurBlindTestVm
                    )
                }
            }
        )
    }
}

@Composable
private fun MainScreenContent(
    modifier: Modifier,
    onStartQuiz: () -> Unit,
    InGameVm: InGameViewModel,
    OurBlindTestVm: OurBlindTestViewModel
) {
    var pseudo by remember { mutableStateOf("") }

    // récupération des scores via le viewModel
    val scoreList by OurBlindTestVm.scores.collectAsState()

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Affichage du tableau des scores
            Text(
                "Tableau des scores",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))
            ScoreTable(scoreList)

            Spacer(Modifier.height(40.dp))

            // Titre
            Text("Blind Test 🎵", fontSize = 28.sp)

            Spacer(Modifier.height(16.dp))

            // Pseudo
            TextField(
                value = pseudo,
                onValueChange = { pseudo = it },
                label = { Text("Pseudo") }
            )

            Spacer(Modifier.height(16.dp))

            // Bouton Start
            Button(
                onClick = onStartQuiz,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Commencer le quiz")
            }

            Spacer(Modifier.height(32.dp))

            // Settings
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Settings")
            }
        }
    }
}

@Composable
fun ScoreTable(scores: List<Score>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        scores.forEach { score ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(score.name, fontSize = 18.sp)
                Text("${score.score} pts", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

class FakeRepo : OurBlindTestRepository {
    private val fakeData = listOf(
        Score(name = "Test", score = 12),
        Score(name = "Preview", score = 5)
    )

    override suspend fun getAllScore() = fakeData

    override suspend fun addOrUpdateScore(score: Score) {}
    override suspend fun deleteScore(score: Score) {}
    override suspend fun getScoreById(id: String) = null
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeViewModel = OurBlindTestViewModel(repository = FakeRepo())
    val fakeViewModelInGame = InGameViewModel(repository = InGameRepositoryListImpl())

    // Force l'init directe
    fakeViewModel.refreshScores()

    OurBlindTestTheme {
        MainScreen(
            onStartQuiz = {},
            InGameVm = fakeViewModelInGame,
            OurBlindTestVm = fakeViewModel
        )
    }
}


 /*
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun preview() {
    OurBlindTestTheme {  {
        val viewModel : OurBlindTestViewModel = viewModel(
            factory = NoteTakerViewModelFactory(NoteTakerRepositoryListImpl())
        )
        DetailsScreen(rememberNavController(), "1", viewModel)
    }
}
*/

























