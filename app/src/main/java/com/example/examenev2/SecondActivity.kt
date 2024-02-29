package com.example.examenev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examenev2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val listaJuegos = intent.getSerializableExtra("ListaJuegos") as ArrayList<Videojuego>

        val nombre = intent.getStringExtra("NOMBRE_VIDEOJUEGO")
        val puntuajeString = intent.getStringExtra("PUNTAJE_VIDEOJUEGO")
        val puntuaje =  puntuajeString?.toDouble()
        Log.v("Nombre y puntaje", "$nombre y  $puntuaje")

        binding.buttonAtr.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCont.setOnClickListener {
            val empresa = binding.editTextEmpresa.text
            val lanzamiento = binding.editTextReleaseDate.text
            if (empresa.isNullOrEmpty()||lanzamiento.isNullOrEmpty()){
                    Toast.makeText(this@SecondActivity, "FALTAN CAMPOS POR AÑADIR", Toast.LENGTH_SHORT).show()
                }
                else{
                    intent = Intent(this,ThirdActivity::class.java).apply {
                       val videojuego = Videojuego(nombre, puntuaje, empresa.toString(),lanzamiento.toString())
                        listaJuegos.add(videojuego)
                        putExtra("ListaJuegos", listaJuegos)
                        Log.v("Lista jueguitos", listaJuegos.toString())
                    }
                    startActivity(intent)
                }
        }
    }
}



