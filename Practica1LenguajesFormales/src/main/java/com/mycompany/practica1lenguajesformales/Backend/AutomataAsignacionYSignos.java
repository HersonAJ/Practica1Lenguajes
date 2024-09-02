/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales.Backend;

/**
 *
 * @author herson
 */
public class AutomataAsignacionYSignos {
    
    private Estado estado;

    
    public String validarAsignacionYSignos(String cadena){
        if(cadena.isEmpty()){
            return null;
        }
        
        Estado estadoActual = estado.Q0;
        
        switch(cadena){
            case "=":
                estadoActual = estado.QF;
                return "#41D9D4";
            case "+=",
                 "-=",
                 "*=",
                 "/=":
                estadoActual = estado.QF;
                return "#FFFFFF";
                //signos y simbolos
            case "(",
                 ")":
                estadoActual = estado.QF;
                return "#9AD8DB";
            case "{",
                 "}":
                estadoActual = estado.QF;
                return "#DBD29A";
            case "[",
                 "]":
                estadoActual = estado.QF;
                return "#DBA49A";
            case ",":
                estadoActual = estado.QF;
                return "#B79ADB";
            case ".":
                estadoActual = estado.QF;
                return "#9ADBA6";
            default:
                return null;
                        
        }
    } 
}
