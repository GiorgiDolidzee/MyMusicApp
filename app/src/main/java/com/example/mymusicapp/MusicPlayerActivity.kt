package com.example.mymusicapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var mp : MediaPlayer

    private var totalTime: Int = 0
    private lateinit var playBtn: Button
    private lateinit var volumeBar: SeekBar
    private lateinit var positionBar: SeekBar

    private lateinit var backFromMusic: ImageView

    private lateinit var elapsedTimeLabel : TextView
    private lateinit var remainingTimeLabel: TextView

    private lateinit var musicImage: ImageView
    private lateinit var musicName: TextView
    private lateinit var musicArtist: TextView
    private lateinit var musicBackImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        supportActionBar?.hide()

        musicImage = findViewById(R.id.musicImageView)
        musicName = findViewById(R.id.musicNameText)
        musicArtist = findViewById(R.id.musicArtistText)
        musicBackImage = findViewById(R.id.backImage)

        val musname = intent.extras?.getString("value")
        if (musname == "loseyourself") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.loseyourselfmusic)
            musicImage.setBackgroundResource(R.drawable.loseyourselfphoto)
            musicBackImage.setBackgroundResource(R.drawable.ukana)
            musicName.text = "Lose Yourself"
            musicArtist.text = "Eminem"

        } else if (musname == "taste") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.taste)
            musicImage.setBackgroundResource(R.drawable.tastephoto)
            musicBackImage.setBackgroundResource(R.drawable.ukanataste)
            musicName.text = "Taste"
            musicArtist.text = "Tyga"

        } else if (musname == "droptheworld") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.droptheworld)
            musicImage.setBackgroundResource(R.drawable.droptheworldphoto)
            musicBackImage.setBackgroundResource(R.drawable.ukanadroptheworld)
            musicName.text = "Drop The World"
            musicArtist.text = "Lil Wayne ft. Eminem"

        } else if (musname == "alleyezonme") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.alleyezonme)
            musicImage.setBackgroundResource(R.drawable.alleyezonmephoto)
            musicBackImage.setBackgroundResource(R.drawable.ukanaalleyezonme)
            musicName.text = "All Eyez On Me"
            musicArtist.text = "2 Pac"

        } else if (musname == "gangstaparadise") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.gangstaparadise)
            musicImage.setBackgroundResource(R.drawable.gangstaparadisephoto)
            musicBackImage.setBackgroundResource(R.drawable.ukanagangstaparadise)
            musicName.text = "Gangsta's Paradise"
            musicArtist.text = "Coolie"

        }  else if (musname == "killer") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.killer)
            musicImage.setBackgroundResource(R.drawable.killerphoto)
            musicBackImage.setBackgroundResource(R.drawable.killerukana)
            musicName.text = "Killer"
            musicArtist.text = "Eminem"

        }  else if (musname == "stilldre") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.stilldre)
            musicImage.setBackgroundResource(R.drawable.stilldrephoto)
            musicBackImage.setBackgroundResource(R.drawable.stilldreukana)
            musicName.text = "Still D.R.E"
            musicArtist.text = "Dr.Dre ft. Snoop Dogg"

        }  else if (musname == "californialove") {
            backFromMusic()
            mp = MediaPlayer.create(this, R.raw.californialove)
            musicImage.setBackgroundResource(R.drawable.californialovephoto)
            musicBackImage.setBackgroundResource(R.drawable.californialoveukana)
            musicName.text = "California Love"
            musicArtist.text = "2Pac ft Dr.Dre"
        } else if (musname == "farshevangi") {
            mp = MediaPlayer.create(this, R.raw.farshevangigaliashi)
            musicImage.setBackgroundResource(R.drawable.farshevangiphoto)
            musicBackImage.setBackgroundResource(R.drawable.farshevangiukana)
            musicName.text = "ფარშევანგი გალიაში"
            musicArtist.text = "Nikoloz Katsitadze & Giorgi Dolidze"
            backFromMusic()
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
                    ) {
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

    fun backFromMusic() {
        backFromMusic = findViewById(R.id.backFromMusic)
        backFromMusic.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            mp.stop()
        }
    }

}