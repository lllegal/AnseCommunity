package com.example.secondapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.auth.IntroActivity
import com.example.secondapplication.auth.UserDataModel
import com.example.secondapplication.databinding.ActivityMainBinding
import com.example.secondapplication.searching.ResultActivity
import com.example.secondapplication.searching.ResultPlusActivity
import com.example.secondapplication.setting.MyPageActivity
import com.example.secondapplication.setting.ProfileActivity
import com.example.secondapplication.utils.FirebaseRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    private var count = 0

    private val usersDataList = mutableListOf<UserDataModel>()
    private val namesDataList = mutableListOf<String>()
    private val coursesDataList = mutableListOf<String>()
    private val uidsDataList = mutableListOf<String>()

    val coursesList: List<String> = listOf(
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
        "기업가정신과창업창의융합PBL"
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        getUserDataList()

        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, coursesList)
        binding.searchBar.setAdapter(adapter)


        binding.profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.searchBtn.setOnClickListener {

//            var searchedUids = mutableListOf<String>()
//            var searched = ""

            var searchedCourse = binding.searchBar.text.toString()

            val index = coursesList.indexOf(searchedCourse)

            if(index == -1) {
                Toast.makeText(this,"완성된 교과목명을 입력해주세요.", Toast.LENGTH_LONG).show()
            } else {
                if (count == 0) {
                    count += 1
                    val intent = Intent(this, ResultActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, ResultPlusActivity::class.java)
                    startActivity(intent)
                }


//                Log.d(TAG, index.toString())
//                var count = 0
//                for (course in coursesDataList) {
//                    if (course.length == 50) {
//                        if (course[index] == '1') {
//                            searchedUids.add(uidsDataList[count])
//                        }
//                    }
//                    count += 1
//                }
//
//                Log.d(TAG, searchedUids.toString())
//
//                val postListener = object : ValueEventListener {
//
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                        val user = dataSnapshot.getValue(UserDataModel::class.java)
//                        //val data = dataSnapshot.getValue(UserDataModel::class.java)
//
//                        searched += user?.username.toString()
//                        searched += "\n"
//                        binding.results.text = searched
//
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {
//                        // Getting Post failed, log a message
//                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                    }
//
//                }
//
//                for (uid in searchedUids) {
//                    FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)
//                }

            }

        }

    }

    private fun getUserDataList() {

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val user = dataModel.getValue(UserDataModel::class.java)

                    usersDataList.add(user!!)
                    namesDataList.add(user.uid.toString())
                    uidsDataList.add(user.uid.toString())
                    coursesDataList.add(user.courses.toString())

                    //Log.d(TAG, coursesDataList.toString())
                    //Log.d(TAG, user?.name.toString())

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FirebaseRef.userInfoRef.addValueEventListener(postListener)

    }
}




//            val postListener = object : ValueEventListener {
//
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                    for (dataModel in dataSnapshot.children) {
//
//                        val user = dataModel.getValue(UserDataModel::class.java)
//                        //val data = dataSnapshot.getValue(UserDataModel::class.java)
//
//                        searched += user?.username.toString()
//                        searched += "/n"
//
//
//                    }
//                    binding.results.text = searched
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    // Getting Post failed, log a message
//                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                }
//            }
//
//            for(uid in searchedUids) {
//                FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)







