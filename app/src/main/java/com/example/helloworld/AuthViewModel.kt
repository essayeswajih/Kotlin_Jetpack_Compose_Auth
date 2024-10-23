package com.example.helloworld

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel


class AuthViewModel(private val sharedPrefs: SharedPreferences) : ViewModel() {

    // Check if user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPrefs.getBoolean("is_logged_in", false)
    }

    // Login method that saves credentials and updates login state
    fun login(email: String, password: String): Boolean {
        println("email: $email, password: $password")
        if (email.equals("wajihsayes@gmail.com") && password.equals("wajih123")) {
            with(sharedPrefs.edit()) {
                putBoolean("is_logged_in", true)
                putString("email", email)
                apply()
            }
            return true
        }
        return false
    }

    // Logout method that clears stored data
    fun logout() {
        with(sharedPrefs.edit()) {
            clear()
            apply()
        }
    }
}
