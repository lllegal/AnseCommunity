package com.example.secondapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.MainActivity
import com.example.secondapplication.R
import com.example.secondapplication.databinding.ActivityJoinBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth


class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true

            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()

            //저기 값이 비어 있는지 확인
            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password1.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password2.isEmpty()) {
                Toast.makeText(this, "비밀번호확인을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            //비밀번호 유효성 확인
            if(!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password1.length < 8) {
                Toast.makeText(this, "비밀번호는 8자 이상이어야 합니다.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(isGoToJoin) {

                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)   //MainActivity로 이동
                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }


    }
}