<%-- 
    Document   : verDetallesProductoAdmin
    Created on : 22-jun-2013, 17:47:12
    Author     : Jorge
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
﻿<!DOCTYPE HTML>
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
          <!-- mensajes información -->
        <s:if test="error !=null ">
            <div class="alert alert-error" style="position: absolute;top: 70px; right: 185px">  
                <a class="close" data-dismiss="alert">×</a>  
                <strong>Fallo!</strong>   <s:property value="error" />
            </div>  
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
        <div class=" alert alert-info">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    Para usar los filtros por defecto use los <strong>TAGS POR DEFECTO</strong>
                                    <p><strong>-SECCIONES:   </strong>SinDeterminar, Alimentacion, ProductosFrescos, Bebidas, Congelados, Farmacia, Mascotas, Cocina, Hogar, AparatosCocina, Otros, Televisiones, Informatica, Videojuegos y Telefonia.</p>
                                    <p><strong>-CATEGORIAS:   </strong>[Aperitivos, Conservas, Chucherias, PastaArroz] [Panaderia, Pescaderia, Carniceria, Fruteria] [Refrescos, Alcohol, Zumo] [Helados, ParaFreir, CongeladosVarios] [HigieneDental, Botiquin, ComplementosAlimenticios] [AlimentacionMascotas, HigieneMascotas, AccesoriosMascotas] [Congeladores, Hornos, Lavavajillas, Microondas] [Aspiradoras, Planchas, Calefactores] [Batidoras, Freidoras, Cafeteras] [Lavadoras, Frigorificos, AireAcondicionado] [LCD, LED, Plasma] [PCSobremesa, Portatil, Accesorios] [Consolas, PS3, Wii] [MovilesLibres, NavegadoresGPS] [Sin Categoría].
                                    </p>
                                    <strong style="color: greenyellow"> Formato imágenes: jpg y png, cualquier otra imágen será ignorada.</strong></div>
        <div class="container">
           <s:form id="tab" action="ActualizarProducto" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="span4">
                    <h2>
                          <input type="text" name="nombre" style ="font-weight:bold;" value="<s:property value="%{#request.productoDetallado.nombre}"/>"</h2>
                    <a href="#" class="thumbnail">
                        <s:if test="%{#request.productoDetallado.imagen}">
                            <img src="img/uploads/<s:property value="%{#request.productoDetallado.id}"/>.jpg" alt="ALT NAME" />
                        </s:if>
                        <s:else>
                            <img src="img/null.jpg" alt="ALT NAME"/>
                        </s:else>
                    </a>
                  
                </div>
                    
                <div class="span6">
                    
                    <h2>Detalles del producto</h2>
                    <div class="row-fluid">
                          <div class="span6">
                <span class="label label-important">ID</span>
                    <h4>
                         <input type="text" name="id" value="<s:property value="%{#request.productoDetallado.id}"/>" class readonly>
                    </h4>
                      <label><b>Sección</b> <a href="#" id="tooltip1" rel="popover" data-content="Ingrese alguna de las secciones disponibles" data-original-title="Sección"><i class="icon-info-sign"></i></a></label>

                    <h4>
                       <input type="text" name="seccion" value="<s:property value="%{#request.productoDetallado.seccion}"/>"> 
                    </h4>
                      <label><b>Categoría</b> <a href="#" id="tooltip2" rel="popover" data-content="Ingrese alguna de las categorías disponibles" data-original-title="Categoría"><i class="icon-info-sign"></i></a></label>

                    <h4>
                       <input type="text" name="categoria" value="<s:property value="%{#request.productoDetallado.categoria}"/>"> 
                    </h4>
                    <span class="label label-inverse">Precio</span>
                    <h4>
                        <input type="text" name="precio" value="<s:property value="%{#request.productoDetallado.precio}"/>"class="input-small">€
                    </h4>
                             </div>
                    
                    
                    
                     <div class="span6">
               <span class="label label-inverse">País de Origen</span>
                    <h4>
                       <input type="text" name="pais" value="<s:property value="%{#request.productoDetallado.paisOrigen}"/>">
                    </h4>
                    
                     <span class="label label-inverse">Oferta</span>
                     
                    <h4>
                         <s:if test="#request.productoDetallado.oferta == true">
                               <input type="checkbox" name="oferta" value="1" checked> 
                      </s:if>
                           <s:if test="#request.productoDetallado.oferta == false">
                                <input type="checkbox" name="oferta" value="0"> 
                      </s:if>
                        
                    </h4>
                    
                     <span class="label label-inverse">Destacado</span>
                    <h4>
                         <s:if test="#request.productoDetallado.destacado == true">
                              <input type="checkbox" name="destacado" value="1" checked> 
                      </s:if>
                           <s:if test="#request.productoDetallado.destacado == false">
                               <input type="checkbox" name="destacado" value="0"> 
                      </s:if>
                    </h4>
                     <span class="label label-inverse">Visible</span>
                     
                    <h4>
                         <s:if test="#request.productoDetallado.visible == true">
                                <input type="checkbox" name="visible" value="1" checked> 
                      </s:if>
                           <s:if test="#request.productoDetallado.visible == false">
                                  <input type="checkbox" name="visible" value="0"> 
                      </s:if>
                    </h4>
                    </div>
        </div>
                    
                    
                    
                </div>
                    <div class="span2">
                    <h2>  
                        <input type="submit" class="btn btn-primary" value="Editar Producto">
                    </h2>
                </div>
            </div>
          
            <div class="row">
                <div class="span12">
                    <h2>
                     <input type="file" name="image"  size="40" />
                    </h2>
                 

                    <h2>Descripción del Producto</h2>
                    <p>
                        <TEXTAREA NAME="descripcion" COLS=50 ROWS=4 style ="width: 800px"><s:property value="%{#request.productoDetallado.descripcion}"/></TEXTAREA>
                        <s:if test="request.productoDetallado.descripcion ==null">             
                            <strong>Producto sin descripción</strong>
                        </s:if>
                    </p>
                   
                </div>
            </div>
     </s:form>
        </div>
                            
                            
  
    <script src="js/bootbox.min.js"></script>
    <script type="text/javascript">

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