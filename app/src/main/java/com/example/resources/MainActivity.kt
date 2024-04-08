package com.example.resources

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val playBtn= findViewById<Button>(R.id.playBtn)
        playBtn.setOnClickListener { playVideo() }
    }

    fun sayTheColor(view: View) {

        val tag = view.tag.toString()
        val resourceId = resources.getIdentifier(
            tag,
            "raw",
            packageName
        )
        val mediaPlayer = MediaPlayer.create(this, resourceId)
            ?: return
        mediaPlayer.start()
    }
    fun playVideo(){
        val i = Intent(this,VideoPlayer::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}