package com.example.mvvmtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Menggunakan AndroidViewModel agar bisa akses Application Context (untuk akses Assets)
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    // LiveData untuk menyimpan state data User
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    // LiveData untuk status Loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Fungsi untuk memicu pengambilan data
    fun loadUsers() {
        _isLoading.value = true // Tampilkan loading
        viewModelScope.launch {
            // Memanggil fungsi background di repository
            val result = repository.getUsersFromAssets()

            _users.value = result // Update data ke UI
            _isLoading.value = false // Sembunyikan loading
        }
    }
}