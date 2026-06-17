package com.example.playarena.ui.reward

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.playarena.data.database.DatabaseProvider
import com.example.playarena.data.repository.MemberRepository
import com.example.playarena.viewmodel.MemberViewModel

@Composable
fun RewardScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel = remember {

        MemberViewModel(
            MemberRepository(
                DatabaseProvider
                    .getDatabase(context)
                    .memberDao()
            )
        )
    }

    val members by viewModel.members
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    val sortedMembers =
        members.sortedByDescending {
            it.points
        }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var dialogMessage by remember {
        mutableStateOf("")
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
            text = "Leaderboard & Reward",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn {

            itemsIndexed(sortedMembers) { index, member ->

                val status = when {

                    member.points >= 200 ->
                        "Gold Member"

                    member.points >= 100 ->
                        "Silver Member"

                    else ->
                        "Bronze Member"
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("#${index + 1}")

                        Text(
                            text = "Nama : ${member.name}"
                        )

                        Text(
                            text = "Total Poin : ${member.points}"
                        )

                        Text(
                            text = "Status : $status"
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        if (member.points >= 50) {

                            Button(
                                onClick = {

                                    viewModel.redeemPoints(
                                        member.id,
                                        50
                                    )

                                    dialogMessage =
                                        "Gratis Main 30 Menit berhasil ditukar"

                                    showDialog = true
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Redeem 50 Point")
                            }

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )
                        }

                        if (member.points >= 100) {

                            Button(
                                onClick = {

                                    viewModel.redeemPoints(
                                        member.id,
                                        100
                                    )

                                    dialogMessage =
                                        "Gratis Main 1 Jam berhasil ditukar"

                                    showDialog = true
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Redeem 100 Point")
                            }

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )
                        }

                        if (member.points >= 150) {

                            Button(
                                onClick = {

                                    viewModel.redeemPoints(
                                        member.id,
                                        150
                                    )

                                    dialogMessage =
                                        "Gratis Main 1 Jam 30 Menit berhasil ditukar"

                                    showDialog = true
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Redeem 150 Point")
                            }
                        }
                    }
                }
            }
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
                Text("Redeem Berhasil")
            },

            text = {
                Text(dialogMessage)
            }
        )
    }
}