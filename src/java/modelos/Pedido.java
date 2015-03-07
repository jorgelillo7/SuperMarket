/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Modelo Pedido
 *
 * @author Jorge
 */
public class Pedido {

    int idPedido;
    float preciototal;
    Date creacion;
    int estado;
    String idUser;
    private List<LineaPedido> carrito = new ArrayList();

    /**
     * Constructor vacio
     */
    public Pedido() {
    }

    /**
     * Constructor de pedido con todos sus datos
     *
     * @param idPedido
     * @param preciototal
     * @param creacion
     * @param estado
     * @param idUser
     */
    public Pedido(int idPedido, float preciototal, Date creacion, int estado, String idUser) {
        this.idPedido = idPedido;
        this.preciototal = preciototal;
        this.creacion = creacion;
        this.estado = estado;
        this.idUser = idUser;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public float getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(float preciototal) {
        this.preciototal = preciototal;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<LineaPedido> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<LineaPedido> carrito) {
        this.carrito = carrito;
    }
}
