package com.erjieisheree.yonuncaconia.repository.user

import com.erjieisheree.yonuncaconia.service.DDBBService

class UsersRepository(
    private val api: DDBBService
) {
    fun addUser(username: String, password: String): Int {
        return api.addUser(username, password)
    }
    fun updateUser(id: String, newNick: String, newPwd: String): Boolean {
        return api.updateUser(id, newNick, newPwd)
    }
    fun findUser(username: String, password: String): Int {
        return api.findUser(username, password)
    }
}