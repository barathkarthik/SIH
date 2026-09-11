package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.ui.components.Gap
import com.example.sih.ui.theme.*
import kotlinx.coroutines.delay

/**
 * Rises over the home screen. Types out the demo question character by
 * character, then calls [onDone] after a short pause so the caller can
 * navigate to the answer. No real speech recognition — this is the fake.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeningSheet(onCancel: () -> Unit, onDone: () -> Unit) {
    val state = rememberModalBottomSheetState()
    var shown by remember { mutableStateOf("") }
    val full = Demo.QUESTION

    LaunchedEffect(Unit) {
        delay(400)
        for (i in 1..full.length) {
            shown = full.substring(0, i)
            delay(45)
        }
        delay(700)
        onDone()
    }

    ModalBottomSheet(
        onDismissRequest = onCancel,
        sheetState = state,
        containerColor = Page,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Gap(24)

            // three fading rings around the mic, suggesting active capture
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.size(180.dp).clip(CircleShape).background(Sand.copy(alpha = 0.4f)))
                Box(Modifier.size(150.dp).clip(CircleShape).background(Sand.copy(alpha = 0.7f)))
                Box(Modifier.size(120.dp).clip(CircleShape).background(Sand))
                Box(
                    Modifier.size(96.dp).clip(CircleShape).background(Accent),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Mic, null, tint = Page, modifier = Modifier.size(40.dp))
                }
            }

            Gap(28)
            Text(
                shown.ifEmpty { " " },
                style = T.H2, color = Ink,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 30.dp)
            )
            Gap(12)
            Text("बोलते रहिए", style = T.Meta, color = Meta)
            Gap(24)
            Text(
                "रद्द करें", style = T.Meta, color = Meta,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onCancel)
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

