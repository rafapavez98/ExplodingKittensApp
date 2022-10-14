package com.example.explodingkittensapp.user

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.explodingkittensapp.*
import com.example.explodingkittensapp.APImodels.Bodies.APIUser
import com.example.explodingkittensapp.APImodels.Responses.APILoginResponse
import com.example.explodingkittensapp.APImodels.Responses.APISigninResponse
import com.example.explodingkittensapp.database.DatabaseRepository
import com.example.explodingkittensapp.database.UserDao
import com.example.explodingkittensapp.database.UserEntityMapper
import com.example.explodingkittensapp.model.*
import com.example.explodingkittensapp.networking.UserRepository
import com.example.explodingkittensapp.networking.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class UserViewModel(application: Application) : AndroidViewModel(application) {

    val app = application

    var users: MutableList<UserModel> = mutableListOf()
    var usersLiveData = MutableLiveData<MutableList<UserModel>>()
    var idLast = 0
    lateinit var token: String
    lateinit var uname: String

    var database: UserDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        database = DatabaseRepository(application).postUserDao()
        loadUsers()
        val service = getRetrofit().create(UserRepository::class.java)
    }

    //DB Methods
    fun saveUser(user: UserModel) {
        executor.execute {
            database.insertUser(UserEntityMapper().mapToCached(user))
            users.add(user)
            usersLiveData.postValue(users)
            idLast ++
        }
    }

    fun loadUsers() {
        executor.execute {
            users = database.getAllUsers().map {
                UserEntityMapper().mapFromCached(it)
            } as MutableList<UserModel>
            if(users.size != 0) {
                usersLiveData.postValue(users)
            }
        }
    }

    //API Methods
    fun addUserAPI(newUser: APIUser){
        val service = getRetrofit().create(UserRepository::class.java)
        val call =  service.createUser(newUser)
        call.enqueue(object :  Callback<APISigninResponse> {
            override fun onFailure(call: Call<APISigninResponse>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<APISigninResponse>, response: Response<APISigninResponse>) {
                if(response.body() != null){
                    val userAPI = response.body()
                    if (userAPI != null) {
                        println(userAPI)
                    }
                }
            }
        })
    }
}