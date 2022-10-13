package com.example.explodingkittensapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.explodingkittensapp.api.RetrofitClient
import com.example.explodingkittensapp.databinding.FragmentSigninBinding
import com.example.explodingkittensapp.models.SigninResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_signin, container, false)

        val signinbtn : Button = view.findViewById(R.id.signinbtn)
        val signinusername : EditText = view.findViewById(R.id.signinusername)
        val signinpassword : EditText = view.findViewById(R.id.signinpassword)
        val signinmail : EditText = view.findViewById(R.id.signinmail)


        signinbtn.setOnClickListener {
            val email = signinmail.text.toString().trim()
            val username = signinusername.text.toString().trim()
            val password = signinpassword.text.toString().trim()
            if(email.isEmpty()){
                signinmail.error = "Email required"
                signinmail.requestFocus()
                return@setOnClickListener
            }
            if(username.isEmpty()){
                signinusername.error = "Username required"
                signinusername.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                signinpassword.error = "Password required"
                signinpassword.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.createUser(
                email,
                username,
                password,
                total_matches = 0,
                winrate = 100,
                friends = listOf()
            ).enqueue(object: Callback<SigninResponse>{
                override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<SigninResponse>,response: Response<SigninResponse>) {
                    Toast.makeText(activity, response.body()?.msg, Toast.LENGTH_LONG).show()
                }

            })
        }

        return view
    }

//Navigation.findNavController(view).navigate(R.id.action_signinFragment_to_loginFragment)
}