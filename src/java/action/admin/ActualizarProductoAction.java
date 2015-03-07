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
 * Action Actualizar datos de producto
 *
 * @author Jorge
 */
public class ActualizarProductoAction extends ActionSupport implements RequestAware, SessionAware, ServletRequestAware {

    //Scopes 
    private Map session;
    private Map<String, Object> request;
    public HttpServletRequest servletRequest;
    // Acceso a Datos
    private String mensaje;
    private String id, nombre, seccion, categoria, precio, pais,
            descripcion, oferta, destacado, visible;
    //Para mensajes de error/exito 
    private String error = null;
    private String exito = null;
    //atributos para capturar la imagen subida
    private File image;
    private String fileUploadContentType;
    private String fileUploadFileName;

    @Override
    public String execute() throws Exception {

        try {
            ProductoDAO proDAO = new ProductoDAO();

            //relleno los datos del nuevo producto
            Producto p = new Producto();
            p.setId(id);
            p.setNombre(nombre);
            p.setCategoria(categoria);
            p.setSeccion(seccion);
            p.setPaisOrigen(pais);
            p.setPrecio(Float.parseFloat(precio));
            p.setDescripcion(descripcion);

            if (oferta == null) {
                p.setOferta(false);
            } else {
                p.setOferta(true);
            }

            if (visible == null) {
                p.setVisible(false);
            } else {
                p.setVisible(true);
            }

            if (destacado == null) {
                p.setDestacado(false);
            } else {
                p.setDestacado(true);
            }

            Producto aux = proDAO.findProducto(id);

            if (image == null) {
                System.out.println("SIN IMG");
                p.setImagen(false);

                if (aux.isImagen()) {  //aunque no suba una imagen si ya tenia una nos la queamos
                    p.setImagen(true);
                    servletRequest.getSession().getServletContext().getRealPath("/");

                }

            } else {
                p.setImagen(true);

            }
            boolean correcto = proDAO.updateProducto(p);
            if (correcto) {
                //Guardar imagen con nombre id del producto en la carpeta web/img/uploads
                if (p.isImagen() && (image != null)) { //nueva imagen
                    String contextPath = servletRequest.getSession().getServletContext().getRealPath("/");
                    String url = contextPath.substring(0, contextPath.length() - 11);
                    url = url + "/web/img/uploads";
                    File destFile = new File(url, id + ".jpg");
                    FileUtils.copyFile(image, destFile);

                }
                exito = "Datos actualizados de forma correcta";

                session.put("ctype", fileUploadContentType);
                System.out.println("ContentType " + fileUploadContentType);

                return "input";
            } else {
                error = "Error no se pudo actualizar";
                return "ERROR";
            }


        } catch (Exception e) {
            System.out.println(e);
            return "ERROR";

        }

    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
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

    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
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
}