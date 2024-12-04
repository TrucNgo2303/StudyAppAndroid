package com.example.studyapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import com.example.studyapp.R
import com.example.studyapp.ui.student.StudentActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var edtUsername : AppCompatEditText
    private lateinit var tvErrorUsername: AppCompatTextView
    private lateinit var edtPassword: AppCompatEditText
    private lateinit var tvErrorPassword: AppCompatTextView
    private lateinit var tvForgotPassword: AppCompatTextView
    private lateinit var btnLogin: AppCompatButton
    private lateinit var tvSignUp: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtUsername = findViewById(R.id.edt_username)
        tvErrorUsername = findViewById(R.id.tv_error_username)
        edtPassword = findViewById(R.id.edt_password)
        tvErrorPassword = findViewById(R.id.tv_error_password)
        tvForgotPassword = findViewById(R.id.tv_fogotpassword)
        btnLogin = findViewById(R.id.btn_login)
        tvSignUp = findViewById(R.id.tv_sign_up_btn)


        edtUsername.addTextChangedListener{
            tvErrorUsername.visibility = View.GONE
        }

        edtPassword.addTextChangedListener {
            tvErrorPassword.visibility = View.GONE
        }

        btnLogin.setOnClickListener{
            if(validateInput())
            {
                clickLogin()
            }
        }

        tvForgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun clickLogin() {
        val intent = Intent(this, StudentActivity::class.java)
        Toast.makeText(this@LoginActivity, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    private fun validateInput(): Boolean {
        var isValid = true
        var username : String = edtUsername.text.toString()
        var password : String = edtPassword.text.toString()

        var user : User = User(username, password)

        if(!user.UsernameLength())
        {
            tvErrorUsername.text = "Tài khoản phải từ 8 đến 100 ký tự"
            tvErrorUsername.visibility = View.VISIBLE
            isValid = false
        }
        else if(user.UsernameEmpty()){
            tvErrorUsername.text = "Tài khoản không được để trống"
            tvErrorUsername.visibility = View.VISIBLE
            isValid = false
        }

        if(!user.PasswordLength())
        {
            tvErrorPassword.text = "Mật khẩu phải từ 8 đến 100 ký tự"
            tvErrorPassword.visibility = View.VISIBLE
            isValid = false
        }
        else if(user.PasswordEmpty()){
            tvErrorPassword.text = "Mật khẩu không được để trống"
            tvErrorPassword.visibility = View.VISIBLE
            isValid = false
        }
        return isValid
    }
}