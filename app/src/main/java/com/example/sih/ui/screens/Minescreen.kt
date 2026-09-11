package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.data.Lang
import com.example.sih.data.S
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun MineScreen(onOfflineDemo: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(12)
        Text(S("mine_title"), style = T.H2, color = Ink)
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
            Triple(Icons.Filled.Language, S("language"), null),
            Triple(Icons.Filled.LocationOn, S("district_row"), Demo.DISTRICT),
            Triple(Icons.Filled.Eco, S("crop_row"), Demo.CROP),
            Triple(Icons.AutoMirrored.Filled.VolumeUp, S("voice_reply"), S("on")),
            Triple(Icons.Filled.Phone, S("call_alert"), S("on")),
        )
        rows.forEachIndexed { i, (icon, label, value) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .let { m ->
                        if (i == 0) m.clickable {
                            Demo.lang = if (Demo.lang == Lang.HI) Lang.EN else Lang.HI
                        } else m
                    }
                    .padding(vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, null, tint = Meta, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(16.dp))
                Text(label, style = T.Body, color = Ink, modifier = Modifier.weight(1f))
                Text(
                    value ?: if (Demo.lang == Lang.HI) "हिन्दी" else "English",
                    style = T.Body, color = Secondary
                )
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
            Text(S("our_number"), style = T.Meta, color = Ink)
            Gap(10)
            Text(Demo.PHONE, style = T.H1, color = Ink)
            Gap(10)
            Text(S("phone_sub"), style = T.Body, color = Secondary)
        }

        Gap(20)
        SecondaryButton(
            S("save_number"),
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Filled.Download
        ) {}
        Gap(14)
        SecondaryButton(
            S("offline_demo"),
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Filled.CloudOff,
            onClick = onOfflineDemo,
        )
        Gap(20)
    }
}