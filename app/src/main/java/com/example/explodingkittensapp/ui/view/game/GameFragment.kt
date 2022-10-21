package com.example.explodingkittensapp.ui.view.game

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
import com.example.explodingkittensapp.ui.view.mygames.MyGamesRecyclerViewAdapter
import com.example.explodingkittensapp.ui.viewmodel.GameViewModel
import com.example.explodingkittensapp.ui.viewmodel.MyGamesViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class GameFragment : Fragment(), OnClickListener {

    lateinit var gameAdapter: GameRecyclerViewAdapter
    lateinit var otherPlayersadapter: OtherPlayersRecyclerViewAdapter
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    private val gameViewModel: GameViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameViewModel.setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uname = userViewModel.uname
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        gameViewModel.gameAPI(uname)

        recyclerView1 = view.findViewById(R.id.playerDeckRecyclerView)
        gameAdapter = GameRecyclerViewAdapter(this)
        recyclerView1.adapter = gameAdapter
        recyclerView1.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.HORIZONTAL,false)

        gameViewModel.gameLiveData.observe(viewLifecycleOwner, Observer {
            gameAdapter.set(it)
        })

        /*recyclerView2 = view.findViewById(R.id.otherPlayersRecyclerView)
        otherPlayersadapter = OtherPlayersRecyclerViewAdapter(this)
        recyclerView2.adapter = otherPlayersadapter
        recyclerView2.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.HORIZONTAL,false)

        gameViewModel.gameLiveData.observe(viewLifecycleOwner, Observer {
            otherPlayersadapter.set(it)
        })*/

        return view
    }

    override fun onClickItem(item: Any) {
        TODO("Not yet implemented")
    }


}