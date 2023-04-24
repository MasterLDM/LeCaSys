
package Principal.Logica.DB;

import java.sql.*;





public class ConexionDB {
    
    public Connection cn;
    

    public Connection conexion() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://192.168.0.122:3306/lecasys","root","");
            
        }
        catch(Exception e)
                {
                    System.out.println(e.getMessage()+ " error");
                }
        return cn;
        
    }
    Statement createStatement()
    {
    throw new UnsupportedOperationException("No soportado");
    }
    
}
