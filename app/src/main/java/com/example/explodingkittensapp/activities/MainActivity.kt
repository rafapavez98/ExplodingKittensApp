package com.example.explodingkittensapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.explodingkittensapp.databinding.ActivityMainBinding
import com.example.explodingkittensapp.model.Friends

var isLoggedIn = false
val friends = Friends.createFriendList()

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}