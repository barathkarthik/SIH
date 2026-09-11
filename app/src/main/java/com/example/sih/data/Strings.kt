package com.example.sih.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

enum class Lang { HI, EN }

/**
 * Every user-facing string, in both languages. Screens call [S] (a
 * composable getter below) instead of writing text directly, so switching
 * [Demo.lang] re-renders the whole app in the other language.
 *
 * Keep this the single source of truth — do not hardcode strings in a
 * screen once it has been converted.
 */
private val hi = mapOf(
    "district" to "नाशिक जिला",
    "today_line" to "आज मौसम साफ़ रहेगा।",
    "ask" to "पूछिए",
    "or_type" to "या नीचे लिखिए",
    "type_hint" to "लिखिए...",
    "onboard_title" to "बोलिए",
    "onboard_sub" to "सवाल पूछिए। हम आपकी भाषा में जवाब देंगे।",
    "listen" to "सुनिए",
    "next" to "आगे",
    "skip" to "छोड़ें",
    "question" to "नाशिक में कल बारिश होगी क्या?",
    "answer" to "हाँ, कल भारी बारिश की संभावना है।",
    "answer_window" to "कल सुबह 6 से रात 9 बजे तक",
    "listen_short" to "सुनें",
    "three_day" to "3 दिन",
    "what_to_do" to "क्या करूँ",
    "source_line" to "मौसम विभाग की चेतावनी, सुबह 8:30 बजे",
    "phone_title" to "आपका फ़ोन नंबर",
    "phone_sub" to "चेतावनी की पुष्टि न होने पर हम इसी नंबर पर कॉल करेंगे।",
    "phone_hint" to "98XXX XXXXX",
    "continue" to "आगे बढ़ें",
    "otp_title" to "कोड डालिए",
    "otp_sub" to "हमने आपके नंबर पर एक कोड भेजा है।",
    "otp_resend" to "कोड दोबारा भेजें",
    "verify" to "पुष्टि करें",
    "language" to "भाषा",
    "district_row" to "जिला",
    "crop_row" to "फ़सल",
    "voice_reply" to "आवाज़ में जवाब",
    "call_alert" to "फ़ोन कॉल चेतावनी",
    "on" to "चालू",
    "our_number" to "हमारा नंबर",
    "save_number" to "फ़ोन में सेव करें",
    "offline_demo" to "बिना इंटरनेट देखें  (डेमो)",
    "mine_title" to "मेरा",
)

private val en = mapOf(
    "district" to "Nashik district",
    "today_line" to "Clear skies today.",
    "ask" to "Ask a question",
    "or_type" to "or type below",
    "type_hint" to "Type here...",
    "onboard_title" to "Speak",
    "onboard_sub" to "Ask a question. We'll answer in your language.",
    "listen" to "Listen",
    "next" to "Next",
    "skip" to "Skip",
    "question" to "Will it rain in Nashik tomorrow?",
    "answer" to "Yes, heavy rain is likely tomorrow.",
    "answer_window" to "From 6 am to 9 pm tomorrow",
    "listen_short" to "Listen",
    "three_day" to "3 days",
    "what_to_do" to "What to do",
    "source_line" to "Weather Department warning, 8:30 am",
    "phone_title" to "Your phone number",
    "phone_sub" to "We'll call this number if a warning goes unconfirmed.",
    "phone_hint" to "98XXX XXXXX",
    "continue" to "Continue",
    "otp_title" to "Enter the code",
    "otp_sub" to "We've sent a code to your number.",
    "otp_resend" to "Resend code",
    "verify" to "Verify",
    "language" to "Language",
    "district_row" to "District",
    "crop_row" to "Crop",
    "voice_reply" to "Voice replies",
    "call_alert" to "Phone call alerts",
    "on" to "On",
    "our_number" to "Our number",
    "save_number" to "Save to phone",
    "offline_demo" to "View offline  (demo)",
    "mine_title" to "Mine",
)

/** Composable string lookup — re-reads on every recomposition, so it
 * reacts instantly when [Demo.lang] changes. Usage: S("answer") */
@Composable
fun S(key: String): String {
    val table = if (Demo.lang == Lang.HI) hi else en
    return table[key] ?: key
}

