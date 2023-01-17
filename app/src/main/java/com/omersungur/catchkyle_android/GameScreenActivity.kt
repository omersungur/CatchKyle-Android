package com.omersungur.catchkyle_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.*
import kotlin.system.exitProcess

class GameScreenActivity : AppCompatActivity() {

    lateinit var scoreText : TextView
    lateinit var timeText : TextView
    lateinit var imageView: ImageView

    var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        scoreText = findViewById(R.id.textScore)
        timeText = findViewById(R.id.textTime)
        imageView = findViewById(R.id.imageView)
        funcTime(view = View(this))

    }

    fun clickKyle(view: View) {
        score++
        scoreText.text = "Score: " + score
    }

    private fun funcTime(view: View) {
        object : CountDownTimer(20000,450) {
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                timeText.text = "Time: " + p0/1000
                play(view)
            }

            override fun onFinish() {

                AlertDialog.Builder(this@GameScreenActivity)
                    .setTitle("do you want to play again?")
                    .setPositiveButton("Yes") {dialog,which ->

                        score = 0 // Yeniden başlarsa skoru 0'ladık
                        scoreText.text = "Score: 0"
                        play(view) // oyunu tekrardan başlattık.
                        funcTime(view) // zaman fonksiyonunu tekrardan çağırdık.
                    }
                    .setNegativeButton("No") {dialog,which ->
                        exitProcess(0)
                    }.show()
            }
        }.start()

    }
    fun play(view: View) {
        imageView.isVisible = false
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics) // ekranın ölçülerini aldık
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val r = Random() // resimin herhangi bir konumda rastgele çıkmasını sağlamak için random sınıfı.

        var x: Int = Math.abs(r.nextInt()) % width // bu ifadeyle x değeri maximum width kadar olabilir.
        var y: Int = (Math.abs(r.nextInt()) % height)

        if(y < 100){
            y += 100
        }
        if(y > 1800) {
            y -= 200
        }
        if(x < 100){
            x += 100
        }
        if(x> 920) {
            x -= 140
        }

        imageView.x = x.toFloat() // resmin x konumundaki değerini random bulduğumuz değer ile ayarladık.
        imageView.y = y.toFloat()
        imageView.isVisible = true
    }
}