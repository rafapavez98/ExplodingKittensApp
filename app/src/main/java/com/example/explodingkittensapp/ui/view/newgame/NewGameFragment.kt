package com.example.explodingkittensapp.ui.view.newgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explodingkittensapp.APImodels.Bodies.APIMatch
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.activities.OnClickListener
import com.example.explodingkittensapp.model.UserModel
import com.example.explodingkittensapp.ui.viewmodel.JoinGameViewModel
import com.example.explodingkittensapp.ui.viewmodel.NewGameViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class NewGameFragment : Fragment(), OnClickListener {

    lateinit var adapter: NewGameRecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    private val newGameViewModel: NewGameViewModel by activityViewModels()
    private val viewModel: JoinGameViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newGameViewModel.setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uname = userViewModel.uname
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)
        newGameViewModel.newGameAPI(uname)
        //println(friendViewModel.friends)
        recyclerView = view.findViewById(R.id.newGameRecyclerView)
        adapter = NewGameRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.VERTICAL,false)

        newGameViewModel.newGameLiveData.observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })

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
    override fun onClickItem(item: Any) {
        if (item is UserModel){
            newGameViewModel.selectNewGame(item)
            newGameViewModel.navigator.navigateToNewGameDetail()
        }
    }


}