/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author herson
 */
public class InterfazGrafica extends JFrame {

    private JPanel jPanel2;
    private JComboBox<Integer> rowSelector;
    private JComboBox<Integer> colSelector;
    private JTextArea textArea1;

    public InterfazGrafica() {
        // Aplicar el Look and Feel de FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Analizador Gráfico");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Crear el JTextArea
        textArea1 = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea1);
        
        // Crear los paneles
        JPanel jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        
        // Configurar el layout de jPanel1
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollPane, BorderLayout.CENTER);

        // Crear el botón
        JButton boton = new JButton("Aceptar");

        // Crear los selectores de filas y columnas
        Integer[] sizes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30};
        rowSelector = new JComboBox<>(sizes);
        colSelector = new JComboBox<>(sizes);
        rowSelector.setSelectedIndex(5); // Seleccionar 8 filas por defecto
        colSelector.setSelectedIndex(6); // Seleccionar 9 columnas por defecto

        // Configurar el layout del JFrame usando GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configurar jPanel1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(jPanel1, gbc);
        
        // Configurar jPanel2
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(jPanel2, gbc);

        // Configurar los selectores de filas y columnas
        JPanel selectorPanel = new JPanel();
        selectorPanel.add(new JLabel("Filas:"));
        selectorPanel.add(rowSelector);
        selectorPanel.add(new JLabel("Columnas:"));
        selectorPanel.add(colSelector);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(selectorPanel, gbc);

        // Configurar el botón
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(boton, gbc);
        

        // Añadir acción al botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = (int) rowSelector.getSelectedItem();
                int cols = (int) colSelector.getSelectedItem();
                configureGrid(rows, cols);
                }
            
        });

        // Hacer visible el JFrame
        setVisible(true);
    }
    
        private void configureGrid(int rows, int cols) {
        jPanel2.removeAll();
        jPanel2.setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows * cols; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            jPanel2.add(cell);
        }

        jPanel2.revalidate();
        jPanel2.repaint();
    }
}
