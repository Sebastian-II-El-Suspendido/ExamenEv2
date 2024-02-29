package com.example.examenev2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.examenev2.databinding.ActivityMainBinding
import com.example.examenev2.databinding.ActivitySaveBinding

class SaveActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySaveBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = DatabaseHelper(this)

        val jueguitos = db.getVideojuego()


        Log.v("Base datos juegos: ", jueguitos.toString())

        binding.textView2.text= jueguitos.toString()

        binding.buttonAtr.setOnClickListener {
            intent = Intent(this,ActivityMainBinding::class.java)
            startActivity(intent)
        }
    }


    class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_VERSION = 1
            private const val DATABASE = "JuegosComprados.db"
            private const val TABLE_VIDEOJUEGOS = "GameTable"
            private const val COLUMN_NOMBRE = "nombre"
            private const val COLUMN_EMPRESA = "empresa"
            private const val COLUMN_LANZAMIENTO = "lanzamiento"
            private const val COLUMN_PUNTAJE = "puntuaje"
        }

        override fun onCreate(db: SQLiteDatabase) {
            val createTable = "CREATE TABLE $TABLE_VIDEOJUEGOS ($COLUMN_NOMBRE TEXT, " +
                    "$COLUMN_PUNTAJE NUMBER, " +
                    "$COLUMN_EMPRESA TEXT," +
                    "$COLUMN_LANZAMIENTO TEXT)"
            db.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_VIDEOJUEGOS")
            onCreate(db)
        }

        fun insertVideojuego(videojuego: Videojuego):Long {
            val db = this.writableDatabase
                val values = ContentValues().apply {
                    put(COLUMN_NOMBRE, videojuego.getNombre())
                    put(COLUMN_PUNTAJE, videojuego.getPuntaje())
                    put(COLUMN_EMPRESA, videojuego.getEmpresa())
                    put(COLUMN_LANZAMIENTO, videojuego.getLanzamiento())
                }

            val id= db.insert(TABLE_VIDEOJUEGOS, null, values)
            db.close()
            return id
        }


        @SuppressLint("Range")
        fun getVideojuego(): ArrayList<Videojuego> {
            val videojuegos = ArrayList<Videojuego>()
            val selectQuery = "SELECT * FROM $TABLE_VIDEOJUEGOS"
            val db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                    val puntuaje = cursor.getDouble(cursor.getColumnIndex(COLUMN_PUNTAJE))
                    val empresa = cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA))
                    val lanzamiento = cursor.getString(cursor.getColumnIndex(COLUMN_LANZAMIENTO))
                    videojuegos.add(Videojuego(nombre, puntuaje,empresa,lanzamiento))
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return videojuegos
        }

    }



}