package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.filled.Air
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.data.Severity
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun AlertsListScreen(onOpenAlert: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Page)) {
        Gap(12)
        Row(
            Modifier.fillMaxWidth().padding(horizontal = Gutter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("चेतावनी", style = T.H2, color = Ink, modifier = Modifier.weight(1f))
        }
        Gap(8)
        Text(
            "नाशिक जिला की चेतावनियाँ",
            style = T.Body, color = Secondary,
            modifier = Modifier.padding(horizontal = Gutter)
        )
        Gap(20)
        Rule()

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Demo.alerts.forEachIndexed { i, alert ->
                Gap(24)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Gutter)
                        .clickable(enabled = i == 0, onClick = onOpenAlert),
                    verticalAlignment = Alignment.Top
                ) {
                    IconBubble(
                        icon = when (alert.severity) {
                            Severity.ORANGE -> Icons.Filled.WarningAmber
                            Severity.YELLOW -> Icons.Filled.Air
                            else -> Icons.Filled.Check
                        },
                        tint = severityColour(alert.severity),
                    )
                    Spacer(Modifier.width(16.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            when (alert.severity) {
                                Severity.ORANGE -> "ऑरेंज अलर्ट"
                                Severity.YELLOW -> "येलो अलर्ट"
                                else -> "सामान्य"
                            },
                            style = T.Meta, color = severityColour(alert.severity)
                        )
                        Gap(6)
                        Text(alert.title, style = T.BodyL, color = Ink)
                        Gap(6)
                        Text(alert.time, style = T.Meta, color = Meta)
                        if (alert.read) {
                            Gap(10)
                            SandPill("पढ़ ली")
                        }
                    }
                }
                Gap(24)
                if (i < Demo.alerts.lastIndex) Rule()
            }

            Gap(24)
            Text(
                "पुरानी चेतावनियाँ तीस दिन तक यहीं रहती हैं।",
                style = T.Meta, color = Meta,
                modifier = Modifier.fillMaxWidth().padding(horizontal = Gutter),
            )
            Gap(40)
        }
    }
}

