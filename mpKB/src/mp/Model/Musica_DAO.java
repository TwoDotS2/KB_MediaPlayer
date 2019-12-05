/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Musica_DAO {
    private Conexao con;    
    private String insertMusic = "insert into \"midia\" "
            + "(\"nome_mus\", \"playlist_pai\", \"filepath\") values (?,?,?)";
    private String deleteMusic = "delete from \"midia\" where \"nome_mus\" = ? ";
    private String returnMusic = "select * from \"midia\"";
    //private String
    public void insertMusic(String nome_playlist){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(insertMusic);
//            instrucao.setString(1, nome_mus);
//            instrucao.setString(2, playlist_pai);
//            instrucao.setString(3, filepath);
            instrucao.execute();
            con.desconectar();
        } catch(Exception e){
            e.getStackTrace();
        } 
    }
    public void deleteMusic(String music){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(deleteMusic);
            instrucao.setString(1, music);
            instrucao.execute();
            con.desconectar();
        } catch(Exception e){
            e.getStackTrace();
        } 
    }
     public ArrayList<Musica> retornarPlaylist(){
        ArrayList<Musica> lista = new ArrayList<Musica>();
        Musica m = null;
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(returnMusic);
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                m = new Musica(rs.getString("nome_mus"), rs.getString("playlist_pai "), rs.getString("filepath"));
                lista.add(m);
            }
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return lista;
    }   
    
}
