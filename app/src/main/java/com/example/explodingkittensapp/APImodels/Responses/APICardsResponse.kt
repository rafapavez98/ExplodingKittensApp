package com.example.explodingkittensapp.APImodels.Responses

import android.os.Parcelable
import com.example.explodingkittensapp.model.CardModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class APICardsResponse(
    val cards: MutableList<CardModel>
): Parcelable