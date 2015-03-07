<%-- 
    Document   : perfil
    Created on : 02-jun-2013, 19:23:44
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
         <s:if test="exito !=null ">
            <div class="alert alert-success" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong>Correcto!</strong>   <s:property value="exito" />
            </div>  
        </s:if>
          
           <!-- gestión login -->
          <s:set name="conectado" value="false"/>
        <s:if test="#session.logged == true">
            <s:set name="conectado" value="true"/>
        </s:if>
        
        <s:if test="#session.cliente.privilegios == 1">
               <jsp:forward page="mainadmin.jsp" />
        </s:if>
   

        <!-- Barra de Arriba -->
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

                    <!-- Barra Buscar -->
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


      
        <!--Filtros -->
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
                            <div class="well">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#home" data-toggle="tab">Información Básica</a></li>
                                    <li><a href="#profile" data-toggle="tab">Actualizar Contraseña</a></li>
                                </ul>
                                <div id="myTabContent" class="tab-content ">
                                    <div class="tab-pane active in" id="home" >
                                <s:form id="tab" action="ActualizarPerfil" method="post">
                                    <s:if test="#session.logged == true">
                                        <div class="span3">

                                            <label>
                                                <b>Datos Personales </b><a href="#" id="tooltip1" rel="popover" data-content="El nombre de usuario no puede ser editado" data-original-title="Username"><i class="icon-info-sign"></i></a></label>
                                            </label>
                                            <input type="hidden" name ="id" value="<s:property value="%{#session.user.id}"/>" class="input-xlarge">     

                                            <label>Nombre de Usuario</label>
                                            <input type="text" name="user" value="<s:property value="%{#session.cliente.username}"/>" class="input-xlarge" readonly>     
                                            <label>Nombre</label>
                                            <input type="text" name="nombre" value="<s:property value="%{#session.cliente.nombre}"/>"  class="input-xlarge" required>
                                            <label>Apellido</label>
                                            <input type="text" name="apellido" value="<s:property value="%{#session.cliente.apellido}"/>"  class="input-xlarge" required>
                                            <label>Email</label>
                                            <input type="email" name="email" value="<s:property value="%{#session.cliente.email}"/>" class="input-xlarge" required>
                                            <label>Número de contacto</label>
                                            <input type="text" name="numcont" value="<s:property value="%{#session.cliente.telefono}"/>" class="input-xlarge" required pattern="[0-9]{9}" title="Escriba su teléfono (9 dígitos)">
                                              
                                            <label>Tarjeta de Crédito</label>
                                            <input type="text" name="datosb" value="<s:property value="%{#session.cliente.numerotarjeta}"/>"  class="input-xlarge" required>
                                            <div>&nbsp</div>
                                        <div class="pull-right">
                                            <input type="submit" class="btn btn-primary" value="Actualizar Información">
                                        </div>
                                        </div>
                                            
                                        <div class="span3 offset1">
                                            <label>
                                                <b>Dirección de entrega </b><a href="#" id="tooltip2" rel="popover" data-content="Por defecto se tomará esta dirección como referencia para el envío de pedidos" data-original-title="Dirección"><i class="icon-info-sign"></i></a>
                                            </label>
                                            </label>
                                            <label>Tipo de vía</label>
                                            <input type="text" name="tipo" value="<s:property value="%{#session.direccion.tipo}"/>"  class="input-xlarge" required>
                                            <label>Nombre</label>
                                            <input type="text" name="nombrecalle" value="<s:property value="%{#session.direccion.nombre}"/>"  class="input-xlarge" required>
                                            <label>Número  Piso   &nbsp Puerta  </label>
                                            <input type="number" name="numero" value="<s:property value="%{#session.direccion.numero}"/>"  class="input-medium" style="width: 35px" required min="1" title="Tiene que ser un número">
                                            <input type="number" name="piso" value="<s:property value="%{#session.direccion.piso}"/>"  class="input-medium" style="width: 35px" required min="1" title="Tiene que ser un número">
                                            <input type="text" name="letra" value="<s:property value="%{#session.direccion.puerta}"/>"  class="input-medium" style="width: 30px" required min="1">
                                         
                                            <label>Localidad &nbsp &nbsp &nbsp &nbsp &nbsp&nbsp Provincia</label>

                                            <input type="text" name ="localidad" value="<s:property value="%{#session.direccion.localidad}"/>"  class="input-medium" style="width: 90px">


                                            <input type="text" name ="prov" value="<s:property value="%{#session.direccion.provincia}"/>"  class="input-medium" style="width: 90px">


                                            <label>Pais &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Codigo Postal</label>
                                          

                                         
                                            <input type="text" name ="pais" value="<s:property value="%{#session.direccion.pais}"/>"  class="input-medium" style="width: 90px" required>
                                           
                                            <input type="text" name ="cp" value="<s:property value="%{#session.direccion.codigopostal}"/>"  class="input-medium" style="width: 60px" required pattern="[0-9]{5}" title="Escriba su C.P (5 dígitos)">

                                     
                                        </div>
                                       
                                    </s:if>
                                </s:form> 
                            </div>
                          <!-- tab2-->
                            <div class="tab-pane fade" id="profile">
                                <s:form id="tab2" action="CambioPassword" method="post">
                                    <s:if test="#session.logged == true">
                                        <input type="hidden" name ="id" value="<s:property value="%{#session.user.id}" />" class="input-xlarge">     

                                        <label>Contraseña Actual</label>
                                        <input type="password" name="oldPass" class="input-xlarge">
                                        <label>Nueva Contraseña</label>
                                        <input id="password" type="password"  name="newPass1" class="input-xlarge" required>
                                        <label>Repetir Nueva Contraseña</label>
                                        <input id="repeatpasword" type="password"  name="newPass2" class="input-xlarge" required>
                                        <div>
                                            <input type="submit" class="btn btn-primary" value="Actualizar">

                                        </div>

                                    </s:if>
                                </s:form> 

                                <script>
                                    function check() {
                                        var elemento = document.getElementById('password');
                                        if (document.getElementById('repeatpassword').value != document.getElementById('password').value) {
                                            elemento.setCustomValidity('Las dos contraseñas deben ser idénticas');
                                        }
                                    }

                                </script>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="container">
            <footer>
                Copyright @SuperMarket 2013
            </footer>
        </div>
        <script>
            $('#tooltip1').popover({trigger: "hover"});
            $('#tooltip2').popover({trigger: "hover"});
        </script>
    </body>
</html>