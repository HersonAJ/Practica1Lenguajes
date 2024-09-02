/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales.Backend;

/**
 *
 * @author herson
 */
public class AutomataIdentificadores {
    
    private Estado estado;

    // Método para validar si una cadena es aceptada por el autómata
    public String validarCadena(String cadena) {
        if (cadena.isEmpty()) {
            return null; // La cadena vacía no es aceptada
        }

        // Estado inicial
        Estado estadoActual = Estado.Q0;

        // Verificar el primer carácter
        char primerSimbolo = cadena.charAt(0);
        if (Character.isLetter(primerSimbolo)) {
            estadoActual = estado.Q1;
        } else {
            return null; // La cadena no comienza con una letra
        }

        // Procesar el resto de los caracteres (si existen)
        for (int i = 1; i < cadena.length(); i++) {
            char simbolo = cadena.charAt(i);
            switch (estadoActual) {
                case Q1:
                    if (Character.isLetter(simbolo) || Character.isDigit(simbolo) || simbolo == '_') {
                        estadoActual = estado.QF;
                    } else {
                        return null; 
                    }
                    break;

                case QF:
                    if (Character.isLetter(simbolo) || Character.isDigit(simbolo) || simbolo == '_') {
                        estadoActual = estado.QF; 
                    } else {
                        return null; 
                    }
                    break;

                default:
                    return null; 
            }
        }

        
            System.out.println("se ingreso una cadena ");
        return (estadoActual == estado.Q1 || estadoActual == estado.QF) ? "#FFD300" : null;
    }
}