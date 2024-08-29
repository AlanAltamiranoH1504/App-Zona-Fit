package zona_fit.datos;

import zona_fit.Conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Implementamos la interfaz
public class ClienteDAO implements IClienteDAO{
    //Definimos instrucciones sql
    private static final String SELECT  = "SELECT id, nombre, apellido, membresia FROM Cliente";
    private static final String SELECTPORID = "SELECT id, nombre, apellido, membresia FROM Cliente WHERE id = ?";
    private static final String INSERT = "INSERT INTO Cliente(nombre, apellido, membresia) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE Cliente SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Cliente WHERE id = ?";


    @Override
    public List<Cliente> select() {
        //Preparamos variables para la conexion
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        //Objeto de la clase Cliente
        Cliente cliente;
        //ArrayList que va a tener todos los cliente del select
        List<Cliente> clientes = new ArrayList<>();

        try {
            //Realizamos la conexion a la DB
            conexion = Conexion.getConection();
            //Inicializamos el objeto instruccion
            instruccion = conexion.prepareStatement(SELECT);
            //Ejecutamos el query
            resultado = instruccion.executeQuery();

            //Mientra haya un registro nuevo
            while (resultado.next()){
                //Definimos variables que van hacer get de lo que hay en la DB
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String apellido = resultado.getString(3);
                int membresia = resultado.getInt(4);

                //Inicializamos el objeto cliente con los valores de los get
                cliente = new Cliente(id, nombre, apellido, membresia);

                //Agregamos el objeto cliente al arrayList
                clientes.add(cliente);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //Cerramos los objetos que abrimos
            Conexion.Close(conexion);
            Conexion.Close(instruccion);
            Conexion.Close(resultado);
        }
        //Regresamos el arrayList
        return clientes;
    }

    @Override
    public boolean clientePorId(Cliente cliente) {
        //Preparamos variables de conexion
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;

        try {
            //Realizamos la conexion
            conexion = Conexion.getConection();
            //Inicializamos el objeto instruccion
            instruccion = conexion.prepareStatement(SELECTPORID);
            //Ejecutamos el set del id
            instruccion.setInt(1, cliente.getId());
            //Ejecutamos el query
            resultado = instruccion.executeQuery();

            if (resultado.next()){
                //Hacemos los set
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setMembresia(resultado.getInt("membresia"));

                //Regresamos true porque se encontro el cliente
                return  true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            //Cerramos objeto de conexion
            Conexion.Close(resultado);
            Conexion.Close(instruccion);
            Conexion.Close(conexion);
        }
        //Regresamos el false si no encontramos el cliente
        return false;
    }

    @Override
    public int insert(Cliente cliente) {
        //Preparamos variable de conexion
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int rows = 0;

        try {
            //Realizamos la conexion a la DB
            conexion = Conexion.getConection();
            //Inicializamos el objeto instruccion
            instruccion = conexion.prepareStatement(INSERT);
            //Hacemos los set
            instruccion.setString(1, cliente.getNombre());
            instruccion.setString(2, cliente.getApellido());
            instruccion.setInt(3, cliente.getMembresia());

            //Ejecutamos el query
            rows = instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            //Cerramos los objetos de conexion
            Conexion.Close(instruccion);
            Conexion.Close(conexion);
        }
        //Regresamos el numero de registros insertados
        return rows;
    }

    @Override
    public int update(Cliente cliente) {
        //Preparamos variable de conexion
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int rows = 0;

        try {
            //Realizamos la conexion
            conexion = Conexion.getConection();
            //Inicializamos el objeto instruccion
            instruccion = conexion.prepareStatement(UPDATE);
            //Hacemos los set
            instruccion.setString(1, cliente.getNombre());
            instruccion.setString(2, cliente.getApellido());
            instruccion.setInt(3, cliente.getMembresia());
            instruccion.setInt(4, cliente.getId());

            //Ejecutamos el query
            rows = instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            //Cerramos objetos de conexion
            Conexion.Close(instruccion);
            Conexion.Close(conexion);
        }
        //Regresamos los rows insertados
        return rows;
    }

    @Override
    public int delete(Cliente cliente) {
        //Preparamos variables de conexion
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int rows = 0;

        try {
            //Realizamos la conexion
            conexion = Conexion.getConection();
            //Incializamos el objeto instruccion
            instruccion = conexion.prepareStatement(DELETE);
            //Hacemos los set
            instruccion.setInt(1, cliente.getId());
            //Ejecutamos query
            rows = instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            Conexion.Close(instruccion);
            Conexion.Close(conexion);
        }
        //Regresamos el numero de registros eliminados
        return rows;
    }
}
