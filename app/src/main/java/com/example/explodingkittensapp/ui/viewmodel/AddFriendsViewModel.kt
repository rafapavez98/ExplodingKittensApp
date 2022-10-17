package com.example.explodingkittensapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.explodingkittensapp.APImodels.Bodies.APIUser
import com.example.explodingkittensapp.APImodels.Responses.APISigninResponse
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.database.DatabaseRepository
import com.example.explodingkittensapp.database.UserDao
import com.example.explodingkittensapp.database.UserEntityMapper
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.navigation.Navigator
import com.example.explodingkittensapp.networking.UsersRemoteRepository
import com.example.explodingkittensapp.networking.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddFriendsViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    var addfriends: MutableList<UserModel> = mutableListOf()
    var addFriendsLiveData = MutableLiveData<MutableList<UserModel>>()
    val chosenAddFriends = MutableLiveData<UserModel>()
    lateinit var uname: String

    lateinit var navigator: Navigator

    var database: UserDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        database = DatabaseRepository(application).postUserDao()
        loadAddFriends()
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }


    fun loadAddFriends() {
        executor.execute {
            addfriends = database.getAllUsers().map {
                UserEntityMapper().mapFromCached(it)
            } as MutableList<UserModel>
            println(addfriends)
            if(addfriends.size != 0) {
                addFriendsLiveData.postValue(addfriends)
            }
        }
    }

    //API Methods
    fun addFriendAPI(newUser: APIUser){
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.createUser(newUser)
        call.enqueue(object : Callback<APISigninResponse> {
            override fun onFailure(call: Call<APISigninResponse>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<APISigninResponse>, response: Response<APISigninResponse>) {
                if(response.body() != null){
                    val userAPI = response.body()
                    if (userAPI != null) {
                        println(userAPI)
                        val usr = UserModel(userAPI.id,userAPI.email,userAPI.username,userAPI.password,userAPI.total_matches,userAPI.winrate,userAPI.friends)
                        //saveFriend(usr)
                    }
                }
            }
        })
    }

    fun addFriendsAPI(username: String){
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.getFriends(username)
        call.enqueue(object : Callback<List<UserModel>> {
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if(response.body() != null){
                    val friendsAPI = response.body()
                    if (friendsAPI != null) {
                        for (user in friendsAPI){
                            if (!addfriends.contains(user)){
                                addfriends.add(user)
                                //saveFriend(user)
                            }
                        }
                        if(addfriends.size != 0) {
                            addFriendsLiveData.postValue(addfriends)
                        }

                    }
                }
            }
        })
    }

    fun selectAddFriends(item: UserModel){
        chosenAddFriends.value = item
        println(chosenAddFriends)
    }

}