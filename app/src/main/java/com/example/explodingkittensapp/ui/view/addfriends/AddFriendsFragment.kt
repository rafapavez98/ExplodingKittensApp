package com.example.explodingkittensapp.ui.view.addfriends

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
import com.example.explodingkittensapp.ui.viewmodel.AddFriendsViewModel
import com.example.explodingkittensapp.ui.viewmodel.UserViewModel


class AddFriendsFragment : Fragment(), OnClickListener {

    lateinit var adapter: AddFriendsRecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    private val addFriendsViewModel: AddFriendsViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFriendsViewModel.setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uname = userViewModel.uname
        val view = inflater.inflate(R.layout.fragment_add_friends, container, false)
        addFriendsViewModel.getFriendsAPI(uname)
        //println(friendViewModel.friends)
        recyclerView = view.findViewById(R.id.addFriendsRecyclerView)
        adapter = AddFriendsRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(activity,1, LinearLayoutManager.VERTICAL,false)

        addFriendsViewModel.addFriendsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })



        return view
    }

    override fun onClickItem(item: Any) {
        if (item is UserModel){
            addFriendsViewModel.selectAddFriends(item)
            addFriendsViewModel.navigator.navigateToAddFriendsDetail()
        }
    }

}