/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author victo
 */
public class Usuario {

    private boolean admin;
    private String user;
    private String pass;
    private int idGaraje;
    private int idUsuario;

    public Usuario(boolean admin, String user, String pass, int idGaraje) {
        this.admin = admin;
        this.user = user;
        this.pass = pass;
        this.idGaraje = idGaraje;
    }

    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdGaraje() {
        return idGaraje;
    }

    public void setIdGaraje(int idGaraje) {
        this.idGaraje = idGaraje;
    }

}
