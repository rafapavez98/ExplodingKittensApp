package com.example.explodingkittensapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val signinbtn : Button = view.findViewById(R.id.loginsigninbtn)

        signinbtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signinFragment)}

        val loginbtn : Button = view.findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeScreenFragment)}

        return view
    }

}