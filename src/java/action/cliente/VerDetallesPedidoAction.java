/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import daos.PedidoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Direccion;
import modelos.DireccionPedidos;
import modelos.LineaPedido;
import modelos.Pedido;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para ver los detalles de un pedido
 *
 * @author Jorge
 */
public class VerDetallesPedidoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    PedidoDAO pedidoDAO;
    ClienteDAO clienteDAO;
    private String mensaje;
    private String idPed;

    public VerDetallesPedidoAction() {
        pedidoDAO = new PedidoDAO();
        clienteDAO = new ClienteDAO();
    }

    public String execute() {


        Pedido ped = new Pedido();
        try {
            ped = pedidoDAO.findPedido(idPed);
        } catch (SQLException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        //listado de productos comprados en el pedido
        ArrayList<LineaPedido> listado = new ArrayList<LineaPedido>();
        try {
            listado = pedidoDAO.findLineasFromIdPed(idPed);
        } catch (SQLException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        ped.setCarrito(listado);



        request.put("pedidoDetallado", ped);

        session = getSession();
        DireccionPedidos dir = new DireccionPedidos();
        try {
            dir = pedidoDAO.findDireccionActualizada(ped.getIdPedido());
        } catch (SQLException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }



        if (dir == null) {
            Direccion direccion = new Direccion();
            try {
                direccion = clienteDAO.findDireccion(ped.getIdUser());
            } catch (SQLException ex) {
                Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.put("direccionEnvio", direccion);
        } else {
            request.put("direccionEnvio", dir);
        }


        /*
         // Usuario us = usuarioDAO.find(oid);
         Pedido ped = pedidoDAO.find(oid);
         request.put("pedidoDetallado", ped);

         */
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

    public String getIdPed() {
        return idPed;
    }

    public void setIdPed(String idPed) {
        this.idPed = idPed;
    }
}