/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

/**
 *
 * @author herson
 */
class TokenInfo {
    public String text;
    public int fila;
    public int columna;
    public String color;

    public TokenInfo(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public TokenInfo(String text, int fila, int columna, String color) {
        this.text = text;
        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public String getColor() {
        return color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
