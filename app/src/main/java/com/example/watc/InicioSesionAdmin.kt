package com.example.watc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InicioSesionAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)

        val Email =findViewById<EditText>(R.id.editTextTextPersonName2)
        val Password =findViewById<EditText>(R.id.editTextTextPassword2)

        val btn: Button = findViewById(R.id.volver)
        val btnLogin: Button = findViewById(R.id.login)

        btnLogin.setOnClickListener{
            val intent: Intent = Intent(this, HomeAdmin:: class.java)
            intent.putExtra("Email",Email.text.toString())
            intent.putExtra("Contrasena",Password.text.toString())
            startActivity(intent);
        }

        btn.setOnClickListener{
            val intent: Intent = Intent(this, InicioSesionLogin:: class.java)
            startActivity(intent);
        }


    }
}