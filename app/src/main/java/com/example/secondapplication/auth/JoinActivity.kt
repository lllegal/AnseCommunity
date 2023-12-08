package com.example.secondapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.R
import com.example.secondapplication.databinding.ActivityJoinBinding
import com.example.secondapplication.utils.FirebaseRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityJoinBinding

    private var name = ""
    private var username = ""
    private var uid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true
            var isPasswordEntered = true

            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()
//            val username = binding.usernameArea.text.toString()

            name = binding.nameArea.text.toString()
            username = binding.usernameArea.text.toString()

            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

//            if(name.isEmpty()) {
//                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_LONG).show()
//                isGoToJoin = false
//            }
//
//            if(username) 중복확인

            if(password1.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
                isPasswordEntered = false
            }

            if(password2.isEmpty()) {
                Toast.makeText(this, "비밀번호확인을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
                isPasswordEntered = false
            }

            if(isPasswordEntered) {
                if(!password1.equals(password2)) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                    isGoToJoin = false
                }

                if(password1.length < 6) {
                    Toast.makeText(this, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_LONG).show()
                    isGoToJoin = false
                }
            }

            if(isGoToJoin) {
                auth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            uid = auth.currentUser?.uid.toString()

                            val userModel = UserDataModel(uid, username, name)

                            FirebaseRef.userInfoRef.child(uid).setValue(userModel)

                            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, IntroActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)

                        } else {

                            Toast.makeText(this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show()

                        }
                    }
            }

        }

    }
}