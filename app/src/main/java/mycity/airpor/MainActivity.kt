package mycity.airpor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import io.paperdb.Paper
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import mycity.airpor.data.DataChecker
import mycity.airpor.ui.theme.NavigationFile
import mycity.airpor.ui.theme.SevenScreen
import mycity.airpor.ui.theme.UnexploredGizaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataChecker = DataChecker(this)

        val scope = MainScope()
        scope.launch {
            Paper.init(applicationContext)
            dataChecker.initialProcess()
        }

        setContent {
            UnexploredGizaTheme {
                // A surface container using the 'background' color from the theme
                NavigationFile()
            }
        }
    }

    companion object{
        val transmitter = flowOf("empty")
    }
}

