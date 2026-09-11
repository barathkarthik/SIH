package com.example.sih.data
import com.example.sih.data.Lang
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class Severity { GREEN, YELLOW, ORANGE, RED }

data class DayForecast(
    val day: String,
    val range: String,
    val note: String,
    val severity: Severity,
)

data class AdviceItem(
    val action: String,
    val reason: String,
    val timing: String,
)

data class AlertRow(
    val title: String,
    val time: String,
    val severity: Severity,
    val read: Boolean,
)

/**
 * Everything the prototype shows. No network, no database — one object the
 * whole app reads from. Only [confirmed] and [language] ever change.
 */
object Demo {

    var confirmed by mutableStateOf(false)
    var language by mutableStateOf("हिन्दी")
    var offline by mutableStateOf(false)
    var lang by mutableStateOf(Lang.HI)

    const val DISTRICT = "नाशिक जिला"
    const val PHONE = "080-XXXXXXX"
    const val USER_NAME = "रमेश पाटील"
    const val USER_PHONE = "+91 98XXX XXXXX"
    const val CROP = "प्याज़"

    // The alert everything hangs off
    const val ALERT_LABEL = "ऑरेंज अलर्ट"
    const val ALERT_TIME = "सुबह 8:30 बजे"
    const val ALERT_HEADLINE = "कल नाशिक में भारी बारिश की चेतावनी है।"
    const val ALERT_RANGE = "64–115"
    const val ALERT_WINDOW = "कल सुबह 6 बजे से रात 9 बजे तक"
    const val ALERT_INTENSITY = "भारी"

    // The answer screen
    const val QUESTION = "नाशिक में कल बारिश होगी क्या?"
    const val ANSWER = "हाँ, कल भारी बारिश की संभावना है।"
    const val ANSWER_WINDOW = "कल सुबह 6 से रात 9 बजे तक"
    const val TODAY_LINE = "आज मौसम साफ़ रहेगा।"

    val threeDays = listOf(
        DayForecast("कल", "64–115", "भारी बारिश, दिन भर", Severity.ORANGE),
        DayForecast("परसों", "20–35", "हल्की बारिश, दोपहर बाद", Severity.GREEN),
        DayForecast("नरसों", "5–10", "छिटपुट बूँदाबाँदी", Severity.GREEN),
    )

    val advice = listOf(
        AdviceItem("कटी हुई फ़सल ढक दीजिए", "कल दिन भर बारिश रहेगी।", "आज शाम तक"),
        AdviceItem("दवा का छिड़काव अभी मत कीजिए", "बारिश में दवा बह जाएगी।", "3 दिन बाद"),
        AdviceItem("खेत की नाली साफ़ कर लीजिए", "पानी जमा हो सकता है।", "आज"),
    )

    val shortAdvice = listOf(
        "फ़सल\nढक दीजिए",
        "छिड़काव\nटालिए",
        "नाली\nसाफ़ कीजिए",
    )

    val alerts = listOf(
        AlertRow("कल भारी बारिश, 64–115 mm", "आज सुबह 8:30 बजे", Severity.ORANGE, true),
        AlertRow("तेज़ हवाएँ, 40–50 km/h", "कल शाम 5:20 बजे", Severity.YELLOW, true),
        AlertRow("मौसम सामान्य रहा", "तीन दिन पहले", Severity.GREEN, false),
    )

    // Trends
    const val YEAR_TOTAL = "612"
    const val TREND_LINE = "पिछले दस साल में यहाँ बारिश कम होती जा रही है।"
    const val TREND_SOURCE = "यह आँकड़े मौसम विभाग के तीस साल के रिकॉर्ड से लिए गए हैं।"

    /** 31 yearly totals, 1996–2026. Hand-made, gently declining. */
    val rainfallByYear = listOf(
        820f, 760f, 910f, 845f, 690f, 880f, 795f, 730f, 815f, 705f,
        870f, 640f, 760f, 830f, 700f, 785f, 690f, 810f, 655f, 745f,
        700f, 620f, 730f, 640f, 690f, 585f, 660f, 560f, 630f, 590f, 612f
    )
    val trendAverage = 745f

    // Provenance
    const val ACCURACY = 82
    val provenance = listOf(
        Triple("मौसम विभाग की चेतावनी", "आज सुबह 8:30 बजे जारी हुई", 0),
        Triple("दो अलग-अलग अनुमान, एक ही जवाब", "दोनों में सिर्फ़ 8 mm का फ़र्क़ है", 1),
        Triple("पिछले 30 दिन में 82% बार सही", "इसी जिले के लिए", 2),
    )
    const val PLAIN_STATEMENT =
        "हम खुद मौसम का अंदाज़ा नहीं लगाते। मौसम विभाग जो बताता है, वही हम आपकी भाषा में कहते हैं।"

    val languages = listOf(
        "हिन्दी" to "Hindi",
        "मराठी" to "Marathi",
        "বাংলা" to "Bengali",
        "தமிழ்" to "Tamil",
        "తెలుగు" to "Telugu",
        "ગુજરાતી" to "Gujarati",
    )
}