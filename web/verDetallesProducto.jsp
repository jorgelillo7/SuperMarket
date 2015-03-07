<%-- 
    Document   : verDetallesProducto
    Created on : 13-jun-2013, 11:47:17
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
            $(function() {
                $('#myCarousel').carousel({
                    interval: 4000
                });
            });

            window.setTimeout(function() {
                $(".alert").fadeTo(500, 0).slideUp(500, function() {
                    $(this).remove();
                });
            }, 2000);
        </script>

    </head>
    <body>
          <!-- mensajes información -->
        <s:if test="error !=null ">
            <div class="alert alert-error" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong>Fallo!</strong>   <s:property value="error" />
            </div>  
        </s:if>
            <!-- gestión login -->
        <s:set name="conectado" value="false"/>
        <s:if test="#session.logged == true">
            <s:set name="conectado" value="true"/>
        </s:if>
        
        <s:if test="#session.user.privilegios == 1">
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

                    <!-- Barra buscar -->
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

      
        <!-- Filtros -->
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
                        <div class="span9">
 
                            <div class="row">
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                <s:if test="#request.producto.imagen ">
                                    <img src="img/uploads/<s:property value="#request.producto.id"/>.jpg" alt="ALT NAME" />
                                </s:if>
                                <s:else>
                                    <img src="img/null.jpg" alt="ALT NAME" />
                                </s:else>
                            </a>
                        </div>
                        <div class="span6">      
                            <h4><strong><a href="#"><s:property value="#request.producto.nombre" /></a></strong></h4>
                           
                            <div>    
                                <h2><s:property value="#request.producto.precio" />€</h2>
                            </div>
                            <s:form action="AnadirProductoCesta" method="post">  
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <i class=" icon-shopping-cart"></i>
                                    </span>
                                    <input style="width: 20px"type="text" value="1" id="" name="cantidad">
                                </div>

                                <button type="submit" class="btn btn-primary pull-right">Comprar</button>
                                <input name="id" type="hidden" value="<s:property value="#request.producto.id" />">

                            </s:form>
                        </div>
                    </div>
                    <hr>
                    <div class="row">

                        <div class="span2">      
                            <span class="label label-important">ID</span>
                            <p><s:property value="%{#request.producto.id}"/></p>
                        </div>
                        <div class="span2">      
                            <span class="label label-inverse">Seccion</span>
                            <p><s:property value="#request.producto.seccion" /></p>
                        </div>
                        <div class="span2">      
                            <span class="label label-inverse">Categoría</span>
                            <p><s:property value="#request.producto.categoria" /></p>
                        </div>
                        <div class="span2">      
                            <span class="label label-inverse">País</span>
                            <p><s:property value="#request.producto.paisOrigen" /></p>
                        </div>
                    </div>
                        <div class="row">
                            <div class="span2">      
                                 <span class="label label-inverse">Oferta</span>
                                  <s:if test="#request.producto.oferta == true">
                               <p>SI</p>
                      </s:if>
                               <s:else>
                                   <p>NO</p>
                               </s:else>
                               
                                
                            </div>
                            <div class="span2">      
                                 <span class="label label-inverse">Destacado</span>
                                  <s:if test="#request.producto.destacado == true">
                               <p>SI</p>
                      </s:if>
                               <s:else>
                                   <p>NO</p>
                               </s:else>
                               
                            </div>
                            
                        </div>
                        </div>
                        <div class="span9">      

        

                            <h2>Descripción del Producto</h2>
                             <p>
                                <s:property value="#request.producto.descripcion" />
                            </p>


                        </div>
                    </div>


                </div>
            </div>
        </div>

    </body>
</html>