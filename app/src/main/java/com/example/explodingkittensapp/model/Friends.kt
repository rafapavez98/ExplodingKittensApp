package com.example.explodingkittensapp.model

class Friends {
    companion object {
        fun createFriendList() : ArrayList<Friend> {
            val listafriends = ArrayList<Friend>()
            listafriends.add(Friend( "user1@gmail.com", "user1", "user1", 0,100))
            listafriends.add(Friend( "user2@gmail.com", "user2", "user2", 0,100))
            listafriends.add(Friend( "user3@gmail.com", "user3", "user3", 0,100))
            listafriends.add(Friend( "user4@gmail.com", "user4", "user4", 0,100))
            listafriends.add(Friend( "user5@gmail.com", "user5", "user5", 0,100))

            return listafriends
        }
    }

}