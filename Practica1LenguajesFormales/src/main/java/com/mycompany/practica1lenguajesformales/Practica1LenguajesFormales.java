/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica1lenguajesformales;

import javax.swing.SwingUtilities;

/**
 *
 * @author herson
 */
public class Practica1LenguajesFormales {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
                }
            });
        }
    }