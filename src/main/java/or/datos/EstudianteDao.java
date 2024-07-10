package or.datos;

import or.conexion.Conexion;
import or.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static or.conexion.Conexion.getConexion;

//DAO - Data Access Object
public class EstudianteDao {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante>estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try{
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            var estudiante = new Estudiante();
            estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
            estudiante.setNombre(rs.getString("nombre"));
            estudiante.setApellido(rs.getString("apellido"));
            estudiante.setTelefono(rs.getString("telefono"));
            estudiante.setEmail(rs.getString("email"));
            estudiantes.add(estudiante);
        }
        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        }
        finally {
            {
                try {
                    con.close();
                }catch (Exception e){
                    System.out.println("Ocurrio un error al cerrar la conecxion " + e.getMessage());
                }
            }
        }
        return estudiantes;
    }
    //findById
    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, estudiante.getIdEstudiante());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setApellido(rs.getString("apellido"));
                    estudiante.setTelefono(rs.getString("telefono"));
                    estudiante.setEmail(rs.getString("email"));
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al buscar estudiante: " + e.getMessage());
        }
        return false;
    }

    public boolean agragarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al agregar estudiante: "+ e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }
    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre =?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }


    public static void main(String[] args) {
        var estudianteDao = new EstudianteDao();

        //Agregar Estudiante
       /* var nuevoEstudiante = new Estudiante("Carlos", "Lara", "123456788", "carlos@mail.com");
        var agregado = estudianteDao.agragarEstudiante(nuevoEstudiante);
        if(agregado)
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        else
            System.out.println("No se agrego el estudiante: " + nuevoEstudiante);
*/

        //Modificamos un estudiante existente (1)
        var estucianteModificar = new Estudiante(1, "Juan Carlos", "Juarez",
                "1234565779", "juan@mail.com");
        var modificado = estudianteDao.modificarEstudiante(estucianteModificar);
        if(modificado)
            System.out.println("Estudiante modificado " + estucianteModificar);
        else
            System.out.println("No se modifico estudiante " + estucianteModificar);

        //Listar los estudiantes
        System.out.println("Listado Estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);
        //Buscar por Id
        //var estudiante1 = new Estudiante(3);
        //System.out.println("Estudiante antes de la busqueda: " + estudiante1);
        //var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        //if(encontrado)
          //  System.out.println("Estudiante encontrado: " + estudiante1);
        //else
          //  System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());
    }
}
