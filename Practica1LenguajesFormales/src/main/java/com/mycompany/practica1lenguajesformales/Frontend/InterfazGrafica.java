/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales.Frontend;


import com.mycompany.practica1lenguajesformales.Frontend.LineNumberingTextArea;
import com.mycompany.practica1lenguajesformales.Frontend.ReportGenerator;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.practica1lenguajesformales.Backend.Automata;
import com.mycompany.practica1lenguajesformales.Backend.TokenInfo;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    private GridPanel gridPanel;
    private JComboBox<Integer> rowSelector;
    private JComboBox<Integer> colSelector;
    private JTextArea textArea1;
    private Automata automata;
    private List<TokenInfo> tokens;
    private Map<String, String> tokenEspecialMap;
    private Map<String, String> colorToTokenTypeMap;
    private FileHandler fileHandler;
    private ReportGenerator reportGenerator;

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

        // Crear el JTextArea con números de línea
        textArea1 = new JTextArea();
        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(textArea1);
        lineNumberingTextArea.setEditable(false);

        // Crear un JScrollPane que contenga ambos JTextAreas
        JScrollPane scrollPane = new JScrollPane(textArea1);
        scrollPane.setRowHeaderView(lineNumberingTextArea);

        // Crear los paneles
        JPanel jPanel1 = new JPanel();
        gridPanel = new GridPanel(); 

        // Configurar el layout de jPanel1
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollPane, BorderLayout.CENTER);

        // Crear el botón
        JButton boton = new JButton("Aceptar");
        JButton saveButton = new JButton("Guardar Imagen");

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

        // Configurar gridPanel (antes jPanel2)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(gridPanel, gbc);

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

        // Configurar el botón de Aceptar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(boton, gbc);

        // Configurar el botón de guardar imagen
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(saveButton, gbc);

        // Crear el JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Crear los JMenus
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuReporte = new JMenu("Reporte");

        // Crear los JMenuItems
        JMenuItem menuItemCargar = new JMenuItem("Cargar Archivo");
        JMenuItem menuItemReporte = new JMenuItem("Generar Reporte");

        // Agregar los JMenuItems a los JMenus
        menuArchivo.add(menuItemCargar);
        menuReporte.add(menuItemReporte);

        // Agregar los JMenus al JMenuBar
        menuBar.add(menuArchivo);
        menuBar.add(menuReporte);

        // Configurar el JMenuBar en el JFrame
        setJMenuBar(menuBar);
        System.out.println("menuBar configurado en el JFrame");

        automata = new Automata();
        fileHandler = new FileHandler();
        
        reportGenerator = new ReportGenerator(gridPanel);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = (int) rowSelector.getSelectedItem();
                int cols = (int) colSelector.getSelectedItem();
                gridPanel.configureGrid(rows, cols);

                String codigo = textArea1.getText();
                tokenEspecialMap = new HashMap<>();
                tokens = automata.analizarCodigo(codigo, tokenEspecialMap);

                // Línea de depuración para verificar los colores de los tokens
                for (TokenInfo token : tokens) {
                    System.out.println("Token: " + token.getText() + ", Color: " + token.getColor());
                }

                gridPanel.updateGrid(tokens, tokenEspecialMap);
            }
        });


        menuItemCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Carga de archivo seleccionado");
                fileHandler.cargarArchivo(textArea1);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileHandler.guardarImagen(gridPanel);
            }
        });

        menuItemReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportGenerator.generateReport();
            }
        });

    }
}