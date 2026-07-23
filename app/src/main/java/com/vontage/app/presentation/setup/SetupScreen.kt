package com.vontage.app.presentation.setup

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SetupScreen(
    onSetupComplete: () -> Unit
) {
    // Permission states
    val videoPermissionState = rememberPermissionState(
        Manifest.permission.READ_MEDIA_VIDEO
    )

    val notificationPermissionState = rememberPermissionState(
        Manifest.permission.POST_NOTIFICATIONS
    )

    // Check if permissions are granted (Android 13+)
    val isVideoGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        videoPermissionState.status.isGranted
    } else {
        true // Older Android doesn't need this specific permission
    }

    val isNotificationGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        notificationPermissionState.status.isGranted
    } else {
        true
    }

    val allPermissionsGranted = isVideoGranted && isNotificationGranted

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Logo / Icon
            Text(
                text = "🎬",
                fontSize = 72.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Welcome to Vontage",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Your professional video editor",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Permission Cards
            SetupPermissionCard(
                icon = "📁",
                title = "Storage Access",
                description = "Vontage needs access to read and save videos from your device.",
                isGranted = isVideoGranted,
                onRequest = { videoPermissionState.launchPermissionRequest() }
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                SetupPermissionCard(
                    icon = "🔔",
                    title = "Notifications",
                    description = "Vontage uses notifications to show export progress.",
                    isGranted = isNotificationGranted,
                    onRequest = { notificationPermissionState.launchPermissionRequest() }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = onSetupComplete,
                enabled = allPermissionsGranted,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (allPermissionsGranted) "🚀 Start Editing" else "Grant Permissions to Continue")
            }

            if (!allPermissionsGranted) {
                Text(
                    text = "Please grant all permissions to continue",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
fun SetupPermissionCard(
    icon: String,
    title: String,
    description: String,
    isGranted: Boolean,
    onRequest: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isGranted) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 28.sp)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(title, fontWeight = FontWeight.Medium)
                    Text(description, style = MaterialTheme.typography.bodySmall)
                }
            }
            if (isGranted) {
                Text("✅", fontSize = 24.sp)
            } else {
                Button(onClick = onRequest, modifier = Modifier.height(36.dp)) {
                    Text("Allow")
                }
            }
        }
    }
}
