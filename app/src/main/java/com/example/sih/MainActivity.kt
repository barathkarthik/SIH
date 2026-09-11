package com.example.sih

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sih.nav.ClimoraApp
import com.example.sih.ui.theme.ClimoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClimoraTheme {
                ClimoraApp()
            }
        }
    }
}
