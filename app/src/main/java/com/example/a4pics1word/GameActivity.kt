package com.example.a4pics1word

import android.content.Context
import android.os.*
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.a4pics1word.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val question = Sorawlar.getQuestion()
    private var repeatedCount = 0
    private var clicked_img: Int = -1
    private var coins_count = 0
    private lateinit var currentQuestion: Question
    private val answerList = mutableListOf<TextView>()
    private val optionList = mutableListOf<TextView>()
    private val userAnswerList = mutableListOf<Pair<String, TextView>>()
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillOptionAndAnswerList()

        setQuestion()

        binding.textLevel.text =
            "${Sorawlar.getQuestion()[currentIndex].id + repeatedCount * question.size}"
        binding.coinCount.text = coins_count.toString()
        binding.clearBtn.setOnClickListener {
            removeAllLetters()
        }

        binding.btnSubmit.setOnClickListener {
            setQuestion()
            setOptionLetters()
            fillOptionAndAnswerList()
            showHide(it.rootView)
        }

        binding.iv1.setOnClickListener {
            clicked_img = 1
            animationScaleUpImageView(1)
        }

        binding.iv2.setOnClickListener {
            clicked_img = 2
            animationScaleUpImageView(2)
        }

        binding.iv3.setOnClickListener {
            clicked_img = 3
            animationScaleUpImageView(3)
        }

        binding.iv4.setOnClickListener {
            clicked_img = 4
            animationScaleUpImageView(4)
        }

        binding.bigImage.setOnClickListener {
            animationScaleDownImageView(clicked_img)

            Handler(Looper.getMainLooper()).postDelayed({
                binding.bigImage.visibility = View.GONE
            }, 180)
        }



        binding.apply {

            fillOptionAndAnswerList()

            bir.setOnClickListener { setLetter(bir) }
            eki.setOnClickListener { setLetter(eki) }
            ush.setOnClickListener { setLetter(ush) }
            tort.setOnClickListener { setLetter(tort) }
            bes.setOnClickListener { setLetter(bes) }
            alti.setOnClickListener { setLetter(alti) }
            jeti.setOnClickListener { setLetter(jeti) }
            segiz.setOnClickListener { setLetter(segiz) }
            togiz.setOnClickListener { setLetter(togiz) }
            on.setOnClickListener { setLetter(on) }
            onBir.setOnClickListener { setLetter(onBir) }
            onEki.setOnClickListener { setLetter(onEki) }
        }
        binding.apply {
            fillOptionAndAnswerList()

            ketek1.setOnClickListener { removeLetter(ketek1) }
            ketek2.setOnClickListener { removeLetter(ketek2) }
            ketek3.setOnClickListener { removeLetter(ketek3) }
            ketek4.setOnClickListener { removeLetter(ketek4) }
            ketek5.setOnClickListener { removeLetter(ketek5) }
            ketek6.setOnClickListener { removeLetter(ketek6) }
            ketek7.setOnClickListener { removeLetter(ketek7) }
            ketek8.setOnClickListener { removeLetter(ketek8) }
        }
        setQuestion()
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }


    fun fillOptionAndAnswerList() {
        // OptionList
        optionList.add(binding.bir)
        optionList.add(binding.eki)
        optionList.add(binding.ush)
        optionList.add(binding.tort)
        optionList.add(binding.bes)
        optionList.add(binding.alti)
        optionList.add(binding.jeti)
        optionList.add(binding.segiz)
        optionList.add(binding.togiz)
        optionList.add(binding.on)
        optionList.add(binding.onBir)
        optionList.add(binding.onEki)


        // AnswerList
        answerList.add(binding.ketek1)
        answerList.add(binding.ketek2)
        answerList.add(binding.ketek3)
        answerList.add(binding.ketek4)
        answerList.add(binding.ketek5)
        answerList.add(binding.ketek6)
        answerList.add(binding.ketek7)
        answerList.add(binding.ketek8)


        binding.btnSubmit.setOnClickListener {
            binding.btnSubmit.isClickable = false
            submitFunction()
        }
    }

    @Suppress
    fun setQuestion() {
        binding.apply {

            textLevel.text = "${currentIndex + 1 + repeatedCount * question.size}"

            iv1.setImageResource(Sorawlar.getQuestion()[currentIndex].images[0])
            iv2.setImageResource(Sorawlar.getQuestion()[currentIndex].images[1])
            iv3.setImageResource(Sorawlar.getQuestion()[currentIndex].images[2])
            iv4.setImageResource(Sorawlar.getQuestion()[currentIndex].images[3])


            repeat(8) {
                answerList[it].text = ""
                answerList[it].visibility = View.GONE
            }
            repeat(Sorawlar.getQuestion()[currentIndex].answer.length) {
                answerList[it].visibility = View.VISIBLE
            }
            setOptionLetters()
        }
    }


    fun setOptionLetters() {
        val optionLetters = mutableListOf<Char>()
        optionLetters.addAll(Sorawlar.getQuestion()[currentIndex].answer.toList())


        repeat(12 - optionLetters.size) {
            optionLetters.add(Random.nextInt(65, 90).toChar())

        }
        optionLetters.shuffle()


        optionList.forEachIndexed { index, textView -> }
        repeat("".length) {
            answerList[it].visibility = View.VISIBLE
        }
        binding.apply {
            bir.text = optionLetters[0].toString()
            eki.text = optionLetters[1].toString()
            ush.text = optionLetters[2].toString()
            tort.text = optionLetters[3].toString()
            bes.text = optionLetters[4].toString()
            alti.text = optionLetters[5].toString()
            jeti.text = optionLetters[6].toString()
            segiz.text = optionLetters[7].toString()
            togiz.text = optionLetters[8].toString()
            on.text = optionLetters[9].toString()
            onBir.text = optionLetters[10].toString()
            onEki.text = optionLetters[11].toString()
        }
    }

    //setLetter
    private fun setLetter(textView: TextView) {
        val letter = textView.text.toString()
        if (letter.isNotEmpty() && userAnswerList.filter { it.first != "" }.size != Sorawlar.getQuestion().size) {
            val pair = Pair(letter, textView)
            val emptyIndex = userAnswerList.indexOf(Pair("", binding.clearBtn))
            if (emptyIndex == -1) {
                userAnswerList.add(pair)
            } else {
                userAnswerList[emptyIndex] = pair
            }
            textView.text = ""
            answerList[userAnswerList.indexOf(pair)].text = letter
        }
        if (userAnswerList.filter { it.first != "" }.size == Sorawlar.getQuestion()[currentIndex].answer.length) {
            var answer = ""
            userAnswerList.forEach {
                answer += it.first
            }
            if (answer == Sorawlar.getQuestion()[currentIndex].answer) {
                answerList.forEach {
                    it.isClickable = false
                }
            } else {

                vibratePhoneCheck()
            }
        }
    }

    private fun vibratePhoneCheck() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        200, VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(200)
            }
        }

    }

    private fun removeLetter(textView: TextView) {
        val letter = textView.text.toString()
        if (letter.isNotEmpty()) {
            val index = answerList.indexOf(textView)
            val pair = userAnswerList[index]
            textView.text = ""
            pair.second.text = pair.first

            userAnswerList[index] = Pair("", binding.clearBtn)
        }
    }

    private fun removeAllLetters() {
        answerList.forEach {
            val letter = it.text.toString()
            if (letter.isNotEmpty()) {
                val index = answerList.indexOf(it)
                val pair = userAnswerList[index]
                it.text = ""
                pair.second.text = pair.first

                userAnswerList[index] = Pair("", binding.bir)
            }
        }
    }

    private fun animationScaleUpImageView(id: Int) {
        when (id) {
            1 -> {
                binding.apply {
                    bigImage.setImageResource(Sorawlar.getQuestion()[currentIndex].images[id - 1])
                    bigImage.visibility = View.VISIBLE
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_up_1
                        )
                    )
                }
            }
            2 -> {
                binding.apply {
                    bigImage.setImageResource(Sorawlar.getQuestion()[currentIndex].images[id - 1])
                    bigImage.visibility = View.VISIBLE
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_up_2
                        )
                    )
                }
            }
            3 -> {
                binding.apply {
                    bigImage.setImageResource(Sorawlar.getQuestion()[currentIndex].images[id - 1])
                    bigImage.visibility = View.VISIBLE
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_up_3
                        )
                    )
                }
            }
            4 -> {
                binding.apply {
                    bigImage.setImageResource(Sorawlar.getQuestion()[currentIndex].images[id - 1])
                    bigImage.visibility = View.VISIBLE
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_up_4
                        )
                    )
                }
            }
        }
    }

    private fun animationScaleDownImageView(id: Int) {
        when (id) {
            1 -> {
                binding.apply {
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_down_1
                        )
                    )
                }
            }

            2 -> {
                binding.apply {
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_down_2
                        )
                    )
                }
            }

            3 -> {
                binding.apply {
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_down_3
                        )
                    )
                }
            }

            4 -> {
                binding.apply {
                    bigImage.startAnimation(
                        AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.scale_down_4
                        )
                    )
                }
            }
        }

    }

    fun View.hideOrShow(boolean: Boolean) {
        if (boolean) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }

    /*private fun showOverlay(show:Boolean){
        binding.apply {
            ivOverlay.isVisible=show
            shine.isVisible=show
            btnSubmit.isVisible=show
            tvNext.isVisible=show
        }
    }*/

    private fun submitFunction() {
        answerList.forEach {
            it.isClickable = true
        }

        coins_count = (coins_count + 4)

        if (currentIndex < Sorawlar.getQuestion().size - 1) {
            currentIndex++
        } else {
            currentIndex = 0
            repeatedCount++
        }


        userAnswerList.clear()
        setQuestion()
        fillOptionAndAnswerList()
    }
}