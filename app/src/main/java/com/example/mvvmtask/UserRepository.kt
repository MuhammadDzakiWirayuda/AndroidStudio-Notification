package com.example.mvvmtask

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

// Repository bertugas mengambil data (sumber data: Assets Lokal)
class UserRepository(private val context: Context) {

    // Fungsi suspend agar bisa dijalankan di background (Coroutine)
    suspend fun getUsersFromAssets(): List<User> {
        // Berpindah ke IO Thread untuk operasi file I/O
        return withContext(Dispatchers.IO) {
            // Simulasi delay seolah-olah download dari internet (2 detik)
            delay(2000)

            val jsonString: String
            try {
                // Membaca file dari folder assets
                jsonString = context.assets.open("data.json")
                    .bufferedReader()
                    .use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return@withContext emptyList()
            }

            // Parsing JSON ke List<User> menggunakan Gson
            val listUserType = object : TypeToken<List<User>>() {}.type
            Gson().fromJson(jsonString, listUserType)
        }
    }
}