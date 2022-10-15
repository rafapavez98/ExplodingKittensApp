package com.example.explodingkittensapp.friend

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.databinding.FragmentFriendsBinding
import com.example.explodingkittensapp.model.Friend
import com.example.explodingkittensapp.model.Friends


class FriendFragment : Fragment(), FriendItemAdapter.ActionListener {

    private lateinit var friendItemAdapter: FriendItemAdapter
    private lateinit var viewModel: FriendViewModel
    private val allFriends = Friends.createFriendList()
    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    private var mLastClickTime = System.currentTimeMillis()
    private val CLICK_TIME_INTERVAL: Long = 300

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FriendViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refresh()

        friendItemAdapter = FriendItemAdapter(mutableListOf(), this)

        binding.friendViewModel = viewModel

        binding.friendListView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = friendItemAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.friendsLiveData.observe(viewLifecycleOwner) { friends ->
            friends?.let {
                binding.friendListView.visibility = View.VISIBLE
                friendItemAdapter.updateFriends(friends)
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                if (it) {
                    binding.friendListView.visibility = View.GONE
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        binding.editTextSearchFriend.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {  }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var searchText = p0.toString().lowercase()
                filterFriends(searchText)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun filterFriends(searchText: String) {
        val filteredFriends = allFriends.filter {
            it.username!!
                .lowercase()
                .contains(searchText)
        }

        friendItemAdapter.updateFriends(filteredFriends)
    }

    override fun goToFriendDetails(friend: Friend) {
        Toast.makeText(context, "hola! hiciste click en el ${friend.username}", Toast.LENGTH_LONG).show()

        val now = System.currentTimeMillis()
        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return;
        }

        mLastClickTime = now;

        val bundle = bundleOf("friendId" to friend.username.toString())

        findNavController().navigate(R.id.action_friendsFragment_to_friendDetails, bundle)
    }

}