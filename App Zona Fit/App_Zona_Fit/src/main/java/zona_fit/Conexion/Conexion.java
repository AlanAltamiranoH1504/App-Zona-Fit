package zona_fit.Conexion;

import java.sql.*;

public class Conexion {
    //Definimos atributos para la conexion a la db
    private static final String URL = "jdbc:mysql://localhost:3306/zona_fit_db2?serverTimezone=America/Mexico_City";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";

    //Metodo de tipo Connection que nos permite realizar una conexion a la DB
    public static Connection getConection() throws SQLException {
        //Regresamos una conexion
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    //Creamos metodos close para cada objeto que abrimos para la DB
    public static void Close(Connection conexion){
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void Close(PreparedStatement instruccion){
        try {
            instruccion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void Close(ResultSet resultado){
        try {
            resultado.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    //Prueba para la conexion a la DB
//    public static void main(String[] args) throws SQLException {
//        var conexion = Conexion.getConection();
//
//        //Preguntamos si la variable conexion es distinto de null
//        if (conexion != null){
//            System.out.println("Hay una conexion a la DB");
//        }else {
//            System.out.println("No hay conexion a la DB");
//        }
//    }
}
