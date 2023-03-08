package com.example.a4pics1word

object Sorawlar {
 fun getQuestion(): MutableList<Question> {
    val question = mutableListOf<Question>()

    question.add(Question(
    id = 1,
    images = mutableListOf(
        R.drawable.cool,
        R.drawable.cool2,
        R.drawable.cool3,
        R.drawable.cool4,
    ),
        answer = "suwiq",
    ))

    question.add(
        Question(
        id = 2,
        images = mutableListOf(
            R.drawable.hot,
            R.drawable.hot2,
            R.drawable.hot3,
            R.drawable.hot4
        ),
            answer = "issi",
    ))

    question.add(
        Question(
        id = 3,
        images = mutableListOf(
            R.drawable.shum,
            R.drawable.shum2,
            R.drawable.shum3,
            R.drawable.shum4
        ),
            answer = "shawqim",
    ))

    question.add(
        Question(
        id = 4,
        images = mutableListOf(
            R.drawable.music,
            R.drawable.music2,
            R.drawable.music3,
            R.drawable.music4
        ),
            answer = "music",
    ))

    question.add(
        Question(
        id = 5,
        images = mutableListOf(
            R.drawable.tochka,
            R.drawable.tochka2,
            R.drawable.tochka3,
            R.drawable.tochka4
        ),
            answer = "tochka",
    ))
    return question
    }
}