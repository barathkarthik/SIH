package com.example.sih.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import com.example.sih.data.Severity
import com.example.sih.ui.theme.*

/** Standard side margin. Every screen uses this and nothing else. */
val Gutter = 24.dp

@Composable
fun severityColour(s: Severity): Color = when (s) {
    Severity.GREEN -> AlertGreen
    Severity.YELLOW -> AlertYellow
    Severity.ORANGE -> AlertOrange
    Severity.RED -> AlertRed
}

/** Thin rule, inset from both edges. Used instead of dividers on cards. */
@Composable
fun Rule(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = Gutter),
        thickness = 1.dp,
        color = Hairline,
    )
}

/** Circular back button on the grey surface. Top-left of most screens. */
@Composable
fun BackButton(onClick: () -> Unit, tint: Color = Ink, bg: Color = SurfaceGrey) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(bg)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "वापस",
            tint = tint,
            modifier = Modifier.size(22.dp),
        )
    }
}

/** Back button on the left, centred title. */
@Composable
fun ScreenHeader(title: String, onBack: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Gutter, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (onBack != null) {
            Box(Modifier.align(Alignment.CenterStart)) { BackButton(onBack) }
        }
        Text(title, style = T.H2, color = Ink)
    }
}

/** A large figure with its unit set small and raised, e.g. 64–115 mm. */
@Composable
fun BigFigure(
    value: String,
    unit: String = "mm",
    colour: Color = Accent,
    unitColour: Color = Meta,
) {
    Text(
        buildAnnotatedString {
            withStyle(T.Display.toSpanStyle().copy(color = colour)) { append(value) }
            append(" ")
            withStyle(T.Unit.toSpanStyle().copy(color = unitColour)) { append(unit) }
        }
    )
}

/** Solid accent action. The heaviest thing on any screen it appears on. */
@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Accent, contentColor = Page),
        contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
        if (icon != null) {
            Icon(icon, null, Modifier.size(22.dp))
            Spacer(Modifier.width(10.dp))
        }
        Text(text, style = T.BodyL)
    }
}

/** Outlined action. Always secondary to a PrimaryButton when both appear. */
@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.5.dp, Accent),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Accent),
        contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
        if (icon != null) {
            Icon(icon, null, Modifier.size(22.dp))
            Spacer(Modifier.width(10.dp))
        }
        Text(text, style = T.BodyL)
    }
}

/** Small sand circle holding an icon. The app's recurring list marker. */
@Composable
fun IconBubble(
    icon: ImageVector,
    tint: Color = Accent,
    bg: Color = Sand,   
    size: Dp = 44.dp,
) {
    Box(
        modifier = Modifier.size(size).clip(CircleShape).background(bg),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(size * 0.5f))
    }
}

/** Same bubble, holding a number instead of an icon. */
@Composable
fun NumberBubble(n: Int, size: Dp = 44.dp) {
    Box(
        modifier = Modifier.size(size).clip(CircleShape).background(Sand),
        contentAlignment = Alignment.Center,
    ) {
        Text("$n", style = T.BodyL, color = Ink)
    }
}

/** Small sand pill, used for timings and statuses. */
@Composable
fun SandPill(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Sand)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text, style = T.Meta, color = Ink)
    }
}

/** Tappable row on the grey surface, with a chevron. Used for provenance. */
@Composable
fun SourceRow(text: String, icon: ImageVector? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Gutter)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceGrey)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Icon(icon, null, tint = Accent, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
        }
        Text(text, style = T.Body, color = Secondary, modifier = Modifier.weight(1f))
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
            tint = Meta, modifier = Modifier.size(22.dp),
        )
    }
}

/** Vertical breathing room. Named so the intent reads in the layout code. */
@Composable
fun Gap(dp: Int) = Spacer(Modifier.height(dp.dp))

