import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD instance;
    Connection con;

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
    public static ConexionBD getInstance() {
        //Verificando si la instancia esta creada o no
        if (instance == null){
            instance = new ConexionBD();
        }
        return instance;
    }

    //Metodo para utilizar la conexion de la base de datos
    public Connection getConnection(){
        return con;
    }

}
