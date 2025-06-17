package com.erjieisheree.yonuncaconia.domain.usecase.user

import com.erjieisheree.yonuncaconia.repository.user.UsersRepository

class LoginUserUseCase(
    private val repository: UsersRepository
) {
    operator fun invoke(username: String, password: String): Int {
        return repository.findUser(username, password)
    }
}