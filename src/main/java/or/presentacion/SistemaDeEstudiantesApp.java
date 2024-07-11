package or.presentacion;

import or.datos.EstudianteDao;
import or.dominio.Estudiante;

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

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDao estudianteDao){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch ( opcion){
            case 1 ->{//Listar estudiantes
                System.out.println("Listado de estudiantes");
                var estudiantes = estudianteDao.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 ->{//Buscar estudiante pro id
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("Estudinate encontrado: " + estudiante);
                else
                    System.out.println("Estudiante no encontrado: "+ estudiante);
                 }
                 case 3 ->{//Agregar estudiante
                     System.out.println("Agregar estudiante:");
                     System.out.print("Nombre: ");
                     var nombre = consola.nextLine();
                     System.out.print("Apellido: ");
                     var apellido = consola.nextLine();
                     System.out.print("Telefono: ");
                     var telefono = consola.nextLine();
                     System.out.print("Email: ");
                     var email = consola.nextLine();
                     //creamos el objeto estudiante (sin id)
                     var estudiante = new Estudiante(nombre, apellido, telefono, email);
                     var agregado = estudianteDao.agragarEstudiante(estudiante);
                     if(agregado)
                         System.out.println("Estudiante agregado: " + estudiante);
                     else
                         System.out.println("Estudiante no agregado: " + estudiante);
                 }
                 case 4 -> { //Modificar estudiante
                     System.out.println("Modificar Estudiante: ");
                     System.out.println("Id Estudiante: ");
                     var idEstudiante = Integer.parseInt(consola.nextLine());
                     System.out.print("Nombre: ");
                     var nombre = consola.nextLine();
                     System.out.print("Apellido: ");
                     var apellido = consola.nextLine();
                     System.out.print("Telefono: ");
                     var telefono = consola.nextLine();
                     System.out.print("Email: ");
                     var email = consola.nextLine();
                     //Creamos el objeto estudiante a modificar
                     var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                     var modificado = estudianteDao.modificarEstudiante(estudiante);
                     if(modificado)
                         System.out.println("Estudiante modificado: "+ estudiante);
                     else
                         System.out.println("Estudiante no modificado: "+ estudiante);
                 }
                 case 5 -> {//Eliminar estudiante
                     System.out.println("Eliminar Estudiante: ");
                     System.out.println("Id Estudiante: ");
                     var idEstudiante = Integer.parseInt(consola.nextLine());
                     var estudiante = new Estudiante(idEstudiante);
                     var eliminado = estudianteDao.eliminarEstudiante(estudiante);
                     if(eliminado)
                         System.out.println("Estudiante eliminado: " + estudiante);
                     else
                         System.out.println("Estudiante NO eliminado: " + estudiante);
                 }
                 case 6 ->{//Salir
                     System.out.println("Haste Pronto!");
                     salir = true;
                 }
            default -> System.out.println("Opcion no reconocida");
        }
        return salir;
    }
}