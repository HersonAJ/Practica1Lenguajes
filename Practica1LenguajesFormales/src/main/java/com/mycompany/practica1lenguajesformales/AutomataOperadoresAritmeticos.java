/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

/**
 *
 * @author herson
 */
public class AutomataOperadoresAritmeticos {
    
    
    private Estado estado;
    
    public String validarOperadorAritmetico(String cadena){
        if (cadena.isEmpty()){
            return "#000000";
        }
        Estado estadoActual = estado.Q0;
        
        switch(cadena){
            case "+":
                estadoActual = estado.QF;
                return "#FF33FF";
            case "-":
                estadoActual = estado.QF;
                return "#C19A6B";
            case "^":
                estadoActual = estado.QF;
                return "#FCD0B4";
            case "/":
                estadoActual = estado.QF;
                return "#B4D941";
            case "Mod":
                estadoActual = estado.QF;
                return "#D9AB41";
            case "*":
                estadoActual = estado.QF;
                return "#D80073";
            default:
                return "#000000";
            
        }
    }
}