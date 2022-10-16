package com.example.explodingkittensapp.friend

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.activities.MainActivity
import com.example.explodingkittensapp.activities.OnClickListener
import com.example.explodingkittensapp.model.UserModel


class FriendFragment : Fragment(), OnClickListener {

    lateinit var adapter: FriendRecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    private val viewModel: FriendViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(activity as MainActivity)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        viewModel.getUsers()
        recyclerView = view.findViewById(R.id.friendRecyclerView)
        adapter = FriendRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(activity,2, LinearLayoutManager.VERTICAL,false)
        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })

        val addFriendsbtn : Button = view.findViewById(R.id.addFriendsbtn)

        addFriendsbtn.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_friendsFragment_to_addFriendsFragment)}

        return view
    }

    override fun onClickItem(item: Any) {
        if (item is UserModel){
            viewModel.select(item)
            viewModel.navigator.navigateToFriendDetail()
        }
    }

}