package com.example.playarena.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.playarena.data.database.DatabaseProvider
import com.example.playarena.data.repository.MemberRepository
import com.example.playarena.data.repository.PlaySessionRepository
import com.example.playarena.navigation.Screen
import com.example.playarena.viewmodel.MemberViewModel
import com.example.playarena.viewmodel.PlaySessionViewModel

@Composable
fun HomeScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val memberViewModel = remember {

        MemberViewModel(
            MemberRepository(
                DatabaseProvider
                    .getDatabase(context)
                    .memberDao()
            )
        )
    }

    val sessionViewModel = remember {

        PlaySessionViewModel(
            PlaySessionRepository(
                DatabaseProvider
                    .getDatabase(context)
                    .playSessionDao()
            )
        )
    }

    val members by memberViewModel.members
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    val sessions by sessionViewModel.sessions
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    val totalMember = members.size

    val totalSession = sessions.size

    val totalPoint =
        members.sumOf {
            it.points
        }

    val topMember =
        members.maxByOrNull {
            it.points
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "🎮 PlayArena",
            style =
                MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Play More. Earn More.",
            style =
                MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "📊 Dashboard",
                    style =
                        MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text =
                        "👥 Total Member : $totalMember"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text =
                        "🎮 Total Session : $totalSession"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text =
                        "⭐ Total Point : $totalPoint"
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                HorizontalDivider()

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "🏆 Top Member"
                )

                Text(
                    text =
                        topMember?.name ?: "-"
                )

                Text(
                    text =
                        "${topMember?.points ?: 0} Point"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.AddMember.route
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Member")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.MemberCard.route
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar Member")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.AddSession.route
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Session")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.SessionHistory.route
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Riwayat Session")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.Reward.route
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reward")
        }
    }
}