package mp.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private String user;
    private String password;
    private String path;
    private Connection con;
	
    public Conexao(){
        path = "jdbc:postgresql://localhost:5432/DB_KB";
        user = "postgres";
        password = "123";
    }

    public void conectar(){    
        try{
            Class.forName("org.postgresql.Driver");    
            con = DriverManager.getConnection(path, user, password); 
        }catch(Exception v){
            
            System.out.println("Error in connection: "+v.getMessage());
        }
    }

    public void desconectar(){
        try{
            
            con.close();
            
        }catch(Exception v){
            
            System.out.println("Error in connection: "+v.getMessage());
            
        }
    }

    public Connection getConexao(){
        
        return con;        
        
    }
}
