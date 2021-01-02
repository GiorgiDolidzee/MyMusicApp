package com.example.mymusicapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.mymusicapp.fragments.HomeFragment
import org.w3c.dom.Text

@Suppress("DEPRECATION")
class LoseyourselfActivity : AppCompatActivity() {

    private lateinit var mp : MediaPlayer
    private var totalTime: Int = 0
    private lateinit var playBtn: Button
    private lateinit var volumeBar: SeekBar
    private lateinit var positionBar: SeekBar

    private lateinit var backFromEminem: ImageView

    private lateinit var elapsedTimeLabel : TextView
    private lateinit var remainingTimeLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loseyourself)
        supportActionBar?.hide()

        mp = MediaPlayer.create(this, R.raw.loseyourselfmusic)

        backFromEminem = findViewById(R.id.backFromEminem)
        backFromEminem.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            mp.stop()
        }



        playBtn = findViewById(R.id.playBtn)
        volumeBar = findViewById(R.id.volumeBar)
        positionBar = findViewById(R.id.positionBar)

        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel)
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel)

        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

        // Volume bar
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                )
                {
                    if (fromUser) {
                        var volumeNum = progress / 50.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

        // Position Bar
        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {

                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var currentPosition = msg.what

            // Update positionbar
            positionBar.progress = currentPosition

            // Update labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"

        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel

    }

    fun playBtnClick(v: View) {

        if (mp.isPlaying) {
            // stop

            mp.pause()
            playBtn.setBackgroundResource(R.drawable.playicon)
        } else {
            // Start

            mp.start()
            playBtn.setBackgroundResource(R.drawable.pauseicon)
        }

    }

}