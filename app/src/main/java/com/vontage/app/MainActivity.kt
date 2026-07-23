package com.vontage.app

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vontage.app.presentation.home.HomeScreen
import com.vontage.app.presentation.setup.SetupScreen
import com.vontage.app.presentation.setup.SetupViewModel
import com.vontage.app.ui.theme.VontageTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VontageTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    VontageApp()
                }
            }
        }
    }
}

@Composable
fun VontageApp() {
    val setupViewModel: SetupViewModel = viewModel()
    val isFirstLaunch by setupViewModel.isFirstLaunch.collectAsState(initial = true)
    val coroutineScope = rememberCoroutineScope()

    // State to track if we're showing setup
    var showSetup by remember { mutableStateOf(isFirstLaunch) }
    var selectedVideoUri by remember { mutableStateOf<Uri?>(null) }

    when {
        showSetup -> {
            SetupScreen(
                onSetupComplete = {
                    coroutineScope.launch {
                        setupViewModel.markSetupCompleted()
                        showSetup = false
                    }
                }
            )
        }
        else -> {
            HomeScreen(
                onVideoSelected = { uri ->
                    selectedVideoUri = uri
                    // 🚧 Phase 2: Navigate to EditorScreen with this URI
                }
            )
        }
    }
}
