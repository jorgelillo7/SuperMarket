/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.PedidoDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para calcular las estadísticas de pedidos
 *
 * @author Jorge
 */
public class EstadisticasPedidosAction extends ActionSupport implements RequestAware, SessionAware {

    //Scope
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private String mensaje;
    private String id;

    public EstadisticasPedidosAction() {
    }

    public String execute() throws Exception {
        PedidoDAO pedDAO = new PedidoDAO();

        
        try {

            int totales = pedDAO.countPedidos();
            int sinenviar = pedDAO.countSinEnviar();
            int enproceso = pedDAO.countEnProceso();
            int entregados = pedDAO.countEntregado();


            session.put("pedidosTotales", totales);
            session.put("pedidosSinEnviar", sinenviar);
            session.put("pedidosEnProceso", enproceso);
            session.put("pedidosEntregados", entregados);

            int porcentajeSE = ((sinenviar * 100) / totales);

            int porcentajeEP = ((enproceso * 100) / totales);

            int porcentajeE = ((entregados * 100) / totales);

            session.put("porcentajeSinEnviar", porcentajeSE + "%;");
            session.put("porcentajeEnProceso", porcentajeEP + "%;");
            session.put("porcentajeEntregados", porcentajeE + "%;");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            session.put("actualizacionFecha", dateFormat.format(date));

            return "SUCCESS";

        } catch (Exception e) {

            System.out.println(e);
            return "ERROR";

        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
