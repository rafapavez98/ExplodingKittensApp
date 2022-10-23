package com.example.explodingkittensapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.explodingkittensapp.APImodels.Bodies.APIGameParticipants
import com.example.explodingkittensapp.APImodels.Bodies.APIMyturn
import com.example.explodingkittensapp.APImodels.Bodies.APIPlay
import com.example.explodingkittensapp.APImodels.Responses.APIMessageResponse
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.model.CardModel
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.navigation.Navigator
import com.example.explodingkittensapp.networking.UsersRemoteRepository
import com.example.explodingkittensapp.networking.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    var cards: MutableList<CardModel> = mutableListOf()
    var players: MutableList<UserModel> = mutableListOf()
    var gameLiveData = MutableLiveData<MutableList<CardModel>>()
    var playersLiveData = MutableLiveData<MutableList<UserModel>>()
    val chosenGame = MutableLiveData<CardModel>()
    var myturn = ""

    lateinit var navigator: Navigator

    init{
        loadGame()
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }

    fun loadGame() {
        if(cards.size != 0) {
            gameLiveData.postValue(cards)
        }
    }

    fun cardsAPI(username: String){
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.getUserCards(username)
        call.enqueue(object : Callback<List<CardModel>> {
            override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<List<CardModel>>, response: Response<List<CardModel>>) {
                if(response.body() != null){
                    val cardsAPI = response.body()
                    if (cardsAPI != null) {
                        cards.clear()
                        for (user in cardsAPI){
                            cards.add(user)
                        }
                        if(cards.size != 0) {
                            gameLiveData.postValue(cards)
                        }
                    }
                }
            }
        })
    }
    fun playersAPI(participants: APIGameParticipants){
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.getGameParticipants(participants)
        call.enqueue(object : Callback<List<UserModel>> {
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if(response.body() != null){
                    val playersAPI = response.body()
                    if (playersAPI != null) {
                        players.clear()
                        for (user in playersAPI){
                            if (!players.contains(user)){
                                players.add(user)
                            }
                        }
                        if(players.size != 0) {
                            playersLiveData.value =(players)
                        }
                    }
                }
            }
        })
    }

    fun getMyTurn(apiMyturn: APIMyturn) {
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.getMyTurn(apiMyturn)
        call.enqueue(object : Callback<APIMessageResponse> {
            override fun onFailure(call: Call<APIMessageResponse>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<APIMessageResponse>, response: Response<APIMessageResponse>) {
                if(response.body() != null){
                    val messageAPI = response.body()
                    if (messageAPI != null) {
                        myturn = messageAPI.msg
                        //println(myturn)
                    }
                }
            }
        })
    }

    fun playCard(apiPlay: APIPlay) {
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call =  service.playCard(apiPlay)
        call.enqueue(object : Callback<APIMessageResponse> {
            override fun onFailure(call: Call<APIMessageResponse>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<APIMessageResponse>, response: Response<APIMessageResponse>) {
                if(response.body() != null){
                    val messageAPI = response.body()
                    if (messageAPI != null) {
                        //myturn = messageAPI.msg
                        //println(myturn)
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