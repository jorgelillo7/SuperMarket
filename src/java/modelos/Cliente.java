/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 * Modelo cliente
 *
 * @author Jorge
 */
public class Cliente {

    String username;
    String pass;
    String nombre;
    String apellido;
    String telefono;
    String email;
    String numerotarjeta;
    int privilegios;
    int baneado;

    /**
     * Constructor cliente con todos los datos
     *
     * @param username
     * @param pass
     * @param nombre
     * @param apellido
     * @param telefono
     * @param email
     * @param numerotarjeta
     * @param privilegios
     * @param baneado
     */
    public Cliente(String username, String pass, String nombre, String apellido, String telefono, String email, String numerotarjeta, int privilegios, int baneado) {
        this.username = username;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.numerotarjeta = numerotarjeta;
        this.privilegios = privilegios;
        this.baneado = baneado;
    }

    /**
     * Constructor cliente vacio
     */
    public Cliente() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerotarjeta() {
        return numerotarjeta;
    }

    public void setNumerotarjeta(String numerotarjeta) {
        this.numerotarjeta = numerotarjeta;
    }

    public int getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(int privilegios) {
        this.privilegios = privilegios;
    }

    public int getBaneado() {
        return baneado;
    }

    public void setBaneado(int baneado) {
        this.baneado = baneado;
    }
}
