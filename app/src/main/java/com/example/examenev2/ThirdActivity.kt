package com.example.examenev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.examenev2.databinding.ActivityMainBinding
import com.example.examenev2.databinding.ActivityThirdBinding
import kotlin.math.log

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityThirdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = SaveActivity.DatabaseHelper(this)


        val jueguitos = intent.getSerializableExtra("ListaJuegos") as ArrayList<Videojuego>


        Log.v("JuegoThird ", jueguitos.toString())
        binding.textView2.text=jueguitos.toString()


        binding.buttonCont.setOnClickListener {
            val intent2 = Intent(this,MainActivity::class.java).apply {
                putExtra("ListaJuegos",jueguitos)
            }
            startActivity(intent2)


        }

        binding.buttonGuard.setOnClickListener {
            for (videojuego in jueguitos) {
                db.insertVideojuego(videojuego)
            }
            intent = Intent(this,SaveActivity::class.java)
            startActivity(intent)
        }



    }
}