package com.example.studentapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studentapp.data.Siswa
import com.example.studentapp.viewmodel.StudentViewModel

@Composable
fun MainScreen(
    viewModel: StudentViewModel
) {

    var nama by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var selectedSiswa by remember {
        mutableStateOf<Siswa?>(null)
    }

    val siswaList by viewModel.siswaList
        .collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        FormInput(
            nama = nama,
            email = email,

            onNamaChange = {
                nama = it
            },

            onEmailChange = {
                email = it
            },

            onSimpanClick = {

                if (
                    nama.isNotBlank() &&
                    email.isNotBlank()
                ) {

                    if (selectedSiswa == null) {

                        viewModel.tambahSiswa(
                            nama,
                            email
                        )

                    } else {

                        viewModel.updateSiswa(

                            selectedSiswa!!.copy(
                                nama = nama,
                                email = email
                            )
                        )

                        selectedSiswa = null
                    }

                    nama = ""
                    email = ""
                }
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn {

            items(siswaList) { siswa ->

                StudentItem(

                    siswa = siswa,

                    onEdit = {

                        selectedSiswa = siswa

                        nama = siswa.nama

                        email = siswa.email
                    },

                    onDelete = {

                        viewModel.hapusSiswa(
                            siswa
                        )
                    }
                )
            }
        }
    }
}