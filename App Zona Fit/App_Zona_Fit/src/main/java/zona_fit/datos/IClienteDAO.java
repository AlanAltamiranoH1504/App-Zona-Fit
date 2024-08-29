package zona_fit.datos;

import zona_fit.dominio.Cliente;

import java.util.List;

public interface IClienteDAO {
    //Definimos metodos abstractos de la interfaz
    public abstract List<Cliente> select ();
    public abstract boolean clientePorId(Cliente cliente);
    public abstract int insert(Cliente cliente);
    public abstract int update(Cliente cliente);
    public abstract int delete(Cliente cliente);
}
