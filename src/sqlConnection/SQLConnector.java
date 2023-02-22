package sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private static SQLConnector instance;
    private Connection connection;
    private String URL="jdbc:mysql://localhost:3306/estudiantes";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String usuario = "root";
    private String password = "root";

    public SQLConnector() {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, usuario, password);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static SQLConnector getInstance()throws SQLException {
        if(instance==null)
            instance=new SQLConnector();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
