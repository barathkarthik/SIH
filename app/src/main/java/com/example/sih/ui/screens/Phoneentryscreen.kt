package com.example.sih.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sih.data.S
import com.example.sih.ui.components.Gap
import com.example.sih.ui.components.Gutter
import com.example.sih.ui.components.PrimaryButton
import com.example.sih.ui.theme.*

@Composable
fun PhoneEntryScreen(onContinue: (String) -> Unit) {
    var digits by remember { mutableStateOf("") }
    val valid = digits.length == 10

    Column(
        Modifier
            .fillMaxSize()
            .background(Page)
            .padding(horizontal = Gutter)
    ) {
        Gap(32)
        Text(S("phone_title"), style = T.H1, color = Ink)
        Gap(10)
        Text(S("phone_sub"), style = T.BodyL, color = Secondary)

        Gap(32)
        Row(verticalAlignment = Alignment.CenterVertically) {
            // fixed country code, not editable
            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceGrey)
                    .padding(horizontal = 16.dp, vertical = 18.dp)
            ) { Text("+91", style = T.BodyL, color = Ink) }

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                value = digits,
                onValueChange = { new ->
                    digits = new.filter { it.isDigit() }.take(10)
                },
                placeholder = { Text(S("phone_hint"), style = T.BodyL, color = Meta) },
                textStyle = T.BodyL.copy(color = Ink),
                singleLine = true,
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Accent,
                    unfocusedBorderColor = Hairline,
                ),
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(Modifier.weight(1f))

        PrimaryButton(
            S("continue"),
            modifier = Modifier.fillMaxWidth(),
        ) { if (valid) onContinue("+91$digits") }
        Gap(20)
    }
}

