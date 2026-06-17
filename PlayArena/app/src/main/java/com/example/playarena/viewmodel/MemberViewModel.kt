package com.example.playarena.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playarena.data.entity.Member
import com.example.playarena.data.repository.MemberRepository
import kotlinx.coroutines.launch

class MemberViewModel(
    private val repository: MemberRepository
) : ViewModel() {

    val members =
        repository.getAllMembers()

    fun addMember(
        name: String,
        email: String,
        phone: String
    ) {

        viewModelScope.launch {

            repository.insertMember(
                Member(
                    name = name,
                    email = email,
                    phone = phone
                )
            )
        }
    }

    fun addPoints(
        memberId: Int,
        point: Int
    ) {

        viewModelScope.launch {

            val member =
                repository.getMemberById(memberId)

            if (member != null) {

                repository.updatePoints(
                    member.copy(
                        points = member.points + point
                    )
                )
            }
        }
    }

    fun redeemPoints(
        memberId: Int,
        point: Int
    ) {

        viewModelScope.launch {

            val member =
                repository.getMemberById(memberId)

            if (
                member != null &&
                member.points >= point
            ) {

                repository.updatePoints(
                    member.copy(
                        points =
                            member.points - point
                    )
                )
            }
        }
    }

    fun deleteMember(
        member: Member
    ) {

        viewModelScope.launch {

            repository.deleteMember(member)
        }
    }

    fun editMember(
        member: Member
    ) {

        viewModelScope.launch {

            repository.updateMember(member)
        }
    }
}