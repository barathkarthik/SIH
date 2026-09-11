package com.example.sih.nav

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.sih.ui.components.Gap
import com.example.sih.ui.screens.*
import com.example.sih.ui.theme.*

object Route {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val ANSWER = "answer"
    const val THREE_DAY = "threeDay"
    const val ADVICE = "advice"
    const val ALERTS = "alerts"
    const val ALERT = "alert"
    const val TRENDS = "trends"
    const val MINE = "mine"
    const val TECHNICAL = "technical"
    const val OFFLINE = "offline"
    const val PHONE = "phone"
    const val OTP = "otp"
}

private data class Tab(
    val route: String,
    val label: String,
    val on: ImageVector,
    val off: ImageVector,
)

private val tabs = listOf(
    Tab(Route.HOME, "आज", Icons.Filled.Home, Icons.Outlined.Home),
    Tab(Route.ALERTS, "चेतावनी", Icons.Filled.Notifications, Icons.Outlined.Notifications),
    Tab(Route.TRENDS, "रुझान", Icons.Filled.ShowChart, Icons.Outlined.ShowChart),
    Tab(Route.MINE, "मेरा", Icons.Filled.Person, Icons.Outlined.Person),
)

private val tabRoutes = tabs.map { it.route }.toSet()

/** Onboarding should not stay on the back stack once passed. */
fun NavHostController.toHome() =
    navigate(Route.HOME) { popUpTo(Route.ONBOARDING) { inclusive = true } }

@Composable
fun ClimoraApp() {
    val nav = rememberNavController()
    val entry by nav.currentBackStackEntryAsState()
    val current = entry?.destination?.route

    Scaffold(
        containerColor = Page,
        bottomBar = { if (current in tabRoutes) ClimoraBottomBar(current, nav) },
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Route.ONBOARDING,
            modifier = Modifier.padding(padding),
        ) {
            composable(Route.HOME) {
                var listening by remember { mutableStateOf(false) }
                HomeScreen(onAsk = { listening = true })
                if (listening) {
                    ListeningSheet(
                        onCancel = { listening = false },
                        onDone = { listening = false; nav.navigate(Route.ANSWER) },
                    )
                }
            }
            composable(Route.ANSWER) {
                var showSheet by remember { mutableStateOf(false) }
                AnswerScreen(
                    onBack = { nav.popBackStack() },
                    onThreeDay = { nav.navigate(Route.THREE_DAY) },
                    onAdvice = { nav.navigate(Route.ADVICE) },
                    onSource = { showSheet = true },
                )
                if (showSheet) {
                    ProvenanceSheet(
                        onDismiss = { showSheet = false },
                        onTechnical = { showSheet = false; nav.navigate(Route.TECHNICAL) },
                    )
                }
            }
            composable(Route.TECHNICAL) { TechnicalDetailScreen { nav.popBackStack() } }
            composable(Route.ADVICE) { AdviceScreen { nav.popBackStack() } }
            composable(Route.ALERT) { AlertScreen { nav.popBackStack() } }
            composable(Route.THREE_DAY) { ThreeDayScreen { nav.popBackStack() } }
            composable(Route.ALERTS) { AlertsListScreen(onOpenAlert = { nav.navigate(Route.ALERT) }) }
            composable(Route.TRENDS) { TrendsScreen() }
            composable(Route.MINE) { MineScreen(onOfflineDemo = { nav.navigate(Route.OFFLINE) }) }
            composable(Route.OFFLINE) { OfflineScreen { nav.popBackStack() } }

            composable(Route.ONBOARDING) {
                OnboardingScreen(onNext = { nav.navigate(Route.PHONE) }, onSkip = { nav.toHome() })
            }
            composable(Route.PHONE) {
                PhoneEntryScreen(onContinue = { phone ->
                    nav.currentBackStackEntry?.savedStateHandle?.set("phone", phone)
                    nav.navigate(Route.OTP)
                })
            }
            composable(Route.OTP) {
                val phone = nav.previousBackStackEntry
                    ?.savedStateHandle?.get<String>("phone") ?: ""
                OtpScreen(phone = phone, onVerified = { nav.toHome() })
            }
        }
    }
}

@Composable
private fun ClimoraBottomBar(current: String?, nav: NavHostController) {
    NavigationBar(containerColor = Page, tonalElevation = 0.dp) {
        tabs.forEach { tab ->
            val selected = current == tab.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) nav.navigate(tab.route) {
                        popUpTo(Route.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(if (selected) tab.on else tab.off, null, Modifier.size(24.dp)) },
                label = { Text(tab.label, style = T.Meta) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Accent,
                    selectedTextColor = Accent,
                    unselectedIconColor = Meta,
                    unselectedTextColor = Meta,
                    indicatorColor = Page,
                ),
            )
        }
    }
}

@Composable
private fun Placeholder(name: String, onNext: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(name, style = T.H1, color = Ink)
        Gap(16)
        Button(onClick = onNext) { Text("आगे") }
    }
}