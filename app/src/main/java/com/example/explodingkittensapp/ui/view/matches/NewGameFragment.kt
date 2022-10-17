package com.example.explodingkittensapp.ui.view.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.explodingkittensapp.APImodels.Bodies.APIMatch
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.ui.viewmodel.JoinGameViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class NewGameFragment : Fragment() {

    private val viewModel: JoinGameViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)

        val homebtn : Button = view.findViewById(R.id.homebtn)

        homebtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_newGameFragment_to_homeScreenFragment)}

        val friendsbtn : Button = view.findViewById(R.id.friendsbtn)
        val createbtn : Button = view.findViewById(R.id.createGamebtn)

        friendsbtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_newGameFragment_to_friendsFragment)
        }

        createbtn.setOnClickListener {
            val settings = mutableListOf<String>()
            val participants = mutableListOf<String>()
            participants.add(userViewModel.uname)

            val newMatch = APIMatch(userViewModel.uname, settings, participants)
            viewModel.createMatchAPI(newMatch, activity, view)
        }

        return view
    }


}