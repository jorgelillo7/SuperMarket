/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 * Modelo de Producto
 *
 * @author Jorge
 */
public class Producto {

    String id;
    String nombre;
    String seccion;
    String categoria;
    float precio;
    String paisOrigen;
    String descripcion;
    boolean oferta;
    boolean destacado;
    boolean imagen;
    boolean visible;

    /**
     * Constructor vacio
     */
    public Producto() {
    }

    /**
     * Constructor de producto con todos sus datos
     *
     * @param nombre
     * @param seccion
     * @param categoria
     * @param precio
     * @param paisOrigen
     * @param descripcion
     * @param oferta
     * @param destacado
     * @param imagen
     * @param visible
     */
    public Producto(String nombre, String seccion, String categoria, float precio, String paisOrigen, String descripcion, boolean oferta, boolean destacado, boolean imagen, boolean visible) {
        this.nombre = nombre;
        this.seccion = seccion;
        this.categoria = categoria;
        this.precio = precio;
        this.paisOrigen = paisOrigen;
        this.descripcion = descripcion;
        this.oferta = oferta;
        this.destacado = destacado;
        this.imagen = imagen;
        this.visible = visible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public boolean isImagen() {
        return imagen;
    }

    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}