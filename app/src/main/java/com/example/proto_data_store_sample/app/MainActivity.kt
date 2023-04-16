package com.example.proto_data_store_sample.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.proto_data_store_sample.R
import com.example.proto_data_store_sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.completeButton.setOnClickListener {
            viewModel.onSave()
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.complete.observe(this) {
            binding.completeTextView.text = it.toString()
        }
    }

}