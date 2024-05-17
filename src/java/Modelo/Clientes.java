package Modelo;

public class Clientes {
     int IdCliente;
     String Dni;
     String Nombres;
     String Direccion;
     String Estado;

    // Constructor
    public Clientes() {
    }

    public Clientes(int IdCliente, String Dni, String Nombres, String Direccion, String Estado) {
        this.IdCliente = IdCliente;
        this.Dni = Dni;
        this.Nombres = Nombres;
        this.Direccion = Direccion;
        this.Estado = Estado;
    }

    // Getters y Setters
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        this.IdCliente = idCliente;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni = dni;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        this.Nombres = nombres;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }
}
