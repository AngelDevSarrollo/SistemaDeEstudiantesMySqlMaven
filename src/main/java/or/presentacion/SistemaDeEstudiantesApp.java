package or.presentacion;

import or.datos.EstudianteDao;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaDeEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        //Se crea una instancia clase servicio
        var estudianteDao = new EstudianteDao();
        while (!salir){
            try {
                mostrarMenu();
                //salir = ejecutarOpciones(consola, estudianteDao);
            }catch (Exception e){
                System.out.println("Ocurrio un error al ejecutar operacion: " + e.getMessage());
            }
            System.out.println();
        }// fin while
    }
    public static void mostrarMenu(){
        System.out.println("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiante
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion:
                """);
    }
}