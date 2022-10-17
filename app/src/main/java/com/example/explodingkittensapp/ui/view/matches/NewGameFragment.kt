package com.example.explodingkittensapp.ui.view.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.explodingkittensapp.R


class NewGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)

        val homebtn : Button = view.findViewById(R.id.homebtn)

        homebtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_newGameFragment_to_homeScreenFragment)}

        val friendsbtn : Button = view.findViewById(R.id.friendsbtn)

        friendsbtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_newGameFragment_to_friendsFragment)}

        return view
    }


}