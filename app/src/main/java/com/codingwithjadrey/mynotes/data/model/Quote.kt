package com.codingwithjadrey.mynotes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quote(
    var id: String = "",
    var quote: String = "",
    var author: String = ""
) : Parcelable