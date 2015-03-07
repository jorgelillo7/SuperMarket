/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.util.Map;
import modelos.Producto;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para ver detalles de un producto
 *
 * @author Jorge
 */
public class VerProductoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    ProductoDAO productoDAO;
    private String id, cantidad;
    private Producto producto;
    private String alerta;

    public VerProductoAction() {
        productoDAO = new ProductoDAO();
    }

    public String execute() {
        try {
            producto = productoDAO.findProducto(id);
            request = getRequest();
            request.put("producto", producto);

            return "SUCCESS";
        } catch (Exception e) {
            System.out.println(e);
            return "ERROR";
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    public Map getRequest() {
        return request;
    }
}
