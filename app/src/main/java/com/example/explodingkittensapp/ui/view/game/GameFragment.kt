package com.example.explodingkittensapp.ui.view.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explodingkittensapp.APImodels.Bodies.APIGameParticipants
import com.example.explodingkittensapp.APImodels.Bodies.APIMyturn
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.activities.OnClickListener
import com.example.explodingkittensapp.model.CardModel
import com.example.explodingkittensapp.ui.viewmodel.GameViewModel
import com.example.explodingkittensapp.ui.viewmodel.MyGamesViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class GameFragment : Fragment(), OnClickListener {

    lateinit var gameAdapter: PlayerDeckRecyclerViewAdapter
    lateinit var otherPlayersadapter: OtherPlayersRecyclerViewAdapter
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    private val cardsGameViewModel: GameViewModel by activityViewModels()
    private val playersGameViewModel: GameViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val gameViewModel: MyGamesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardsGameViewModel.setNavigator(activity as MainActivity)
        val aux = gameViewModel.chosenMyGames.value?.let { APIMyturn(it.gamename, userViewModel.uname) }

        if (aux != null) {
            cardsGameViewModel.getMyTurn(aux)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uname = userViewModel.uname
        val gamename = gameViewModel.gamename

        val participants = gameViewModel.chosenMyGames.value?.participants
        val lastcard = gameViewModel.chosenMyGames.value?.lastcard
        //val turn = gameViewModel.chosenMyGames.value?.turn

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val drawbtn : Button = view.findViewById(R.id.drawbtn)
        val lastcardimageview : ImageView = view.findViewById(R.id.lastCardImageView)

        val skipcard = R.drawable.skip
        val defusecard = R.drawable.defuse
        val kittencard = R.drawable.kitten1

        // agregar cada caso de cartas a medida que se van implementando mas cartas
        if (lastcard == "5GKopmheJVBVJwcEMWtI") { // kitten
            lastcardimageview.setImageResource(kittencard)
        }
        else if (lastcard == "PHKvNq2afTrKUHYznI1W"){
            lastcardimageview.setImageResource(skipcard)
        }
        else if (lastcard == "5VYvZ4k72Y2fbfEmGdiV"){
            lastcardimageview.setImageResource(defusecard)
        }



        cardsGameViewModel.cardsAPI(uname)

        //cards recyclerview
        recyclerView1 = view.findViewById(R.id.playerDeckRecyclerView)
        gameAdapter = PlayerDeckRecyclerViewAdapter(this)
        recyclerView1.adapter = gameAdapter
        recyclerView1.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.HORIZONTAL,false)

        cardsGameViewModel.gameLiveData.observe(viewLifecycleOwner, Observer {
            gameAdapter.set(it)
        })

        //participants recycler view
        val newAPIGameParticipants = APIGameParticipants(uname, gamename)
        playersGameViewModel.playersAPI(newAPIGameParticipants)
        recyclerView2 = view.findViewById(R.id.otherPlayersRecyclerView)
        otherPlayersadapter = OtherPlayersRecyclerViewAdapter(this)
        recyclerView2.adapter = otherPlayersadapter
        recyclerView2.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.HORIZONTAL,false)

        playersGameViewModel.playersLiveData.observe(viewLifecycleOwner, Observer {
            otherPlayersadapter.set(it)
        })

        drawbtn.setOnClickListener{
            println("CLICK")

            if (cardsGameViewModel.myturn != gameViewModel.chosenMyGames.value?.turn.toString()){ // fix my turn para que este actualizada desde un principio con el valor
                drawbtn.isClickable = false
            }
        }

        return view
    }

    override fun onClickItem(item: Any) {
        if (item is CardModel){
            //playcard
        }
        else{
            TODO("Not yet implemented")
        }

    }


}