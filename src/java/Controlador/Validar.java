/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;
//Esto es un servlet
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author danie
 */
public class Validar extends HttpServlet {
EmpleadoDAO  edao=new EmpleadoDAO(); //Instancia la clase EmpleadoDAO
Empleado em=new Empleado(); //Se instancia   la etnidad empleado
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
String accion=request.getParameter("accion");   //accion es nombre del boton de la accion
if(accion.equalsIgnoreCase("Ingresar")){//Si el valor es igual
    //Capturar datos que esta ingresando
    String user=request.getParameter("txtuser");
    String pass=request.getParameter("txtpass");
    em=edao.validar(user,pass); //Variable entidad empleado sera igual a la clase EmpleadoDAO con el metodo validar
    if(em.getUser()!= null){//Cuando el usuario es validado nos redirecciona al controlador
   //Como se valido, entonces se pueden enviar los datos
   request.setAttribute("usuario", em);
        request.getRequestDispatcher("Controlador?menu=Principal").forward(request,response);//Nos redirecciona al controlador
}else{//Nos redirecciona a la misma pagina del login
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
else{
    //Caso contrario es denegado, nos redirecciona a la misma pagina
    request.getRequestDispatcher("index.jsp").forward(request, response);
    
}
//processRequest(request, response);
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
