/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import modelos.Cliente;
import modelos.Direccion;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para modificar la dirección de envio para un pedido específico
 *
 * @author Jorge
 */
public class ActualizarDatosEnvioAction extends ActionSupport implements RequestAware, SessionAware {

    //Scope
    private Map session;
    private Map<String, Object> request;
    
    //Acceso a datos
    String alerta = null;
    String error = null;
    private String tipo;
    private String nombre;
    private String numero;
    private String piso;
    private String puerta;
    private String localidad;
    private String provincia;
    private String codigopostal;
    private String comunidad;
    private String pais;
    private String cuenta;

    public ActualizarDatosEnvioAction() {
    }

    @Override
    public String execute() throws Exception {

        session = getSession();
        Cliente cliente = (Cliente) session.get("cliente");
        
        //si no hay cliente logeado no puede actualizar
        if (cliente == null) {
            error = "Conéctese para realizar un pedido";
            return "ERROR";
        }

        Direccion direccion = new Direccion();
        direccion.setCodigopostal(Integer.parseInt(codigopostal));

        direccion.setLocalidad(localidad);
        direccion.setNombre(nombre);
        direccion.setNumero(Integer.parseInt(numero));
        direccion.setPais(pais);
        direccion.setPiso(Integer.parseInt(piso));
        direccion.setProvincia(provincia);
        direccion.setPuerta(puerta);
        direccion.setTipo(tipo);

        if (session.containsKey("direccion")) {
            session.remove("direccion");
        }

        session.put("direccion", direccion);

        alerta = "Datos modificados";

        if (session.containsKey("cuenta")) {
            session.remove("cuenta");
        }

        session.put("cuenta", cuenta);

     

        return "SUCCESS";
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getPiso() {
        return piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public String getComunidad() {
        return comunidad;
    }

    public String getPais() {
        return pais;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
