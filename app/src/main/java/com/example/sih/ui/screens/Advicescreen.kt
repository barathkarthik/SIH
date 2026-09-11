package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.HorizontalDivider
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
fun AdviceScreen(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
    ) {
        Gap(12)
        ScreenHeader("क्या करूँ", onBack)
        Gap(12)

        // Context: a sentence, and a button. Never a middot string.
        Row(
            Modifier.fillMaxWidth().padding(horizontal = Gutter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Eco, null, tint = Accent, modifier = Modifier.size(26.dp))
            Spacer(Modifier.width(12.dp))
            Text(
                "${Demo.CROP} की फ़सल, कटाई के समय",
                style = T.Body, color = Secondary, modifier = Modifier.weight(1f)
            )
            Box(
                Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .border(1.dp, Hairline, RoundedCornerShape(999.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) { Text("बदलें", style = T.Meta, color = Accent) }
        }

        Gap(16)
        Rule()

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Demo.advice.forEachIndexed { i, item ->
                Gap(28)
                Row(Modifier.padding(horizontal = Gutter)) {
                    NumberBubble(i + 1)
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Text(item.action, style = T.BodyL, color = Ink)
                        Gap(6)
                        Text(item.reason, style = T.Body, color = Secondary)
                        Gap(10)
                        SandPill(item.timing)
                    }
                }
                Gap(28)
                if (i < Demo.advice.lastIndex) Rule()
            }

            Rule()
            Gap(20)
            Text(
                "यह सलाह मौसम विभाग की कृषि सलाह पर आधारित है।",
                style = T.Meta, color = Meta,
                modifier = Modifier.padding(horizontal = Gutter)
            )
            Gap(20)
        }

        SecondaryButton(
            "सुनें",
            modifier = Modifier.fillMaxWidth().padding(horizontal = Gutter),
            icon = Icons.AutoMirrored.Filled.VolumeUp
        ) {}
        Gap(20)
    }
}

