package com.vikanshu.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {

                val weatherRepository: WeatherRepository = koinInject()

                LaunchedEffect(key1 = true) {
                    weatherRepository.getWeatherInformation("gurgaon").collectLatest {
                        Log.e("VIKANSHU", Gson().toJson(it))
                    }
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        Greeting("Android")
    }
}