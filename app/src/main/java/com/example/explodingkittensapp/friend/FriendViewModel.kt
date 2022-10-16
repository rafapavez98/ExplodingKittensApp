package com.example.explodingkittensapp.friend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.repository.UserRepository

class FriendViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        repository = UserRepository(application)
    }
    val app = application
    var usersLiveData = MutableLiveData<List<UserModel>>()
    var selected = UserModel("1","user1@gmail.com","user1","Description",0,100, mutableListOf())

    lateinit var navigator: com.example.explodingkittensapp.navigation.Navigator


    fun getUsers() {
        val users = repository.getUsers()
        usersLiveData = users as MutableLiveData<List<UserModel>>
    }

    fun select(user: UserModel){
        selected = user
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = com.example.explodingkittensapp.navigation.Navigator(activity)
    }




}