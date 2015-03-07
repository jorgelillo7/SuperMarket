/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modelos.Producto;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para añadir productos
 *
 * @author Jorge
 */
public class AnadirProductoAction extends ActionSupport implements RequestAware, SessionAware, ServletRequestAware {
    //Scopes

    private Map session;
    private Map<String, Object> request;
    public HttpServletRequest servletRequest;
    // Acceso a Datos
    private String mensaje;
    private String nombre, seccion, categoria, precio, pais, descripcion;
    private String oferta;
    private String destacado;
    private String visible;
    //atributos para capturar la imagen subida
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String error = null;
    private String exito = null;

    public AnadirProductoAction() {
    }

    public String execute() throws Exception {
        
         try{ 
            float floatnum = Float.parseFloat(precio);
        }catch(NumberFormatException e){ 
        //La cadena no se puede convertir a float 
            addFieldError("precio", "El precio debe ser un número (Ej. 8.80)");
            return "ERROR";
        }  

        ProductoDAO proDAO = new ProductoDAO();

        Producto p = new Producto();
        p.setNombre(nombre);
        p.setCategoria(categoria);
        p.setSeccion(seccion);
        p.setPaisOrigen(pais);
        p.setPrecio(Float.parseFloat(precio));
        p.setDescripcion(descripcion);

        if (oferta.equals("0")) {
            p.setOferta(false);
        }

        if (oferta.equals("0, 1")) {
            p.setOferta(true);
        }

        if (destacado.equals("0")) {
            p.setDestacado(false);
        }

        if (destacado.equals("0, 1")) {
            p.setDestacado(true);
        }

        if (visible.equals("0")) {
            p.setVisible(false);
        }

        if (visible.equals("0, 1")) {
            p.setVisible(true);
        }



        if (fileUpload == null) {
            p.setImagen(false);
        }

        boolean check = proDAO.nuevoProducto(p);  //crea nuevo producto
        String id = proDAO.getId(p);


        if ((fileUpload != null) && (fileUploadContentType.equals("image/png") || fileUploadContentType.equals("image/jpeg"))) {

            p = proDAO.findProducto(id);
            String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
            String url = contextPath.substring(0, contextPath.length() - 11);
            url = url + "/web/img/uploads";
            File destFile = new File(url, id + ".jpg");
            FileUtils.copyFile(fileUpload, destFile);
            p.setImagen(true);
            proDAO.changeImagen(p);
        }
        if (check) {
            exito = "Producto añadido con éxito";
        } else {
            error = "Hubo un problema, revise sus datos";
        }
        return "input";

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

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDestacado() {
        return destacado;
    }

    public void setDestacado(String destacado) {
        this.destacado = destacado;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    public Map getRequest() {
        return request;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
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
