package com.example.explodingkittensapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.explodingkittensapp.api.APIService
import com.example.explodingkittensapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
/**  private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://exploding-kittens-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getUsers("users")
            val users = call.body()
            if(call.isSuccessful){
                //mostrar
            }else{
                //no mostrar
            }
        }
    }

**/
}