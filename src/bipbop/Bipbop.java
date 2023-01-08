/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bipbop;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author sara
 */
public class Bipbop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException  {
        Scanner scanner = new Scanner(System.in);
        int[][] tablero = {{0,0,0},
                           {0,0,0},
                           {0,0,0}};

        System.out.print("Bip bop");
        for (int i = 0; i < 4; i++) {
            System.out.print(".");
            Thread.sleep(300);
        }
        
        System.out.println("\nIntroduzca la opción que desea jugar:");
        System.out.println("1. Jugador vs Jugador");
        System.out.println("2. Jugador vs CPU");
        System.out.println("3. CPU vs CPU");
        int jugador0Control = 0;
        int jugador1Control = 0;
        int opcion;
        char [] letra = {'A','B','C','|'};

        do{
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                    //0 jugador - 1 ordenador
                case 1: //ya está definido correctamente
                        break;
                case 2: jugador1Control = 1;
                        break;
                case 3: jugador0Control = 1;
                        jugador1Control = 1;
                        break;  
                default: System.out.println("Opción no disponible :(");
            }
        } while (opcion > 3 || opcion < 1);
        
        System.out.println("\n/|1|2|3|");
            for (int i = 0; i < tablero.length; i++) {
                System.out.print("" + letra[i] + letra[3]);
                for (int j = 0; j < tablero.length; j++) {
                    if (tablero[i][j] == 0){
                        System.out.print(" |");
                    } else if (tablero[i][j] == 1){
                        System.out.print("X|");
                    } else {
                        System.out.print("O|");
                    }
                }
                System.out.println("\n--------");
            }
        
        boolean partidaEnCurso = true;
        
            //la ficha que empieza es al azar
        int jugadorActual = jugadorEmpieza();
        
        if (jugadorActual == 0) {
            System.out.println("Comienza el jugador con la ficha O!!");
        } else {
            System.out.println("Comienza el jugador con la ficha X!!");
        }
        
            //bipbop = hay línea, como en el bingo
        int bipbop = 0;
        
        do{
                //pedir la coordenada, asignar letra a la fila y num a la columna
            String coordenada = "";
            if (jugadorActual == 0){
                if (jugador0Control == 0){
                    System.out.println("Te toca Jugador1:");
                    coordenada = scanner.nextLine();
                } else {
                    coordenada = jugadaCPU (tablero);
                }
            }
            if (jugadorActual == 1){
                if (jugador1Control == 0){
                    System.out.println("Te toca Jugador2:");
                    coordenada = scanner.nextLine();
                } else {
                    coordenada = jugadaCPU (tablero);
                }
            }
                
            coordenada = coordenada.trim();
            coordenada = coordenada.toUpperCase();
            int fila = 0;
            int columna = 0;

            System.out.println("\n > " + coordenada);
            switch(coordenada.charAt(0)){
                case 'A': fila = 0;
                        break;
                case 'B': fila = 1;
                        break;
                case 'C': fila = 2;  
                        break;
            }

            switch(coordenada.charAt(1)){
                case '1': columna = 0;
                        break;
                case '2': columna = 1;
                        break;
                case '3': columna = 2;  
                        break;
            }

            if (tablero[fila][columna] == 0){
                tablero[fila][columna] = 1;  
            } else {
                System.out.println("Posición ya ocupada, introduzca una nueva coordenada a continuación:");  
                continue;
            }
            
                //cambiar de jugador
            if (jugadorActual == 0){
                tablero[fila][columna] = 2;
            } else if (jugadorActual == 1){
                tablero[fila][columna] = 1;
            }
            
                //imprimir tablero actualizado
            System.out.println("/|1|2|3|");
            for (int i = 0; i < tablero.length; i++) {
                System.out.print("" + letra[i] + letra[3]);
                for (int j = 0; j < tablero.length; j++) {
                    if (tablero[i][j] == 0){
                        System.out.print(" |");
                    } else if (tablero[i][j] == 1){
                        System.out.print("X|");
                    } else {
                        System.out.print("O|");
                    }
                }
                
                System.out.println("\n--------");
            }
            
            bipbop = hayLinea(tablero);
            
            if (bipbop == 1) {
                System.out.println("X HA GANADO!!! 🥳 🥳 🥳 ");
            } else if (bipbop == 2){
                System.out.println("O HA GANADO!!! 🥳 🥳 🥳 ");
            } else if (bipbop == 3){
                System.out.println("EMPATE... 😟  ");
            }
            
            if (jugadorActual == 0) {
                jugadorActual = 1;
            } else {
                jugadorActual = 0;
            }
            
        } while (bipbop == 0);
        }
        
    
    public static int jugadorEmpieza(){
        int num = (int)(Math.random()*2);
        return num;
    }
    
    public static int hayLinea (int[][]tablero){  
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0] != 0){
                if (tablero[i][0] == tablero[i][1]){
                    if (tablero[i][1] == tablero[i][2]) {
                        if (tablero[i][0] == 1){
                            return 1;
                        } else {
                            return 2;
                        }
                    }
                }
            }    
            if (tablero[0][i] != 0){
                if (tablero[0][i] == tablero[1][i]){
                    if (tablero[1][i] == tablero[2][i]) {
                        if (tablero[0][i] == 1){
                            return 1;
                        } else {
                            return 2;
                        }
                    }
                }
            }
            
        }
        if (tablero[1][1] != 0){
            if (tablero[0][0] == tablero[1][1]){
                if (tablero[1][1] == tablero[2][2]) {
                    if (tablero[1][1] == 1) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        }
        
        if (tablero[1][1] != 0){
            if (tablero[0][2] == tablero[1][1]){
                if (tablero[1][1] == tablero[2][0]) {
                    if (tablero[1][1] == 1) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        }
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 0) {
                    return 0;
                }
            }
        }
        
        //0--->SEGUIR JUGANDO   1--->X   2--->O   3--->EMPATE
        return 3;
    }
    
    public static String jugadaCPU (int[][] tablero) throws InterruptedException {
        String cadenaDisponible = "";
        char [] letra = {'A','B','C'};
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 0) {
                    String i1 = "" + letra[i];
                    String j1 = Integer.toString(j + 1);
                    cadenaDisponible = cadenaDisponible + i1 + j1 + " ";
                }
            }
        }

        cadenaDisponible = cadenaDisponible.trim();
        String[] posiciones = cadenaDisponible.split(" ");

        int numCoorCPU = (int)(Math.random()* posiciones.length);
        String coordenada = posiciones[numCoorCPU];
        System.out.print("Pensando.");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(400);
        }
    
        return coordenada;
    }
}
