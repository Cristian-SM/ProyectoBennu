package bennuinc;

import java.util.Scanner;
import bennuinc.Metodos;

public class BennuINC {
    
    // • pueda generar números aleatorios y los guarde en un archivo.
    // • busque un número dentro de tu archivo de números.
    // • mostrar los números del archivo.


    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            Scanner entrada=new Scanner(System.in);
            int r;
            boolean salir = false;
            Metodos m = new Metodos();
            
            while (!salir) {
                //MUESTRA EL MENU
                m.mostrarMenu();
                r = entrada.nextInt();
                if(r>=1 && r<=5){
                    //EMPIEZA LO CHIDO
                    switch(r){
                        //CREAR Y GUARDAR ARCHIVO
                        case 1:
                            System.out.println("-------- OPCIÓN 1 --------");
                            System.out.println("GENERAR NÚMEROS ALEATORIOS");
                            m.generarNumeros();
                            break;
                            
                        //BUSCAR NUMERO DE UN ARCHIVO
                        case 2:
                            System.out.println("-------- OPCIÓN 2 --------");
                            System.out.println("BUSCAR NÚMERO DENTRO DEL ARCHIVO");
                            m.verArchivos();
                            break;
                            
                        //ORDENA EL ARCHIVO Y GUARDA
                         case 3:
                            System.out.println("-------- OPCIÓN 3 --------");
                            System.out.println("ORDENAR DE MENOR A MAYOR UN ARCHIVO");
                            m.ordenarArchivo();
                            break;
                            
                         //VE LOS NUMEROS DEL ARCHIVO   
                         case 4:
                            System.out.println("-------- OPCIÓN 4 --------");
                            System.out.println("VER CONTENIDO DEL ARCHIVO");
                            m.mostrarContenidoArchivo();
                            break;
                         
                         //SALE
                         case 5:
                            System.out.println("-------- OPCIÓN 5 --------");
                            System.out.println("ADIOS");
                            salir=true;
                            break;
                            
                    }
                }else{
                    throw new Exception("Solo deben ser números de 1 a 4");
                }
                

            }
        } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        
       
            
        
    }
    
}
