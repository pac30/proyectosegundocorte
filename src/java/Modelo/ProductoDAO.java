/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Producto buscar(int id){
        Producto p=new Producto();
        String sql="select * from producto where idproducto= "+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        }catch (SQLException e){
            System.out.println("error en buscar "+e.getMessage());
        }
        return p;
    }
    public int actualizarstock(int id,int stock){
        String sql="update producto set Stock=? where idproducto=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        }catch(SQLException e){ 
            System.out.println("error en actualizar stock "+e.getMessage());
        }
        return r;
    }

    public List listar() {
        String sql = "select * from producto";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setId(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setPrecio(rs.getDouble(3));
                prod.setStock(rs.getInt(4));
                prod.setEstado(rs.getString(5));
                lista.add(prod);
            }
        } catch (SQLException e) {
           System.out.println("error en listar "+e.getMessage());
        }
        return lista;
    }

   public int agregar(Producto producto){
    String sql = "insert into producto(Nombres, Precio, Stock, Estado)values(?,?,?,?)";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, producto.getNombre());
        ps.setDouble(2, producto.getPrecio());
        ps.setInt(3, producto.getStock());
        ps.setString(4, producto.getEstado());
        ps.executeUpdate();
    } catch (SQLException e){
        System.out.println("error en agregar "+e.getMessage());
    }
    return r;
}

public Producto listarId(int id){
    Producto producto = new Producto();
    String sql = "select * from producto where IdProducto=?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id); // Set the parameter value
        rs = ps.executeQuery();
        while(rs.next()){
            producto.setId(rs.getInt(1)); // Set the ID
            producto.setNombre(rs.getString(2));
            producto.setPrecio(rs.getDouble(3));
            producto.setStock(rs.getInt(4));
            producto.setEstado(rs.getString(5));
        }
    } catch (SQLException e){
        System.out.println("error en listar id "+e.getMessage());
    }
    return producto;
}


public int actualizar(Producto producto){
    String sql = "update producto set Nombres=?,Precio=?,Stock=?,Estado=? where IdProducto=?";
    try{
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, producto.getNombre());
        ps.setDouble(2, producto.getPrecio());
        ps.setInt(3, producto.getStock());
        ps.setString(4, producto.getEstado());
        ps.setInt(5, producto.getIdProducto());
        ps.executeUpdate();
    } catch (SQLException e){
        System.out.println("error en actualizar "+e.getMessage());
    }
    return r;
}

public void delete(int id){
    String sql = "delete from producto where IdProducto=?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id); // Set the parameter value
        ps.executeUpdate();
    } catch (SQLException e){
        System.out.println("error en eliminar "+e.getMessage());
    }
}


}

