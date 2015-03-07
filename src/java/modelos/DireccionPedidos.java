/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 * Modelo DireccionPedidos, usado para asociar direcciones alternativas para
 * pedidos sin modificar la dirección asociada al perfil
 *
 * @author Jorge
 */
public class DireccionPedidos {

    String tipo;
    String nombre;
    int numero;
    int piso;
    String puerta;
    int codigopostal;
    String localidad;
    String provincia;
    String pais;
    int id_pedido;

    /**
     * Constructor de nueva DireccionPedidos con todos sus datos
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
     * @param id_pedido
     */
    public DireccionPedidos(String tipo, String nombre, int numero, int piso, String puerta, int codigopostal, String localidad, String provincia, String pais, int id_pedido) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.numero = numero;
        this.piso = piso;
        this.puerta = puerta;
        this.codigopostal = codigopostal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.id_pedido = id_pedido;
    }

    /**
     * Constructor vacio
     */
    public DireccionPedidos() {
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

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}