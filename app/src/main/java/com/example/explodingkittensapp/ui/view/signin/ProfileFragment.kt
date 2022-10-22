package com.example.explodingkittensapp.ui.view.signin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel
import org.w3c.dom.Text


class ProfileFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        val email : TextView = view.findViewById(R.id.profileEmail)
        val profileusername : TextView = view.findViewById(R.id.profileUserName)
        val profilematches : TextView = view.findViewById(R.id.profileMatches)
        val profilewinrate : TextView = view.findViewById(R.id.profileWinrate)

        email.text = "Email: "+ viewModel.profile.email
        profileusername.text = viewModel.profile.username
        profilematches.text = "Total Matches: "+ viewModel.profile.total_matches
        profilewinrate.text = "WinRate: "+ viewModel.profile.winrate

        return view
    }


}