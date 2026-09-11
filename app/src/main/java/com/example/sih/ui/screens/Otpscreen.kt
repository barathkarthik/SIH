package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sih.data.S
import com.example.sih.ui.components.*
import com.example.sih.ui.theme.*

@Composable
fun OtpScreen(phone: String, onVerified: () -> Unit) {
    var code by remember { mutableStateOf("") }
    val focus = LocalFocusManager.current

    LaunchedEffect(code) {
        if (code.length == 4) onVerified()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(32)
        Text(S("otp_title"), style = T.H1, color = Ink)
        Gap(10)
        Text(S("otp_sub"), style = T.BodyL, color = Secondary)
        Gap(4)
        Text(phone, style = T.BodyL, color = Ink)

        Gap(36)

        // Invisible field driving four visible boxes — simplest reliable
        // pattern for OTP input in Compose without a third-party library.
        Box {
            BasicTextField(
                value = code,
                onValueChange = { new -> code = new.filter { it.isDigit() }.take(4) },
                textStyle = TextStyle(color = androidx.compose.ui.graphics.Color.Transparent),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done,
                ),
                modifier = Modifier.matchParentSize(),
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                repeat(4) { i ->
                    val filled = i < code.length
                    Box(
                        Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (filled) Sand else SurfaceGrey),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            code.getOrNull(i)?.toString() ?: "",
                            style = T.H1, color = Ink,
                        )
                    }
                }
            }
        }

        Gap(28)
        Text(
            S("otp_resend"), style = T.Meta, color = Accent,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start
        )

        Spacer(Modifier.weight(1f))

        PrimaryButton(
            S("verify"),
            modifier = Modifier.fillMaxWidth(),
        ) { if (code.length == 4) onVerified() }
        Gap(20)
    }
}