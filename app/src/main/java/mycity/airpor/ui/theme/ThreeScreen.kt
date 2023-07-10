package mycity.airpor.ui.theme

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mycity.airpor.R
import mycity.airpor.game.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreeScreen() {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    val nameField = remember {
        mutableStateOf("Enter your name")
    }


    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.back_1080_1920),
            contentDescription = "back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )



        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
            

            Text(
                text = "Settings",
                fontSize = 32.sp,
                fontFamily = Constants.lemonFont,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(96.dp))

            TextField(
                value = nameField.value,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    textColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onValueChange = { text ->
                    nameField.value = text
                    sharedPref.edit().putString(Constants.SHARED_NAME, text).apply()
                }
            )




        }
    }
}