/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.Model;

/**
 *
 * @author Aluno
 */
public class Musica {
    private String playlist_pai;
    private String nome_mus;
    private String filepath;

    public Musica(String playlist_pai, String nome_mus, String filepath) {
        this.playlist_pai = playlist_pai;
        this.nome_mus = nome_mus;
        this.filepath = filepath;
    }
    
    public Musica() {
    }
    
}
