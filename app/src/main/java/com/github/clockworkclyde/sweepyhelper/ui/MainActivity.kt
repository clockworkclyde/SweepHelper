package com.github.clockworkclyde.sweepyhelper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeScreen
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewModel
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsScreen
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import com.github.clockworkclyde.sweepyhelper.ui.theme.SweepyHelperTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SweepyHelperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "rooms") {
                        composable("rooms") {
                            RoomsScreen(
                                navController = navController,
                                viewModel = getViewModel<RoomsViewModel>()
                            )
                        }

                        composable("compose") {
                            ComposeScreen(
                                navController = navController,
                                viewModel = getViewModel<ComposeViewModel>()
                            )
                        }
                    }
                }
            }
        }
    }
}