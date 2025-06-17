package com.erjieisheree.yonuncaconia.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erjieisheree.yonuncaconia.data.DataStoreManager
import com.erjieisheree.yonuncaconia.domain.usecase.user.LoginUserUseCase
import com.erjieisheree.yonuncaconia.domain.usecase.user.SignUserUseCase
import com.erjieisheree.yonuncaconia.domain.usecase.user.UpdateUserUseCase
import com.erjieisheree.yonuncaconia.repository.user.UsersRepository
import com.erjieisheree.yonuncaconia.service.apis.MySqlApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(private val dataStore: DataStoreManager) : ViewModel() {

    val signUserUseCase = SignUserUseCase(UsersRepository(MySqlApi()))
    val updateUserUseCase = UpdateUserUseCase(UsersRepository(MySqlApi()))
    val loginUseCase = LoginUserUseCase(UsersRepository(MySqlApi()))

    val username: StateFlow<String> = dataStore.dataFlow
        .map { it.username }
        .stateIn(viewModelScope, SharingStarted.Lazily, "")

    val password: StateFlow<String> = dataStore.dataFlow
        .map { it.password }
        .stateIn(viewModelScope, SharingStarted.Lazily, "")

    val userId: StateFlow<String> = dataStore.dataFlow
        .map { it.id }
        .stateIn(viewModelScope, SharingStarted.Lazily, "0")

    fun signUser(username: String, password: String): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            val id = signUserUseCase(username, password)
            if (id != 0) localSaveUserData(username, password, id.toString())
        }
    }

    fun loginUser(username: String, password: String): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            val id = loginUseCase(username, password)
            println("After login: $id")
            if (id != 0) localSaveUserData(username, password, id.toString())
        }
    }

    fun updateUser(id: String, newNick: String, newPwd: String): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            if (updateUserUseCase(id, newNick, newPwd)) localSaveUserData(newNick, newPwd, id)
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStore.saveUserData("", "", "0")
        }
    }

    private fun localSaveUserData(username: String, password: String, id: String) {
        viewModelScope.launch {
            dataStore.saveUserData(username, password, id)
        }
    }
}