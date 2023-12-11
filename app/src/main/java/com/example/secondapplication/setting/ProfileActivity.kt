package com.example.secondapplication.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.R
import com.example.secondapplication.auth.IntroActivity
import com.example.secondapplication.auth.UserDataModel
import com.example.secondapplication.databinding.ActivityProfileBinding
import com.example.secondapplication.utils.FirebaseAuthUtils
import com.example.secondapplication.utils.FirebaseRef
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private val TAG = "ProfileActivity"

    private val uid = FirebaseAuthUtils.getUid()

    private lateinit var binding: ActivityProfileBinding

    val coursesList : List<String> = listOf(
        "전산기계제도",
        "창의적기초공학설계",
        "소프트웨어기초",
        "컴퓨터프로그래밍기초PBL/실습",
        "정역학",
        "고체역학1",
        "유체역학",
        "Linux시스템프로그래밍",
        "자료구조및알고리즘PBL",
        "열역학",
        "객체지향프로그밍/실습",
        "기계공작실습",
        "공학수학1/이산수학",
        "항공우주및소프트웨어공학개론",
        "동역학",
        "이산신호처리",
        "고체역학2",
        "공학수학2",
        "항공전자기초",
        "수치해석",
        "비행역학",
        "소프트웨어개발론PBL",
        "항공기구조역학",
        "공기역학",
        "기계진동학",
        "CAD응용",
        "자동제어",
        "운영체제커널",
        "데이터베이스",
        "컴퓨터구조",
        "압축성유체역학",
        "항공기설계",
        "항공기추진시스템",
        "컴퓨터네트워크",
        "항공그래픽스및시뮬레이션",
        "종합설계",
        "항공우주및소프트웨어공학실험",
        "수직이착륙기항공역학",
        "소프트웨어연구참여",
        "연구참여형캡스톤디자인",
        "비행제어설계",
        "풍력발전시스템",
        "우주비행역학및위성제어",
        "디바이스드라이버설계",
        "항공우주SW표준과테스팅",
        "전산역학입문",
        "임베디드하드웨어",
        "기계학습",
        "공학문서작성및발표",
        "기업가정신과창업창의융합PBL")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        getMyData()

        binding.logoutBtn.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.myPageBtn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        binding.editCoursesBtn.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getMyData() {

        val myUsername = binding.myUsername
        val myName = binding.myName
        val myCourses = binding.myCourses
        var binaryCourses : String = ""

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val data = dataSnapshot.getValue(UserDataModel::class.java)

                myUsername.text = data!!.username
                myName.text = data.name
                binaryCourses = data.courses.toString()

                if (binaryCourses == "null") {
                    myCourses.text = ""
                } else {
                    var courses = ""
                    for(index in 0..49) {
                        if (binaryCourses[index] == '1') {
                            courses += coursesList[index]
                            courses += "\n"
                        }
                    }
                    myCourses.text = courses
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }

        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }


}