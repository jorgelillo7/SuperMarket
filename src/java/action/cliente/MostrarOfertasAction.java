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
 * Action para buscar los producto en oferta y destacados
 *
 * @author Jorge
 */
public class MostrarOfertasAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private String mensaje;

    public MostrarOfertasAction() {
    }

    public String execute() throws Exception {
        ProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> listadoTotal;
        listadoTotal = (ArrayList<Producto>) productoDAO.findAllProducts();


        ArrayList<Producto> ofertas = new ArrayList<Producto>();
        for (int i = 0; i < listadoTotal.size(); i++) {
            Producto aux = listadoTotal.get(i);
            if (aux.isOferta()) {
                 if (aux.isVisible()) {
                ofertas.add(aux);
                 }
            }

        }

        request.put("listaOfertas", ofertas);

        ArrayList<Producto> destacados = new ArrayList<Producto>();

        for (int i = 0; i < listadoTotal.size(); i++) {
            Producto aux = listadoTotal.get(i);
            if (aux.isDestacado()) {
                 if (aux.isVisible()) {
                destacados.add(aux);
                 }
            }

        }

        request.put("listaDestacados", destacados);

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
