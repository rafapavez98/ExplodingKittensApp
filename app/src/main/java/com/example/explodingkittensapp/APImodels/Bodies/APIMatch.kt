package com.example.explodingkittensapp.APImodels.Bodies

data class APIMatch(
    var creator: String,
    var settings: List<String>,
    var participants: List<String>
)