/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

/**
 *
 * @author herson
 */
public class AutomataPalabrasReservadas {

    private Estado estado;

    // Método para validar si una cadena es aceptada por el autómata
    public String validarPalabraReservada(String cadena) {
        if (cadena.isEmpty()) {
            return null; // La cadena vacía no es aceptada
        }

        Estado estadoActual = estado.Q0; // Estado inicial

        switch(cadena){
            case "Module",
                 "End",
                 "Sub",
                 "Main",
                 "Dim",
                 "As",
                 "Integer",
                 "String",
                 "Boolean",
                 "Double", 
                 "Char",
                 "Console.WriteLine",
                 "Console.ReadLine",
                 "If",
                 "ElseIf",
                 "Else",
                 "Then",
                 "While",
                 "Do",
                 "Loop",
                 "For",
                 "To",
                 "Next",
                 "Function",
                 "Return",
                 "Const":
                estadoActual = estado.QF;
                break;
            default:
                return null;
                
        }
        System.out.println("SE ingreso una palabra reservada");

        // Devuelve "#FFD300" si termina en el estado final, de lo contrario null
        return (estadoActual == estado.Q1 || estadoActual == estado.QF) ? "#60A917" : null;
    }
}