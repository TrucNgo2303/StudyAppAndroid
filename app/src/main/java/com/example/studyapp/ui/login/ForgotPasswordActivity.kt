package com.example.studyapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import com.example.studyapp.R

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var edtEmail : AppCompatEditText
    private lateinit var tvErrorEmail: AppCompatTextView
    private lateinit var btnForgotPassword: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        edtEmail = findViewById(R.id.edt_email)
        tvErrorEmail = findViewById(R.id.tv_error_email)
        btnForgotPassword = findViewById(R.id.btn_forgot_password)

        edtEmail.addTextChangedListener {
            tvErrorEmail.visibility = View.GONE
        }

        btnForgotPassword.setOnClickListener{
            if(validateInput())
            {
                clickForgotPassword()
            }
        }

    }

    private fun clickForgotPassword() {
        val intent = Intent(this, ConfirmCodeActivity::class.java)
        startActivity(intent)
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val email: String = edtEmail.text.toString().trim()
        var user = User("","",email)


        if (user.ErrorEmail()) {
            tvErrorEmail.text = "Email không đúng định dạng"
            tvErrorEmail.visibility = View.VISIBLE
            isValid = false
        }

        return isValid
    }
}