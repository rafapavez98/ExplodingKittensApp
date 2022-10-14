package com.example.explodingkittensapp.APImodels.Responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class APILoginResponse(
    val msg: String
): Parcelable