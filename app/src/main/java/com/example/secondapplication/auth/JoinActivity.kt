package com.example.secondapplication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()

            //저기 값이 비어 있는지 확인
            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
            }

            if(password1.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
            }

            if(password2.isEmpty()) {
                Toast.makeText(this, "비밀번호확인을 입력해주세요", Toast.LENGTH_LONG).show()
            }

        }



//        auth.createUserWithEmailAndPassword("abc@abc.com", "12341234")
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
//                } else {
//                    Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
//                }
//            }
    }
}