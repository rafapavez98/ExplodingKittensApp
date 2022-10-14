package com.example.explodingkittensapp.model

class Friends {

    companion object {
        fun createFriendList() : ArrayList<Friend> {
            val listafriends = ArrayList<Friend>()
            listafriends.add(Friend("1", "a@a", "aa", "123", 0,100))
            listafriends.add(Friend("2", "a@a", "aa", "123", 0,100))
            listafriends.add(Friend("3", "a@a", "aa", "123", 0,100))
            listafriends.add(Friend("4", "a@a", "aa", "123", 0,100))
            listafriends.add(Friend("5", "a@a", "aa", "123", 0,100))

            return listafriends
        }
    }

}