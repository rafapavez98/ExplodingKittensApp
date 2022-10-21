package com.example.explodingkittensapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.explodingkittensapp.APImodels.Bodies.APIUser
import com.example.explodingkittensapp.APImodels.Responses.APISigninResponse
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.model.CardModel
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

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    var game: MutableList<CardModel> = mutableListOf()
    var gameLiveData = MutableLiveData<MutableList<CardModel>>()
    val chosenGame = MutableLiveData<CardModel>()

    lateinit var navigator: Navigator

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init{

        loadGame()

    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }



    fun loadGame() {
        if(game.size != 0) {
            gameLiveData.postValue(game)
        }
    }



    fun gameAPI(username: String){
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.getUserCards(username)
        call.enqueue(object : Callback<List<CardModel>> {
            override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<List<CardModel>>, response: Response<List<CardModel>>) {
                if(response.body() != null){
                    val gameAPI = response.body() //revisar si cambiar
                    if (gameAPI != null) {
                        for (user in gameAPI){
                            if (!game.contains(user)){
                                game.add(user)
                                //saveFriend(user)
                            }
                        }
                        if(game.size != 0) {
                            gameLiveData.postValue(game)
                        }
                    }
                }
            }
        })
    }

    fun selectGame(item: CardModel){
        chosenGame.value = item
        println(chosenGame)
    }



}