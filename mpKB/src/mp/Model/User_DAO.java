package mp.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class User_DAO {
    
    private Conexao con = new Conexao();
    private String insertUser = "insert into \"registros\" "
            + "(\"usuario\", \"senha\") values (?,?)";
    private String verifyUser = "select \"usuario\" from \"registros\" where \"usuario\" = ?";
    private final String BUSCAR = "select \"usuario\", \"senha\" from \"registros\" where \"usuario\" = ?";

    
    public void inserirUser(String usuario, String senha){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(insertUser);
            instrucao.setString(1, usuario);
            instrucao.setString(2, senha);
            instrucao.execute();
            con.desconectar();
        } catch(SQLException e){
            e.getStackTrace();
        } 
    }
    

    public User loginDAO(String usuario){
        User u = null;
        try{
            con.conectar();
            
            PreparedStatement instrucao = con.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, usuario);
            ResultSet rs = instrucao.executeQuery();
            
            if(rs.next()){
                u = new User(rs.getString("usuario"),rs.getString("senha"));
            }
           
            con.desconectar();

        }catch(SQLException e){
            e.getStackTrace();
    }
       
    return u;
    }    

    public boolean verifyUserDAO(String usuario){
        Boolean bool = true;
        try{
            con.conectar();
          
            PreparedStatement instrucao = con.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, usuario);
            ResultSet rs = instrucao.executeQuery();
            
            if(rs.next()){
                bool = false;
            }
           
            con.desconectar();

        }catch(SQLException e){
            e.getStackTrace();
        } 
    return bool;
    }    


   }

    
 

