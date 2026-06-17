package com.example.playarena.data.repository

import com.example.playarena.data.dao.MemberDao
import com.example.playarena.data.entity.Member

class MemberRepository(
    private val memberDao: MemberDao
) {

    fun getAllMembers() =
        memberDao.getAllMembers()

    suspend fun insertMember(
        member: Member
    ) {
        memberDao.insertMember(member)
    }

    suspend fun updateMember(
        member: Member
    ) {
        memberDao.updateMember(member)
    }

    suspend fun getMemberById(
        memberId: Int
    ): Member? {

        return memberDao.getMemberById(memberId)
    }

    suspend fun updatePoints(
        member: Member
    ) {

        memberDao.updateMember(member)
    }

    suspend fun deleteMember(
        member: Member
    ) {

        memberDao.deleteMember(member)
    }
}