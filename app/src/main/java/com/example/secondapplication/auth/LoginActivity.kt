package com.example.secondapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.MainActivity
import com.example.secondapplication.R
import com.example.secondapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginBtn.setOnClickListener {

            val email = binding.emailArea.text.toString()
            val password = binding.passwordArea.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)   //MainActivity로 이동
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "이메일 또는 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}