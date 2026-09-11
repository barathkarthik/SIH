package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun MineScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(12)
        Text("मेरा", style = T.H2, color = Ink)
        Gap(28)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(72.dp).clip(CircleShape).background(Sand),
                contentAlignment = Alignment.Center
            ) { Text("र", style = T.H1, color = Ink) }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(Demo.USER_NAME, style = T.H2, color = Ink)
                Gap(4)
                Text(Demo.USER_PHONE, style = T.Meta, color = Meta)
            }
        }

        Gap(32)
        Rule(Modifier.padding(horizontal = 0.dp))

        val rows = listOf(
            Triple(Icons.Filled.Language, "भाषा", Demo.language),
            Triple(Icons.Filled.LocationOn, "जिला", "नाशिक"),
            Triple(Icons.Filled.Eco, "फ़सल", Demo.CROP),
            Triple(Icons.Filled.VolumeUp, "आवाज़ में जवाब", "चालू"),
            Triple(Icons.Filled.Phone, "फ़ोन कॉल चेतावनी", "चालू"),
        )
        rows.forEachIndexed { i, (icon, label, value) ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, null, tint = Meta, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(16.dp))
                Text(label, style = T.Body, color = Ink, modifier = Modifier.weight(1f))
                Text(value, style = T.Body, color = Secondary)
                Spacer(Modifier.width(6.dp))
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
                    tint = Meta, modifier = Modifier.size(20.dp)
                )
            }
            if (i < rows.lastIndex) Rule(Modifier.padding(horizontal = 0.dp))
        }

        Gap(28)
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Sand)
                .padding(20.dp)
        ) {
            Text("हमारा नंबर", style = T.Meta, color = Ink)
            Gap(10)
            Text(Demo.PHONE, style = T.H1, color = Ink)
            Gap(10)
            Text(
                "चेतावनी की पुष्टि न होने पर इसी नंबर से कॉल आएगा। इसे अपने फ़ोन में सेव कर लीजिए।",
                style = T.Body, color = Secondary
            )
        }

        Gap(20)
        SecondaryButton(
            "फ़ोन में सेव करें",
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Filled.Download
        ) {}
        Gap(20)
    }
}

