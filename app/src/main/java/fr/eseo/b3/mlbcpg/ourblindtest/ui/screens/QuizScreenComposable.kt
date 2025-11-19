package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.QuizViewModel

@Composable
fun QuizScreen(onFinish: () -> Unit) {
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
                        onFinish = onFinish
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
    onFinish: () -> Unit
) {
    val question by viewModel.currentQuestion

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = question, fontSize = 22.sp)
        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            viewModel.answerCorrect()
            onFinish()
        }) {
            Text("Répondre : Correct ✔")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = onFinish) {
            Text("Répondre : Incorrect ❌")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    OurBlindTestTheme {
        val fakeViewModel = QuizViewModel() // ok en preview si ton VM a un constructeur sans param

        QuizScreenContent(
            modifier = Modifier.fillMaxWidth(),
            viewModel = fakeViewModel,
            onFinish = {}
        )
    }
}


