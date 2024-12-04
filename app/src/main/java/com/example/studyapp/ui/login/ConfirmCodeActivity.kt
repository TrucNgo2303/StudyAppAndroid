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

class ConfirmCodeActivity : AppCompatActivity() {
    private lateinit var edtCode : AppCompatEditText
    private lateinit var tvErrorCode : AppCompatTextView
    private lateinit var btnConfirmCode : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_code)

        edtCode = findViewById(R.id.edt_code)
        tvErrorCode = findViewById(R.id.tv_error_code)
        btnConfirmCode = findViewById(R.id.btn_confirm_code)

        edtCode.addTextChangedListener {
            tvErrorCode.visibility = View.GONE
        }

        btnConfirmCode.setOnClickListener {
            if(validateInput())
            {
                clickCode()
            }
        }

    }

    private fun validateInput(): Boolean {
        var isValid = true
        var confirmCode: String = edtCode.text.toString()

        if(confirmCode != "123456")
        {
            tvErrorCode.text = "Mã xác nhận sai"
            tvErrorCode.visibility = View.VISIBLE
            isValid = false
        }
        return isValid
    }

    private fun clickCode() {
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
    }
}