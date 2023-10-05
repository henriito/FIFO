/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proyecto_fn_sistemas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author 50251
 */



public class Proyecto_FN_Sistemas {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de procesos: ");
        int numProcesos = sc.nextInt();

        List<Proceso> listaProcesos = new ArrayList<>();

        for (int i = 0; i < numProcesos; i++) {
            System.out.print("Ingrese el nombre del proceso " + (i + 1) + ": ");
            String nombre = sc.next();
            System.out.print("Ingrese la ráfaga de tiempo de CPU del proceso " + (i + 1) + ": ");
            int rafagaTiempoCPU = sc.nextInt();
            System.out.print("Ingrese el tiempo de llegada del proceso " + (i + 1) + ": ");
            int tiempoLlegada = sc.nextInt();
          
            listaProcesos.add(new Proceso(nombre, tiempoLlegada, rafagaTiempoCPU));
        }

        Collections.sort(listaProcesos, Comparator.comparingInt(Proceso::getTiempoLlegada));

        int tiempoEsperaTotal = 0;
        int tiempoRetornTotal = 0;
        int tiempoActual = 0;

        System.out.println("Diagrama de Gantt:");

        for (Proceso proceso : listaProcesos) {
            if (tiempoActual < proceso.getTiempoLlegada()) {
                tiempoActual = proceso.getTiempoLlegada();
            }

            proceso.setTiempoEspera(tiempoActual - proceso.getTiempoLlegada());

            // Imprimir el proceso en el diagrama de Gantt
            for (int i = 0; i < proceso.getRafagaTiempoCPU(); i++) {
                System.out.print(proceso.getNombre());
            }

            tiempoActual += proceso.getRafagaTiempoCPU();

            proceso.setTiempoRetorno(tiempoActual); // Modificado para representar el tiempo de retorno como el momento en que termina el proceso

            tiempoEsperaTotal += proceso.getTiempoEspera();
            tiempoRetornTotal += proceso.getTiempoRetorno();
        }

        System.out.println(); // Nueva línea para separar el diagrama de Gantt

        System.out.println("Tiempos de Espera y Retorno individuales:");

        for (Proceso proceso : listaProcesos) {
            System.out.println("|Proceso: " + proceso.getNombre() + ",  |Tiempo de Espera: " + proceso.getTiempoEspera() + ",| Tiempo de Retorno: " + proceso.getTiempoRetorno());
        }

        double tiempoPromedioEspera = (double) tiempoEsperaTotal / numProcesos;
         double tiempoPromedioRetorno = (double)  tiempoRetornTotal / numProcesos;

        System.out.println("Tiempo promedio de Espera: " + tiempoPromedioEspera);
        System.out.println("Tiempo promedio de Retorno: " + tiempoPromedioRetorno);
        sc.close();
    }
}
class Proceso {
    private String nombre;
    private int tiempoLlegada;
    private int rafagaTiempoCPU;
    private int tiempoEspera;
    private int tiempoRetorno;


    public Proceso(String nombre, int tiempoLlegada, int rafagaTiempoCPU) {
        this.nombre = nombre;
        this.tiempoLlegada = tiempoLlegada;
        this.rafagaTiempoCPU = rafagaTiempoCPU;
        this.tiempoEspera = 0;
        this.tiempoRetorno = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int getRafagaTiempoCPU() {
        return rafagaTiempoCPU;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public int getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoRetorno(int tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    
}}
