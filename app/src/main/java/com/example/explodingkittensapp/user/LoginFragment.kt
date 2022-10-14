package com.example.explodingkittensapp.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.explodingkittensapp.APImodels.Bodies.APILogin
import com.example.explodingkittensapp.APImodels.Bodies.APIUser
import com.example.explodingkittensapp.R


class LoginFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val signinbtn : Button = view.findViewById(R.id.loginsigninbtn)

        signinbtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signinFragment)}

        val loginbtn : Button = view.findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener {
            val email : EditText = view.findViewById(R.id.mail)
            val password : EditText = view.findViewById(R.id.password)

            val lemail = email.text.toString()
            val lpassword = password.text.toString()

            val newAPILogin = APILogin(lemail, lpassword)
            viewModel.loginUserAPI(newAPILogin, view)
            Toast.makeText(activity, "Welcome", Toast.LENGTH_LONG).show()
        }
        return view
    }

}