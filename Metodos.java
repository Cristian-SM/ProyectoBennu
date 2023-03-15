/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bennuinc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Cristian
 */
public class Metodos {
    
    List listaNumeros;
    List listaNumerosOrdenados;
    
    boolean respPregunta = false ;
    
    int respNumero;
    int numeroAOrdenar;
    
    String nombreArchivo;
    String numeroABuscar;
    
    Scanner entrada=new Scanner(System.in);
    
     // LIMPIA
    public void limpiar(){
        
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.flush();

    }
    
    //MUESTRA EL MENU DE LA APP
    public void mostrarMenu(){
        System.out.println("----------------------------------------");
        System.out.println("        SUPER SISTEMA DE ARCHIVOS");
        System.out.println("----------------------------------------");
        System.out.println("Ingrese una opción");
        System.out.println("1. Generar y guardar números aleatorios");
        System.out.println("2. Buscar un número dentro del archivo");
        System.out.println("3. Ordenar números de un archivo");
        System.out.println("4. Ver el contenido de un archivo");
        System.out.println("5. Salir");
        System.out.println("----------------------------------------");
    }
    
    //METODO QUE ORDENA ARREGLO
    public void ordenarInsercion(int[] array){
        int aux; //PARA CAMBIAR LOS NUMEROS
        int c1; // CUENTA VARIABLE DE REFERENCIA
        int c2; // CUENTA
        
        //QUICKSORT
        Arrays.sort(array);
        
        /*
        //CICLO QUE RECORRE EL ARREGLO IZQ a DER
        for(c1 = 1; c1<array.length; c1++){
            aux=array[c1];
            
            //RECORRE DE DER A IZQ Y SI HAY UNO MAYOR LO CAMBIA
            for(c2=c1-1; c2>=0 && array[c2]>aux;c2--){
                array[c2+1]=array[c2];
                array[c2]=aux;
            }
        }
        */
    }
    
    //GENERA NUMEROS RANDOM y utiliza el metodo de guardar
    public void generarNumeros() throws IOException {
        try {
            int c = 0 ; //contador
            System.out.println("¿Cuántos números aleatorios quiere crear?");
            respNumero = entrada.nextInt();
            System.out.println("\nLos números aleatorios generados son:");
            listaNumeros = new ArrayList();
            while(c<respNumero){
                //genera numeros random y guarda a la lista
                int numeroRandom = (int) (Math.random()*100);
                listaNumeros.add(numeroRandom);
                //contador que me dice cuando salir del while
                c += 1;
            }
            System.out.println(listaNumeros);
            guardarArchivo();
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
        
    }
    
    //GUARDA EL ARCHIVO EN TXT
    public void guardarArchivo() throws FileNotFoundException, IOException{
        try {
            System.out.println("\nIngrese nombre del archivo y formato .txt");
            nombreArchivo = entrada.next();

            //CREA EL ARCHIVO CON EL NOMBRE Y RUTA, SERA EN LA MISMA CARPETA
            String ruta = nombreArchivo;
            File file = new File(ruta);

            String contenido;
            // VALIDA EL ARCHIVO SI NO EXISTE
            if (!file.exists()) {
               file.createNewFile();
               FileWriter fw = new FileWriter(file);
               BufferedWriter bw = new BufferedWriter(fw);
               for (Object listaNumero : listaNumeros) {
                   //los crea como fila
                    contenido = listaNumero.toString()+"\n";
                    bw.write(contenido);
               }
               
               bw.close();
               System.out.println("\nSE GUARDO CORRECTAMENTE COMO: " + ruta + "\n\n\n");

           }else{
               System.out.println("\nESTE ARCHIVO YA ESTA CREADO");
           }
            nombreArchivo = "";
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
    }
    
    //GUARDA NUEVO ARCHIVO ORDENADO
    public void guardaNuevoArchivo(String nombreArchivo) throws FileNotFoundException, IOException{
        try {
            
            System.out.println("\nIngrese nuevo nombre del archivo (" + nombreArchivo + ") para guardar con números ordenados en formato .txt:");
            String nombreNuevoArchivo = entrada.next();

            //CREA EL ARCHIVO CON EL NOMBRE Y RUTA, SERA EN LA MISMA CARPETA
            String ruta = nombreNuevoArchivo;
            File file = new File(ruta);

            String contenido;
            // VALIDA EL ARCHIVO SI NO EXISTE
            if (!file.exists()) {
               file.createNewFile();
               FileWriter fw = new FileWriter(file);
               BufferedWriter bw = new BufferedWriter(fw);
               for (Object listaNumero : listaNumerosOrdenados) {
                   //los guarda con salto de linea
                    contenido = listaNumero.toString()+"\n";
                    bw.write(contenido);
               }
                              
               bw.close();
               System.out.println("\nSE GUARDO CORRECTAMENTE COMO: " + ruta + "\n\n\n");

           }else{
               System.out.println("\nESTE ARCHIVO YA ESTA CREADO" + "\n\n\n");
           }
            nombreArchivo = "";
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
    }
    
    //PERMITE LEER LO QUE EL ARCHIVO TIENE
    public void verArchivos() throws FileNotFoundException, Exception {
        try {
            System.out.println("Ingrese en nombre del archivo con su nombre y formato .txt");
            nombreArchivo = entrada.next();

            File file = new File(nombreArchivo);            
            // VALIDA EL ARCHIVO SI NO EXISTE
            if (file.exists()) {
                //System.out.println("El archivo " + nombreArchivo + " EXISTE");
                System.out.println("\nIngrese que número quiere buscar");
                respNumero = entrada.nextByte();
                if(respNumero>0){
                    buscarNumeroEnArchivo(nombreArchivo, respNumero);
                }else{
                    throw new Exception("\nIngrese solo números superior a 0" + "\n\n\n");
                }
                
            }else{
                //System.out.println("El archivo " + nombreArchivo + " NO EXISTE");
                throw new Exception("\nEl archivo: " + nombreArchivo + " no existe" + "\n\n\n");
            }
            nombreArchivo = "";
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage() + "\n\n\n");
        }        
   }
    
    //BUSCA EL NÚMERO DENTRO DEL ARCHIVO
    public void buscarNumeroEnArchivo(String nombreArchivo, int n){
        try {
            //abre el archivo
            numeroABuscar = String.valueOf(n);
            FileInputStream fis = new FileInputStream(nombreArchivo);
            Properties buscar = new Properties();
            buscar.load(fis);
            
            //busca el numero
            if(buscar.getProperty(numeroABuscar)!=null){
                System.out.println("\nFELICIDADES!!! El número " + numeroABuscar +" si existe en el archivo " + nombreArchivo + "\n\n\n");
            }else{
                System.out.println("\nEN EL ARCHIVO " + nombreArchivo + " NO EXISTE EL NÚMERO INGRESADO" + "\n\n\n");
            }
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
    }
    
    //MUESTRA EL ARCHIVO
    public void mostrarDatosArchivo(File nombreArchivo) throws FileNotFoundException{
        try{
            Scanner obj = new Scanner(nombreArchivo);
            int numero;
            listaNumeros = new ArrayList();
            String cadena; 
            FileReader f = new FileReader(nombreArchivo); 
            BufferedReader b = new BufferedReader(f); 
            
            //añade lo que encontro a una lista
            while((cadena = b.readLine())!=null) {
                numero = Integer.parseInt(cadena.trim());
                //System.out.println(numero);
                listaNumeros.add(numero);
            }
            b.close();
            
            //los muestra
            System.out.println("\nLOS NÚMEROS SON:");
            System.out.println("ANTES: " + listaNumeros);
            
            //con la lista, se crea el arreglo
            int tamaño = listaNumeros.size();
            int [] arreglo = new int[tamaño];
            
            for (int i = 0; i < listaNumeros.size(); i++) {
                numero = (int) listaNumeros.get(i);
                arreglo [i] = numero;
            }
            
            //crea nueva lista para los numeros ordenados
            listaNumerosOrdenados = new ArrayList();
            
            //ordena llamando al metodo
            ordenarInsercion(arreglo);
            
            //los muestra
            System.out.println("ORDENADOS");
            for(int i=0; i<arreglo.length;i++){
                listaNumerosOrdenados.add(arreglo[i]);
            }
            System.out.println("DESPUES: " + listaNumerosOrdenados);
            
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public void ordenarArchivo(){
        try {
            System.out.println("Ingrese en nombre del archivo con su nombre y formato .txt para odenar");
            nombreArchivo = entrada.next();

            File file = new File(nombreArchivo);
            // VALIDA EL ARCHIVO SI NO EXISTE
            if (file.exists()) {
                //muestra antes y despues de ordenar
                mostrarDatosArchivo(file);
                
                //guarda el nuevo archivo
                guardaNuevoArchivo(nombreArchivo);
            }else{
                //System.out.println("El archivo " + nombreArchivo + " NO EXISTE");
                throw new Exception("El archivo: " + nombreArchivo + " no existe");
            }
            nombreArchivo = "";            
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public void mostrarContenidoArchivo() throws FileNotFoundException, IOException{
        System.out.println("Ingrese en nombre del archivo con su nombre y formato .txt para odenar");
        nombreArchivo = entrada.next();

        File file = new File(nombreArchivo);
        // VALIDA EL ARCHIVO SI NO EXISTE
        if (file.exists()) {
            Scanner obj = new Scanner(nombreArchivo);
            int numero;
            listaNumeros = new ArrayList();
            String cadena; 
            FileReader f = new FileReader(nombreArchivo); 
            BufferedReader b = new BufferedReader(f); 
            
            //añade lo que encontro a una lista
            while((cadena = b.readLine())!=null) {
                numero = Integer.parseInt(cadena.trim());
                //System.out.println(numero);
                listaNumeros.add(numero);
            }
            b.close();
            
            //los muestra
            System.out.println("\nLOS NÚMEROS DEL ARCHIVO " + nombreArchivo + " SON:");
            System.out.println(listaNumeros);
            System.out.println("");
        }
            
    }
}
