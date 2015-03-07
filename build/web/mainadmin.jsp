<%-- 
    Document   : mainadmin
    Created on : 01-jun-2013, 13:40:31
    Author     : Jorge
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title> SuperMarket  </title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/estilos.css">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
    </head>
    <body>
           <!-- Control conexión, no tiene privilegios nos vamos a la página básica -->
        <s:set name="conectado" value="false"/>
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

                    <!-- Barra buscar -->

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

        <!-- División pantalla -->
        <div class="container">
            <div class="row">
                <!-- Zona izquierda  -->
                <div class="span8">
                    <div class="hero-unit">
                      
                        <img src="img/adminpc.png" height="200" width="700" alt="">
                        <p>
                        <p>
                        <center>
                            <a href="<s:url action="EstadisticasPedidos"></s:url>" class="btn btn-primary btn-large" >
                                Ver Estadisticas Pedidos
                            </a>
                        </center>
                        </p>
                    </div>
                </div>
               <!-- Zona derecha estadísticas -->
                <div class="span4">
                    <h4>Estadísticas pedidos:</h4>
                    <ul class="unstyled">
                          <s:if test="#session.pedidosTotales == null">
                              Sin datos, por favor refreque estadísticas.
                          </s:if>
                          <s:else>
                           Última actualización: <s:property value="#session.actualizacionFecha" />   
                        <p>
                        <li>Pedidos Totales<span class="pull-right strong"><s:property value="#session.pedidosTotales" /></span>
                            <div class="progress progress-important">
                                <div class="bar" style="width: 100%;"></div>
                            </div>
                        </li>
                        <li>Sin Enviar<span class="pull-right strong"><s:property value="#session.pedidosSinEnviar" /></span>
                            <div class="progress progress-danger">
                                <div class="bar" style="width: <s:property value="#session.porcentajeSinEnviar" />"></div>
                            </div>
                        </li>
                        <li>En proceso<span class="pull-right strong"><s:property value="#session.pedidosEnProceso" /></span>
                            <div class="progress progress-warning">
                                <div class="bar" style="width: <s:property value="#session.porcentajeEnProceso" />"></div>
                            </div>
                        </li>
                        <li>Entregado<span class="pull-right strong"><s:property value="#session.pedidosEntregados" /></span>
                            <div class="progress progress-success">
                                <div class="bar" style="width: <s:property value="#session.porcentajeEntregados" />"></div>
                            </div>
                        </li>
                          </s:else>
                    </ul>
                    <br>
                </div>
            </div>
            <!-- Zona banners publicidad -->                    
            <div class="row" id="slider-thumbs">
                <div class="span12">
                    
                    <ul class="thumbnails">
                        <li class="span2">
                            <a class="thumbnail" id="carousel-selector-0">
                                <img src="img/baquia.jpg" />
                            </a>
                        </li>
                        
                        <li class="span2">
                            <a class="thumbnail" id="carousel-selector-0">
                                <img src="img/elmundo.png" />
                            </a>
                        </li>
                        
                        <li class="span2">
                            <a class="thumbnail" id="carousel-selector-0">
                                <img src="img/ps4.jpg" />
                            </a>
                        </li>
                        <li class="span2">
                            <a class="thumbnail" id="carousel-selector-0">
                                <img src="img/gh.png" />
                            </a>
                        </li>
                        
                        <li class="span2">
                            <a class="thumbnail" id="carousel-selector-0">
                                <img src="img/android.jpg" />
                            </a>
                        </li>
                        <li class="span2">
                            <a class="thumbnail" id="carousel-selector-0">
                                <img src="img/ios6.jpg" />
                            </a>
                        </li>
                    </ul>
                </div>
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