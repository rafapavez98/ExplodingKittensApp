package com.example.explodingkittensapp.ui.view.friendsrequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.ui.viewmodel.FriendsRequestsViewModel

class FriendsRequestsDetails : Fragment() {

    private val viewModel: FriendsRequestsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(activity as MainActivity)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.friends_requests_details_fragment, container, false)
        val selected = viewModel.chosenFriendsRequests.value

        val username = view.findViewById<TextView>(R.id.friendsRequestsUserName)
        val winrate = view.findViewById<TextView>(R.id.friendsRequestsUserWinrate)
        val email = view.findViewById<TextView>(R.id.friendsRequestsUserEmail)
        val matches= view.findViewById<TextView>(R.id.friendsRequestsUserMatches)

        if (selected != null) {
            username.text = selected.username
            winrate.text = selected.winrate.toString()
            email.text = selected.email
            matches.text = selected.total_matches.toString()
        }

        return view
    }


}