package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.data.S
import com.example.sih.ui.components.Gap
import com.example.sih.ui.components.Gutter
import com.example.sih.ui.theme.*

@Composable
fun HomeScreen(onAsk: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(16)

        // Header
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Climora", style = T.H1, color = Accent, modifier = Modifier.weight(1f))
            Box(
                Modifier.size(44.dp).clip(CircleShape).background(Sand),
                contentAlignment = Alignment.Center
            ) { Text("हिं", style = T.Meta, color = Ink) }
        }

        Gap(40)

        // Today, stated quietly
        Text(S("district"), style = T.Meta, color = Meta)
        Gap(8)
        Text(S("today_line"), style = T.H1, color = Ink)
        Gap(16)
        Icon(
            Icons.Outlined.WbCloudy, null,
            tint = Accent, modifier = Modifier.size(44.dp)
        )

        // Deliberately empty
        Spacer(Modifier.weight(1f))

        // The invitation
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Sand),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(116.dp)
                        .clip(CircleShape)
                        .background(Accent)
                        .clickable(onClick = onAsk),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Mic, null, tint = Page, modifier = Modifier.size(48.dp))
                }
            }
            Gap(24)
            Text(S("ask"), style = T.BodyL, color = Secondary)
            Gap(6)
            Text(S("or_type"), style = T.Meta, color = Meta, textAlign = TextAlign.Center)
        }

        Spacer(Modifier.weight(1f))

        // Text fallback
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(SurfaceGrey)
                .clickable(onClick = onAsk)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Keyboard, null, tint = Meta, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
            Text(S("type_hint"), style = T.Body, color = Meta)
        }

        Gap(16)
    }
}