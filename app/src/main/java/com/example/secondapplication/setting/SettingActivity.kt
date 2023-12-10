package com.example.secondapplication.setting

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.R
import com.example.secondapplication.auth.UserDataModel
import com.example.secondapplication.databinding.ActivitySettingBinding
import com.example.secondapplication.utils.FirebaseAuthUtils
import com.example.secondapplication.utils.FirebaseRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SettingActivity : AppCompatActivity() {

    private val TAG = "SettingActivity"

    private val uid = FirebaseAuthUtils.getUid()

    private lateinit var binding: ActivitySettingBinding

    var courses = "00000000000000000000000000000000000000000000000000"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

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

        if (courses[0] == '1') {
            binding.cb1.setChecked(true)
        }

        if (courses[1] == '1') {
            binding.cb2.setChecked(true)
        }

        if (courses[2] == '1') {
            binding.cb3.setChecked(true)
        }

        if (courses[3] == '1') {
            binding.cb4.setChecked(true)
        }

        if (courses[4] == '1') {
            binding.cb5.setChecked(true)
        }

//        binding.cb12.isChecked = true
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

        //getMyCheckBox()

        var listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {

                    R.id.cb1 -> {
                        Toast.makeText(this, "체크되었다.", Toast.LENGTH_SHORT).show()
                        courses = courses.substring(0, 0) + "1" + courses.substring(1 + 1)
                    }

                    R.id.cb2 -> {
                        courses = courses.substring(0, 1) + "1" + courses.substring(1 + 1)
                    }

                    R.id.cb3 -> {
                        courses = courses.substring(0, 2) + "1" + courses.substring(2 + 1)
                    }

                    R.id.cb4 -> {
                        courses = courses.substring(0, 3) + "1" + courses.substring(3 + 1)
                    }

                    R.id.cb5 -> {
                        courses = courses.substring(0, 4) + "1" + courses.substring(4 + 1)
                    }

                }
            } else {
                when (buttonView.id) {

                    R.id.cb1 -> {
                        Toast.makeText(this, "체크해제?", Toast.LENGTH_SHORT).show()
                        courses = courses.substring(0, 0) + "0" + courses.substring(1 + 1)
                    }

                    R.id.cb2 -> {
                        courses = courses.substring(0, 1) + "0" + courses.substring(1 + 1)
                    }

                    R.id.cb3 -> {
                        courses = courses.substring(0, 2) + "0" + courses.substring(2 + 1)
                    }

                    R.id.cb4 -> {
                        courses = courses.substring(0, 3) + "0" + courses.substring(3 + 1)
                    }

                    R.id.cb5 -> {
                        courses = courses.substring(0, 4) + "0" + courses.substring(4 + 1)
                    }

                }
            }
        }

        binding.cb1.setOnCheckedChangeListener(listener)
        binding.cb2.setOnCheckedChangeListener(listener)
        binding.cb3.setOnCheckedChangeListener(listener)
        binding.cb4.setOnCheckedChangeListener(listener)
        binding.cb5.setOnCheckedChangeListener(listener)

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


    private fun getMyCheckBox() {

        for (index in 1..50) {
            if (courses[index-1].equals("1")) {
                when (index) {
                    1 -> binding.cb1.isChecked = true
                    2 -> binding.cb2.isChecked = true
                    3 -> binding.cb3.isChecked = true
                    4 -> binding.cb4.isChecked = true
                    5 -> binding.cb5.isChecked = true
                }
            }
        }
    }

}



//
//
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

