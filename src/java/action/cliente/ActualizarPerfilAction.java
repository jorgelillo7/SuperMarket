/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import java.util.Map;
import modelos.Cliente;
import modelos.Direccion;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para modificar el perfil del cliente
 *
 * @author Jorge
 */
public class ActualizarPerfilAction extends ActionSupport implements RequestAware, SessionAware {

    //Scope
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private String mensaje;
    private String id, user, nombre, apellido, email, datosb, numcont,
            tipo, nombrecalle, numero, piso, letra, cp, pais,
            localidad, prov;
    private String error = null;
    private String exito = null;

    public String execute() {


        try {
            ClienteDAO cliDAO = new ClienteDAO();
            Cliente cli = cliDAO.findCliente(user);
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setTelefono(numcont);
            cli.setEmail(email);
            cli.setNumerotarjeta(datosb);
            Direccion dir = cliDAO.findDireccion(user);
            dir.setTipo(tipo);
            dir.setNombre(nombrecalle);
            dir.setNumero(Integer.parseInt(numero));
            dir.setPiso(Integer.parseInt(piso));
            dir.setPuerta(letra);
            dir.setLocalidad(localidad);
            dir.setProvincia(prov);
            dir.setPais(pais);
            dir.setCodigopostal(Integer.parseInt(cp));

            boolean correcto = cliDAO.updateCliente(cli, dir);
            if (correcto) {
                exito = "Datos actualizados de forma correcta";
                session = getSession();
                session.put("cliente", cli);
                session.put("direccion", dir);
                session.put("logged", true);
                return "SUCCESS";
            } else {
                error = "Error no se pudo actualizar";
                return "ERROR";
            }


        } catch (Exception e) {
            System.out.println(e);

        }

        return "SUCCESS";

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatosb() {
        return datosb;
    }

    public void setDatosb(String datosb) {
        this.datosb = datosb;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombrecalle() {
        return nombrecalle;
    }

    public void setNombrecalle(String nombrecalle) {
        this.nombrecalle = nombrecalle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExito() {
        return exito;
    }

    public void setExito(String exito) {
        this.exito = exito;
    }

    public String getNumcont() {
        return numcont;
    }

    public void setNumcont(String numcont) {
        this.numcont = numcont;
    }
}
