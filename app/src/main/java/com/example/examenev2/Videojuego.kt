package com.example.examenev2

import java.io.Serializable

class Videojuego(
    private var nombre: String?,
    private var puntuaje: Double?,
    private var empresa: String?,
    private var lanzamiento: String?
):Serializable {

    fun getNombre(): String? = nombre
    fun getPuntaje(): Double? = puntuaje
    fun getEmpresa(): String? = empresa
    fun getLanzamiento(): String? = lanzamiento

    override fun toString(): String {
        return "Videojuego: \nNombre='$nombre', \npuntuaje=$puntuaje, \nempresa='$empresa', \nlanzamiento='$lanzamiento'"
    }


}