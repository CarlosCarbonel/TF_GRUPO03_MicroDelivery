package com.upc.tf_grupo03_microdelivery.entidades

class Usuarios {

    var id:Int = 0
    lateinit var dni:String
    lateinit var nombres:String
    lateinit var apellidos:String
    lateinit var usuario:String
    lateinit var contrasena:String
    lateinit var correo:String
    lateinit var distrito:String
    lateinit var direccion:String
    var flgencontrado:Boolean = true
    var tipoUsuario:Int = 0
}