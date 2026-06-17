package com.example.playarena.ui.member

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.playarena.data.database.DatabaseProvider
import com.example.playarena.data.repository.MemberRepository
import com.example.playarena.viewmodel.MemberViewModel

@Composable
fun AddMemberScreen(
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

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
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
            text = "Tambah Member",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Nama")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            label = {
                Text("Nomor HP")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {

                if (
                    name.isBlank() ||
                    email.isBlank() ||
                    phone.isBlank()
                ) {

                    errorMessage =
                        "Semua data wajib diisi"

                } else {

                    viewModel.addMember(
                        name,
                        email,
                        phone
                    )

                    showDialog = true

                    errorMessage = ""

                    name = ""
                    email = ""
                    phone = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Member")
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
                    "Data Member berhasil disimpan"
                )
            }
        )
    }
}