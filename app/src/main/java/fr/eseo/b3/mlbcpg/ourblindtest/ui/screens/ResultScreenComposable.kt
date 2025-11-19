package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.QuizViewModel

@Composable
fun ResultScreen(
    viewModel: QuizViewModel = viewModel(),
    onRestart: () -> Unit
) {
    val score by viewModel.score

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Résultat 🎉", fontSize = 28.sp)
        Spacer(Modifier.height(16.dp))
        Text("Score : $score", fontSize = 24.sp)
        Spacer(Modifier.height(24.dp))

        Button(onClick = onRestart) {
            Text("Rejouer")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val vm = QuizViewModel().apply { answerCorrect() } // score = 1
    ResultScreen(
        viewModel = vm,
        onRestart = {}
    )
}

