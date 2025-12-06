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

1. Tampilan Awal (Sebelum Proses)

<img width="181" height="396" alt="image" src="https://github.com/user-attachments/assets/5e0e22c7-0ea2-45b2-a518-13f240d208b3" />

2. Tampilan Setelah Data Muncul

<img width="181" height="393" alt="image" src="https://github.com/user-attachments/assets/00ebdff0-3bc1-4d30-855b-00ffd329f3a7" />

3. Notifikasi Lokal
  
<img width="179" height="387" alt="image" src="https://github.com/user-attachments/assets/17f8184a-c25c-40a5-8130-c9cca32fd827" />

