package com.erjieisheree.yonuncaconia.domain.usecase.user

import com.erjieisheree.yonuncaconia.repository.user.UsersRepository

class UpdateUserUseCase(
    private val repository: UsersRepository
) {
    operator fun invoke(id: String, newNick: String, newPwd: String): Boolean {
        return repository.updateUser(id, newNick, newPwd)
    }
}