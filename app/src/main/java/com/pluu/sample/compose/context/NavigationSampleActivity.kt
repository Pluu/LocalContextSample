package com.pluu.sample.compose.context

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pluu.sample.compose.context.ui.theme.SampleTheme
import kotlinx.serialization.Serializable

class NavigationSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTheme {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(fraction = 0.5f)
                    ) {
                        ContextText("Activity")
                    }

                    HorizontalDivider()

                    NavigationScreen(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(fraction = 1f)
                    )
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, NavigationSampleActivity::class.java))
        }
    }
}

@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen1,
        modifier = modifier,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        composable<Screen1> {
            NavigationItem(beforeText = "Screen1 Navigation") {
                navController.navigate(Screen2)
            }
        }
        composable<Screen2> {
            NavigationItem(beforeText = "Screen2 Navigation") {
                navController.popBackStack(Screen1, false)
            }
        }
    }
}

@Composable
fun NavigationItem(
    beforeText: String,
    modifier: Modifier = Modifier,
    onClink: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ContextText(beforeText) {
            Button(onClink) { Text("Navigate") }
        }
    }
}

@Serializable
data object Screen1

@Serializable
data object Screen2