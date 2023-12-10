package com.example.secondapplication.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthUtils {

    companion object {

        private lateinit var auth : FirebaseAuth

        fun getUid() : String {

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()

        }
    }


}