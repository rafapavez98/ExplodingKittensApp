package com.example.explodingkittensapp.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.explodingkittensapp.databinding.FriendDetailsFragmentBinding
import com.example.explodingkittensapp.model.Friends

class FriendDetails : Fragment() {
    private val allFriends = Friends.createFriendList()
    private var friendName = "0"
    private var _binding: FriendDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FriendDetailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            friendName = FriendDetailsArgs.fromBundle(it).friendId
            setFriendAttributes(friendName)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setFriendAttributes(friendName: String) {
        val username = _binding?.userName
        val email = _binding?.userEmail
        val totalmatches = _binding?.userMatches
        val winrate = _binding?.userWinrate


        val friend = allFriends.find { it.username.toString() == friendName }

        if (friend != null) {
            username?.text = friend.username
            email?.text = friend.email
            totalmatches?.text = friend.total_matches.toString()
            winrate?.text = friend.winrate.toString()

        }
    }
}