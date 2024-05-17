/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;
//Esto es un servlet

import Modelo.Clientes;
import Modelo.ClientesDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Escribir nombre metodo y despues de las comillas el mas y get mensaje
/**
 *
 * @author danie
 */
public class Controlador extends HttpServlet {

    Empleado em = new Empleado();//Instanciamos entidad e instancia DAO
    EmpleadoDAO edao = new EmpleadoDAO();
    Clientes c = new Clientes();
    ClientesDAO clienteDAO = new ClientesDAO();
    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    int ide;
    int idc;
    int idp;

    Venta v=new Venta();
    List<Venta>lista=new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    String numeroserie;
    VentaDAO vdao=new VentaDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");//Tenemos una variable llamada accion, que recibe la accion del usuario, y se ejecuta la accion correspondiente

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("Empleado")) {//Dentro de cada if se crean los switch

            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);

                    break;

                case "Agregar":
                    //Variables que almacenan datos ingresados para agregar
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    em.setDni(dni);//Se estan agregando los datos
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Actualizar": //Actualiza los datos de esa misma fila, no agregar un nuevo dato
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");
                    em.setDni(dni1);//Se estan agregando los datos
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);//Se envia el id que es capturado cuando el usuario presiona editar
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);//e almacena datos fila seleccionada
                    request.setAttribute("empleado", e);//Enviar datos al formulario
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List listaClientes = clienteDAO.listar();
                    request.setAttribute("clientes", listaClientes);
                    break;
                case "Agregar":
                    String dniCliente = request.getParameter("txtDniC");
                    String nombresCliente = request.getParameter("txtNombresC");
                    String direccionCliente = request.getParameter("txtDireccionC");
                    String estadoCliente = request.getParameter("txtEstadoC");
                    c.setDni(dniCliente);
                    c.setNombres(nombresCliente);
                    c.setDireccion(direccionCliente);
                    c.setEstado(estadoCliente);
                    clienteDAO.agregar(c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String dniC = request.getParameter("txtDniC");
                    String nombresC = request.getParameter("txtNombresC");
                    String direccionC = request.getParameter("txtDireccionC");
                    String estadoC = request.getParameter("txtEstadoC");
                    c.setDni(dniC);
                    c.setNombres(nombresC);
                    c.setDireccion(direccionC);
                    c.setEstado(estadoC);
                    c.setIdCliente(idc);
                    clienteDAO.actualizar(c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Clientes cl = clienteDAO.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idc = Integer.parseInt(request.getParameter("id"));
                    clienteDAO.delete(idc);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List listaProductos = productoDAO.listar();
                    request.setAttribute("productos", listaProductos);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombres");
                    double precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String estado = request.getParameter("txtEstado");
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setStock(stock);
                    producto.setEstado(estado);
                    productoDAO.agregar(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombreActualizar = request.getParameter("txtNombres");
                    double precioActualizar = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stockActualizar = Integer.parseInt(request.getParameter("txtStock"));
                    String estadoActualizar = request.getParameter("txtEstado");
                    producto.setNombre(nombreActualizar);
                    producto.setPrecio(precioActualizar);
                    producto.setStock(stockActualizar);
                    producto.setEstado(estadoActualizar);
                    producto.setId(idp); // Ensure idp is set correctly
                    productoDAO.actualizar(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto p = productoDAO.listarId(idp);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Delete":
                    idp = Integer.parseInt(request.getParameter("id"));
                    productoDAO.delete(idp);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente": {
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    c = clienteDAO.buscar(dni);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    break;
                }
               
               
            case "BuscarProducto":
                int id=Integer.parseInt(request.getParameter("codigoproducto"));
                producto=productoDAO.listarId(id);
                request.setAttribute("c", c);
                request.setAttribute("producto", producto);
                request.setAttribute("lista", lista);
                request.setAttribute("totalpagar", totalPagar);
                break;
          
                
            case "Agregar":
                    request.setAttribute("c", c);
                totalPagar=0.0;
                item=item+1;
                cod=producto.getIdProducto();
                descripcion=request.getParameter("nombreproducto");
                precio=Double.parseDouble(request.getParameter("precio"));
                cant=Integer.parseInt(request.getParameter("cant"));
                subtotal=precio*cant;
                v=new Venta();
                v.setItem(item);
                v.setIdproducto(cod);
                v.setDescripcion(descripcion);
                v.setPrecio(precio);
                v.setCantidad(cant);
                v.setSubtotal(subtotal);
                lista.add(v);
                for(int i = 0 ; i < lista.size() ; i++){
                    totalPagar=totalPagar +lista.get(i).getSubtotal();
                }
               request.setAttribute("totalpagar", totalPagar);
                request.setAttribute("lista", lista);
                break;
            case "GenerarVenta":
                for(int i = 0; i <lista.size();i++){
                    Producto pr=new Producto();
                    int cantidad=lista.get(i).getCantidad();
                    int idproducto=lista.get(i).getIdproducto();
                    ProductoDAO aO=new ProductoDAO();
                    pr=aO.buscar(idproducto);
                    int sac=pr.getStock()-cantidad;
                    aO.actualizarstock(idproducto, sac);
                }
                v.setIdcliente(c.getIdCliente());
                v.setIdempleado(2);
                v.setNumserie(numeroserie);
                v.setFecha("2019-06-14");
                v.setMonto(totalPagar);
                v.setEstado("1");
                vdao.guardarVenta(v);
                
                int idv=Integer.parseInt(vdao.IdVentas());
                for(int i = 0; i <lista.size();i++){
                    v=new Venta();
                    v.setId(idv);
                    v.setIdproducto(lista.get(i).getIdproducto());
                    v.setCantidad(lista.get(i).getCantidad());
                    v.setPrecio(lista.get(i).getPrecio());
                    vdao.guardarDetalleventas(v);
                }
                request.setAttribute("nserie", numeroserie);
                break;
                  default: {
                      
                    v=new Venta();
                    lista=new ArrayList<>();
                    item=0;
                    totalPagar=0.0;
                
                    numeroserie=vdao.GenerarSerie();
                    
                    if(numeroserie==null){
                    numeroserie="00000001";
                    request.setAttribute("nserie", numeroserie);
                    }else{
                        int incrementar=Integer.parseInt(numeroserie);
                        GenerarSerie gs=new GenerarSerie();
                        numeroserie=gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    }
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
