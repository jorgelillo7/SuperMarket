/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 * Modelo dirección
 *
 * @author Jorge
 */
public class Direccion {

    String tipo;
    String nombre;
    int numero;
    int piso;
    String puerta;
    int codigopostal;
    String localidad;
    String provincia;
    String pais;

    /**
     * Constructor con todos los datos de Direccion
     *
     * @param tipo
     * @param nombre
     * @param numero
     * @param piso
     * @param puerta
     * @param codigopostal
     * @param localidad
     * @param provincia
     * @param pais
     */
    public Direccion(String tipo, String nombre, int numero, int piso, String puerta, int codigopostal, String localidad, String provincia, String pais) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.numero = numero;
        this.piso = piso;
        this.puerta = puerta;
        this.codigopostal = codigopostal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
    }

    /**
     * Constructor vacio
     */
    public Direccion() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}