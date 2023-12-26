package com.example.testing

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView

class Pilihkonsul : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilihkonsul)

        val cardKonsul0 = findViewById<CardView>(R.id.konsultan0)
        val cardKonsul1 = findViewById<CardView>(R.id.konsultan1)
        val cardKonsul2 = findViewById<CardView>(R.id.konsultan2)
        val cardKonsul3 = findViewById<CardView>(R.id.konsultan3)

        val backKonsul = findViewById<View>(R.id.backKonsul)

        backKonsul.setOnClickListener {
            val intent = Intent(this@Pilihkonsul, BerandaActivity::class.java)
            startActivity(intent)
        }
        cardKonsul0.setOnClickListener{
            showPopUpDialog(this, 0)

        }
        cardKonsul1.setOnClickListener{
            showPopUpDialog(this, 1)

        }
        cardKonsul2.setOnClickListener{
            showPopUpDialog(this, 2)

        }
        cardKonsul3.setOnClickListener{
            showPopUpDialog(this, 3)

        }

    }

    fun openWhatsApp(view: View) {
        val phoneNumber = "+6282258864305" // Replace with the recipient's phone number
        val message = "Assalamualaikum, saya menghubungi anda dari aplikasi Dengue Care untuk melakukan konsultasi mengenai penyakit Demam Berdarah."

        val uri = Uri.parse("https://wa.me/$phoneNumber/?text=${Uri.encode(message)}")

        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.`package` = "com.android.chrome"

        // Check if Chrome is installed
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // If Chrome is not installed, open in the default browser
            intent.`package` = null
            startActivity(intent)
        }
    }

}