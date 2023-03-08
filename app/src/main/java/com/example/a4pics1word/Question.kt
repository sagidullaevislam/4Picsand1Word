package com.example.a4pics1word

import android.provider.MediaStore.Images

data class Question(
    val id: Int,
    val images: MutableList<Int>,
    val answer: String,
)
