package fr.eseo.b3.mlbcpg.ourblindtest

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion.ListOfQuestion
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepository
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoriesRoomImp
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OurBlindTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(


                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val application = LocalContext.current.applicationContext as Application
    val repository = OurBlindTestRepositoriesRoomImp(application)
    val setting: Setting = Setting(5,"null","Hollow kight")

    val viewModel : InGameViewModel = viewModel(
        factory = InGameViewModelFactory(
            (InGameRepositoryListImpl())
        )
    )

    val test = viewModel.nextQuestion()
    Log.d("test", test.toString())

    val question = ListOfQuestion().generateQuestions(setting)
    Log.d("question", question.toString())





    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OurBlindTestTheme {
        Greeting("Android")
    }
}