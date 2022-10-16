package com.example.explodingkittensapp.navigation

import androidx.navigation.findNavController
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity

class Navigator(val activity: MainActivity?) {

    fun navigateToFriendDetail(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_friendsFragment_to_friendDetails)
    }
}