package com.example.explodingkittensapp.ui.view.addfriend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.ui.viewmodel.AddFriendsViewModel

class AddFriendsDetails : Fragment() {

    private val viewModel: AddFriendsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(activity as MainActivity)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_friends_details_fragment, container, false)
        val selected = viewModel.chosenAddFriends.value

        val username = view.findViewById<TextView>(R.id.addFriendsUserName)
        val winrate = view.findViewById<TextView>(R.id.addFriendsUserWinrate)
        val email = view.findViewById<TextView>(R.id.addFriendsUserEmail)
        val matches= view.findViewById<TextView>(R.id.addFriendsUserMatches)

        if (selected != null) {
            username.text = selected.username
            winrate.text = selected.winrate.toString()
            email.text = selected.email
            matches.text = selected.total_matches.toString()
        }

        return view
    }


}