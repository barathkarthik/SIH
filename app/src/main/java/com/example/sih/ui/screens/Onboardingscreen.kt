package com.example.sih.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sih.data.S
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun OnboardingScreen(onNext: () -> Unit, onSkip: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(16)

        // progress track, panel 2 of 3
        Box(
            Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(Hairline)
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.66f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(999.dp))
                    .background(Accent)
            )
        }

        Spacer(Modifier.weight(1f))

        // the illustration
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Sand),
            contentAlignment = Alignment.Center
        ) {
            JourneyDiagram()
        }

        Spacer(Modifier.weight(1f))

        Text(S("onboard_title"), style = T.H1, color = Ink, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Gap(10)
        Text(
            S("onboard_sub"),
            style = T.BodyL, color = Secondary,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )

        Gap(20)
        Box(
            Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(Sand)
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.AutoMirrored.Filled.VolumeUp, null, tint = Ink, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(S("listen"), style = T.Meta, color = Ink)
            }
        }

        Spacer(Modifier.weight(1f))

        PrimaryButton(S("next"), modifier = Modifier.fillMaxWidth(), onClick = onNext)
        Gap(14)
        Text(
            S("skip"), style = T.Meta, color = Meta,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )
        Gap(16)
    }
}

/** Person → cloud → phone, joined by one curved line. No text inside it. */
@Composable
private fun JourneyDiagram() {
    Box(Modifier.fillMaxSize(0.7f)) {
        Canvas(Modifier.fillMaxSize()) {
            val y = size.height * 0.55f
            val leftX = size.width * 0.18f
            val midX = size.width * 0.5f
            val rightX = size.width * 0.82f
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(leftX, y)
                quadraticBezierTo(midX, y + 60f, rightX, y)
            }
            drawPath(
                path, color = Accent,
                style = Stroke(width = 4f, cap = StrokeCap.Round)
            )
            listOf(leftX, midX, rightX).forEach { x ->
                drawCircle(Accent, radius = 6f, center = Offset(x, if (x == midX) y + 30f else y))
            }
        }
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Person, null, tint = Accent, modifier = Modifier.size(48.dp))
            Icon(Icons.Filled.Cloud, null, tint = Accent, modifier = Modifier.size(44.dp))
            Icon(Icons.Filled.Phone, null, tint = Accent, modifier = Modifier.size(44.dp))
        }
    }
}