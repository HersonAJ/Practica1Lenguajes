/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author herson
 */
public class ReportGenerator {

    private GridPanel gridPanel;
    private Map<String, String> colorToTokenTypeMap;

    public ReportGenerator(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        this.colorToTokenTypeMap = new HashMap<>();
        initializeColorToTokenTypeMap();
    }

    private void initializeColorToTokenTypeMap() {
        colorToTokenTypeMap.put("#FFD300", "Identificador");
        colorToTokenTypeMap.put("#FF33FF", "Operador Aritmético");
        colorToTokenTypeMap.put("#C19A6B", "Operador Aritmético");
        colorToTokenTypeMap.put("#FCD0B4", "Operador Aritmético");
        colorToTokenTypeMap.put("#B4D941", "Operador Aritmético");
        colorToTokenTypeMap.put("#D9AB41", "Operador Aritmético");
        colorToTokenTypeMap.put("#D80073", "Operador Aritmético");
        colorToTokenTypeMap.put("#6A00FF", "Relacionales o Comparación");
        colorToTokenTypeMap.put("#3F2212", "Relacionales o Comparación");
        colorToTokenTypeMap.put("#D9D441", "Relacionales o Comparación");
        colorToTokenTypeMap.put("#D94A41", "Relacionales o Comparación");
        colorToTokenTypeMap.put("#E3C800", "Relacionales o Comparación");
        colorToTokenTypeMap.put("#F0A30A", "Relacionales o Comparación");
        colorToTokenTypeMap.put("#414ED9", "Operador Lógico");
        colorToTokenTypeMap.put("#41D95D", "Operador Lógico");
        colorToTokenTypeMap.put("#A741D9", "Operador Lógico");
        colorToTokenTypeMap.put("#41D9D4", "Operador Asignación");
        colorToTokenTypeMap.put("#FFFFFF", "Operador Asignación");
        colorToTokenTypeMap.put("#60A917", "Palabra Reservada");
        colorToTokenTypeMap.put("#1BA1E2", "Número Entero");
        colorToTokenTypeMap.put("#FFFF88", "Número Decimal");
        colorToTokenTypeMap.put("#E51400", "Cadena");
        colorToTokenTypeMap.put("#FA6800", "Booleano");
        colorToTokenTypeMap.put("#0050EF", "Caracter");
        colorToTokenTypeMap.put("#9AD8DB", "Signos y Símbolos");
        colorToTokenTypeMap.put("#DBD29A", "Signos y Símbolos");
        colorToTokenTypeMap.put("#DBA49A", "Signos y Símbolos");
        colorToTokenTypeMap.put("#B79ADB", "Signos y Símbolos");
        colorToTokenTypeMap.put("#9ADBA6", "Signos y Símbolos");
    }

    public void generateReport() {
        String[] columnNames = {"Token", "Lexema", "Fila", "Columna", "Cuadro"};
        List<Object[]> data = new ArrayList<>();

        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel cell = (JPanel) comp;
                String token = (String) cell.getClientProperty("token");
                String color = (String) cell.getClientProperty("color");
                Integer fila = (Integer) cell.getClientProperty("fila");
                Integer columna = (Integer) cell.getClientProperty("columna");

                if (token != null && color != null && fila != null && columna != null) {
                    // Obtener el tipo de token basado en el color
                    String tokenType = colorToTokenTypeMap.get(color);
                    String cuadroText = "<html>Fila: " + fila + "<br>Columna: " + columna + "<br>Color: " + color + "</html>";

                    JLabel cuadroLabel = new JLabel(cuadroText);
                    cuadroLabel.setOpaque(true);
                    cuadroLabel.setBackground(Color.decode(color));
                    cuadroLabel.setPreferredSize(new Dimension(50, 50));

                    data.add(new Object[]{tokenType, token, fila, columna, cuadroLabel});
                }
            }
        }

        Object[][] dataArray = data.toArray(new Object[0][]);
        DefaultTableModel model = new DefaultTableModel(dataArray, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) {
                    return JLabel.class;
                }
                return String.class;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(50);
        table.getColumnModel().getColumn(4).setCellRenderer(new JLabelRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JFrame reportFrame = new JFrame("Reporte de Tokens");
        reportFrame.setSize(800, 600);
        reportFrame.setLayout(new BorderLayout());
        reportFrame.add(scrollPane, BorderLayout.CENTER);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);
    }

    class JLabelRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }
}