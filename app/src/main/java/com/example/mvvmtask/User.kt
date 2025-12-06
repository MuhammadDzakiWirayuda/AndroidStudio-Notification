package com.example.mvvmtask

// Model sederhana sesuai struktur JSON
data class User(
    val id: Int,
    val name: String,
    val role: String,
    val email: String
) {
    // Helper untuk menampilkan data dengan rapi di TextView
    override fun toString(): String {
        return "ID: $id\nNama: $name\nRole: $role\nEmail: $email\n\n"
    }
}