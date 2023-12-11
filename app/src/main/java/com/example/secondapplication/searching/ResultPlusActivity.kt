package com.example.secondapplication.searching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.secondapplication.R
import com.example.secondapplication.databinding.ActivityResultPlusBinding
import com.example.secondapplication.setting.MyPageActivity

class ResultPlusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultPlusBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result_plus)

        binding.sky.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            startActivity(intent)
        }


    }
}