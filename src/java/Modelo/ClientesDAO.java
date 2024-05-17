package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Clientes buscar(String dni){
        Clientes c=new Clientes();
        String sql="select * from cliente where Dni= "+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println("se encontro el cliente ");
                c.setIdCliente(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEstado(rs.getString(5));
            }
        } catch (SQLException e){    
            System.out.println("error en buscar "+e.getMessage());
        }
        return c;
    }
    
    public List listar() {
    String sql = "select * from cliente"; // Se añadió un espacio antes de "from"
    List<Clientes> lista = new ArrayList<>();
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Clientes c = new Clientes();
            c.setIdCliente(rs.getInt(1));
            c.setDni(rs.getString(2));
            c.setNombres(rs.getString(3));
            c.setDireccion(rs.getString(4));
            c.setEstado(rs.getString(5));
            lista.add(c);
        }
    } catch (SQLException e) {
        // Manejo de excepciones
         System.out.println("error en listar "+e.getMessage());
    }
    return lista;
}

    public int agregar(Clientes c) {
        String sql = "insert into cliente(Dni, Nombres, Direccion, Estado) values (?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
             System.out.println("error en agregar "+e.getMessage());
        }
        return r;
    }

    public Clientes listarId(int idC) {
    Clientes c = new Clientes();
    String sql = "select * from cliente where IdCliente = ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idC);  // Aquí asignamos el parámetro idC a la consulta
        rs = ps.executeQuery();
        while (rs.next()) {
            c.setIdCliente(rs.getInt(1));
            c.setDni(rs.getString(2));
            c.setNombres(rs.getString(3));
            c.setDireccion(rs.getString(4));
            c.setEstado(rs.getString(5));
        }
    } catch (SQLException e) {
        System.out.println("error en listar id "+e.getMessage());
    }
    return c;
}


    public int actualizar(Clientes c) {
        String sql = "update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEstado());
            ps.setInt(5, c.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("error en actualizar "+e.getMessage());
        }
        return r;
    }

    public void delete(int idC) {
    String sql = "delete from cliente where IdCliente = ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idC);  // Aquí asignamos el parámetro idC a la consulta
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("error en eliminar "+e.getMessage());
    }
}

}
