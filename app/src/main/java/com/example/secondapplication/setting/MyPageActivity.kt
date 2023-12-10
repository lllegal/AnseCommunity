package com.example.secondapplication.setting

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.R
import com.example.secondapplication.auth.UserDataModel
import com.example.secondapplication.databinding.ActivityMyPageBinding
import com.example.secondapplication.utils.FirebaseAuthUtils
import com.example.secondapplication.utils.FirebaseRef
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MyPageActivity : AppCompatActivity() {

    private val TAG = "MyPageActivity"

    private val uid = FirebaseAuthUtils.getUid()

    private lateinit var binding: ActivityMyPageBinding

    private var name = ""
    private var phoneNumber = ""
    private var url = ""
    private var kakaoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)

        getMyData()

        binding.myInfoSaveBtn.setOnClickListener {

            name = binding.myName.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("name").setValue(name)

            phoneNumber = binding.myPhoneNumber.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("phoneNumber").setValue(phoneNumber)

            url = binding.myUrl.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("url").setValue(url)

            kakaoId = binding.myKakaoId.text.toString()
            FirebaseRef.userInfoRef.child(uid).child("kakaoId").setValue(kakaoId)

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()

        }

    }


    private fun getMyData() {

        val myEmail = binding.myEmail

        val user = Firebase.auth.currentUser
        user?.let {
            myEmail.setText(it.email)
        }

        val myUid = binding.myUid
        val myUsername = binding.myUsername
        val myName = binding.myName
        val myPhoneNumber = binding.myPhoneNumber
        val myUrl = binding.myUrl
        val myKakaoId = binding.myKakaoId


        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                Log.d(TAG, dataSnapshot.toString())
                val data = dataSnapshot.getValue(UserDataModel::class.java)

                myUid.text = data!!.uid
                myUsername.setText(data.username)
                myName.setText(data.name)
                myPhoneNumber.setText(data.phoneNumber)
                myUrl.setText(data.url)
                myKakaoId.setText(data.kakaoId)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }

        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }


}