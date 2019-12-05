package mp.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Afonso
 */
public class Playlist_DAO {
    private Conexao con;
    
    private String createPlaylist = "insert into \"playlist\" "
            + "(\"dono\", \"nome_playlist\") values (?,?)";
    private String deletePlaylist = "delete from \"playlist\" where \"nome_playlist\" = ? ";
    private String returnPlaylist = "select * from \"playlist\"";
    public void createPlaylist(String nome_playlist, User user){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(createPlaylist);
            instrucao.setString(1, user.getUsername());
            instrucao.setString(2, nome_playlist);
            instrucao.execute();
            con.desconectar();
        } catch(Exception e){
            e.getStackTrace();
        } 
    }
    public void deletePlaylist(String nome_playlist){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(deletePlaylist);
            instrucao.setString(1, nome_playlist);
            instrucao.execute();
            con.desconectar();
        } catch(Exception e){
            e.getStackTrace();
        } 
    }
    public ArrayList<Playlist> retornarPlaylist(){
        ArrayList<Playlist> lista = new ArrayList<Playlist>();
        Playlist p = null;
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(returnPlaylist);
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                p = new Playlist(rs.getString("dono"), rs.getString("nome_playlist"));
                lista.add(p);
            }
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return lista;
    }
}
