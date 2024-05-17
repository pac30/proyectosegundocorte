<%-- 
    Document   : RegistrarVenta
    Created on : 2/05/2024, 6:30:20 p. m.
    Author     : dell
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            @media print{
                .parte01, .btn, .accion{
                    display: none;
                }  
            }
        </style>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="col-sm-5 parte01">
                <div class="card">
                    <form action="Controlador?menu=NuevaVenta" method="POST">
                     <div class="card-body">
                         <div class="form-group">
                             <label>Datos del Cliente</label>
                         </div>
                         <div class="form-group d-flex">
                             <div class="col-sm-6 d-flex">
                                 <input type="text" name="codigocliente" value="${c.getDni()}" class="form-control" placeholder="Codigo">
                                 <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">  
                             </div>
  
                                <div class="col-sm-6">
                                    <input type="text" name="nombrescliente" value="${c.getNombres()}"placeholder="Datos Cliente" class="form-control">
                                 
                             </div>
                            
                         </div>
                         <div class="form-group">
                             <label>Datos Producto</label>
                         </div>
                         <div class="form-group d-flex">
                             <div class="col-sm-6 d-flex">
                                 <input type="text" name="codigoproducto" class="form-control" placeholder="Codigo">
                                 <button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">Buscar</button>  
                             </div>
  
                                <div class="col-sm-6">
                                    <input type="text" name="nombreproducto" value="${producto.getNombre()}"placeholder="Datos Producto " class="form-control">
                                 
                             </div>
                             
                         </div>
                        
                         <div class="form-group d-flex">
                              <div class="col-sm-6 d-flex">
                                 <input type="text" name="precio" value="${producto.getPrecio()}" class="form-control" placeholder="$/ .0.00">
                                 
                             </div>
  
                                <div class="col-sm-3">
                                    <input type="number" value="1" name="cant" placeholder="" class="form-control">
                             </div>
                              <div class="col-sm-3">
                                    <input type="text" name="stock" value="${producto.getStock()}" placeholder="Stock" class="form-control">
                             </div>
                         </div>
                             
                         <div class="form-group">
                             <button type="submit" name="accion" value="Agregar" class="btn btn-outline-info">Agregar Producto </button>
                         </div>
                         </div>
                          </form>
                </div>
                </div>    
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex col-sm-6 ml-auto">
                            <label>Nro.Serie:  </label>
                    <input type="text" name="NroSerie" value="${nserie}" class="form-control">
                </div>
                        <br>
                <table class="table table-hover">
                    <thead>
                    <th>Nro</th>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>SubTotal</th>
                    <th class="accion">Acciones</th>
                    </thead>
                    <tbody>
                        <c:forEach var="list" items="${lista}">
                            
                            <tr>
                                <td>${list.getItem()}</td>
                                <td>${list.getIdproducto()}</td>
                                <td>${list.getDescripcion()}</td>
                                <td>${list.getPrecio()}</td>
                                <td>${list.getCantidad()}</td>
                                <td>${list.getSubtotal()}</td>
                                <td class="d-flex">
                                    <a href="#" class="btn btn-warning">Editar</a>
                                    <a href="#" class="btn btn-danger" style="margin-left:10px">Delete</a>
                                </td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" onclick="print()" class="btn btn-success">Generar Venta</a>
                            <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                        </div>
                        <div class="col-sm-4 ml-auto">
                            <label class="col sm-4 text-right mt-1">Total a Pagar</label>
                            <input type="text" name="txtTotal" value="$/. ${totalpagar}" class="form-control">
                        </div>   
                    </div>      
        </div>
                  </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</html>