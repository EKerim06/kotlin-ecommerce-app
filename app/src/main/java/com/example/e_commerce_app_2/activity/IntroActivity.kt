package com.example.e_commerce_app_2.activity

import android.content.Intent
import android.os.Bundle
import com.example.e_commerce_app_2.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }


    }
}