/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import tools.PostgreSQLConectionManager;
import java.sql.*;
import java.util.ArrayList;
import modelos.Direccion;
import modelos.DireccionPedidos;
import modelos.LineaPedido;
import modelos.Pedido;
import modelos.Producto;

/**
 * Clase que contiene todas las consultas a la BBDD realacionadas con pedidos.
 *
 * @author Jorge
 */
public class PedidoDAO {

    /**
     * Listado de pedidos
     *
     * @return ArrayList de pedidos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Pedido> findAllPedido() throws SQLException, ClassNotFoundException {
        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        st = conn.createStatement();
        rs = st.executeQuery("select * from \"Pedido\" ");
        while (rs.next()) {

            float precioTotal = rs.getFloat("precioTotal");
            Timestamp creacion = rs.getTimestamp("creacion");
            int estado = rs.getInt("estado");
            String user = rs.getString("user");
            int id_ped = rs.getInt("id_pedido");

            Pedido pedido = new Pedido(id_ped, precioTotal, creacion, estado, user);
            lista.add(pedido);
        }
        return lista;
    }

    /**
     * Crear nuevo pedido
     *
     * @param ped Objeto pedido
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean nuevoPedido(Pedido ped) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();

            Statement st = null;
            st = conn.createStatement();
            String sql = "INSERT into \"Pedido\" values (" + ped.getPreciototal() + ",'" + ped.getCreacion() + "'," + ped.getEstado() + ",'" + ped.getIdUser() + "'" + ",'" + ped.getIdPedido() + "')";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Conteo total de pedidos
     *
     * @return numero de pedidos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int countPedidos() throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        int count = 0;

        st = conn.createStatement();
        rs = st.executeQuery("select count(*) from \"Pedido\" ");
        while (rs.next()) {


            count = rs.getInt("count");

        }

        return count;
    }

    /**
     * Insertar nueva linea de pedido
     *
     * @param lp Objeto linea de pedido
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean nuevaLineaPedido(LineaPedido lp) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();

            Statement st = null;
            st = conn.createStatement();
            String sql = "INSERT into \"LineaPedido\" values (" + lp.getIdPedido() + ",'" + lp.getCantidad() + "'," + lp.getPrecioproducto() + ",'" + lp.getIdProducto() + "'" + ",'" + lp.getTotal() + "')";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Lista de pedidos de un cliente
     *
     * @param id idCliente
     * @return ArrayList de Pedidos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Pedido> findOrdersByUser(String id) throws SQLException, ClassNotFoundException {
       PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        st = conn.createStatement();
        String sql = "select * from \"Pedido\" where \"user\" =  '" + id + "'";
        rs = st.executeQuery(sql);
        while (rs.next()) {

            float precioTotal = rs.getFloat("precioTotal");
            Timestamp creacion = rs.getTimestamp("creacion");
            int estado = rs.getInt("estado");
            String user = rs.getString("user");
            int id_ped = rs.getInt("id_pedido");

            Pedido pedido = new Pedido(id_ped, precioTotal, creacion, estado, user);
            lista.add(pedido);

        }

        return lista;
    }

    /**
     * Encontrar pedido (con datos)
     *
     * @param idPed Id Pedido
     * @return Objeto pedido
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Pedido findPedido(String idPed) throws SQLException, ClassNotFoundException {
        String pedidoBBDD = ""; //para coger el idpedido de la base de datos


        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        String sql = "select * from \"Pedido\" where \"id_pedido\"='" + idPed + "'";
        rs = st.executeQuery(sql);
        if (rs.next()) {

            pedidoBBDD = rs.getString("id_pedido");

            if (idPed.equals(pedidoBBDD)) {
                Pedido ped = new Pedido(rs.getInt("id_pedido"), rs.getFloat("precioTotal"), rs.getTimestamp("creacion"), rs.getInt("estado"), rs.getString("user"));
                return ped;
            } else {
                conn.close();

                return null;
            }
        } else {

            return null;
        }
    }

    /**
     * Lista de lineas de pedido asociadas a un Pedido
     *
     * @param id_ped Id Pedido
     * @return ArrayList de Linea de Pedido
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<LineaPedido> findLineasFromIdPed(String id_ped) throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<LineaPedido> lista = new ArrayList<LineaPedido>();

        st = conn.createStatement();
        String sql = "select * from \"LineaPedido\" where \"id_pedido\" =  '" + id_ped + "'";
        rs = st.executeQuery(sql);
        while (rs.next()) {

            int id_pedido = rs.getInt("id_pedido");
            int cantidad = rs.getInt("cantidad");
            float precio = rs.getFloat("precio");
            int id_producto = rs.getInt("id_producto");
            float total = rs.getFloat("total");

            String idProd = String.valueOf(id_producto);
            ProductoDAO prodDAO = new ProductoDAO();
            Producto p1 = prodDAO.findProducto(idProd);
            LineaPedido lp = new LineaPedido(id_pedido, id_producto, p1, cantidad, precio, total);
            lista.add(lp);
        }

        return lista;
    }

    /**
     * Actualizar pedido
     *
     * @param ped Objeto pedido
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updatePedido(Pedido ped) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "UPDATE \"Pedido\" SET  \"estado\"= " + ped.getEstado() + " WHERE id_pedido = '" + ped.getIdPedido() + "'";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Conteo de pedidos sin enviar
     *
     * @return numero de pedidos sin enviar
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int countSinEnviar() throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        int count = 0;

        st = conn.createStatement();
        rs = st.executeQuery("select count(*) from \"Pedido\" where \"estado\" = 0");
        while (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    /**
     * Conteo de pedidos en proceso
     *
     * @return numero de pedidos en proceso
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int countEnProceso() throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        int count = 0;

        st = conn.createStatement();
        rs = st.executeQuery("select count(*) from \"Pedido\" where \"estado\" = 1");
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    /**
     * Conteo de pedidos entregados
     *
     * @return numero de pedidos entregados
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int countEntregado() throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        int count = 0;

        st = conn.createStatement();
        rs = st.executeQuery("select count(*) from \"Pedido\" where \"estado\" = 2");
        while (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    /**
     * Añade nueva direccion (solo para un pedido, no asociada al cliente)
     *
     * @param dir Objeto direccion
     * @return true si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean nuevaDireccionPedido(DireccionPedidos dir) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;
            st = conn.createStatement();

            String sql = "INSERT into \"DireccionPedidos\" values ('" + dir.getTipo() + "','" + dir.getNombre() + "'," + dir.getNumero() + "," + dir.getPiso() + ",'" + dir.getPuerta() + "'," + dir.getCodigopostal() + ",'" + dir.getLocalidad() + "','" + dir.getProvincia() + "','" + dir.getPais() + "','" + dir.getId_pedido() + "')";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }

    /**
     * Encontrar dirección asociada a un pedido
     *
     * @param idPedido id del pedido
     * @return objeto DireccionPedidos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public DireccionPedidos findDireccionActualizada(int idPedido) throws SQLException, ClassNotFoundException {
        int idPed = 0; //para coger el idPedido de la base de datos

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery("select * from \"DireccionPedidos\" where \"id_pedido\"='" + idPedido + "'");
        if (rs.next()) {
            idPed = rs.getInt("id_pedido");

            if (idPedido == (idPed)) {
                DireccionPedidos dir = new DireccionPedidos(rs.getString("tipo"), rs.getString("nombre"), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("piso")), rs.getString("puerta"), Integer.parseInt(rs.getString("codigopostal")), rs.getString("localidad"), rs.getString("provincia"), rs.getString("pais"), rs.getInt("id_pedido"));
                return dir;
            } else {
                conn.close();
                return null;
            }
        } else {
            return null;
        }
    }
}
