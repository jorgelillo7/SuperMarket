/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import tools.PostgreSQLConectionManager;
import java.sql.*;
import java.util.ArrayList;
import modelos.Cliente;
import modelos.Direccion;

/**
 * Clase que contiene todas las consultas a la BBDD realacionadas con clientes.
 *
 * @author Jorge
 */
public class ClienteDAO {

    /**
     * Login en app
     *
     * @param user idUsuario
     * @param pass contraseña
     * @return True si es correcto, false en caso contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean loginApp(String user, String pass) throws SQLException, ClassNotFoundException {
        String us = ""; //para coger el usuario de la base de datos
        String pa = ""; // para coger el pass de la base de datos

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery("select \"userName\", \"pass\" from \"Cliente\" where \"userName\"='" + user + "'");
        if (rs.next()) {

            us = rs.getString("userName");
            pa = rs.getString("pass");
            if (user.equals(us) && pass.equals(pa)) {
                return true;
            } else {
                conn.close();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Comprueba si está el usuario en la base de datos
     *
     * @param user idUser
     * @return True si está, false no está.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean findByUserName(String user) throws SQLException, ClassNotFoundException {
        String userBBDD = ""; //para coger el usuario de la base de datos


       PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery("select \"userName\" from \"Cliente\" where \"userName\"='" + user + "'");
        if (rs.next()) {
            userBBDD = rs.getString("userName");

            if (user.equals(userBBDD)) {
                return true;
            } else {
                conn.close();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Comprobar contraseña
     *
     * @param user idUsuario
     * @param passOld contraseña
     * @return Contraseña ok: true, caso contrario false.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean checkPass(String user, String passOld) throws SQLException, ClassNotFoundException {
        String userBBDD = ""; //para coger el usuario de la base de datos


        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery("select * from \"Cliente\" where \"userName\"='" + user + "' and \"pass\"='" + passOld + "'");
        if (rs.next()) {
            userBBDD = rs.getString("userName");

            if (user.equals(userBBDD)) {
                return true;
            } else {
                conn.close();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Encontrar cliente (con datos)
     *
     * @param user idusuario
     * @return Devuelve un objeto de clase Cliente con sus datos rellenos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Cliente findCliente(String user) throws SQLException, ClassNotFoundException {
        String userBBDD = ""; //para coger el usuario de la base de datos


        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery("select * from \"Cliente\" where \"userName\"='" + user + "'");
        if (rs.next()) {
            userBBDD = rs.getString("userName");

            if (user.equals(userBBDD)) {
                Cliente cli = new Cliente(rs.getString("userName"), rs.getString("pass"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("email"), rs.getString("tarjeta"), Integer.parseInt(rs.getString("privilegios")), Integer.parseInt(rs.getString("baneado")));
                return cli;
            } else {
                conn.close();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Busca la dirección asociada a un cliente
     *
     * @param user idUsuario
     * @return Clase dirección (con datos)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Direccion findDireccion(String user) throws SQLException, ClassNotFoundException {
        String userBBDD = ""; //para coger el usuario de la base de datos


        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        Direccion dirNull = new Direccion("0", "0", 0, 0, "0", 0, "0", "0", "0");

        st = conn.createStatement();
        rs = st.executeQuery("select * from \"Direccion\" where \"userNameCli\"='" + user + "'");
        if (rs.next()) {
            userBBDD = rs.getString("userNameCli");

            if (user.equals(userBBDD)) {
                Direccion dir = new Direccion(rs.getString("tipo"), rs.getString("nombre"), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("piso")), rs.getString("puerta"), Integer.parseInt(rs.getString("codigopostal")), rs.getString("localidad"), rs.getString("provincia"), rs.getString("pais"));
                return dir;
            } else {
                conn.close();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Añadir nuevo cliente a la BBDD
     *
     * @param cli Objeto cliente
     * @param dir Objeto dirección
     * @return booleano para confirmar
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean nuevoCliente(Cliente cli, Direccion dir) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;



            st = conn.createStatement();
            String sql = "INSERT into \"Cliente\" values ('" + cli.getUsername() + "','" + cli.getPass() + "','" + cli.getNombre() + "','" + cli.getApellido() + "','" + cli.getTelefono() + "','" + cli.getEmail() + "'," + cli.getPrivilegios() + "," + cli.getBaneado() + ",'" + cli.getNumerotarjeta() + "')";
            st.executeUpdate(sql);

            if (dir != null) {
                String sql2 = "INSERT into \"Direccion\" values ('" + cli.getUsername() + "','" + dir.getTipo() + "','" + dir.getNombre() + "'," + dir.getNumero() + "," + dir.getPiso() + ",'" + dir.getPuerta() + "'," + dir.getCodigopostal() + ",'" + dir.getLocalidad() + "','" + dir.getProvincia() + "','" + dir.getPais() + "')";
                st.executeUpdate(sql2);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }

    /**
     * Actualizar datos cliente
     *
     * @param cli Objeto cliente
     * @param dir Objecto dirección
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateCliente(Cliente cli, Direccion dir) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "UPDATE \"Cliente\" SET  \"telefono\"= '" + cli.getTelefono() + "', \"nombre\"= '" + cli.getNombre() + "',\"apellido\"= '" + cli.getApellido() + "',\"email\"= '" + cli.getEmail() + "',\"tarjeta\"= '" + cli.getNumerotarjeta() + "' WHERE \"userName\" = '" + cli.getUsername() + "'   ";
            st.executeUpdate(sql);

            String sql2 = "UPDATE \"Direccion\" SET  \"tipo\"= '" + dir.getTipo() + "', \"nombre\"= '" + dir.getNombre() + "',\"numero\"= " + dir.getNumero() + ",\"piso\"= " + dir.getPiso() + ",\"puerta\"= '" + dir.getPuerta() + "',\"codigopostal\"= " + dir.getCodigopostal() + ",\"localidad\"= '" + dir.getLocalidad() + "',\"provincia\"= '" + dir.getProvincia() + "',\"pais\"= '" + dir.getPais() + "' WHERE \"userNameCli\" = '" + cli.getUsername() + "'";

            st.executeUpdate(sql2);

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Actualiza password
     *
     * @param cli Objeto cliente
     * @return booleano para comprobaciones
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updatePass(Cliente cli) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;

            st = conn.createStatement();

            String sql = "UPDATE \"Cliente\" SET  \"pass\"= '" + cli.getPass() + "' WHERE \"userName\" = '" + cli.getUsername() + "'";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }

    /**
     * Listado de clientes
     *
     * @return ArrayList de clientes
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Cliente> findAllClients() throws SQLException, ClassNotFoundException {

        PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        st = conn.createStatement();
        rs = st.executeQuery("select * from \"Cliente\" ");
        while (rs.next()) {

            String userName = rs.getString("userName");
            String pass = rs.getString("pass");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            int privilegios = rs.getInt("privilegios");
            int baneado = rs.getInt("baneado");
            String tarjeta = rs.getString("tarjeta");

            Cliente cliente = new Cliente(userName, pass, nombre, apellido, telefono, email, tarjeta, privilegios, baneado);
            lista.add(cliente);
          

        }

        return lista;
    }

    /**
     * Cambiar baneo cliente
     *
     * @param cli Objeto cliente
     * @return booleano para comprobaciones
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changeBaneo(Cliente cli) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;


            st = conn.createStatement();

            String sql = "UPDATE \"Cliente\" SET  \"baneado\"= " + cli.getBaneado() + " WHERE \"userName\" = '" + cli.getUsername() + "'";
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }

    /**
     * Borrar usuario
     *
     * @param cli Objeto cliente
     * @return booleano para comprobaciones
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean deleteUser(Cliente cli) throws SQLException, ClassNotFoundException {
        try {
            PostgreSQLConectionManager conMg = PostgreSQLConectionManager.instance();
        Connection conn = conMg.getConnection();
            Statement st = null;


            st = conn.createStatement();

            if (cli.getPrivilegios() == 0) {
                String sql2 = "DELETE FROM \"Direccion\" WHERE \"userNameCli\" = '" + cli.getUsername() + "'";
                st.executeUpdate(sql2);

               /* String sql3 = "DELETE FROM \"Pedido\" WHERE \"user\" = '" + cli.getUsername() + "'";
                st.executeUpdate(sql3);*/
            }

            String sql = "DELETE FROM \"Cliente\" WHERE \"userName\" = '" + cli.getUsername() + "'";
            st.executeUpdate(sql);

            return true;


        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
