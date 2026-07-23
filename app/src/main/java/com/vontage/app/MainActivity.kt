package com.vontage.app

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vontage.app.presentation.home.HomeScreen
import com.vontage.app.ui.theme.VontageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VontageTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    HomeScreen { uri: Uri ->
                        // TODO: Navigate to Editor
                    }
                }
            }
        }
    }
}
