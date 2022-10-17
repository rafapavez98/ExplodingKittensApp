package com.example.explodingkittensapp.ui.view.mygames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.activities.OnClickListener
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.ui.viewmodel.MyGamesViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class MyGamesFragment : Fragment(), OnClickListener {

    lateinit var adapter: MyGamesRecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    private val myGamesViewModel: MyGamesViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myGamesViewModel.setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uname = userViewModel.uname
        val view = inflater.inflate(R.layout.fragment_my_games, container, false)
        myGamesViewModel.myGamesAPI(uname)
        //println(friendViewModel.friends)
        recyclerView = view.findViewById(R.id.myGamesRecyclerView)
        adapter = MyGamesRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.VERTICAL,false)

        myGamesViewModel.myGamesLiveData.observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })



        return view
    }

    override fun onClickItem(item: Any) {
        TODO("Not yet implemented")
    }


}