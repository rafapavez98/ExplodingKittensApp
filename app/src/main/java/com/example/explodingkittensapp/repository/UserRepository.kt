package com.example.explodingkittensapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.explodingkittensapp.database.UserDao
import com.example.explodingkittensapp.database.UserEntityMapper
import com.example.explodingkittensapp.model.AppDatabase
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.networking.UsersRemoteRepository
import com.example.explodingkittensapp.networking.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {

    private val service: UsersRemoteRepository
    private val users = MutableLiveData<List<UserModel>>()
    private val database: AppDatabase
    private val userDao: UserDao
    private val userEntityMapper: UserEntityMapper
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()


    init {
        service = getRetrofit().create(UsersRemoteRepository::class.java)
        database = Room.databaseBuilder(application, AppDatabase::class.java, "ecomerce-db").build()
        userDao = database.userDao()
        userEntityMapper = UserEntityMapper()
    }

    fun getFriendUsers() {
        executor.execute {
            userDao.getAllUsers()
        }

    }

    fun getUser(id: Long) {
        executor.execute{
            userDao.getUser(id)
        }

    }

    fun getUsers(): LiveData<List<UserModel>> {
        getCacheUsers()
        if (users.value.isNullOrEmpty()) {
            getRemoteUsers()
        }
        executor.execute{
            userDao.getAllUsers()
        }
        getCacheUsers()
        return users
    }

    private fun getRemoteUsers() {
        val call = service.getUsers()
        call.enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                val body = response.body()
                if (body != null) {
                    val us = users.value
                    executor.execute {
                        body.forEach {
                            userDao.insertUser(userEntityMapper.mapToCached(it))
                        }
                    }
                }
            }
            //entra aca
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                println("Fallo la request")
            }
        })
    }

    private fun getCacheUsers() {
        executor.execute {
            users.postValue(
                userDao.getAllUsers().map { userEntityMapper.mapFromCached(it) })
        }
    }

}