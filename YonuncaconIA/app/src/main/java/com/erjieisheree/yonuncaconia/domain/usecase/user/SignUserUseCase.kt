package com.erjieisheree.yonuncaconia.domain.usecase.user

import com.erjieisheree.yonuncaconia.repository.user.UsersRepository

class SignUserUseCase(
    private val repository: UsersRepository
) {
    operator fun invoke(username: String, password: String): Int {
        return repository.addUser(username, password)
    }
}