package com.example.mvvmask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// State untuk UI (Loading atau Data Ready)
data class UiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val isFinished: Boolean = false // Trigger untuk notifikasi
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    // Menggunakan StateFlow yang lebih cocok untuk Compose
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, isFinished = false)

            val result = repository.getUsersFromAssets()

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                users = result,
                isFinished = true
            )
        }
    }

    // Reset status finished agar notifikasi tidak muncul berulang saat rotate layar
    fun onNotificationShown() {
        _uiState.value = _uiState.value.copy(isFinished = false)
    }
}