package com.example.examenev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examenev2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var jueguitos: ArrayList<Videojuego>
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        jueguitos = ArrayList<Videojuego>()


        binding.buttonCont.setOnClickListener {
            val nombre = binding.editTextNombre.text
            val puntuaje = binding.editTextPuntacion.text

            if (nombre.isNullOrEmpty()||puntuaje.isNullOrEmpty()){
                Toast.makeText(this , "FALTAN CAMPOS POR AÑADIR", Toast.LENGTH_SHORT).show()
            }
                else{
                intent = Intent(this,SecondActivity::class.java).apply {
                    putExtra("NOMBRE_VIDEOJUEGO", nombre.toString())
                    putExtra("PUNTAJE_VIDEOJUEGO", puntuaje.toString())
                    putExtra("ListaJuegos", jueguitos)
                    Log.v("Nombre y puntaje", "$nombre y  $puntuaje")
                }
                startActivity(intent)
                }
        }



    }


}