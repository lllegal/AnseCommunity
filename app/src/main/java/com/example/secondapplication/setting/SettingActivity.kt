package com.example.secondapplication.setting

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.R
import com.example.secondapplication.auth.UserDataModel
import com.example.secondapplication.databinding.ActivitySettingBinding
import com.example.secondapplication.utils.FirebaseAuthUtils
import com.example.secondapplication.utils.FirebaseRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingActivity : AppCompatActivity() {

    private val TAG = "SettingActivity"

    private val uid = FirebaseAuthUtils.getUid()

    private lateinit var binding: ActivitySettingBinding

    var courses = "00000000000000000000000000000000000000000000000000"
    var variableForSync = "1"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        val job = CoroutineScope(Dispatchers.Main).launch {

            val job1 = async { firstJob() }
            Log.d(TAG, "1번째 작업 후의 courses =  $courses")

            while (variableForSync == "1") {
                delay(10L)
            }

            val job2 = async { secondJob() }

        }

    }


    private suspend fun firstJob() {
        ///////////////////////////////// 읽기 시작
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Log.d(TAG, dataSnapshot.toString())

                val data = dataSnapshot.getValue(UserDataModel::class.java)
                courses = data?.courses ?: "00000000000000000000000000000000000000000000000000"
                variableForSync = data?.courses ?: "00000000000000000000000000000000000000000000000000"

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }

        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)
        ///////////////////////////////// 읽기 끝
    }

    private suspend fun secondJob() {

        for (index in 1..50) {
            if (courses[index-1] == '1') {
                when (index) {
                    1 -> binding.cb1.isChecked = true
                    2 -> binding.cb2.isChecked = true
                    3 -> binding.cb3.isChecked = true
                    4 -> binding.cb4.isChecked = true
                    5 -> binding.cb5.isChecked = true
                    6 -> binding.cb6.isChecked = true
                    7 -> binding.cb7.isChecked = true
                    8 -> binding.cb8.isChecked = true
                    9 -> binding.cb9.isChecked = true
                    10 -> binding.cb10.isChecked = true
                    11 -> binding.cb11.isChecked = true
                    12 -> binding.cb12.isChecked = true
                    13 -> binding.cb13.isChecked = true
                    14 -> binding.cb14.isChecked = true
                    15 -> binding.cb15.isChecked = true
                    16 -> binding.cb16.isChecked = true
                    17 -> binding.cb17.isChecked = true
                    18 -> binding.cb18.isChecked = true
                    19 -> binding.cb19.isChecked = true
                    20 -> binding.cb20.isChecked = true
                    21 -> binding.cb21.isChecked = true
                    22 -> binding.cb22.isChecked = true
                    23 -> binding.cb23.isChecked = true
                    24 -> binding.cb24.isChecked = true
                    25 -> binding.cb25.isChecked = true
                    26 -> binding.cb26.isChecked = true
                    27 -> binding.cb27.isChecked = true
                    28 -> binding.cb28.isChecked = true
                    29 -> binding.cb29.isChecked = true
                    30 -> binding.cb30.isChecked = true
                    31 -> binding.cb31.isChecked = true
                    32 -> binding.cb32.isChecked = true
                    33 -> binding.cb33.isChecked = true
                    34 -> binding.cb34.isChecked = true
                    35 -> binding.cb35.isChecked = true
                    36 -> binding.cb36.isChecked = true
                    37 -> binding.cb37.isChecked = true
                    38 -> binding.cb38.isChecked = true
                    39 -> binding.cb39.isChecked = true
                    40 -> binding.cb40.isChecked = true
                    41 -> binding.cb41.isChecked = true
                    42 -> binding.cb42.isChecked = true
                    43 -> binding.cb43.isChecked = true
                    44 -> binding.cb44.isChecked = true
                    45 -> binding.cb45.isChecked = true
                    46 -> binding.cb46.isChecked = true
                    47 -> binding.cb47.isChecked = true
                    48 -> binding.cb48.isChecked = true
                    49 -> binding.cb49.isChecked = true
                    50 -> binding.cb50.isChecked = true
                }
            }
        }

        //getMyCheckBox()

        var listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {

                    R.id.cb1 -> { courses = courses.substring(0, 0) + "1" + courses.substring(0 + 1) }
                    R.id.cb2 -> { courses = courses.substring(0, 1) + "1" + courses.substring(1 + 1) }
                    R.id.cb3 -> { courses = courses.substring(0, 2) + "1" + courses.substring(2 + 1) }
                    R.id.cb4 -> { courses = courses.substring(0, 3) + "1" + courses.substring(3 + 1) }
                    R.id.cb5 -> { courses = courses.substring(0, 4) + "1" + courses.substring(4 + 1) }
                    R.id.cb6 -> { courses = courses.substring(0, 5) + "1" + courses.substring(5 + 1) }
                    R.id.cb7 -> { courses = courses.substring(0, 6) + "1" + courses.substring(6 + 1) }
                    R.id.cb8 -> { courses = courses.substring(0, 7) + "1" + courses.substring(7 + 1) }
                    R.id.cb9 -> { courses = courses.substring(0, 8) + "1" + courses.substring(8 + 1) }
                    R.id.cb10 -> { courses = courses.substring(0, 9) + "1" + courses.substring(9 + 1) }
                    R.id.cb11 -> { courses = courses.substring(0, 10) + "1" + courses.substring(10 + 1) }
                    R.id.cb12 -> { courses = courses.substring(0, 11) + "1" + courses.substring(11 + 1) }
                    R.id.cb13 -> { courses = courses.substring(0, 12) + "1" + courses.substring(12 + 1) }
                    R.id.cb14 -> { courses = courses.substring(0, 13) + "1" + courses.substring(13 + 1) }
                    R.id.cb15 -> { courses = courses.substring(0, 14) + "1" + courses.substring(14 + 1) }
                    R.id.cb16 -> { courses = courses.substring(0, 15) + "1" + courses.substring(15 + 1) }
                    R.id.cb17 -> { courses = courses.substring(0, 16) + "1" + courses.substring(16 + 1) }
                    R.id.cb18 -> { courses = courses.substring(0, 17) + "1" + courses.substring(17 + 1) }
                    R.id.cb19 -> { courses = courses.substring(0, 18) + "1" + courses.substring(18 + 1) }
                    R.id.cb20 -> { courses = courses.substring(0, 19) + "1" + courses.substring(19 + 1) }
                    R.id.cb21 -> { courses = courses.substring(0, 20) + "1" + courses.substring(20 + 1) }
                    R.id.cb22 -> { courses = courses.substring(0, 21) + "1" + courses.substring(21 + 1) }
                    R.id.cb23 -> { courses = courses.substring(0, 22) + "1" + courses.substring(22 + 1) }
                    R.id.cb24 -> { courses = courses.substring(0, 23) + "1" + courses.substring(23 + 1) }
                    R.id.cb25 -> { courses = courses.substring(0, 24) + "1" + courses.substring(24 + 1) }
                    R.id.cb26 -> { courses = courses.substring(0, 25) + "1" + courses.substring(25 + 1) }
                    R.id.cb27 -> { courses = courses.substring(0, 26) + "1" + courses.substring(26 + 1) }
                    R.id.cb28 -> { courses = courses.substring(0, 27) + "1" + courses.substring(27 + 1) }
                    R.id.cb29 -> { courses = courses.substring(0, 28) + "1" + courses.substring(28 + 1) }
                    R.id.cb30 -> { courses = courses.substring(0, 29) + "1" + courses.substring(29 + 1) }
                    R.id.cb31 -> { courses = courses.substring(0, 30) + "1" + courses.substring(30 + 1) }
                    R.id.cb32 -> { courses = courses.substring(0, 31) + "1" + courses.substring(31 + 1) }
                    R.id.cb33 -> { courses = courses.substring(0, 32) + "1" + courses.substring(32 + 1) }
                    R.id.cb34 -> { courses = courses.substring(0, 33) + "1" + courses.substring(33 + 1) }
                    R.id.cb35 -> { courses = courses.substring(0, 34) + "1" + courses.substring(34 + 1) }
                    R.id.cb36 -> { courses = courses.substring(0, 35) + "1" + courses.substring(35 + 1) }
                    R.id.cb37 -> { courses = courses.substring(0, 36) + "1" + courses.substring(36 + 1) }
                    R.id.cb38 -> { courses = courses.substring(0, 37) + "1" + courses.substring(37 + 1) }
                    R.id.cb39 -> { courses = courses.substring(0, 38) + "1" + courses.substring(38 + 1) }
                    R.id.cb40 -> { courses = courses.substring(0, 39) + "1" + courses.substring(39 + 1) }
                    R.id.cb41 -> { courses = courses.substring(0, 40) + "1" + courses.substring(40 + 1) }
                    R.id.cb42 -> { courses = courses.substring(0, 41) + "1" + courses.substring(41 + 1) }
                    R.id.cb43 -> { courses = courses.substring(0, 42) + "1" + courses.substring(42 + 1) }
                    R.id.cb44 -> { courses = courses.substring(0, 43) + "1" + courses.substring(43 + 1) }
                    R.id.cb45 -> { courses = courses.substring(0, 44) + "1" + courses.substring(44 + 1) }
                    R.id.cb46 -> { courses = courses.substring(0, 45) + "1" + courses.substring(45 + 1) }
                    R.id.cb47 -> { courses = courses.substring(0, 46) + "1" + courses.substring(46 + 1) }
                    R.id.cb48 -> { courses = courses.substring(0, 47) + "1" + courses.substring(47 + 1) }
                    R.id.cb49 -> { courses = courses.substring(0, 48) + "1" + courses.substring(48 + 1) }
                    R.id.cb50 -> { courses = courses.substring(0, 49) + "1" + courses.substring(49 + 1) }
                }
            } else {
                when (buttonView.id) {
                    R.id.cb1 -> { courses = courses.substring(0, 0) + "0" + courses.substring(0 + 1) }
                    R.id.cb2 -> { courses = courses.substring(0, 1) + "0" + courses.substring(1 + 1) }
                    R.id.cb3 -> { courses = courses.substring(0, 2) + "0" + courses.substring(2 + 1) }
                    R.id.cb4 -> { courses = courses.substring(0, 3) + "0" + courses.substring(3 + 1) }
                    R.id.cb5 -> { courses = courses.substring(0, 4) + "0" + courses.substring(4 + 1) }
                    R.id.cb6 -> { courses = courses.substring(0, 5) + "0" + courses.substring(5 + 1) }
                    R.id.cb7 -> { courses = courses.substring(0, 6) + "0" + courses.substring(6 + 1) }
                    R.id.cb8 -> { courses = courses.substring(0, 7) + "0" + courses.substring(7 + 1) }
                    R.id.cb9 -> { courses = courses.substring(0, 8) + "0" + courses.substring(8 + 1) }
                    R.id.cb10 -> { courses = courses.substring(0, 9) + "0" + courses.substring(9 + 1) }
                    R.id.cb11 -> { courses = courses.substring(0, 10) + "0" + courses.substring(10 + 1) }
                    R.id.cb12 -> { courses = courses.substring(0, 11) + "0" + courses.substring(11 + 1) }
                    R.id.cb13 -> { courses = courses.substring(0, 12) + "0" + courses.substring(12 + 1) }
                    R.id.cb14 -> { courses = courses.substring(0, 13) + "0" + courses.substring(13 + 1) }
                    R.id.cb15 -> { courses = courses.substring(0, 14) + "0" + courses.substring(14 + 1) }
                    R.id.cb16 -> { courses = courses.substring(0, 15) + "0" + courses.substring(15 + 1) }
                    R.id.cb17 -> { courses = courses.substring(0, 16) + "0" + courses.substring(16 + 1) }
                    R.id.cb18 -> { courses = courses.substring(0, 17) + "0" + courses.substring(17 + 1) }
                    R.id.cb19 -> { courses = courses.substring(0, 18) + "0" + courses.substring(18 + 1) }
                    R.id.cb20 -> { courses = courses.substring(0, 19) + "0" + courses.substring(19 + 1) }
                    R.id.cb21 -> { courses = courses.substring(0, 20) + "0" + courses.substring(20 + 1) }
                    R.id.cb22 -> { courses = courses.substring(0, 21) + "0" + courses.substring(21 + 1) }
                    R.id.cb23 -> { courses = courses.substring(0, 22) + "0" + courses.substring(22 + 1) }
                    R.id.cb24 -> { courses = courses.substring(0, 23) + "0" + courses.substring(23 + 1) }
                    R.id.cb25 -> { courses = courses.substring(0, 24) + "0" + courses.substring(24 + 1) }
                    R.id.cb26 -> { courses = courses.substring(0, 25) + "0" + courses.substring(25 + 1) }
                    R.id.cb27 -> { courses = courses.substring(0, 26) + "0" + courses.substring(26 + 1) }
                    R.id.cb28 -> { courses = courses.substring(0, 27) + "0" + courses.substring(27 + 1) }
                    R.id.cb29 -> { courses = courses.substring(0, 28) + "0" + courses.substring(28 + 1) }
                    R.id.cb30 -> { courses = courses.substring(0, 29) + "0" + courses.substring(29 + 1) }
                    R.id.cb31 -> { courses = courses.substring(0, 30) + "0" + courses.substring(30 + 1) }
                    R.id.cb32 -> { courses = courses.substring(0, 31) + "0" + courses.substring(31 + 1) }
                    R.id.cb33 -> { courses = courses.substring(0, 32) + "0" + courses.substring(32 + 1) }
                    R.id.cb34 -> { courses = courses.substring(0, 33) + "0" + courses.substring(33 + 1) }
                    R.id.cb35 -> { courses = courses.substring(0, 34) + "0" + courses.substring(34 + 1) }
                    R.id.cb36 -> { courses = courses.substring(0, 35) + "0" + courses.substring(35 + 1) }
                    R.id.cb37 -> { courses = courses.substring(0, 36) + "0" + courses.substring(36 + 1) }
                    R.id.cb38 -> { courses = courses.substring(0, 37) + "0" + courses.substring(37 + 1) }
                    R.id.cb39 -> { courses = courses.substring(0, 38) + "0" + courses.substring(38 + 1) }
                    R.id.cb40 -> { courses = courses.substring(0, 39) + "0" + courses.substring(39 + 1) }
                    R.id.cb41 -> { courses = courses.substring(0, 40) + "0" + courses.substring(40 + 1) }
                    R.id.cb42 -> { courses = courses.substring(0, 41) + "0" + courses.substring(41 + 1) }
                    R.id.cb43 -> { courses = courses.substring(0, 42) + "0" + courses.substring(42 + 1) }
                    R.id.cb44 -> { courses = courses.substring(0, 43) + "0" + courses.substring(43 + 1) }
                    R.id.cb45 -> { courses = courses.substring(0, 44) + "0" + courses.substring(44 + 1) }
                    R.id.cb46 -> { courses = courses.substring(0, 45) + "0" + courses.substring(45 + 1) }
                    R.id.cb47 -> { courses = courses.substring(0, 46) + "0" + courses.substring(46 + 1) }
                    R.id.cb48 -> { courses = courses.substring(0, 47) + "0" + courses.substring(47 + 1) }
                    R.id.cb49 -> { courses = courses.substring(0, 48) + "0" + courses.substring(48 + 1) }
                    R.id.cb50 -> { courses = courses.substring(0, 49) + "0" + courses.substring(49 + 1) }
                }
            }
        }

        binding.cb1.setOnCheckedChangeListener(listener)
        binding.cb2.setOnCheckedChangeListener(listener)
        binding.cb3.setOnCheckedChangeListener(listener)
        binding.cb4.setOnCheckedChangeListener(listener)
        binding.cb5.setOnCheckedChangeListener(listener)
        binding.cb6.setOnCheckedChangeListener(listener)
        binding.cb7.setOnCheckedChangeListener(listener)
        binding.cb8.setOnCheckedChangeListener(listener)
        binding.cb9.setOnCheckedChangeListener(listener)
        binding.cb10.setOnCheckedChangeListener(listener)
        binding.cb11.setOnCheckedChangeListener(listener)
        binding.cb12.setOnCheckedChangeListener(listener)
        binding.cb13.setOnCheckedChangeListener(listener)
        binding.cb14.setOnCheckedChangeListener(listener)
        binding.cb15.setOnCheckedChangeListener(listener)
        binding.cb16.setOnCheckedChangeListener(listener)
        binding.cb17.setOnCheckedChangeListener(listener)
        binding.cb18.setOnCheckedChangeListener(listener)
        binding.cb19.setOnCheckedChangeListener(listener)
        binding.cb20.setOnCheckedChangeListener(listener)
        binding.cb21.setOnCheckedChangeListener(listener)
        binding.cb22.setOnCheckedChangeListener(listener)
        binding.cb23.setOnCheckedChangeListener(listener)
        binding.cb24.setOnCheckedChangeListener(listener)
        binding.cb25.setOnCheckedChangeListener(listener)
        binding.cb26.setOnCheckedChangeListener(listener)
        binding.cb27.setOnCheckedChangeListener(listener)
        binding.cb28.setOnCheckedChangeListener(listener)
        binding.cb29.setOnCheckedChangeListener(listener)
        binding.cb30.setOnCheckedChangeListener(listener)
        binding.cb31.setOnCheckedChangeListener(listener)
        binding.cb32.setOnCheckedChangeListener(listener)
        binding.cb33.setOnCheckedChangeListener(listener)
        binding.cb34.setOnCheckedChangeListener(listener)
        binding.cb35.setOnCheckedChangeListener(listener)
        binding.cb36.setOnCheckedChangeListener(listener)
        binding.cb37.setOnCheckedChangeListener(listener)
        binding.cb38.setOnCheckedChangeListener(listener)
        binding.cb39.setOnCheckedChangeListener(listener)
        binding.cb40.setOnCheckedChangeListener(listener)
        binding.cb41.setOnCheckedChangeListener(listener)
        binding.cb42.setOnCheckedChangeListener(listener)
        binding.cb43.setOnCheckedChangeListener(listener)
        binding.cb44.setOnCheckedChangeListener(listener)
        binding.cb45.setOnCheckedChangeListener(listener)
        binding.cb46.setOnCheckedChangeListener(listener)
        binding.cb47.setOnCheckedChangeListener(listener)
        binding.cb48.setOnCheckedChangeListener(listener)
        binding.cb49.setOnCheckedChangeListener(listener)
        binding.cb50.setOnCheckedChangeListener(listener)


        binding.myCoursesSaveBtn.setOnClickListener {

            FirebaseRef.userInfoRef.child(uid).child("courses").setValue(courses)

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()
        }

    }


    private fun getMyData() {

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                //Log.d(TAG, dataSnapshot.toString())
                val data = dataSnapshot.getValue(UserDataModel::class.java)

                courses = data?.courses.toString() ?: "00000000000000000000000000000000000000000000000000"

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }

        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }


//    private fun getMyCheckBox() {
//
//        for (index in 1..50) {
//            if (courses[index-1] == '1') {
//                when (index) {
//                    1 -> binding.cb1.isChecked = true
//                    2 -> binding.cb2.isChecked = true
//                    3 -> binding.cb3.isChecked = true
//                    4 -> binding.cb4.isChecked = true
//                    5 -> binding.cb5.isChecked = true
//                }
//            }
//        }
//    }

}



//val checkBoxesList = listOf<View>(
//            binding.cb1,
//            binding.cb2,
//            binding.cb3,
//            binding.cb4,
//            binding.cb5,
//            binding.cb6,
//            binding.cb7,
//            binding.cb8,
//            binding.cb9,
//            binding.cb10,
//            binding.cb11,
//            binding.cb12,
//            binding.cb13,
//            binding.cb14,
//            binding.cb15,
//            binding.cb16,
//            binding.cb17,
//            binding.cb18,
//            binding.cb19,
//            binding.cb20,
//            binding.cb21,
//            binding.cb22,
//            binding.cb23,
//            binding.cb24,
//            binding.cb25,
//            binding.cb26,
//            binding.cb27,
//            binding.cb28,
//            binding.cb29,
//            binding.cb30,
//            binding.cb31,
//            binding.cb32,
//            binding.cb33,
//            binding.cb34,
//            binding.cb35,
//            binding.cb36,
//            binding.cb37,
//            binding.cb38,
//            binding.cb39,
//            binding.cb40,
//            binding.cb41,
//            binding.cb42,
//            binding.cb43,
//            binding.cb44,
//            binding.cb45,
//            binding.cb46,
//            binding.cb47,
//            binding.cb48,
//            binding.cb49,
//            binding.cb50
//)


//        binding.completeBtn.setOnClickListener {
//
//            var currentModel = UserDataModel(uid, null)
//
//            val postListener = object : ValueEventListener {
//
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                    val data = dataSnapshot.getValue(UserDataModel::class.java)
//                    currentModel.name = data!!.name
//
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    // Getting Post failed, log a message
//                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                }
//            }
//
//            phoneNumber = binding.phoneArea.text.toString()
//
//            currentModel.phone = phoneNumber