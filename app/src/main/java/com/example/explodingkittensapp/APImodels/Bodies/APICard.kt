package com.example.explodingkittensapp.APImodels.Bodies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class APICard(
    var id: String,
    var type: String

): Parcelable