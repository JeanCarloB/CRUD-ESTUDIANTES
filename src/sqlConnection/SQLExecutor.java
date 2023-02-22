package sqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLExecutor {
    private ResultSet rs;
    private PreparedStatement ps;
    private SQLConnector sqlConnector;

    public SQLExecutor() {
        try{
        sqlConnector=SQLConnector.getInstance();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void prepareStatement(String valores[]){
        try{
            ps=sqlConnector.getConnection().prepareStatement(valores[0]);
            for(int i=1;i< valores.length;i++){
                ps.setString(i,valores[i]);
            }
            ps.executeUpdate();//insert delete update
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet ejecutaQuery(String sql){
        try{
            ps=sqlConnector.getConnection().prepareStatement(sql);
            rs=ps.executeQuery();//select
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
