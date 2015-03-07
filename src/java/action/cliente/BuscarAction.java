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
 * Action para buscar y mostrar productos bajo unos criterios de búsqueda: determinados por los filtros o por el usuario en el buscador
 * @author Jorge
 */
public class BuscarAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private String mensaje;
    private String producto;
    ArrayList<Producto> listadoCategorias;
    ArrayList<Producto> listadoSecciones;
    ArrayList<Producto> listadoNombres;
    int esta = 0;
    boolean seccion = false;
    boolean nombre = false;

    public BuscarAction() {
    }

    public String execute() throws Exception {

        ProductoDAO proDAO = new ProductoDAO();
        ArrayList<Producto> listaProductos;
        try {
            listaProductos = (ArrayList<Producto>) proDAO.findAllProducts();

            //Buscar por nombre
            listadoNombres = new ArrayList<Producto>();
            for (int i = 0; i < listaProductos.size(); i++) {
                Producto prodAux = listaProductos.get(i);
                if (prodAux.isVisible()) {
                    int tamaño;
                    if (prodAux.getNombre().length() < producto.length()) {
                        tamaño = prodAux.getNombre().length();
                    } else {
                        tamaño = producto.length();
                    }
                    String aux = prodAux.getNombre().substring(0, tamaño);
                    String prod = producto.substring(0, tamaño);

                    if ((prod.toLowerCase()).equals(aux.toLowerCase())) {

                        listadoNombres.add(prodAux);
                        nombre = true;
                    }
                }
            }

            if (nombre) {
                request.put("listaProductos", listadoNombres);
                return "SUCCESS";
            }




            //Buscar por sección
            listadoSecciones = new ArrayList<Producto>();
            for (int i = 0; i < listaProductos.size(); i++) {
                Producto prodAux = listaProductos.get(i);
                if (prodAux.isVisible()) {
                    if (prodAux.getSeccion().equals(producto)) {
                        listadoSecciones.add(prodAux);
                        seccion = true;
                    }
                }
            }

            if (seccion) {
                request.put("listaProductos", listadoSecciones);

                return "SUCCESS";
            } else {

                //Buscar por categoría
                listadoCategorias = new ArrayList<Producto>();
                for (int i = 0; i < listaProductos.size(); i++) {
                    Producto prodAux = listaProductos.get(i);
                    if (prodAux.isVisible()) {
                        if (prodAux.getCategoria().equals(producto)) {
                            listadoCategorias.add(prodAux);
                        }
                    }
                }
                request.put("listaProductos", listadoCategorias);

                return "SUCCESS";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "ERROR";
        }

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

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
