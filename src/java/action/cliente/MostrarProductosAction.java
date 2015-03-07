/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.util.ArrayList;
import java.util.Map;
import modelos.Producto;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para mostrar todos los productos
 *
 * @author Jorge
 */
public class MostrarProductosAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private String mensaje;

    public MostrarProductosAction() {
    }

    public String execute() throws Exception {
        ProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> listadoTotal;
        listadoTotal = (ArrayList<Producto>) productoDAO.findAllProducts();

        ArrayList<Producto> listado = new ArrayList<Producto>();

        for (int i = 0; i < listadoTotal.size(); i++) {
            Producto aux = listadoTotal.get(i);
            if (aux.isVisible()) {
                listado.add(aux);
            }

        }
        request.put("listaProductos", listado);
        return "SUCCESS";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
