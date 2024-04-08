package com.example.resources

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideoPlayer : AppCompatActivity() {
    private lateinit var videoView:VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        videoView = findViewById(R.id.videoView)
        playVideo()

        val goBackBtn = findViewById<Button>(R.id.goBackBtn)
        goBackBtn.setOnClickListener { goBack() }
    }

    private fun playVideo() {
        videoView.setVideoPath(
            "android.resource://"+
                    packageName+"/"
                    +R.raw.vid)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()
    }
    private fun goBack(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}