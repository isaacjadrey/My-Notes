package com.codingwithjadrey.mynotes.data.model

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Notes(
        var id: String,
        val title: String,
        val body: String,
        val tags: MutableList<String> = arrayListOf(),
        @ServerTimestamp
        val date: Date = Date()
) : Parcelable