package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.Icon
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
fun ThreeDayScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Page)) {
        Gap(12)
        ScreenHeader("अगले 3 दिन", onBack)
        Gap(8)
        Text(
            Demo.DISTRICT, style = T.Meta, color = Meta,
            modifier = Modifier.padding(horizontal = Gutter)
        )
        Gap(24)

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Demo.threeDays.forEachIndexed { i, day ->
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = Gutter),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        if (day.severity == Severity.ORANGE) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    Modifier.size(8.dp)
                                        .background(AlertOrange, shape = androidx.compose.foundation.shape.CircleShape)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(day.day, style = T.Meta, color = AlertOrange)
                            }
                        } else {
                            Text(day.day, style = T.Meta, color = Meta)
                        }
                        Gap(8)
                        BigFigure(day.range, colour = Ink)
                        Gap(6)
                        Text(day.note, style = T.Body, color = Secondary)
                    }
                    Icon(
                        Icons.Filled.Cloud, null,
                        tint = Accent, modifier = Modifier.size(44.dp)
                    )
                }
                Gap(32)
                if (i < Demo.threeDays.lastIndex) { Rule(); Gap(32) }
            }

            Rule()
            Gap(20)
            Text(
                "मौसम विभाग, आज 08:30",
                style = T.Meta, color = Meta,
                modifier = Modifier.padding(horizontal = Gutter)
            )
            Gap(20)
        }

        SecondaryButton(
            "तीनों दिन सुनें",
            modifier = Modifier.fillMaxWidth().padding(horizontal = Gutter),
            icon = Icons.AutoMirrored.Filled.VolumeUp
        ) {}
        Gap(20)
    }
}

