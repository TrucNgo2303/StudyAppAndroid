package com.example.studyapp.ui.login

import android.text.TextUtils
import android.util.Patterns

class User (private var username: String = "", private var password: String = "", private  var email: String = "") {

    fun setUsername(username: String) {
        this.username = username
    }
    fun getUsername() : String{
        return username
    }

    fun setPassword(password: String){
        this.password = password
    }
    fun getPassword(): String{
        return password
    }
    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return email
    }

    fun UsernameLength(): Boolean {
        return username.length in 8..30
    }
    fun PasswordLength(): Boolean {
        return password.length in 8..30
    }
    fun UsernameEmpty(): Boolean {
        return username.isEmpty()
    }
    fun PasswordEmpty(): Boolean {
        return password.isEmpty()
    }
    fun ErrorEmail() : Boolean {
        return TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}