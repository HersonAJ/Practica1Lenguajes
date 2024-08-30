/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

/**
 *
 * @author herson
 */
public class AutomataTipoDeDatos {
    
    private Estado estado;
    
    Estado estadoActual = estado.Q0;
    
    public String validarTipoDato(String cadena) {
    // Verificar si es un número entero
    try {
        Integer.parseInt(cadena);
        estadoActual = estado.QF;
        System.out.println("si es un entero");
        return "#1BA1E2";
    } catch (NumberFormatException e) {
        // No es un número entero
    }

    // Verificar si es un número decimal
    try {
        Double.parseDouble(cadena);
        if (cadena.contains(".")) {
            estadoActual = estado.QF;
            System.out.println("si es un decimal ");
            return "#FFFF88";
        }
    } catch (NumberFormatException e) {
        // No es un número decimal
    }

    // Verificar si es un booleano
    if (cadena.equals("True") || cadena.equals("False")) {
        estadoActual = estado.QF;
        System.out.println("si es booleano");
        return "#FA6800";
    }

    // Verificar si es una cadena (entre comillas dobles)
    if (cadena.startsWith("\"") && cadena.endsWith("\"")) {
        estadoActual = estado.QF;
        return "#E51400";
    }

    // Verificar si es un carácter (entre comillas simples)
    if (cadena.startsWith("'") && cadena.endsWith("'") && cadena.length() == 3) {
        estadoActual = estado.QF;
        return "#0050EF";
    }

    return null;
}
}
