package com.example.playarena.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.playarena.data.database.DatabaseProvider
import com.example.playarena.data.repository.PlaySessionRepository
import com.example.playarena.viewmodel.PlaySessionViewModel

@Composable
fun SessionHistoryScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel = remember {

        PlaySessionViewModel(
            PlaySessionRepository(
                DatabaseProvider
                    .getDatabase(context)
                    .playSessionDao()
            )
        )
    }

    val sessions by viewModel.sessions
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("← Kembali")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Riwayat Session",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn {

            items(sessions) { session ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Member ID : ${session.memberId}"
                        )

                        Text(
                            text = "Durasi : ${session.duration} Menit"
                        )

                        Text(
                            text = "Konsol : ${session.consoleType}"
                        )

                        Text(
                            text = "Point : ${session.pointEarned}"
                        )

                        Text(
                            text = "Tanggal : ${session.date}"
                        )
                    }
                }
            }
        }
    }
}