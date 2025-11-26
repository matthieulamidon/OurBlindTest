package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.QuizViewModel

@Composable
fun QuizScreen(onFinish: () -> Unit, InGameVM : InGameViewModel)
    {
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
                    QuizScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onFinish = onFinish,
                        answers = listOf("Réponse A", "Réponse B", "Réponse C", "Réponse D"),
                        InGameVM = InGameVM
                    )
                }
            }
        )
    }
}

@Composable
private fun QuizScreenContent(
    modifier: Modifier,
    viewModel: QuizViewModel = viewModel(),
    answers: List<String>,
    onFinish: () -> Unit,
    InGameVM: InGameViewModel
) {
    val question by viewModel.currentQuestion

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Zone Timer + Animation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Animation ici", fontSize = 18.sp)
                Text("Timer ici", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Question
        Text(
            text = question,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(Modifier.height(40.dp))

        // Réponses
        answers.forEach { answer ->
            AnswerButton(
                text = answer,
                onClick = {
                    viewModel.answerCorrect()
                    onFinish()
                }
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}


@Composable
fun AnswerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(text, fontSize = 24.sp)
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    OurBlindTestTheme {
        val fakeViewModel = QuizViewModel()
        val fakeViewModelInGame = InGameViewModel(InGameRepositoryListImpl())
        OurBlindTestAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )
        QuizScreenContent(
            modifier = Modifier.fillMaxWidth(),
            viewModel = fakeViewModel,
            onFinish = {},
            answers = listOf("Réponse A", "Réponse B", "Réponse C", "Réponse D"),
            InGameVM = fakeViewModelInGame
        )
    }
}



