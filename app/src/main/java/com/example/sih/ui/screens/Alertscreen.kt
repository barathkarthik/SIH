package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sih.data.Demo
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun AlertScreen(onBack: () -> Unit) {
    var confirmed by remember { mutableStateOf(false) }
    var calling by remember { mutableStateOf(false) }

    // The escalation, shortened to 6 seconds so it demos live.
    LaunchedEffect(confirmed) {
        if (!confirmed) {
            delay(6000)
            if (!confirmed) calling = true
        }
    }

    if (calling) {
        IncomingCall(onAnswer = { calling = false; confirmed = true })
        return
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(12)
        BackButton(onBack)
        Gap(16)

        // ---- the orange panel, inset from both edges ----
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(AlertOrange)
                .padding(24.dp)
        ) {
            Text(
                Demo.ALERT_TIME, style = T.Meta, color = OnAlertMuted,
                modifier = Modifier.align(Alignment.End)
            )
            Gap(8)
            Icon(
                Icons.Filled.WarningAmber, null,
                tint = OnAlert, modifier = Modifier.size(40.dp)
            )
            Gap(10)
            Text(Demo.ALERT_LABEL, style = T.Meta, color = OnAlertMuted)
            Gap(12)
            Text(Demo.ALERT_HEADLINE, style = T.H1, color = OnAlert)
            Gap(18)
            BigFigure(Demo.ALERT_RANGE, colour = OnAlert, unitColour = OnAlertMuted)
            Gap(6)
            Text(Demo.ALERT_WINDOW, style = T.BodyL, color = OnAlertMuted)
        }

        Gap(28)

        // ---- intensity, one line and one track ----
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("कितनी बारिश", style = T.Meta, color = Meta, modifier = Modifier.weight(1f))
            Text(Demo.ALERT_INTENSITY, style = T.BodyL, color = AlertOrange)
        }
        Gap(10)
        Box(
            Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(SurfaceGrey)
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(999.dp))
                    .background(AlertOrange)
            )
        }

        Gap(28)

        // ---- three advice cards in a row ----
        Text("क्या करें", style = T.Meta, color = Meta)
        Gap(14)
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Demo.shortAdvice.forEach { label ->
                Column(
                    Modifier
                        .weight(1f)
                        .height(140.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Page)
                        .border(1.dp, Hairline, RoundedCornerShape(20.dp))
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Filled.Shield, null,
                        tint = Accent, modifier = Modifier.size(30.dp)
                    )
                    Gap(12)
                    Text(label, style = T.Body, color = Ink, textAlign = TextAlign.Center)
                }
            }
        }

        Gap(24)

        // ---- source ----
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(SurfaceGrey)
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Shield, null, tint = Accent, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
            Text("मौसम विभाग की चेतावनी", style = T.Body, color = Secondary)
        }

        Spacer(Modifier.weight(1f))

        // ---- actions, side by side ----
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                "सुनें",
                modifier = Modifier.weight(1f),
                icon = Icons.AutoMirrored.Filled.VolumeUp
            ) {}
            PrimaryButton(
                if (confirmed) "पुष्टि हो गई" else "समझ गया",
                modifier = Modifier.weight(2f),
                icon = Icons.Filled.Check
            ) { confirmed = true }
        }

        Gap(14)
        Text(
            "पुष्टि न होने पर पाँच मिनट में फ़ोन आएगा।",
            style = T.Meta, color = Meta,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
        Text(
            Demo.PHONE, style = T.Meta, color = Meta,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
        Gap(16)
    }
}

/** The escalation, made visible. This is the moment that sells the project. */
@Composable
private fun IncomingCall(onAnswer: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Accent)
            .padding(Gutter),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text("आ रही है कॉल", style = T.Meta, color = OnAlertMuted)
        Gap(12)
        Text("Climora", style = T.H1, color = OnAlert)
        Gap(8)
        Text(Demo.PHONE, style = T.BodyL, color = OnAlertMuted)
        Gap(32)
        Text(
            "चेतावनी की पुष्टि नहीं हुई, इसलिए हम कॉल कर रहे हैं।",
            style = T.Body, color = OnAlertMuted, textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(1f))
        Box(
            Modifier
                .size(84.dp)
                .clip(CircleShape)
                .background(AlertGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Call, null, tint = OnAlert,
                modifier = Modifier.size(36.dp)
            )
        }
        Gap(16)
        SecondaryButton("उठाइए", modifier = Modifier.fillMaxWidth(), onClick = onAnswer)
        Gap(40)
    }
}

