package com.example.videoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private var ourRequestCode: Int = 123 //Puede ser cualquier número

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoView = findViewById(R.id.vvVideo)


        //Configurar el controlador de medios para el play pause next prev
        val mediaCollection = MediaController(this)
        mediaCollection.setAnchorView(videoView)
        videoView.setMediaController(mediaCollection)
    }

    fun iniciarVideo(view: View){
        //Inicio de intento para capturar el video
        val intento = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if(intento.resolveActivity(packageManager) != null){
            startActivityForResult(intento,ourRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ourRequestCode && resultCode == AppCompatActivity.RESULT_OK){
            //Obtener data de Uri
            val videoUri = data?.data
            videoView.setVideoURI(videoUri)
            videoView.start()
        }

    }
}