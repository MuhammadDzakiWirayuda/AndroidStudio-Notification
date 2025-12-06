**Tugas Kelompok: Aplikasi Android MVVM Background Process**

Repositori ini berisi kode sumber untuk tugas pembuatan aplikasi Android sederhana yang menerapkan arsitektur MVVM, pemrosesan data asynchronous, dan notifikasi lokal.


**Anggota Kelompok**

Muhammad Dzaki Wirayuda (23523197)
Ahmad Aiman Zumar Prawirosunoto (23523112)
Raditya Pratama (23523169)
Muhammad Ibnu Rasyid (23523127)

**Fitur Utama**

Arsitektur MVVM: 

Pemisahan logic bisnis (ViewModel) dan UI (Activity).

Asynchronous Data Loading: Menggunakan Kotlin Coroutines (Dispatchers.IO) untuk membaca data JSON tanpa memblokir UI thread.

Local Assets: Data bersumber dari file data.json di folder assets.Local Notification: Menampilkan notifikasi sistem setelah proses pengambilan data selesai.



**Prasyarat**

Android Studio Iguana / Jellyfish (atau versi terbaru).Minimal SDK 24.Target SDK 34.

Struktur KodeUser.kt: Data Class (Model).UserRepository.kt: Menangani pembacaan file assets/data.json dengan delay simulasi 2 detik.

MainViewModel.kt: Mengelola state UI (Loading & Data) menggunakan LiveData.

MainActivity.kt: Menampilkan UI, meminta izin notifikasi, dan memicu notifikasi lokal.



**Cara Menjalankan**

Clone repositori ini.

Buka di Android Studio.

Sinkronkan Gradle (Sync Project with Gradle Files).

Jalankan pada Emulator atau Device Android.


**Screenshot**

1. Tampilan Awal (Sebelum Proses)(Tempatkan screenshot tampilan awal di sini)
2. Tampilan Setelah Data Muncul(Tempatkan screenshot data list di sini)
3. Notifikasi Lokal(Tempatkan screenshot notifikasi di status bar di sini)
