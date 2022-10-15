package com.example.explodingkittensapp.friend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.explodingkittensapp.model.Friend
import com.example.explodingkittensapp.model.Friends
import kotlinx.coroutines.launch

class FriendViewModel(application: Application): AndroidViewModel(application) {
    private val allFriends = Friends.createFriendList()

    internal val friendsLiveData = MutableLiveData<MutableList<Friend>>()
    internal val loadingLiveData = MutableLiveData(true)

    fun refresh() {
        loadFriends()
        loadingLiveData.value = false
    }

    private fun loadFriends() {
        viewModelScope.launch {
            friendsLiveData.postValue(allFriends.toMutableList())
        }
    }

}