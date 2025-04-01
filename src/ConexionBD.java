import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static Connection con;

    //Constructor privado
    private ConexionBD(){
        //Conectando la base de datos al proyecto
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tarea4", "root", "Jefer1605@");
            System.out.println("Se ha conectado a la BD");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Patron Singleton para utilizar una misma instancia
    //Metodo para utilizar la conexion de la base de datos
    public static Connection getInstance() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tarea4", "root", "Jefer1605@");
                System.out.println("Conexión establecida con éxito.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("No se encontró el driver JDBC de MySQL.", e);
            }
        }
        return con;
    }

}
