import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Vehiculo";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Chikibaby22";

    public static Connection obtenerConexion() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el controlador JDBC: " + e.getMessage());
        }
        return connection;
    }
}

