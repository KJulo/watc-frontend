package com.example.watc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.comienza)
        btn.setOnClickListener{

            val intent:Intent = Intent(this, InicioSesionLogin:: class.java)

            startActivity(intent)
        }
    }
}