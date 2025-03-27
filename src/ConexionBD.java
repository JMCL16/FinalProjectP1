import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD instance;
    Connection con;

    private ConexionBD(){
        //Constructor privado
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tarea4", "root", "Jefer1605@");
            System.out.println("Se ha conectado a la BD");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConexionBD getInstance() {
        if (instance == null){
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getConnection(){
        return con;
    }

}
