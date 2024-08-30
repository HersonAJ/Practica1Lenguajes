/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

/**
 *
 * @author herson
 */
public class AutomataRelacionalesComparacion {
    
    
    private Estado estado;
    
    public String validarOperadorRelacionalComparacion(String cadena){
        if (cadena.isEmpty()){
            return null;
        }
        Estado estadoActual = estado.Q0;
        
        switch(cadena){
            case "==":
                estadoActual = estado.QF;
                return "#6A00FF";
            case "<>":
                estadoActual = estado.QF;
                return "#3F2212";
            case ">":
                estadoActual = estado.QF;
                return "#D9D441";
            case "<":
                estadoActual = estado.QF;
                return "#D94A41";
            case ">=":
                estadoActual = estado.QF;
                return "#E3C800";
            case "<=":
                estadoActual = estado.QF;
                return "#F0A30A";
            default:
                return null;
            
        }
    }
}