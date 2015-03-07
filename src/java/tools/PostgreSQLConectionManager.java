/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.*;

/**
 * Conexión a la base de datos Singleton
 *
 * @author Jorge
 */
public class PostgreSQLConectionManager {

    private static PostgreSQLConectionManager instance = new PostgreSQLConectionManager();
    private static final String pgDriver = "org.postgresql.Driver";
    private static final String pgUrl = "jdbc:postgresql://localhost:5432/Backup";
    private static final String usr = "postgres";
    private static final String psw = "password";
    private static Connection connection = null;

    private PostgreSQLConectionManager() {

        //comprobación driver postgres
        try {
            Class.forName(pgDriver);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    /**
     * Obtener conexión
     *
     * @return Devuelve la conexión a la base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        connection = DriverManager.getConnection(pgUrl, usr, psw);
        return connection;
    }

    /**
     * Devuelve instancia de la clase
     *
     * @return
     */
    public static PostgreSQLConectionManager instance() {
        return instance;
    }
}