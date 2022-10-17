package com.example.explodingkittensapp.ui.view.joingame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.explodingkittensapp.APImodels.Bodies.APIAcceptInvite
import com.example.explodingkittensapp.APImodels.Bodies.APIRejectInvite
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.model.FriendInviteModel
import com.example.explodingkittensapp.ui.viewmodel.FriendViewModel
import com.example.explodingkittensapp.ui.viewmodel.FriendsRequestsViewModel
import com.example.explodingkittensapp.ui.viewmodel.JoinGameViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class JoinGameDetails : Fragment() {

    private val viewModel: JoinGameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(activity as MainActivity)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.join_game_details_fragment, container, false)
        val selected = viewModel.chosenJoinGame.value

        val username = view.findViewById<TextView>(R.id.joinGameName)
        val winrate = view.findViewById<TextView>(R.id.joinGameUserWinrate)
        val email = view.findViewById<TextView>(R.id.joinGameUserEmail)
        val matches= view.findViewById<TextView>(R.id.joinGameUserMatches)

        if (selected != null) {
            username.text = selected.username
            winrate.text = selected.winrate.toString()
            email.text = selected.email
            matches.text = selected.total_matches.toString()
        }

        return view
    }


}