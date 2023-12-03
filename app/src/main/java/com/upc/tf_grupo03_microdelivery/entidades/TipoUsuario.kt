package com.upc.tf_grupo03_microdelivery.entidades

class TipoUsuario {

    var id:Int = 0
    lateinit var tipo:String

    override fun toString(): String {
        return tipo
    }

}