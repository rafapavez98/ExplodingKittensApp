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

class FriendsRequestsViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    var friendsRequests: MutableList<UserModel> = mutableListOf()
    var friendsRequestsLiveData = MutableLiveData<MutableList<UserModel>>()
    val chosenFriendsRequests = MutableLiveData<UserModel>()

    lateinit var navigator: Navigator

    //lateinit var navigator: Navigator

    var database: UserDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        database = DatabaseRepository(application).postUserDao()
        loadFriendsRequests()
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }
    //DB Methods
    fun saveFriendsRequests(user: UserModel) {
        executor.execute {
            database.insertUser(UserEntityMapper().mapToCached(user))
            friendsRequests.add(user)
            friendsRequestsLiveData.postValue(friendsRequests)
        }
    }

    fun loadFriendsRequests() {
        executor.execute {
            friendsRequests = database.getAllUsers().map {
                UserEntityMapper().mapFromCached(it)
            } as MutableList<UserModel>
            println(friendsRequests)
            if(friendsRequests.size != 0) {
                friendsRequestsLiveData.postValue(friendsRequests)
            }
        }
    }

    //API Methods
    fun addFriendsRequestsAPI(newUser: APIUser){
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

    fun friendsRequestsAPI(username: String){
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
                            if (!friendsRequests.contains(user)){
                                friendsRequests.add(user)
                                //saveFriend(user)
                            }
                        }
                        if(friendsRequests.size != 0) {
                            friendsRequestsLiveData.postValue(friendsRequests)
                        }
                        //friends2 = friendsAPI
                    }
                }
            }
        })
    }

    fun selectFriendsRequests(item: UserModel){
        chosenFriendsRequests.value = item
        println(chosenFriendsRequests)
    }



}