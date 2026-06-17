package com.example.playarena.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.playarena.data.database.DatabaseProvider
import com.example.playarena.data.repository.MemberRepository
import com.example.playarena.data.repository.PlaySessionRepository
import com.example.playarena.viewmodel.MemberViewModel
import com.example.playarena.viewmodel.PlaySessionViewModel

@Composable
fun AddSessionScreen(
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

    val playSessionViewModel = remember {

        PlaySessionViewModel(
            PlaySessionRepository(
                DatabaseProvider
                    .getDatabase(context)
                    .playSessionDao()
            )
        )
    }

    var memberId by remember {
        mutableStateOf("")
    }

    var duration by remember {
        mutableStateOf("")
    }

    var consoleType by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

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
            text = "Tambah Session",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = memberId,
            onValueChange = {
                memberId = it
            },
            label = {
                Text("Member ID")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = duration,
            onValueChange = {
                duration = it
            },
            label = {
                Text("Durasi Bermain (menit)")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = consoleType,
            onValueChange = {
                consoleType = it
            },
            label = {
                Text("Jenis Konsol")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {

                if (
                    memberId.isBlank() ||
                    duration.isBlank() ||
                    consoleType.isBlank()
                ) {

                    errorMessage =
                        "Semua data wajib diisi"

                } else {

                    val pointEarned =
                        (duration.toInt() / 60) * 10

                    playSessionViewModel.addSession(
                        memberId = memberId.toInt(),
                        duration = duration.toInt(),
                        consoleType = consoleType,
                        pointEarned = pointEarned,
                        date = "2026-06-17"
                    )

                    memberViewModel.addPoints(
                        memberId = memberId.toInt(),
                        point = pointEarned
                    )

                    showDialog = true

                    errorMessage = ""

                    memberId = ""
                    duration = ""
                    consoleType = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Session")
        }

        if (errorMessage.isNotEmpty()) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            confirmButton = {

                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },

            title = {
                Text("Berhasil")
            },

            text = {
                Text(
                    "Session berhasil disimpan"
                )
            }
        )
    }
}