package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.ImageView


class BerandaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

    }

        fun fKonsul(view: View) {
            // Start SecondActivity when the button is clicked
            val intent = Intent(this@BerandaActivity, Pilihkonsul::class.java)
            startActivity(intent)
        }
        fun fDiagnosis(view: View) {
            // Start SecondActivity when the button is clicked
            val intent = Intent(this@BerandaActivity, Diagnosis::class.java)
            startActivity(intent)
        }

        fun fInfo(view: View) {
            val intent = Intent(this@BerandaActivity, InformasiDBD::class.java)
            startActivity(intent)
        }

        fun fprofile(view: View) {
            val intent = Intent(this@BerandaActivity, Profile::class.java)
            startActivity(intent)
        }


    }

