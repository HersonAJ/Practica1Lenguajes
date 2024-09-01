/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author herson
 */
public class GridPanel extends JPanel {
    private int rows;
    private int cols;

    public GridPanel() {
       
    }

    public void configureGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        removeAll();
        setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows * cols; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            int filaActual = i / cols + 1;
            int columnaActual = i % cols + 1;

            cell.putClientProperty("fila", filaActual);
            cell.putClientProperty("columna", columnaActual);

            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel clickedCell = (JPanel) e.getSource();
                    String token = (String) clickedCell.getClientProperty("token");
                    String color = (String) clickedCell.getClientProperty("color");
                    Integer fila = (Integer) clickedCell.getClientProperty("fila");
                    Integer columna = (Integer) clickedCell.getClientProperty("columna");

                    if (token != null && color != null) {
                        JOptionPane.showMessageDialog(null, "Token: " + token + "\nColor: " + color + "\nFila: " + fila + "\nColumna: " + columna);
                    }
                }
            });

            add(cell);
        }

        revalidate();
        repaint();
    }

    public void updateGrid(List<TokenInfo> tokens, Map<String, String> tokenEspecialMap) {
        int index = 0;
        for (TokenInfo token : tokens) {
            String color = token.getColor();

            if (index < getComponentCount()) {
                JPanel cell = (JPanel) getComponent(index);
                cell.setBackground(Color.decode(color));
                cell.putClientProperty("token", token.getText());
                cell.putClientProperty("color", color);
                cell.putClientProperty("fila", index / cols + 1);
                cell.putClientProperty("columna", index % cols + 1);
            }
            index++;
        }

        for (Map.Entry<String, String> entry : tokenEspecialMap.entrySet()) {
            String key = entry.getKey();
            String color = entry.getValue();

            String[] parts = key.split(",");
            int fila = Integer.parseInt(parts[0]) - 1;
            int columna = Integer.parseInt(parts[1]) - 1;

            int cellIndex = fila * cols + columna;

            if (cellIndex < getComponentCount()) {
                JPanel cell = (JPanel) getComponent(cellIndex);
                cell.setBackground(Color.decode(color));
                cell.putClientProperty("token", "Square.Color(" + color + ", " + fila + ", " + columna + ")");
                cell.putClientProperty("color", color);
                cell.putClientProperty("fila", fila + 1);
                cell.putClientProperty("columna", columna + 1);
            }
        }
    }
}