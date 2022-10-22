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
import com.example.explodingkittensapp.model.MatchModel
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.navigation.Navigator
import com.example.explodingkittensapp.networking.MatchInviteRemoteRepository
import com.example.explodingkittensapp.networking.UsersRemoteRepository
import com.example.explodingkittensapp.networking.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyGamesViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    var myGames: MutableList<MatchModel> = mutableListOf()
    var myGamesLiveData = MutableLiveData<MutableList<MatchModel>>()
    val chosenMyGames = MutableLiveData<MatchModel>()
    lateinit var gamename: String

    lateinit var navigator: Navigator

    //lateinit var navigator: Navigator

    //var database: UserDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        //database = DatabaseRepository(application).postUserDao()
        loadMyGames()
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }

    //DB Methods
    fun saveMyGames(user: MatchModel) {
        executor.execute {
            //database.insertUser(UserEntityMapper().mapToCached(user))
            myGames.add(user)
            myGamesLiveData.postValue(myGames)
        }
    }

    fun loadMyGames() {
        if(myGames.size != 0) {
            myGamesLiveData.postValue(myGames)
        }
    }

    //API Methods
    fun addMyGamesAPI(newUser: APIUser){
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
                        //val usr = UserModel(userAPI.id,userAPI.email,userAPI.username,userAPI.password,userAPI.total_matches,userAPI.winrate,userAPI.friends)
                        //saveFriend(usr)
                    }
                }
            }
        })
    }

    fun myGamesAPI(username: String){
        val service = getRetrofit().create(MatchInviteRemoteRepository::class.java)
        val call =  service.getMatches(username) //se queda como friends
        call.enqueue(object : Callback<List<MatchModel>> {
            override fun onFailure(call: Call<List<MatchModel>>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<List<MatchModel>>, response: Response<List<MatchModel>>) {
                if(response.body() != null){
                    val myGamesAPI = response.body() //revisar si cambiar
                    if (myGamesAPI != null) {
                        myGames.clear()
                        for (user in myGamesAPI){
                            if (!myGames.contains(user)){
                                myGames.add(user)
                                //saveFriend(user)
                            }
                        }
                        if(myGames.size != 0) {
                            myGamesLiveData.postValue(myGames)
                        }
                        //friends2 = friendsAPI
                    }
                }
            }
        })
    }

    fun selectMyGames(item: MatchModel){
        chosenMyGames.value = item
        gamename = item.gamename
        println(chosenMyGames)
    }



}