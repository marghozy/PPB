package com.example.studentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentapp.data.AppDatabase
import com.example.studentapp.ui.MainScreen
import com.example.studentapp.ui.theme.StudentAppTheme
import com.example.studentapp.viewmodel.StudentViewModel
import com.example.studentapp.viewmodel.StudentViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val database =
            AppDatabase.getDatabase(this)

        val dao =
            database.siswaDao()

        setContent {

            StudentAppTheme {

                val viewModel: StudentViewModel =
                    viewModel(
                        factory =
                            StudentViewModelFactory(
                                dao
                            )
                    )

                MainScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}