package com.example.anew.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.anew.ui.theme.NewTheme
import com.example.anew.navigation.AppNavigation

@Composable
fun AppContent() {
    var isDarkTheme by remember { mutableStateOf(false) }

    NewTheme(darkTheme = isDarkTheme) {
        AppNavigation(
            isDarkTheme = isDarkTheme,
            onToggleTheme = { isDarkTheme = it }
        )
    }
}
