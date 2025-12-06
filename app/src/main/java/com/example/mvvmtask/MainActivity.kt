package com.example.mvvmtask

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mvvmtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Inisialisasi ViewModel
    private val viewModel: MainViewModel by viewModels()

    // Konstanta untuk Notifikasi
    private val CHANNEL_ID = "channel_download_complete"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel() // Buat channel notifikasi (wajib untuk Android O+)

        // Setup Listener Tombol
        binding.btnLoadData.setOnClickListener {
            // Cek izin notifikasi untuk Android 13+
            checkPermissionAndLoadData()
        }

        // Observer: Memantau perubahan data Users
        viewModel.users.observe(this) { userList ->
            if (userList.isNotEmpty()) {
                val formattedText = StringBuilder()
                userList.forEach { user ->
                    formattedText.append(user.toString())
                }
                binding.tvResult.text = formattedText.toString()

                // Tampilkan notifikasi saat data selesai dimuat
                showNotification()
            }
        }

        // Observer: Memantau status Loading
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnLoadData.isEnabled = false
                binding.tvResult.text = "Sedang memproses data..."
            } else {
                binding.progressBar.visibility = View.GONE
                binding.btnLoadData.isEnabled = true
            }
        }
    }

    private fun checkPermissionAndLoadData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                viewModel.loadUsers()
            }
        } else {
            viewModel.loadUsers()
        }
    }

    // Launcher untuk meminta izin runtime (Android 13+)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.loadUsers()
        } else {
            Toast.makeText(this, "Izin notifikasi ditolak", Toast.LENGTH_SHORT).show()
            viewModel.loadUsers() // Tetap load data meski tanpa notifikasi
        }
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_download_done) // Icon bawaan android
            .setContentTitle("Proses Selesai!")
            .setContentText("Data berhasil dimuat dari penyimpanan lokal.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Download Channel"
            val descriptionText = "Notifikasi saat download selesai"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}