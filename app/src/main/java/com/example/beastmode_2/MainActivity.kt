package com.example.beastmode_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.beastmode_2.Presentation.AndroidSplashScreen.FeastModeSplashScreen
import com.example.beastmode_2.Presentation.Navigation.RecipeNavHost
import com.example.beastmode_2.Presentation.Screens.HomeScreen.HomeScreen
import com.example.beastmode_2.Presentation.Screens.HomeScreen.Homeheader
import com.example.beastmode_2.ui.theme.BeastMode_2Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeastMode_2Theme {
                var showSplash by remember { mutableStateOf(true) }
                if (showSplash) {
                    FeastModeSplashScreen(
                        onFinished = {
                            showSplash = false
                        }
                    )
                } else {
                    RecipeNavHost(application)
                }
            }
        }
    }
}
