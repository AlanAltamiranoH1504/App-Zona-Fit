package zona_fit.main;

import zona_fit.datos.ClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);
        //Variable para salir del menu
        boolean salir = false;

        do{
            try{
                System.out.println("\t**** APP ZONA FIT ****");
                System.out.println("1. Listar clientes");
                System.out.println("2. Buscar cliente por id");
                System.out.println("3. Agregar un cliente");
                System.out.println("4. Modificar un cliente");
                System.out.println("5. Eliminar un cliente");
                System.out.println("6. Salir de menu");
                System.out.print("Digita la opcion que deseas realizar: ");
                var opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion){
                    case 1:
                        System.out.println("\t *** LISTAR CLIENTES ***");
                        //Llamamos al metodo listarClientes
                        listarClientes(clienteDAO);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("\t *** BUSCAR CLIENTE POR ID ***");
                        //Llamamos al metoo buscarPorID
                        buscarPorID(clienteDAO);
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("\t *** AGREGAR UN CLIENTE ***");
                        //Llamamos al metodo agregarCliente
                        agregarCliente(clienteDAO, scanner);
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("\t *** MODIFICAR UN CLIENTE ***");
                        listarClientes(clienteDAO);
                        //Llamamos al metodo modificarCliente
                        modificarClient(clienteDAO, scanner);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("\t *** ELIMINAR UN CLIENTE ***");
                        //Llamamos al metodo eliminarCliente
                        listarClientes(clienteDAO);
                        eliminarCliente(clienteDAO, scanner);
                        listarClientes(clienteDAO);
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("Hasta luego...");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta: " + opcion);
                        salir = true;
                        break;
                }
            }catch (Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }while (!salir);
    }

    //Metodo que lista los clientes de la DB
    public static void listarClientes(ClienteDAO clienteDAO){
        //ArrayList que contendra los clientes de la DB
        List<Cliente> clientes = clienteDAO.select();

        //Iteramos el arrayList
        for (var cliente: clientes){
            System.out.println("cliente = " + cliente);
        }
    }

    //Metodo que busca un cliente por id
    public static void buscarPorID(ClienteDAO clienteDAO){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa el ID del cliente que quieres buscar: ");
            var idBusqueda = Integer.parseInt(scanner.nextLine());
            //Creamos objeto de tipo Cliente con el id
            Cliente clienteBusqueda = new Cliente(idBusqueda);

            var encontrado = clienteDAO.clientePorId(clienteBusqueda);
            if (encontrado){
                System.out.println("Cliente encontrado = " + clienteBusqueda);
            }else {
                System.out.println("El cliente con ID {" + idBusqueda + "}, no se encuentra en la DB");
            }
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }

    //Metodo que agrega un cliente a la DB
    public static void agregarCliente(ClienteDAO clienteDAO, Scanner scanner){
        try{
            System.out.print("Ingresa el nombre del nuevo cliente: ");
            var nombreNuevo = scanner.nextLine();
            System.out.print("Ingresa los apellidos del nuevo cliente: ");
            var apellidosNuevo = scanner.nextLine();
            System.out.println("Generando numero de membresia....");
            var aleatorio = new Random();
            var numeroMembresia = aleatorio.nextInt(0, 9999);

            //Creamos objeto de tipo Cliente
            Cliente clienteNuevo = new Cliente(nombreNuevo, apellidosNuevo, numeroMembresia);
            //Agreamos el cliente nuevo a la DB
            clienteDAO.insert(clienteNuevo);
            System.out.println("→ Cliente agregado a la DB");
        }catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }

    //Metodo que modifica un cliente de la DB
    public static void modificarClient(ClienteDAO clienteDAO, Scanner scanner){
        System.out.print("Ingresa el ID del cliente a modificar: ");
        var idModificar = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingresa el nuevo nombre: ");
        var nombreModificar = scanner.nextLine();
        System.out.print("Ingresa los nuevos apellidos: ");
        var apellidosModificar = scanner.nextLine();
        System.out.println("Generando nuevo numero de membresia...");
        var aleatorio = new Random();
        var membresiaModificar = aleatorio.nextInt(0, 9999);

        //Creamos nuevo objeto de la clase Cliente
        Cliente clienteModificar = new Cliente(idModificar, nombreModificar, apellidosModificar, membresiaModificar);

        //Agregamos el cliente modificado a la DB
        clienteDAO.update(clienteModificar);
        System.out.println("→ Cliente modificado en la DB");
    }

    //Metodo que elimina un cliente de la DB
    public static void eliminarCliente(ClienteDAO clienteDAO, Scanner scanner){
        System.out.print("Ingresa el ID del cliente a eliminar: ");
        var idEliminar = Integer.parseInt(scanner.nextLine());

        //Creamos objeto con el id dado
        Cliente clienteEliminar = new Cliente(idEliminar);

        //Eliminamos el cliente de la DB
        clienteDAO.delete(clienteEliminar);
        System.out.println("→ Cliente eliminado de la DB");
    }
}
