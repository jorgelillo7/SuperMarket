<%-- 
    Document   : cesta
    Created on : 14-jun-2013, 17:22:24
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
        <script>
            $('#myCarousel').carousel({
                interval: 2000
            })


            window.setTimeout(function() {
                $("#alert").fadeTo(500, 0).slideUp(500, function() {
                    $(this).remove();
                });
            }, 4000);
        </script>

    </head>
    <body>
      <!-- mensajes información -->
        <s:if test="error !=null ">
            <div id="alert" class="alert alert-error" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong>Fallo!</strong>   <s:property value="error" />
            </div>  
        </s:if>
        <s:if test="alerta !=null ">
            <div id="alert" class="alert alert-success" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong></strong>   <s:property value="alerta" />
            </div>  
        </s:if>
      
       <!-- Comprobación para login posterior  -->
        <s:set name="conectado" value="false"/>
        <s:if test="#session.logged == true">
            <s:set name="conectado" value="true"/>
        </s:if>
        
           <!-- Si inicia sesión un admin avanza a su pantalla correspondiente  -->
        <s:if test="#session.cliente.privilegios == 1">
               <jsp:forward page="mainadmin.jsp" />
        </s:if>


        <!-- Barra de arriba -->
       <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a href="#" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a href="index.jsp" class="brand">SuperMarket</a>
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li>
                                <a href="sobreNosotros.jsp">
                                    Sobre SuperMarket
                                </a>
                            </li>
                        </ul>
                    </div>

                    <!-- Barra para buscar  -->
                    <form class="navbar-search pull-left" action="Buscar" method="post">
                        <div class="input-append">
                            <input type="text" name="producto" class="input-xlarge" placeholder="Buscar">
                            <button class="btn" type="submit">                               
                                <i class="icon-search"></i> 
                            </button>
                        </div>
                    </form>
                    
                    <ul class="nav pull-right">
                        <li>
                            <a href="cesta.jsp"><i class="icon-shopping-cart icon-white"></i> Cesta  
                               <s:if test="#session.cesta != null">     
                                <span class="badge badge-info"> <s:property value="%{#session.cesta.carrito.size}" /></span> 
                                </s:if>
                            </a>
                        </li>


                        <!-- Login -->
                        <li class="dropdown"> 
                            <a class="dropdown-toggle" data-toggle="dropdown" href=""><i class="icon-user icon-white"></i>
                                <s:if test="#conectado == false"> Login </s:if>
                                <s:if test="#session.logged == true">    
                                    <s:property value="%{#session.cliente.username}" />
                                </s:if>
                                <b class="caret"></b>
                            </a>
                            <ul class=" dropdown-menu">
                                <s:if test="#conectado == false">
                                    <s:form style="margin: 0px" accept-charset="UTF-8" action="CheckLogin" method="post">
                                        <div style="margin:0;padding:0;display:inline">
                                            <input name="utf8" type="hidden" value="&#x2713;">
                                            <input name="authenticity_token" type="hidden" value="4L/A2ZMYkhTD3IiNDMTuB/fhPRvyCNGEsaZocUUpw40=">
                                        </div>
                                        <fieldset class='textbox' style="padding:10px">
                                            <div class="input-prepend"><span class="add-on"><i class="icon-user"></i></span><input type="text" id="" name="username" placeholder="Usuario"></div>
                                            <div class="input-prepend"><span class="add-on"><i class="icon-lock"></i></span><input type="password" id="" name="password" placeholder="Contraseña"></div>
                                            <label for="checkbox">
                                               
                                                <button class="btn btn-inverse" name="commit" type="submit">   Aceptar   </button>
                                                <a href="registro.jsp" class="btn btn-primary" name="commit" type="submit">   Registrarse </a>
                                            </label>
                                        </fieldset>

                                    </s:form>
                                </s:if>
                                   <!-- Login cuando usuario está conectado  -->
                                <s:if test="#session.logged == true">
                                    <fieldset class='textbox text-success' style="padding:10px">
                                        Hola, <s:property value="%{#session.cliente.nombre}" />
                                    </fieldset>
                                    <li class="divider"></li>
                                    <li><a href="perfil.jsp"><i class="icon-user icon-black"></i> Mi Cuenta</a> </li>
                                    <li><a href="<s:url action="VerPedidos"><s:param name="id" value="%{#session.cliente.username}" ></s:param></s:url>"><i class="icon-tasks icon-black"></i> Mis Pedidos</a></li>
                                    <li><a href="<s:url action="LogOff"/>"><i class="icon-remove icon-black"></i> Desconectar</a></li>
                                </s:if>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Filtros izquierda -->
      <div class="container">
            <div class="row">
                <div class="span3">
                    <div class="well sidebar-nav">
                        <ul class="nav nav-list">
                          <li class="nav-header"><span class="label label-success">General</span></li>
                            <li class=""><a href="<s:url action="VerProductos"/>">Ver todos los productos</a></li>
                            <li class=""><a href="<s:url action="Ofertas"/>"/>Productos en oferta</a></li>
                            <li class="">
                            <li class="nav-header"><span class="label label-info">Supermercado</span></li>  
                       
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Alimentación</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Alimentacion\"}" ></s:param></s:url>"><span class="label label-info">Alimentación</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Aperitivos\"}" ></s:param></s:url>">Aperitivos</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Conservas\"}" ></s:param></s:url>"/>Conservas</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Chucherias\"}" ></s:param></s:url>">Chucherías varias</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"PastaArroz\"}" ></s:param></s:url>">Pasta y Arroz</a></li>
                                    </ul>
                                </div>
                           
                            <p>       
                             <div class="dropdown">
                                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">Productos Frescos</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"ProductosFrescos\"}" ></s:param></s:url>"><span class="label label-info">Productos Frescos</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Panaderia\"}" ></s:param></s:url>">Panadería</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Pescaderia\"}" ></s:param></s:url>"/>Pescadería</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Carniceria\"}" ></s:param></s:url>">Carnicería</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Fruteria\"}" ></s:param></s:url>">Frutería</a></li>
                                    </ul>
                                </div>     
                                             <p>
                                      <div class="dropdown">
                                               <a class="dropdown-toggle" data-toggle="dropdown" href="#">Bebidas</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Bebidas\"}" ></s:param></s:url>"><span class="label label-info">Bebidas</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Refrescos\"}" ></s:param></s:url>">Refrescos</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Alcohol\"}" ></s:param></s:url>"/>Alcohol</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Zumo\"}" ></s:param></s:url>">Zumos</a></li>
                            
                                    </ul>
                                </div>
                                             <p>
                                               <div class="dropdown">
                                               <a class="dropdown-toggle" data-toggle="dropdown" href="#">Congelados</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Congelados\"}" ></s:param></s:url>"><span class="label label-info">Congelados</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Helados\"}" ></s:param></s:url>">Helados</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"ParaFreir\"}" ></s:param></s:url>"/>Para Freir</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"CongeladosVarios\"}" ></s:param></s:url>">Congelados Varios</a></li>
                            
                                    </ul>
                                </div>
                                             <p>
                                               <div class="dropdown">
                                               <a class="dropdown-toggle" data-toggle="dropdown" href="#">Farmacia</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Farmacia\"}" ></s:param></s:url>"><span class="label label-info">Farmacia</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"HigieneDental\"}" ></s:param></s:url>">Higiene Dental</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Botiquin\"}" ></s:param></s:url>"/>Botiquín</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"ComplementosAlimenticios\"}" ></s:param></s:url>">Complementos alimenticios</a></li>
                            
                                    </ul>
                                </div>
                                             <p>
                                               <div class="dropdown">
                                               <a class="dropdown-toggle" data-toggle="dropdown" href="#">Mascotas</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Mascotas\"}" ></s:param></s:url>"><span class="label label-info">Mascotas</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"AlimentacionMascotas\"}" ></s:param></s:url>">Alimentacion</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"HigieneMascotas\"}" ></s:param></s:url>"/>Higiene</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"AccesoriosMascotas\"}" ></s:param></s:url>">Accesorios</a></li>
                                    </ul>
                                </div>
                            <li><a href="#"></a></li>
                            <li class="nav-header"><span class="label label-warning">Electrodomésticos</span></li>
        
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cocina</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Cocina\"}" ></s:param></s:url>"><span class="label label-warning">Cocina</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Congeladores\"}" ></s:param></s:url>">Congeladores</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Hornos\"}" ></s:param></s:url>"/>Hornos</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Lavavajillas\"}" ></s:param></s:url>">Lavavajillas</a></li>
                                              <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Microondas\"}" ></s:param></s:url>">Microondas</a></li>
                                    </ul>
                                </div>
                           
                            <p>       
                             <div class="dropdown">
                                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">Hogar</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Hogar\"}" ></s:param></s:url>"><span class="label label-warning">Hogar</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Aspiradoras\"}" ></s:param></s:url>">Aspiradoras</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Planchas\"}" ></s:param></s:url>"/>Planchas</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Calefactores\"}" ></s:param></s:url>">Calefactores</a></li>
                            
                                    </ul>
                                </div>  
                            
                            <p>
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Aparatos de cocina</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"AparatosCocina\"}" ></s:param></s:url>"><span class="label label-warning">Aparatos de cocina</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Batidoras\"}" ></s:param></s:url>">Batidoras</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Freidoras\"}" ></s:param></s:url>"/>Freidoras</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Cafeteras\"}" ></s:param></s:url>">Cafeteras</a></li>
                            
                                    </ul>
                                </div>
                           
                            <p>       
                             <div class="dropdown">
                                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">Otros</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Otros\"}" ></s:param></s:url>"><span class="label label-warning">Otros</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Lavadoras\"}" ></s:param></s:url>">Lavadoras</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Frigorificos\"}" ></s:param></s:url>"/>Frigoríficos</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"AireAcondicionado\"}" ></s:param></s:url>">Aire acondicionado</a></li>
                            
                                    </ul>
                                </div>  
                                             <p>
                            <li class="nav-header"><span class="label label-important">Electrónica</span></li>
        
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Televisiones</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Televisiones\"}" ></s:param></s:url>"><span class="label label-important">Televisiones</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"LCD\"}" ></s:param></s:url>">LCD</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"LED\"}" ></s:param></s:url>"/>LED</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Plasma\"}" ></s:param></s:url>">Plasma</a></li>
                            
                                    </ul>
                                </div>
                           
                            <p>       
                             <div class="dropdown">
                                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">Informática</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Informatica\"}" ></s:param></s:url>"><span class="label label-important">Informática</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"PCSobremesa\"}" ></s:param></s:url>">PC sobremesa</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Portatil\"}" ></s:param></s:url>"/>Portátiles</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Accesorios\"}" ></s:param></s:url>">Accesorios</a></li>
                            
                                    </ul>
                                </div>  
                            
                            <p>
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Videojuegos</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Videojuegos\"}" ></s:param></s:url>"><span class="label label-important">Videojuegos</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Consolas\"}" ></s:param></s:url>">Consolas</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"PS3\"}" ></s:param></s:url>"/>Juegos PS3</a></li>
                                             <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Wii\"}" ></s:param></s:url>">Juegos Wii</a></li>
                                           
                            
                                    </ul>
                                </div>
                           
                            <p>       
                             <div class="dropdown">
                                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">Telefonía</a>
                                     <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                         <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"Telefonia\"}" ></s:param></s:url>"><span class="label label-important">Telefonía</span></a></li>
                                           <li class="divider"></li>
                                          <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"MovilesLibres\"}" ></s:param></s:url>">Móviles Libres</a></li>
                                            <li><a tabindex="-1" href="<s:url action="Buscar"><s:param name="producto" value="%{\"NavegadoresGPS\"}" ></s:param></s:url>"/>Navegadores GPS</a></li>
                            
                                    </ul>
                                </div> 
                           
                          
                            <li class="nav-header"><a href="<s:url action="Buscar"><s:param name="producto" value="%{\"SinDeterminar\"}" ></s:param></s:url>"><span class="label label-default">Sin Determinar</span></a></li>
             
                        </ul>
                    </div>
                </div>
                                            
                <!--Cesta  -->       
                <div class="span9">
                    <s:if test="#session.cesta != null">
                        <h2>Cesta de la compra</h2>
                        <h4>Los productos añadidos a su carrito son:</h4>
                        <s:iterator value="#session.cesta.carrito" var="linea">
                            <div class="row">
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <s:if test="prod.imagen ">
                                            <img src="img/uploads/<s:property value="prod.id"/>.jpg" alt="ALT NAME">
                                        </s:if>
                                        <s:else>
                                            <img src="img/null.jpg" alt="ALT NAME">
                                        </s:else>
                                    </a>
                                </div>
                                <div class="span6">      
                                    <h4><strong><a href="<s:url action="VerProducto"><s:param name="id" value="%{prod.id}" /> </s:url>" ><s:property value="prod.nombre"/></a></strong></h4>			
                                    <div>    
                                        <h5>Precio Unidad: <s:property value="prod.precio"/>€</h5>
                                    </div>
                                    <s:form action="ActualizarCantidadProducto" >
                                        <div class="input-prepend">
                                            <span class="add-on">
                                                <i class=" icon-shopping-cart"></i>
                                            </span>
                                            <input name="id" type="hidden" value="<s:property value="prod.id"/>">
                                            <input style="width: 30px; margin-right: 3px" type="text" value="<s:property value="cantidad"/>" name="cantidad"/>                            
                                        </div>    
                                        <div>
                                            <button type="submit"  class="btn btn-primary"><i class="icon-plus-sign icon-white"></i> Actualizar</button>
                                            <a href="<s:url action="EliminarProductoCesta">
                                                   <s:param name="id" value="%{prod.id}" />
                                               </s:url>" 
                                               class="btn btn-danger"><i class="icon-remove icon-white"></i></a> 
                                            <h4 class="pull-right">Total: <s:property value="total"/> €</h4>
                                        </div>
                                    </s:form>
                                </div>
                            </div>
                            <hr>
                        </s:iterator>
                        <div class="row">
                     <!-- Dirección de envio  -->
                            <div class="span5">
                                <h3>Datos de envío</h3>
                                <label><b>Dirección de entrega</b> <a href="#" id="tooltip1" rel="popover" data-content="La dirección de entrega será el domicilio donde se entregue el pedido. Si desea enviarlo a otra dirección actualice los datos. (El cambio sólo afectará a la sesión actual)" data-original-title="Dirección de Envío"><i class="icon-info-sign"></i></a></label>

                                <s:if test="#session.logged == true">
                                    <s:if test="#session.direccion != null">
                                        <form action="ActualizarDatosEnvio" method="post">
                                            <input type="text" name="tipo" value="<s:property value="%{#session.direccion.tipo}" />" class="input-small" required rel="tooltip" title="Tipo de calle">
                                            <input type="text" name="nombre" value="<s:property value="%{#session.direccion.nombre}" />" class="input-medium" required rel="tooltip" title="Nombre">
                                            <input type="number" name="numero" value="<s:property value="%{#session.direccion.numero}" />" class="input-small" required min="1" rel="tooltip" title="Número">
                                            <input type="number" name="piso" value="<s:property value="%{#session.direccion.piso}" />" class="input-small" required min="1" rel="tooltip" title="Piso">
                                            <input type="text" name="puerta" value="<s:property value="%{#session.direccion.puerta}" />" class="input-small" required min="1" rel="tooltip" title="Puerta">
                                            <input type="text" name="localidad" value="<s:property value="%{#session.direccion.localidad}" />" class="input-medium" rel="tooltip" title="Localidad">
                                            <input type="text" name="provincia" value="<s:property value="%{#session.direccion.provincia}" />" class="input-medium" rel="tooltip" title="Provincia">
                                            <input type="text" name="codigopostal" value="<s:property value="%{#session.direccion.codigopostal}" />" class="input-medium" required pattern="[0-9]{5}" title="Escriba su C.P (5 dígitos)">
                                          
                                            <input type="text" name="pais" value="<s:property value="%{#session.direccion.pais}" />" class="input-medium" required rel="tooltip" title="País">
                                            
                                            <div>
                                                <input type="submit" class="btn btn-primary" value="Actualizar Datos">
                                            </div>  
                                        </form>
                                    </s:if>
                                    <s:else>
                                        <form action="ActualizarDatosEnvio" method="post">
                                            <input type="text" name="tipo" value="<s:property value="%{#session.cliente.direccion.tipo}" />" class="input-small" required rel="tooltip" title="Tipo de calle">
                                            <input type="text" name="nombre" value="<s:property value="%{#session.cliente.direccion.nombre}" />" class="input-medium" required rel="tooltip" title="Nombre">
                                            <input type="number" name="numero" value="<s:property value="%{#session.cliente.direccion.numero}" />" class="input-small" required min="1" rel="tooltip" title="Número">
                                            <input type="number" name="piso" value="<s:property value="%{#session.cliente.direccion.piso}" />" class="input-small" required min="1" rel="tooltip" title="Piso">
                                            <input type="number" name="puerta" value="<s:property value="%{#session.cliente.direccion.puerta}" />" class="input-small" required min="1" rel="tooltip" title="Puerta">
                                            <input type="text" name="localidad" value="<s:property value="%{#session.cliente.direccion.localidad}" />" class="input-medium" rel="tooltip" title="Localidad">
                                            <input type="text" name="provincia" value="<s:property value="%{#session.cliente.direccion.provincia}" />" class="input-medium" rel="tooltip" title="Provincia">
                                            <input type="text" name="codigopostal" value="<s:property value="%{#session.cliente.direccion.codigopostal}" />" class="input-medium" required pattern="[0-9]{5}" title="Escriba su C.P (5 dígitos)">
                                   
                                            <input type="text" name="pais" value="<s:property value="%{#session.cliente.direccion.pais}" />" class="input-medium" required rel="tooltip" title="País">
                                          
                                            <div>
                                                <input type="submit" class="btn" value="Actualizar Datos">
                                            </div>  
                                        </form>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <form action="ActualizarDatosEnvio" method="post">
                                        <input type="text" name="tipo" placeholder="Tipo" class="input-small">
                                        <input type="text" name="nombre" placeholder="nombre" class="input-medium">
                                        <input type="text" name="numero" placeholder="numero" class="input-small">
                                        <input type="text" name="piso" placeholder="piso" class="input-small">
                                        <input type="text" name="puerta" placeholder="puerta" class="input-small">
                                        <input type="text" name="localidad" placeholder="localidad" class="input-medium">
                                        <input type="text" name="provincia" placeholder="provincia" class="input-medium">
                                        <input type="text" name="codigopostal" placeholder="codigo postal" class="input-medium">
                                     
                                        <input type="text" name="pais" placeholder="pais" class="input-medium">
                                       
                                        <div>
                                            <input type="submit" class="btn" value="Actualizar Datos">
                                        </div> 
                                    </form>
                                </s:else>
                            </div>
                            <div class="span4">
                              <div class="span5">
                                  <p>
                                  <br>
                                <div class=" alert alert-error">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <p>Tras ver toda la información de su <strong>cesta</strong> de productos usted puede realizar el pedido.</p>
                                    <p>Para realizar su pedido con éxito pulse sobre 'Realizar Pedido'. Si en caso contrario quiere cancelar su pedido pusle sobre 'Deshacer Pedido'</p>
                                </div>
                            </div>
                              
                               
                            </div>
                        </div>
                            
                        <div class="row">
                            
                            <div class="span7">
                                <div class="pull-right">
                                    <h4>Precio Final del pedido:</h4><h2 class="">   <s:property value="%{#session.cesta.preciototal}" /> € </h2>
                                    <a href="<s:url action="CrearPedido"/>" class="btn btn-inverse"> Realizar Pedido</a> 
                                    <a href="<s:url action="DeshacerPedido"/>" class="btn btn-danger">Deshacer Pedido</a>
                                </div>
                            </div>
                        </div>
                    </s:if>
                    <s:else>
                           <!-- No cesta no pedidos -->
                        <div class="hero-unit">
                            <h2>Su cesta se encuentra vacía</h2>
                            <p>Pruebe con alguno de nuestros productos destacados</p>
                        </div>
                    </s:else>
                </div>
            </div>
        </div>
        <script>
            $('#tooltip1').popover({trigger: "hover"});
            $('#tooltip2').popover({trigger: "hover"});
              
              $(document).ready(function () {
                $("input").tooltip({
                  'selector': '',
                  'placement': 'bottom'
                });
              
              });
        </script>
    </body>
</html>