package com.example.sih.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.JoinFull
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvenanceSheet(onDismiss: () -> Unit, onTechnical: () -> Unit) {
    val state = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = state,
        containerColor = Page,
    ) {
        Column(Modifier.padding(horizontal = Gutter)) {
            Text("यह बात कहाँ से आई?", style = T.H2, color = Ink)
            Gap(28)

            ProvenanceRow(
                icon = Icons.Filled.Shield,
                title = "मौसम विभाग की चेतावनी",
                sub = "आज सुबह 8:30 बजे जारी हुई",
            )
            Gap(24)
            Rule(Modifier.padding(horizontal = 0.dp))
            Gap(24)

            ProvenanceRow(
                icon = Icons.Filled.JoinFull,
                title = "दो अलग-अलग अनुमान, एक ही जवाब",
                sub = "दोनों में सिर्फ़ 8 mm का फ़र्क़ है",
            )
            Gap(24)
            Rule(Modifier.padding(horizontal = 0.dp))
            Gap(24)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    IconBubble(Icons.Filled.Check)
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text("पिछले 30 दिन में 82% बार सही", style = T.BodyL, color = Ink)
                        Gap(4)
                        Text("इसी जिले के लिए", style = T.Meta, color = Meta)
                    }
                }
                AccuracyRing(Demo.ACCURACY)
            }

            Gap(28)
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Sand)
                    .padding(20.dp)
            ) {
                Text(Demo.PLAIN_STATEMENT, style = T.Body, color = Ink)
            }

            Gap(20)
            SourceRow("तकनीकी जानकारी", onClick = onTechnical)
            Gap(24)
        }
    }
}

@Composable
private fun ProvenanceRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, sub: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconBubble(icon)
        Spacer(Modifier.width(16.dp))
        Column {
            Text(title, style = T.BodyL, color = Ink)
            Gap(4)
            Text(sub, style = T.Meta, color = Meta)
        }
    }
}

@Composable
private fun AccuracyRing(percent: Int) {
    val accent = Accent
    val track = Hairline
    Box(Modifier.size(56.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = 5f
            drawArc(
                color = track, startAngle = -90f, sweepAngle = 360f,
                useCenter = false, style = Stroke(stroke, cap = StrokeCap.Round)
            )
            drawArc(
                color = accent, startAngle = -90f, sweepAngle = 360f * (percent / 100f),
                useCenter = false, style = Stroke(stroke, cap = StrokeCap.Round)
            )
        }
        Text("$percent%", style = T.Meta, color = Ink)
    }
}

/** Behind "तकनीकी जानकारी" — English, for a judge or meteorologist. */
@Composable
fun TechnicalDetailScreen(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(12)
        ScreenHeader("Technical detail", onBack)
        Gap(4)
        Text(
            "Nashik district · 24 Sept 2026", style = T.Meta, color = Meta,
            modifier = Modifier.padding(horizontal = Gutter)
        )
        Gap(32)

        Column(Modifier.padding(horizontal = Gutter)) {
            listOf(
                "IMD district warning" to "64–115 mm",
                "GFS (NOAA)" to "71 mm",
                "WRF (IMD product)" to "68 mm",
            ).forEach { (label, value) ->
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(label, style = T.BodyL, color = Ink, modifier = Modifier.weight(1f))
                    Text(value, style = T.BodyL, color = Ink)
                }
            }
            Gap(8)
            Text("Spread: 8 mm · Issued 08:30 IST", style = T.Meta, color = Meta)

            Gap(40)
            Text("Forecast verification", style = T.H2, color = Ink)
            Gap(10)
            Text(
                "Every forecast is stored and compared against what IMD later recorded for this district.",
                style = T.Body, color = Secondary
            )
            Gap(20)
            Row(Modifier.fillMaxWidth()) {
                listOf("8.2 mm" to "MAE", "11.4 mm" to "RMSE", "\u22121.6 mm" to "Bias").forEach { (v, l) ->
                    Column(Modifier.weight(1f)) {
                        Text(v, style = T.H2, color = Accent)
                        Text(l, style = T.Meta, color = Meta)
                    }
                }
            }
            Gap(8)
            Text("Rolling 30 days · n = 30", style = T.Meta, color = Meta)

            Gap(40)
            Text(
                "Sources: IMD district warnings and observations, NOAA GFS, IMD WRF products.",
                style = T.Meta, color = Meta
            )
            Gap(4)
            Text("Climora does not generate forecasts.", style = T.Meta, color = Meta)
        }
    }
}

