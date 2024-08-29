package zona_fit.dominio;

import java.lang.ref.Cleaner;
import java.util.Objects;

/**
 * Clase que funciona como dominio de la DB
 */

public class Cliente {
    //Atributos de la clase
    private int id;
    private String nombre;
    private String apellido;
    private int membresia;

    //Constructor vacio
    public Cliente(){

    }
    //Constructor que solo recibe el id
    public Cliente(int id){
        this.id = id;
    }
    //Constructor que recibe todos los atributos menos el id
    public Cliente(String nombre, String apellido, int membresia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }
    //Constructor que inicializa todos los atributos
    public Cliente(int id, String nombre, String apellido, int membresia){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }

    //Metodos GET y SET
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return this.apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public int getMembresia(){
        return this.membresia;
    }
    public void setMembresia(int membresia){
        this.membresia = membresia;
    }

    //Sobreescribimos metodo toString
    @Override
    public String toString(){
        return "Id Cliente: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido + ", Membresia: " + this.membresia;
    }

    //Sobreescribimos metodos equals y hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && membresia == cliente.membresia && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, membresia);
    }
}
