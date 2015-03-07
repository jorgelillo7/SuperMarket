<%-- 
    Document   : verPedidosAdmin
    Created on : 18-jun-2013, 19:40:56
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
                        <!-- Login -->
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


            <br>
            <div class="span12">
                <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>En esta tabla podrá visualizar todos los <strong>PEDIDOS</strong> de la aplicación.</p>
                    <p>Concretamente estos pedidos son realizados por todos los usuarios registrados. Si accede en detalles podrá ver
                       cada uno por separado con sus detalles.</p>
                </div>
                <h2><strong>Pedidos de Clientes</strong></h2>
                <s:if test="#request.basedatos.size() > 0">
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Número de Pedido</th>
                            <th>Cliente</th>
                            <th>Fecha de Creación</th>
                            <th>Estado</th> 
                            <th>Precio Total</th>
                            <th>Acción</th>
                        </tr>
                    </thead>   
                    <tbody>
                        <s:iterator value="#request.basedatos" var="pedidos">    
                        <thead>
                            <tr>
                                <td><s:property value="idPedido"/></td>
                                <td><s:property value="idUser"/></td>
                                <td><s:date name="creacion" format="dd/MM/yyyy HH:mm" /></td>
                               
                                <s:if test="estado == 0">
                                    <td><span class="label label-important">Sin Enviar</span></td>
                                </s:if>
                                <s:if test="estado == 1">
                                    <td><span class="label label-info">En proceso</span></td>
                                </s:if>
                                <s:if test="estado == 2">
                                    <td><span class="label label-success">Entregado</span></td>
                                </s:if>
                                <td><s:property value="preciototal"/>€</td> 
                                <td>
                                    <a href="
                                       <s:url action="VerPedidoAdmin">
                                           <s:param name="id" value="%{idPedido}" />
                                       </s:url>" class="btn"><i class="icon-plus-sign"></i> <strong>Detalles</strong></a>
                                </td>
                                
                                
                                
                                <td>
                                <div class="btn-group">
                                    <a class="btn dropdown-toggle btn btn-inverse" data-toggle="dropdown" href="#">
                                        Cambiar Estado
                                        <span class="caret"></span>
                                      </a>
                                      <ul class="dropdown-menu">
                                        <!-- dropdown menu links -->
                                        <li class=""><a href="<s:url action="CambiarEstado"><s:param name="estadonuevo" value="%{0}" ></s:param> <s:param name="id" value="%{idPedido}"> </s:param></s:url>">Estado 0: Sin Enviar</a></li>
                                         <li class=""><a href="<s:url action="CambiarEstado"><s:param name="estadonuevo" value="%{1}" ></s:param><s:param name="id" value="%{idPedido}"> </s:param></s:url>">Estado 1: En proceso</a></li>
                                         <li class=""><a href="<s:url action="CambiarEstado"><s:param name="estadonuevo" value="%{2}" ></s:param><s:param name="id" value="%{idPedido}"> </s:param></s:url>">Estado 2: Entregado</a></li>
                                      </ul>
                                    </div>
                                </td>      
                            </tr>
                        </s:iterator>
                        </tbody>
                </table>
                </s:if>
                <s:else>
                    <h4>Todavía no se han realizado pedidos</h4>
                </s:else>
            </div>
        </div>
        <br>
        <div class="container">
            <footer>
                Copyright @Supermarket 2013
            </footer>
        </div>
    </body>
</html>