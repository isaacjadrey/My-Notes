package com.codingwithjadrey.mynotes.util

interface StateListener {
    fun started()
    fun success()
    fun failure(message: String)
}