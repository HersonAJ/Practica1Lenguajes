/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

/**
 *
 * @author herson
 */
public class AutomataOperadoresLogicos {
    
    private Estado estado;
    
    public String validarOperadoresLogicos(String cadena){
    if(cadena.isEmpty()){
    return null;
}
    
    Estado estadoActual = estado.Q0;
    
    switch(cadena){
        case "And":
            estadoActual = estado.QF;
            return "#414ED9";
        case "Or":
            estadoActual = estado.QF;
            return "#41D95D";
        case "Not":
            estadoActual = estado.QF;
            return "#A741D9";
        default:
            return null;
    }
    
}
   
}