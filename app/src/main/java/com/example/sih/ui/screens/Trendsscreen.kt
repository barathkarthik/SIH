package com.example.sih.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun TrendsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(12)
        Text("रुझान", style = T.H2, color = Ink)
        Gap(20)
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(Demo.DISTRICT, style = T.Body, color = Secondary, modifier = Modifier.weight(1f))
            Box(
                Modifier
                    .background(SurfaceGrey, RoundedCornerShape(999.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) { Text("बदलें", style = T.Meta, color = Accent) }
        }
        Gap(28)

        Text("इस साल अब तक", style = T.Meta, color = Meta)
        Gap(8)
        BigFigure(Demo.YEAR_TOTAL, colour = Ink)
        Gap(10)
        Text(
            buildAnnotatedString {
                withStyle(T.BodyL.toSpanStyle().copy(color = AlertOrange)) { append("18% कम") }
                withStyle(T.BodyL.toSpanStyle().copy(color = Secondary)) {
                    append(" है, तीस साल के औसत से।")
                }
            }
        )

        Gap(32)
        RainfallChart(Demo.rainfallByYear, Demo.trendAverage)

        Gap(28)
        Text(Demo.TREND_LINE, style = T.Body, color = Secondary)
        Gap(24)
        Rule(Modifier.padding(horizontal = 0.dp))
        Gap(16)
        Text(Demo.TREND_SOURCE, style = T.Meta, color = Meta)

        Gap(24)
        SecondaryButton(
            "सुनकर समझिए",
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.AutoMirrored.Filled.VolumeUp
        ) {}
        Gap(20)
    }
}

/** Hand-drawn so there is no chart-library dependency to configure. */
@Composable
private fun RainfallChart(values: List<Float>, average: Float) {
    val accent = Accent
    val meta = Meta
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val max = values.max() * 1.05f
        val min = values.min() * 0.95f
        val range = (max - min).coerceAtLeast(1f)
        val stepX = size.width / (values.size - 1)
        fun y(v: Float) = size.height - ((v - min) / range) * size.height

        // average line, dashed
        val avgY = y(average)
        drawLine(
            color = meta,
            start = Offset(0f, avgY),
            end = Offset(size.width, avgY),
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f)),
        )

        // the rainfall line
        for (i in 0 until values.lastIndex) {
            drawLine(
                color = accent,
                start = Offset(i * stepX, y(values[i])),
                end = Offset((i + 1) * stepX, y(values[i + 1])),
                strokeWidth = 5f,
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
            )
        }

        // latest year marker
        val lastX = (values.size - 1) * stepX
        val lastY = y(values.last())
        drawCircle(color = accent, radius = 9f, center = Offset(lastX, lastY))
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("1996", style = T.Meta, color = Meta)
        Text("2006", style = T.Meta, color = Meta)
        Text("2016", style = T.Meta, color = Meta)
        Text("2026", style = T.Meta, color = Meta)
    }
}

