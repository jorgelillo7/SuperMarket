/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

/**
 * Action para registrar a un nuevo cliente
 *
 * @author Jorge
 */
import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Direccion;

import org.apache.struts2.interceptor.SessionAware;

public class RegistrarseAction extends ActionSupport implements SessionAware {

    //Scopes
    private Map session;
    //Acceso a datos
    private String nombre;
    private String apellidos;
    private String telefono;
    private String username;
    private String password;
    private String password2;
    private String email;
    private String tipo;
    private String nombrecalle;
    private String piso;
    private String letra;
    private String localidad;
    private String provincia;
    private String pais;
    private String numero;
    private String cp;
    private String cuenta;
    private String mensaje;
    private String error = null;
    private String exito = null;

    public RegistrarseAction() {
    }

    public String execute() {
        ClienteDAO cliDAO = new ClienteDAO();
        try {
            if (cliDAO.findByUserName(username) == true) {  //comprobación si existe user
                error = "Usuario ya existente, elija otro nombre de usuario";
                return "ERROR";
            } else {
                if (password.equals(password2)) { //comprobación passwords
                    Cliente clienteNuevo = new Cliente();
                    clienteNuevo.setNombre(nombre);
                    clienteNuevo.setApellido(apellidos);
                    clienteNuevo.setTelefono(telefono);
                    clienteNuevo.setEmail(email);
                    clienteNuevo.setNumerotarjeta(cuenta);
                    clienteNuevo.setUsername(username);

                    clienteNuevo.setPass(password);
                    clienteNuevo.setPrivilegios(0);
                    clienteNuevo.setBaneado(0);

                    Direccion direccionNueva = new Direccion();
                    direccionNueva.setTipo(tipo);
                    direccionNueva.setNombre(nombrecalle);
                    direccionNueva.setNumero(Integer.parseInt(numero));
                    direccionNueva.setPiso(Integer.parseInt(piso));
                    direccionNueva.setPuerta(letra);
                    direccionNueva.setLocalidad(localidad);
                    direccionNueva.setProvincia(provincia);
                    direccionNueva.setPais(pais);
                    direccionNueva.setCodigopostal(Integer.parseInt(cp));

                    boolean resultado = cliDAO.nuevoCliente(clienteNuevo, direccionNueva);


                    if (resultado) {
                        exito = "Usuario registrado de forma correcta";
                        session = getSession();   //autologin una vez registrado
                        session.put("cliente", clienteNuevo);
                        session.put("direccion", direccionNueva);
                        session.put("logged", true);
                        return "SUCCESS";
                    } else {
                        error = "Error nombre de usuario no disponible";
                        return "ERROR";
                    }
                } else {
                    error = "Las contraseñas no coinciden";
                    return "ERROR";
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarseAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarseAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "ERROR";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
}