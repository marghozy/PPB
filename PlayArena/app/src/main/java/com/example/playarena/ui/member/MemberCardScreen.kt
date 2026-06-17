package com.example.playarena.ui.member

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
import com.example.playarena.data.entity.Member
import com.example.playarena.data.repository.MemberRepository
import com.example.playarena.viewmodel.MemberViewModel

@Composable
fun MemberCardScreen(
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

    var selectedMember by remember {
        mutableStateOf<Member?>(null)
    }

    var editMember by remember {
        mutableStateOf<Member?>(null)
    }

    var editName by remember {
        mutableStateOf("")
    }

    var editEmail by remember {
        mutableStateOf("")
    }

    var editPhone by remember {
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
            text = "Daftar Member",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn {

            items(members) { member ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("ID : ${member.id}")

                        Text("Nama : ${member.name}")

                        Text("Email : ${member.email}")

                        Text("Phone : ${member.phone}")

                        Text("Point : ${member.points}")

                        val status = when {

                            member.points >= 200 ->
                                "Gold Member"

                            member.points >= 100 ->
                                "Silver Member"

                            else ->
                                "Bronze Member"
                        }

                        Text("Status : $status")

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Button(
                            onClick = {

                                editMember = member

                                editName = member.name
                                editEmail = member.email
                                editPhone = member.phone
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Edit Member")
                        }

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Button(
                            onClick = {
                                selectedMember = member
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                    MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Hapus Member")
                        }
                    }
                }
            }
        }
    }

    editMember?.let { member ->

        AlertDialog(

            onDismissRequest = {
                editMember = null
            },

            title = {
                Text("Edit Member")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = editName,
                        onValueChange = {
                            editName = it
                        },
                        label = {
                            Text("Nama")
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    OutlinedTextField(
                        value = editEmail,
                        onValueChange = {
                            editEmail = it
                        },
                        label = {
                            Text("Email")
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    OutlinedTextField(
                        value = editPhone,
                        onValueChange = {
                            editPhone = it
                        },
                        label = {
                            Text("Phone")
                        }
                    )
                }
            },

            confirmButton = {

                Button(
                    onClick = {

                        viewModel.editMember(
                            member.copy(
                                name = editName,
                                email = editEmail,
                                phone = editPhone
                            )
                        )

                        editMember = null
                    }
                ) {
                    Text("Simpan")
                }
            },

            dismissButton = {

                OutlinedButton(
                    onClick = {
                        editMember = null
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }

    selectedMember?.let { member ->

        AlertDialog(

            onDismissRequest = {
                selectedMember = null
            },

            title = {
                Text("Hapus Member")
            },

            text = {
                Text(
                    "Yakin ingin menghapus ${member.name}?"
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        viewModel.deleteMember(member)

                        selectedMember = null
                    }
                ) {
                    Text("Ya")
                }
            },

            dismissButton = {

                OutlinedButton(
                    onClick = {
                        selectedMember = null
                    }
                ) {
                    Text("Tidak")
                }
            }
        )
    }
}