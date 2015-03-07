<%-- 
    Document   : verClientes
    Created on : 18-jun-2013, 19:40:03
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
        <script src="js/bootbox.min.js"></script>

    </head>
    <body>
         <!-- mensajes información -->        
                <s:if test="error !=null ">
            <div class="alert alert-error" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong>Fallo!</strong>   <s:property value="error" />
            </div>  
        </s:if>
         <s:if test="exito !=null ">
            <div class="alert alert-success" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong>Correcto!</strong>   <s:property value="exito" />
            </div>  
        </s:if>
               <!--  gestión login --> 
        <s:set name="conectado" value="false"/> 
        <s:if test="#session.logged == true">
            <s:set name="conectado" value="true"/>
        </s:if>
        <s:if test="#conectado == false"> 
            <jsp:forward page="index.jsp" />
        </s:if>
        <s:if test="#session.cliente.privilegios == 0">
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

        <!--División pantalla -->
        <div class="container">
            <br>
            <div class="row">
                <div class="span9">
                         <div class="alert alert-info">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <p>En esta tabla podrá visualizar todos los <strong>Usuarios</strong> de la aplicación.
              Existen dos tipos de usuarios, los clientes registrados en la aplicación y 
              los administradores</p>
              <p>En el panel derecho podrá crear nuevos usuarios administradores</p>
            </div>
                    <h1>Listado de Usuarios</h1>
               
                    <table class="table table-striped table-condensed">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Nombre</th>
                                <th>Apellido</th>    
                                <th>Email</th> 
                                <th>Privilegios</>
                                <th>Acciones</th>  				  
                            </tr>
                        </thead>   
                        <tbody>
                            <s:iterator value="#request.listado" var="usuario">    
                                <tr>
                                    <td><s:property value="username"/></td>
                                    <td><s:property value="nombre"/></td>
                                    <td><s:property value="apellido"/></td>
                                    <td><s:property value="email"/></td>	
                                     <s:if test="privilegios == 0">
                                       <td><span class="label label-success">Cliente</span></td>
                                   </s:if>
                                       <s:if test="privilegios == 1">
                                       <td><span class="label label-inverse">Admin</span></td>
                                   </s:if>
                                   
                                    <td>
                                        <a href="#" onclick="eliminar('<s:property value="username"/>')" class="btn btn-danger"><i class="icon-trash"></i> <strong></strong></a>
                                  
                                        <s:if test="%{baneado}">
                                        
                                                <a href="#" onclick="confirmar('<s:property value="username"/>')" class="btn btn-success"><i class="icon-user"></i> <strong>Desbanear</strong></a>
                                    </s:if>
                                    <s:else>
                                            <a href="#" onclick="confirmar('<s:property value="username"/>')" class="btn btn-danger"><i class="icon-trash"></i> <strong> Banear  </strong></a>
                                    </s:else>
                                       
                                        
                                    </td> 					
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>


                </div>

                <form class="span3" action="AnadirAdmin" method="post">

                    <h3>Crear Administrador</h3>
                       
                    <label>Username</label>
                    <input type="text" name="username" class="span3" placeholder="Nombre Usuario" required>
                    <label>Email</label>
                        <input type="email" name="email" class="span3" placeholder="Correo" required>
                        <label>Password</label>
                        <input id="password" type="password" name="password" class="span3" placeholder="Contraseña" required>
                        <label>Repetir Password</label>
                        <input id="repeatpassword" type="password" name="repeatpassword" class="span3" placeholder="Repetir Contraseña" required >

             

                    <input type="submit" class="btn" value="Añadir Administrador" >
                </form>
             

            </div>

        </div>
    </div>

    <br>
    <div class="container">
        <footer>
            Copyright @Supermarket 2013
        </footer>
    </div>


    <script type="text/javascript">

    function confirmar(e)
    {
        bootbox.confirm("¿Estas seguro que desea realizar esta accion?", function(confirmed) {
            if (confirmed)
            {
                
                $.ajax({
                    type: "GET",
                    url: 'BanearClientes.action?username=' + e,
                    data: {},
                    dataType: "html",
                    success: function()
                    {
                        location.href="VerClientes.action";
                    }
                 });

            }
        });
    }
    function eliminar(e)
    {
        bootbox.confirm("¿Seguro que desea eliminar este usuario?", function(confirmed) {
            if (confirmed)
            {
                
                $.ajax({
                    type: "GET",
                    url: 'BorrarCliente.action?username=' + e,
                    data: {},
                    dataType: "html",
                    success: function()
                    {
                        location.href="VerClientes.action";
                    }
                 });

            }
        });
    }
    </script>
</body>
</html>