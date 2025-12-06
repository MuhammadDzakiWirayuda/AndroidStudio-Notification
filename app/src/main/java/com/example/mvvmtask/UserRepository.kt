package com.example.mvvmask

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

class UserRepository(private val context: Context) {

    suspend fun getUsersFromAssets(): List<User> {
        return withContext(Dispatchers.IO) {
            // Simulasi loading 2 detik
            delay(2000)

            val jsonString: String
            try {
                jsonString = context.assets.open("data.json")
                    .bufferedReader()
                    .use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return@withContext emptyList()
            }

            val listUserType = object : TypeToken<List<User>>() {}.type
            Gson().fromJson(jsonString, listUserType)
        }
    }
}