package com.example.secondapplication.setting

import android.os.Bundle
import android.util.Log
import android.widget.EditText
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

class SettingActivity : AppCompatActivity() {

    private val TAG = "SettingActivity"

    private val uid = FirebaseAuthUtils.getUid()

    private lateinit var binding: ActivitySettingBinding

    private var phoneNumber = ""
    private var url = ""
    private var kakaoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        getMyData()

        binding.completeBtn.setOnClickListener {

            phoneNumber = binding.phoneNumberArea.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("phoneNumber").setValue(phoneNumber)

            url = binding.urlArea.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("url").setValue(url)

            kakaoId = binding.kakaoIdArea.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("kakaoId").setValue(kakaoId)

            Toast.makeText(this, "수정이 완료되었습니다.", Toast.LENGTH_LONG).show()

        }

    }

    private fun getMyData() {

        val myPhoneNumber = findViewById<EditText>(R.id.phoneNumberArea)
        val myUrl = findViewById<EditText>(R.id.urlArea)
        val myKakaoId = findViewById<EditText>(R.id.kakaoIdArea)

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                //Log.d(TAG, dataSnapshot.toString())
                val data = dataSnapshot.getValue(UserDataModel::class.java)

                myPhoneNumber.setText(data?.phoneNumber)
                myUrl.setText(data?.url)
                myKakaoId.setText(data?.kakaoId)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }

}





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

