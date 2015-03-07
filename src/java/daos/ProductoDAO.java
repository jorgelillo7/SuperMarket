/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import tools.PostgreSQLConectionManager;
import java.sql.*;
import java.util.ArrayList;
import modelos.Producto;

/**
 * Clase que contiene todas las consultas a la BBDD realacionadas con productos.
 *
 * @author Jorge
 */
public class ProductoDAO {

    /**
     * Listado de productos
     *
     * @return ArrayList de productos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Producto> findAllProducts() throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Producto> lista = new ArrayList<Producto>();

        st = conn.createStatement();
        rs = st.executeQuery("select oid, * from \"Producto\" ");
        while (rs.next()) {

            String id = rs.getString("oid");
            String nombre = rs.getString("nombre");
            String seccion = rs.getString("seccion");
            String categoria = rs.getString("categoria");
            float precio = rs.getFloat("precio");
            String pais = rs.getString("pais");
            String descripcion = rs.getString("descripcion");
            boolean oferta = rs.getBoolean("oferta");
            boolean destacado = rs.getBoolean("destacado");
            boolean imagen = rs.getBoolean("imagen");
            boolean visible = rs.getBoolean("visible");
            Producto producto = new Producto(nombre, seccion, categoria, precio, pais, descripcion, oferta, destacado, imagen, visible);
            lista.add(producto);

            producto.setId(id);


        }

        return lista;
    }

    /**
     * Encontrar producto por id
     *
     * @param prodId id producto
     * @return Objeto Producto
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Producto findProducto(String prodId) throws SQLException, ClassNotFoundException {
        String idBBDD = ""; //para coger el usuario de la base de datos

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery("select oid,* from \"Producto\" where oid='" + prodId + "'");
        if (rs.next()) {
            idBBDD = rs.getString("oid");

            if (prodId.equals(idBBDD)) {
                Producto pro = new Producto(rs.getString("nombre"), rs.getString("seccion"), rs.getString("categoria"), rs.getFloat("precio"), rs.getString("pais"), rs.getString("descripcion"), rs.getBoolean("oferta"), rs.getBoolean("destacado"), rs.getBoolean("imagen"), rs.getBoolean("visible"));
                pro.setId(prodId);
                return pro;
            } else {
                conn.close();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Cambiar visibilidad de producto
     *
     * @param pro Objeto Producto
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changeMostrar(Producto pro) throws SQLException, ClassNotFoundException {
        try {
           PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "UPDATE \"Producto\" SET  \"visible\"= " + pro.isVisible() + " WHERE oid = '" + pro.getId() + "'";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Añadir nuevo producto
     *
     * @param pro Objeto producto
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean nuevoProducto(Producto pro) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();

            Statement st = null;
            st = conn.createStatement();
            String sql = "INSERT into \"Producto\" values ('" + pro.getNombre() + "','" + pro.getSeccion() + "','" + pro.getCategoria() + "'," + pro.getPrecio() + ",'" + pro.getPaisOrigen() + "','" + pro.getDescripcion() + "'," + pro.isOferta() + "," + pro.isDestacado() + "," + pro.isImagen() + "," + pro.isVisible() + ")";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Obtener oid de un producto
     *
     * @param pro Objeto Producto
     * @return id del producto
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String getId(Producto pro) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            String idNewProduct = "";
            String nameBBDD = "";

            Statement st = null;
            ResultSet rs = null;
            st = conn.createStatement();
            rs = st.executeQuery("select oid,* from \"Producto\" where nombre='" + pro.getNombre() + "'");
            if (rs.next()) {
                nameBBDD = rs.getString("nombre");
                if (pro.getNombre().equals(nameBBDD)) {
                    idNewProduct = (rs.getString("oid"));
                }
            }
            return idNewProduct;

        } catch (Exception e) {
            System.out.println(e);
            return "null";
        }

    }

    /**
     * Cambiar campo imagen de un producto (si imagen es true tiene imagen, si
     * es false no tiene)
     *
     * @param pro Objeto producto
     * @return true si los cambios son correctos, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changeImagen(Producto pro) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "UPDATE \"Producto\" SET  \"imagen\"= " + pro.isImagen() + " WHERE oid = '" + pro.getId() + "'";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Borrar producto
     *
     * @param pro Objeto producto
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean deleteProduct(Producto pro) throws SQLException, ClassNotFoundException {
        try {
           PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "DELETE FROM \"Producto\" WHERE \"oid\" = '" + pro.getId() + "'";
            st.executeUpdate(sql);


            return true;


        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Actualizar datos de un producto
     *
     * @param prod Objeto producto
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateProducto(Producto prod) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "UPDATE \"Producto\" SET  \"nombre\"= '" + prod.getNombre() + "', \"seccion\"= '" + prod.getSeccion() + "',\"categoria\"= '" + prod.getCategoria() + "',\"precio\"= " + prod.getPrecio() + ",\"pais\"= '" + prod.getPaisOrigen() + "',\"descripcion\"= '" + prod.getDescripcion() + "',\"oferta\"= '" + prod.isOferta() + "',\"destacado\"= '" + prod.isDestacado() + "',\"imagen\"= '" + prod.isImagen() + "',\"visible\"= '" + prod.isVisible() + "'  WHERE \"oid\" = '" + prod.getId() + "'   ";
            st.executeUpdate(sql);


            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
