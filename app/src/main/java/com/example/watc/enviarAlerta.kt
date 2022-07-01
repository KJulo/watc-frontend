package com.example.watc

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Build
import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_enviar_alerta.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider

import java.io.File

private const val SELECT_ACTIVITY =1
private const val PERMISSION_CODE= 2
private const val IMAGE_CAPTURE_CODE= 3


class enviarAlerta : AppCompatActivity() {

    private var imageUri: Uri? = null
    private var imageUri2: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_alerta)
        cargarFoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }else {
                    openCamera()
                }
            }else{
                openCamera()
            }

        }
        cargarGaleria.setOnClickListener {
            ImgController.selectPhotofromGallery(this,SELECT_ACTIVITY)
        }
    }

    private fun openCamera(){
        val values= ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera")
        imageUri2=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        val cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri2)
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            PERMISSION_CODE->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    openCamera()
                }else{
                    Toast.makeText(this,"Permiso denegado",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode==Activity.RESULT_OK){
            imageSelect_iv.setImageURI(imageUri2)
        }
        if (requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK) {
            imageUri=data!!.data
            imageSelect_iv.setImageURI(imageUri)
        }

    }
/*
        val bundle= intent.extras
        val tipoAlerta = bundle?.getString("tipoAlerta")



          val btn: Button = findViewById(R.id.enviar)
          btn.setOnClickListener {
              val intent: Intent = Intent(this, Home::class.java)

              val texDescripcion= findViewById<TextView>(R.id.textAlerta)
              intent.putExtra("Descripcion",texDescripcion.text.toString())
              startActivity(intent)


              val toast = Toast.makeText(this, "La alerta ha sido recibida, enviaremos un guardia al sector del problema", Toast.LENGTH_LONG)
              toast.show()

          }

       val enviar: Button = findViewById(R.id.enviar)
        enviar.setOnClickListener{
            val builder = AlertDialog.Builder(this@enviarAlerta)
            val view = layoutInflater.inflate(R.layout.alerta_enviada, null)
            builder.setView(view)

            val enviada= builder.create()
            enviada.show()


            view.setOnClickListener {
                val intent: Intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
        }

*/ }
