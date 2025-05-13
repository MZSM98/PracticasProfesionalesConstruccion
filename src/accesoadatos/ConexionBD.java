package accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static ConexionBD instance;
    private final Connection conexionBD;
    
    public ConexionBD() throws SQLException {
        Properties props = ConfiguracionDB.loadProperties();
        String URL = props.getProperty("db.url");
        String USER = props.getProperty("db.user");
        String PASSWORD = props.getProperty("db.password");
        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver"); 

        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver de base de datos no encontrado", e);
        }
        
        this.conexionBD = DriverManager.getConnection(URL, USER, PASSWORD);        
    }
    
    public static ConexionBD getInstance() throws SQLException {
        if (instance == null || instance.getConexionBD().isClosed()) {
            instance = new ConexionBD();
        }
        return instance;
    }
    
    public Connection getConexionBD() {
        return conexionBD;
    }
}
