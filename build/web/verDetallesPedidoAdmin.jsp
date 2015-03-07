<%-- 
    Document   : verDetallesPedidoAdmin
    Created on : 22-jun-2013, 17:47:17
    Author     : Jorge
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title> SuperMarket </title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/estilos.css">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
 
    </head>
    <body>
           <!-- gestion login -->
        <s:if test="#session.logged == true">
            <s:set name="conectado" value="true"/>
        </s:if>
        <s:if test="#conectado == false"> 
            <jsp:forward page="index.jsp" />
        </s:if>
        <s:if test="#session.user.privilegios == 0">
            <jsp:forward page="index.jsp" />
        </s:if>
        
        <!-- Barra de arriba -->
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a href="#" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a href="mainadmin.jsp" class="brand">SuperMarket Admin Panel</a> 
                    <div class="nav-collapse">
                        <ul class="nav">

                            <li>
                                <a href=<s:url action="VerClientes"/>>
                                    Clientes 
                                </a>
                            </li>
  
                            <li class="">
                                <a href="<s:url action="VerProductosAdmin"/>">
                                    Productos
                                </a>
                            </li>
                            <li>
                                <a href="<s:url action="VerPedidosAdmin"/>">
                                    Pedidos
                                </a>
                            </li>
                        </ul>
                    </div>

                   

                    <ul class="nav pull-right">

                        <!--Login -->
                        <li class="dropdown"> 
                            <a class="dropdown-toggle" data-toggle="dropdown" href=""><i class="icon-user icon-black"></i>
                                Administrador                  
                            </a>
                            <ul class=" dropdown-menu">

                                <fieldset class='textbox text-success' style="padding:10px">
                                    Bienvenido Administrador

                                </fieldset>
                                <li class="divider"></li>
                                <li><a href="<s:url action="LogOff"/>"><i class="icon-remove icon-black"></i> Desconectar</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

      
        <div class="container">
            <div class="row">
                <div class="span12">
                    <h2><strong>Información de Pedido</strong></h2>

                    
          <div class="row-fluid">
            <div class="span4">
                    <span class="label label-inverse">IDPedido</span>
                              <p>
                                  <s:property value="%{#request.pedido.idPedido}"/>  
                              </p>
                              <span class="label label-inverse">Fecha de Inicio</span>
                              <p>
                                  <s:date name="%{#request.pedido.creacion}" format="dd/MM/yyyy HH:mm" />
                              </p>

            </div>
            <div class="span4">
                 <span class="label label-inverse">Precio</span>
                              <p>
                              <h3><s:property value="%{#request.pedido.preciototal}"/>€</h3>
                              </p>
                              <span class="label label-inverse">Estado del pedido</span>
                              <p>
                              <p>
                                  <s:if test="request.pedido.estado == 0">
                                  <span class="label label-important">Sin Enviar</span>
                                  </s:if>
                              <s:if test="request.pedido.estado == 1">
                                  <span class="label label-info">En proceso</span>
                              </s:if>

                              <s:if test="request.pedido.estado == 2">
                                  <span class="label label-success">Entregado</span>
                              </s:if>
                              </p>
                              </p>
                </div>
         
                              
                 <div class ="span4">
                                  
                    <span class="label label-inverse">Dirección de entrega</span>
                     <address>
                         
                        <s:if test='%{#request.direccionEnvio == null}'>
                            Sin dirección, el cliente ha sido <b style="color: red">eliminado</b>
                         </s:if>
                         <s:else>
                         <s:property value="%{#request.direccionEnvio.tipo}"/>
                         <s:property value="%{#request.direccionEnvio.nombre}"/> nº
                         <s:property value="%{#request.direccionEnvio.numero}"/>
                         <s:property value="%{#request.direccionEnvio.piso}"/>º
                         <s:property value="%{#request.direccionEnvio.puerta}"/><br>
                          <s:property value="%{#request.direccionEnvio.localidad}"/>
                           <s:property value="%{#request.direccionEnvio.provincia}"/>
                         (<s:property value="%{#request.direccionEnvio.codigopostal}"/>)<br>
                         <s:property value="%{#request.direccionEnvio.pais}"/>
                         </s:else>
                     </adress>
                 </div>
                 <br>
                
            </div>   
                  
           </div>
              
            </div>

            <h3>Lista de Productos adquiridos</h3>
            <table class="table table-striped table-condensed">
                <thead>
                    <tr>
                        <th>Nombre </th>
                        <th>Precio</th>    
                        <th>Cantidad</>
                        <th>Total</th>  				  
                    </tr>
                </thead>
                <s:iterator value="#request.pedido.carrito" var="producto"> 
                    <p>
                    <tbody>
                        <tr>

                            <td><a href="<s:url action="VerDetallesProducto"><s:param name="idPro" value="%{prod.id}" /> </s:url>"><s:property value="prod.nombre"/></a></td>
                            <td><s:property value="precioproducto"/>€</td>
                            <td><s:property value="cantidad"/></td>	
                            <td><s:property value="total"/>€</td>

                        </tr>
                        </p>
                    </s:iterator>
                </tbody>
            </table>
        </p>



    </div>

    <br>
    <div class="container">
        <footer>
            Copyright @Supermarket 2013
        </footer>
    </div>
</body>
</html>