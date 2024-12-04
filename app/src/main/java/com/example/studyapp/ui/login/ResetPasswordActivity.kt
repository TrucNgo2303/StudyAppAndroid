package com.example.studyapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import com.example.studyapp.R

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var edtNewPassword : AppCompatEditText
    private lateinit var tvErrorNewPassword : AppCompatTextView
    private lateinit var edtNewPasswordConfirm : AppCompatEditText
    private lateinit var tvErrorNewPasswordConfirm : AppCompatTextView
    private lateinit var btnResetPassword : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        edtNewPassword = findViewById(R.id.edt_new_password)
        tvErrorNewPassword = findViewById(R.id.tv_error_new_password)
        edtNewPasswordConfirm = findViewById(R.id.edt_new_password_confirm)
        tvErrorNewPasswordConfirm = findViewById(R.id.tv_error_new_password_confirm)
        btnResetPassword = findViewById(R.id.btn_reset_password)


        edtNewPassword.addTextChangedListener {
            tvErrorNewPassword.visibility = View.GONE
        }
        edtNewPasswordConfirm.addTextChangedListener {
            tvErrorNewPasswordConfirm.visibility = View.GONE
        }

        btnResetPassword.setOnClickListener {
            if(validateInput())
            {
                clickResetPassword()
            }
        }


    }

    private fun validateInput(): Boolean {
        var isValid = true

        val newPassword: String = edtNewPassword.text.toString()
        val newPasswordConfirm : String = edtNewPasswordConfirm.text.toString()

        if(TextUtils.isEmpty(newPassword))
        {
            tvErrorNewPassword.text = "Mật khẩu không được để trống"
            tvErrorNewPassword.visibility = View.VISIBLE
            isValid = false
        }
        else if(newPassword.length < 8 || newPassword.length > 30)
        {
            tvErrorNewPassword.text = "Mật khẩu phải lớn hơn 8 kí tự"
            tvErrorNewPassword.visibility = View.VISIBLE
            isValid = false
        }

        if(newPassword != newPasswordConfirm)
        {
            tvErrorNewPasswordConfirm.text = "Mật khẩu không trùng khớp"
            tvErrorNewPasswordConfirm.visibility = View.VISIBLE
            isValid = false
        }

        return isValid
    }

    private fun clickResetPassword() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}