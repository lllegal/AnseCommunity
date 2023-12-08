package com.example.secondapplication.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapplication.R
import com.example.secondapplication.auth.UserDataModel
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

    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        getMyData()

        val myEmail = findViewById<EditText>(R.id.myEmail)

        val user = Firebase.auth.currentUser
        user?.let {

            myEmail.setText(it.email)

        }


        findViewById<Button>(R.id.nameRevisionBtn).setOnClickListener {

            name = findViewById<EditText>(R.id.myName).text.toString()
            FirebaseRef.userInfoRef.child(uid).child("name").setValue(name)

            Toast.makeText(this, "이름이 변경되었습니다.", Toast.LENGTH_LONG).show()

        }

        findViewById<Button>(R.id.settingBtn).setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

    }


    private fun getMyData() {

        val myUid = findViewById<TextView>(R.id.myUid)
        val myName = findViewById<EditText>(R.id.myName)
        val myUsername = findViewById<EditText>(R.id.myUsername)

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                Log.d(TAG, dataSnapshot.toString())
                val data = dataSnapshot.getValue(UserDataModel::class.java)

                myUid.text = data!!.uid
                myName.setText(data.name)
                myUsername.setText(data.username)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }

}