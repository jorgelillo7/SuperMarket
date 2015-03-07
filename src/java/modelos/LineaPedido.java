/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 * Modelo Linea de Pedido
 *
 * @author Jorge
 */
public class LineaPedido {

    int idPedido;
    int idProducto;
    Producto prod;
    int cantidad;
    float precioproducto;
    float total;

    /**
     * Constructor vacio
     */
    public LineaPedido() {
    }

    /**
     * Constructor de linea de pedido con todos los datos
     *
     * @param idPedido
     * @param idProducto
     * @param prod
     * @param cantidad
     * @param precioproducto
     * @param total
     */
    public LineaPedido(int idPedido, int idProducto, Producto prod, int cantidad, float precioproducto, float total) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.prod = prod;
        this.cantidad = cantidad;
        this.precioproducto = precioproducto;
        this.total = total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(float precioproducto) {
        this.precioproducto = precioproducto;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }
}
