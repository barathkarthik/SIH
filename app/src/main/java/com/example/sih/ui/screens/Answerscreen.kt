package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun AnswerScreen(
    onBack: () -> Unit,
    onThreeDay: () -> Unit,
    onAdvice: () -> Unit,
    onSource: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(16)

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            BackButton(onBack)
            Spacer(Modifier.width(16.dp))
            Text("Climora", style = T.H2, color = Accent, modifier = Modifier.weight(1f))
            Box(
                Modifier.size(44.dp).clip(CircleShape).background(Sand),
                contentAlignment = Alignment.Center
            ) { Text("हिं", style = T.Meta, color = Ink) }
        }

        Gap(28)

        // The question, set as quiet editorial text
        Text("\u201C ${Demo.QUESTION}", style = T.Body, color = Meta)
        Gap(16)
        HorizontalDivider(thickness = 1.dp, color = Hairline)
        Gap(32)

        // Severity marker
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(10.dp).clip(CircleShape).background(AlertOrange))
            Spacer(Modifier.width(10.dp))
            Text(Demo.ALERT_LABEL, style = T.Meta, color = AlertOrange)
        }

        Gap(14)
        Text(Demo.ANSWER, style = T.H1, color = Ink)
        Gap(20)
        BigFigure(Demo.ALERT_RANGE)
        Gap(8)
        Text(Demo.ANSWER_WINDOW, style = T.Body, color = Meta)

        Gap(40)

        // Three quiet actions
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            QuietAction(Icons.AutoMirrored.Filled.VolumeUp, "सुनें") {}
            QuietAction(Icons.Filled.CalendarMonth, "3 दिन", onThreeDay)
            QuietAction(Icons.Filled.Lightbulb, "क्या करूँ", onAdvice)
        }

        Gap(32)
        HorizontalDivider(thickness = 1.dp, color = Hairline)

        // Provenance
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onSource)
                .padding(vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "मौसम विभाग की चेतावनी, सुबह 8:30 बजे",
                style = T.Meta, color = Meta, modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
                tint = Meta, modifier = Modifier.size(22.dp)
            )
        }

        Spacer(Modifier.weight(1f))

        // Composer
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Row(
                Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(SurfaceGrey)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) { Text("लिखिए...", style = T.Body, color = Meta) }
            Spacer(Modifier.width(12.dp))
            Box(
                Modifier.size(56.dp).clip(CircleShape).background(Accent),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Lightbulb, null,
                    tint = Page, modifier = Modifier.size(0.dp)
                )
                Text("🎙", style = T.BodyL, color = Page)
            }
        }
        Gap(16)
    }
}

@Composable
private fun QuietAction(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick).padding(8.dp)
    ) {
        Icon(icon, null, tint = Secondary, modifier = Modifier.size(28.dp))
        Gap(8)
        Text(label, style = T.Meta, color = Secondary)
    }
}

