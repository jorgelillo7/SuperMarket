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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Direccion;
import modelos.DireccionPedidos;
import modelos.LineaPedido;
import modelos.Pedido;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para crear un nuevo pedido
 *
 * @author Jorge
 */
public class CrearPedidoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    ClienteDAO clienteDAO;
    PedidoDAO pedidoDAO;
    String alerta = null;
    String error = null;
    boolean check;

    public CrearPedidoAction() {
        clienteDAO = new ClienteDAO();
        pedidoDAO = new PedidoDAO();
    }

    public String execute() {

        session = getSession();
        //cliente debe estar conectado para poder procesar el pedido
        Cliente cliente = (Cliente) session.get("cliente");
        if (cliente == null) {
            error = "Conéctese para realizar un pedido";
            return "ERROR";
        }

        Pedido pedido = (Pedido) session.get("cesta");
        if (pedido != null) {

            pedido.setIdUser(cliente.getUsername());
            pedido.setEstado(0); //estado 0 creado

            Date ahora = new Date();
            pedido.setCreacion(ahora);

            try {
                check = pedidoDAO.nuevoPedido(pedido);
            } catch (SQLException ex) {
                Logger.getLogger(CrearPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CrearPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (check) { //nuevo pedido creado = true
                session.remove("cesta");
                alerta = "Su pedido se ha realizado con éxito";
                
                //guardamos en linea de pedido los elementos de la cesta
                List<LineaPedido> listaPedidos = new ArrayList<LineaPedido>();
                listaPedidos = pedido.getCarrito();
                for (int i = 0; i < listaPedidos.size(); i++) {
                    LineaPedido lineapedidoAux = listaPedidos.get(i);

                    LineaPedido lineapedido = new LineaPedido();

                    lineapedido.setCantidad(lineapedidoAux.getCantidad());
                    lineapedido.setPrecioproducto(lineapedidoAux.getPrecioproducto());
                    lineapedido.setTotal(lineapedidoAux.getTotal());
                    float preciototal = 0;
                    preciototal = lineapedidoAux.getTotal();


                    lineapedido.setIdProducto(lineapedidoAux.getIdProducto());
                    lineapedido.setIdPedido(pedido.getIdPedido());
                    try {
                        pedidoDAO.nuevaLineaPedido(lineapedido);
                        request.put("precioTotalPedido", pedido.getPreciototal());
                        session = getSession();
                        Direccion dir = (Direccion) session.get("direccion");

                        Direccion dirBBDD = clienteDAO.findDireccion(cliente.getUsername());


                        if (dir.getTipo().equals(dirBBDD.getTipo()) && dir.getNombre().equals(dirBBDD.getNombre())) {
                            request.put("direccionEnvio", dirBBDD);

                        } else {
                            DireccionPedidos dirActualizada = new DireccionPedidos(dir.getTipo(), dir.getNombre(), dir.getNumero(), dir.getPiso(), dir.getPuerta(), dir.getCodigopostal(), dir.getLocalidad(), dir.getProvincia(), dir.getPais(), pedido.getIdPedido());
                            pedidoDAO.nuevaDireccionPedido(dirActualizada);

                            request.put("direccionEnvio", dirActualizada);
                        }

                        request.put("idPedido", pedido.getIdPedido());
                    } catch (SQLException ex) {
                        Logger.getLogger(CrearPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CrearPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }


            return "SUCCESS";

        } else {
            error = "Un error ha ocurrido en la aplicación";
            return "ERROR";
        }
    }

    public Map<String, Object> getRequest() {
        return request;
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

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
