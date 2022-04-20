package com.example.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.order.databinding.LoadingFragmentBinding
import com.example.order.databinding.MainActivityBinding
import com.example.order.ui.main.LoadingFragment
import com.example.order.ui.main.MainFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, LoadingFragment.newInstance())
                .commitNow()
        }
    }
}