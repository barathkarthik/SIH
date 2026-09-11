package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Phone
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

/**
 * The answer screen with no connectivity. In the demo, reach this by
 * toggling airplane mode on the device — that is the whole point of the
 * screen, so wire it behind a route you can trigger live rather than a
 * gesture on the answer screen itself.
 */
@Composable
fun OfflineScreen(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(12)
        ScreenHeader("जवाब", onBack)
        Gap(20)

        // notice
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Sand)
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.CloudOff, null, tint = Secondary, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
            Text(
                "अभी इंटरनेट नहीं है। यह पहले सहेजी गई जानकारी है।",
                style = T.Body, color = Secondary
            )
        }

        Gap(28)

        // the saved answer, one shade lighter throughout
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(10.dp).clip(CircleShape).background(Meta))
            Spacer(Modifier.width(10.dp))
            Text(Demo.ALERT_LABEL, style = T.Meta, color = Meta)
        }
        Gap(14)
        Text(Demo.ANSWER, style = T.H1, color = Secondary)
        Gap(20)
        BigFigure(Demo.ALERT_RANGE, colour = Secondary, unitColour = Meta)
        Gap(8)
        Text(Demo.ANSWER_WINDOW, style = T.Body, color = Meta)

        Gap(20)
        Text("मौसम विभाग की चेतावनी, सुबह 8:30 बजे", style = T.Meta, color = Meta)
        Text("चार घंटे पहले सहेजी गई थी", style = T.Meta, color = Meta)

        Gap(28)
        Rule(Modifier.padding(horizontal = 0.dp))
        Gap(28)

        // the fallback, the only full-colour element on the screen
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Sand)
                .padding(20.dp)
        ) {
            Icon(Icons.Filled.Phone, null, tint = Accent, modifier = Modifier.size(28.dp))
            Gap(14)
            Text("बिना इंटरनेट भी पूछ सकते हैं", style = T.BodyL, color = Ink)
            Gap(8)
            Text(
                "इस नंबर पर मिस्ड कॉल दीजिए, हम आपको कॉल करके बता देंगे।",
                style = T.Body, color = Secondary
            )
            Gap(14)
            Text(Demo.PHONE, style = T.H1, color = Accent)
            Gap(18)
            PrimaryButton(
                "अभी कॉल कीजिए",
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Filled.Phone
            ) {}
        }

        Gap(20)
    }
}

