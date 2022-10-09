package com.example.explodingkittensapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class SigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_signin, container, false)

        val signinbtn : Button = view.findViewById(R.id.signinbtn)

        signinbtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_signinFragment_to_loginFragment)}


        return view
    }


}