/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1lenguajesformales;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author herson
 */
public class Automata {
    
    private String color;

    public Automata() {}


    public String validarComentario(String token) {
        if (token.startsWith("'") && (token.length() == 1 || !token.endsWith("'"))) {
            return "#B3B3B3"; // Color para comentarios
        }
        return null;
    }

        public String validarTokenEspecial(String token) {
            if (token.startsWith("Square.Color(") && token.endsWith(")")) {
                return token.substring(13, token.length() - 1); // Extrae el color
            }
            return null;
        }



    public String[] validarSquareColorEspecial(String token) {
        if (token.startsWith("Square.Color(") && token.endsWith(")")) {
            String contenido = token.substring(13, token.length() - 1).replaceAll("\\s+", ""); // Eliminar espacios
            String[] partes = contenido.split(","); // Divide el contenido por comas

            if (partes.length == 3) {
                String color = partes[0].trim();
                String fila = partes[1].trim();
                String columna = partes[2].trim();

                // Verificar que el color comience con '#' y que fila y columna sean números enteros
                if (color.startsWith("#") && esEntero(fila) && esEntero(columna)) {
                    return new String[]{color, fila, columna};
                }
            }
        }
        return null; // Retorna null si el token no es válido
    }

    private boolean esEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
        public String determinarColor(String token) {

    // Crear todas las instancias antes de usarlas
    AutomataPalabrasReservadas automataPalabrasReservadas = new AutomataPalabrasReservadas();
    AutomataTipoDeDatos automataTipoDeDatos = new AutomataTipoDeDatos();
    AutomataIdentificadores automataIdentificadores = new AutomataIdentificadores();
    AutomataOperadoresAritmeticos automataAritmetico = new AutomataOperadoresAritmeticos();
    AutomataOperadoresLogicos automataLogico = new AutomataOperadoresLogicos();
    AutomataAsignacionYSignos automataSignos = new AutomataAsignacionYSignos();
    AutomataRelacionalesComparacion automataRelacional = new AutomataRelacionalesComparacion();

    String color;

    // Validar si el token es una palabra reservada
    color = automataPalabrasReservadas.validarPalabraReservada(token);
    if (color != null) return color;

    // Validar si el token es un tipo de dato
    color = automataTipoDeDatos.validarTipoDato(token);
    if (color != null) return color;

    // Validar si el token es un identificador
    color = automataIdentificadores.validarCadena(token);
    if (color != null) return color;
    
    //validar si el token es un operador aritmetico 
    color = automataAritmetico.validarOperadorAritmetico(token);
    if (color != null)  return color;
    
    //validar operador logico 
    color = automataLogico.validarOperadoresLogicos(token);
    if(color != null) return color;
    
    //validar operadores de asignacion y signos 
    color = automataSignos.validarAsignacionYSignos(token);
    if(color != null) return color;
    
    color = automataRelacional.validarOperadorRelacionalComparacion(token);
    if (color != null) return color;
    
    color = validarComentario(token);
    if (color != null) return color;
    
    color = validarTokenEspecial(token);
    if (color != null) return color;
    
    
    

    // Si no se encontró ningún color en las validaciones anteriores, usar el método obtenerColor
   // return obtenerColor(token);
   return null;
}
        
    public List<TokenInfo> analizarCodigo(String codigo, Map<String, String> tokenEspecialMap) {
    List<TokenInfo> tokens = new ArrayList<>();
    String[] lineas = codigo.split("\n");
    int totalTokens = 0;
    int nullTokens = 0;

        for (String linea : lineas) {
            linea = linea.trim();
            if (linea.isEmpty()) {
                continue; // Ignorar líneas vacías
            }

            StringTokenizer tokenizer = new StringTokenizer(linea, " \t\n\r\f", false);

            while (tokenizer.hasMoreTokens()) {
                String palabra = tokenizer.nextToken().trim();
                if (palabra.isEmpty()) {
                    nullTokens++; 
                    continue; // Ignorar tokens vacíos
                }

                // Verificar si es un token especial Square.Color(#color, fila, columna)
                if (palabra.startsWith("Square.Color(") && palabra.endsWith(")")) {
                    if (esTokenConPosicion(palabra)) {
                        registrarTokenEspecial(palabra, tokenEspecialMap);
                        continue; // No agregar a la lista de TokenInfo
                    }
                }

                totalTokens++; 

                // Determinar el color del token
                String color = determinarColor(palabra);
                if (color != null) {
                    tokens.add(new TokenInfo(palabra, color));
                } else {
                    // Si el token no es reconocido, asignar color negro
                    tokens.add(new TokenInfo(palabra, "#000000"));
                }
                System.out.println("Token " + totalTokens + ": " + palabra); 
            }
        }

        // Imprimir la cantidad de tokens encontrados y tokens nulos
        System.out.println("Total de tokens encontrados: " + totalTokens);
        System.out.println("Total de tokens nulos o vacíos: " + nullTokens);

        return tokens;
    }

    private boolean esTokenConPosicion(String palabra) {
        // Buscar los paréntesis y las comas
        int primerParentesis = palabra.indexOf('(');
        int ultimaComa = palabra.lastIndexOf(',');
        int ultimoParentesis = palabra.indexOf(')', ultimaComa);

        if (primerParentesis > 0 && ultimaComa > primerParentesis && ultimoParentesis > ultimaComa) {
            String contenido = palabra.substring(primerParentesis + 1, ultimoParentesis);
            String[] partes = contenido.split(",");

            // Verificar si hay exactamente tres partes: color, fila, columna
            return partes.length == 3 && partes[0].trim().startsWith("#");
        }
        return false;
    }

    private void registrarTokenEspecial(String palabra, Map<String, String> tokenEspecialMap) {

        int primerParentesis = palabra.indexOf('(');
        int ultimaComa = palabra.lastIndexOf(',');
        int ultimoParentesis = palabra.indexOf(')', ultimaComa);

        String contenido = palabra.substring(primerParentesis + 1, ultimoParentesis);
        String[] partes = contenido.split(",");

        if (partes.length == 3) {
            String color = partes[0].trim();  // Color (incluye el #)
            String fila = partes[1].trim();   // Fila
            String columna = partes[2].trim(); // Columna

            // Crear una clave única basada en fila y columna
            String key = fila + "," + columna;

            // Almacenar el color con la clave en el mapa
            tokenEspecialMap.put(key, color);
        }
    }



}
