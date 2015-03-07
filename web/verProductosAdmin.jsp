<%-- 
    Document   : verProductosAdmin
    Created on : 18-jun-2013, 19:40:14
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
          <!-- gestion login -->
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
         
            <div class="row">
                <h1>Productos</h1>

                <s:if test="#request.listaProductos.size()>0">

                <table class="table table-striped table-condensed">
                    <thead>

                    <th>Nombre</th>
                    <th>Sección</th> 
                    <th>Categoría</th> 
                    <th>Precio</th>  
                    <th>Pais</th> 
                    <th>Oferta</th>
                    <th>Destacado</th>
                    <th>Acciones</th>  				  

                    </thead>   
                    <tbody>
                        <s:iterator value="#request.listaProductos" var="producto">
                            <tr>
                                <s:hidden value="id" />
                                <td><s:property value="nombre"/></td>
                                <td><s:property value="seccion"/></td>
                                <td> <s:property value="categoria"/></td>
                                <td><s:property value="precio"/>€</td>
                                <td> <s:property value="paisOrigen"/></td>
                                <s:if test="oferta == false">
                                     <td> No</td>
                                   </s:if>
                                <s:if test="oferta == true">
                                     <td> Si</td>
                                   </s:if>
                               
                                     <s:if test="destacado == false">
                                     <td> No</td>
                                   </s:if>
                                <s:if test="destacado == true">
                                     <td> Si</td>
                                   </s:if>
                                <td>
                                    <a href="<s:url action="VerDetallesProducto"><s:param name="idPro" value="%{id}" ></s:param></s:url>" class="btn"><strong>Detalles</strong></a> 
                                   <!-- <a href="<s:url action="VerDetallesProducto"><s:param name="idPro" value="%{id}" ></s:param></s:url>" class="btn"><i class="icon-pencil"></i> <strong>Editar</strong></a> -->
                                      <td>
                                        <a href="#" onclick="eliminar('<s:property value="id"/>')" class="btn btn-danger"><i class="icon-trash"></i> <strong></strong></a>
                                    <s:if test="%{visible}">
                                        <a href="#" onclick="confirmar('<s:property value="id"/>')" class="btn btn-danger"><i class="icon-remove"></i> <strong></strong></a>

                                    </s:if>
                                    <s:else>
                                        <a href="#" onclick="confirmar('<s:property value="id"/>')" class="btn btn-success"><i class="icon-plus-sign"></i> <strong></strong></a>
                                    </s:else>
                                </td> 					
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                </s:if>
                <s:else>
                    <h4>Todavía no se han creado Productos</h4>
                </s:else>
            </div>
 
            <div class="row">
                    <s:form action="AnadirProducto" method="POST" enctype="multipart/form-data">
            


                    <h1>Creación de nuevo Producto</h1>

                    <div class="span4">

                        <label>Nombre de Producto</label>
                        <input type="text" name="nombre" class="span3" placeholder="Nombre de Producto" required>
                        <label>Sección</label>
                           
                        <select name=seccion size=2 id="seccionCombo" onchange="change()">
                          <option name=SinDeterminar value=SinDeterminar selected> Sin Determinar </option>
                          <option name=Alimentacion value=Alimentacion> Alimentación </option>
                          <option name=ProductosFrescos value=ProductosFrescos> Productos Frescos </option>
                          <option name=Bebidas value=Bebidas> Bebidas </option>
                          <option name=Congelados value=Congelados> Congelados </option>
                          <option name=Farmacia value=Farmacia> Farmacia </option>
                          <option name=Mascotas value=Mascotas> Mascotas </option>
                          <option name=Cocina value=Cocina> Cocina </option>
                          <option name=Hogar value=Hogar> Hogar </option>
                          <option name=AparatosCocina value=AparatosCocina> Aparatos de Cocina </option>
                          <option name=Otros value=Otros> Otros </option>
                          <option name=Televisiones value=Televisiones> Televisiones </option>
                          <option name=Informatica value=Informatica> Informática </option>
                          <option name=Videojuegos value=Videojuegos> Videojuegos </option>
                          <option name=Telefonia value=Telefonia> Telefonía </option>
                         
                        
                        </select>
                        
                        
                        <label>Categoría</label>
                          <select name=categoria size=2 id="categoriaCombo" required>
                            <option name=SinCategoria value=SinCategoria selected> Sin Categoría </option>
                        
                        </select>
                        
                        <hr/>
                        <div style="color: red"><s:fielderror /></div>
                        <label>Precio</label>
                        <input type="text" name="precio" class="span3" placeholder="Precio" required >
                        
                        <label> Oferta   &nbsp&nbsp 
                            <input type="hidden" name="oferta" value="0">
                            <input type="checkbox" name="oferta" value="1"> 
                       
                        
                        &nbsp&nbsp Destacado   &nbsp&nbsp
                            <input type="hidden" name="destacado" value="0">
                            <input type="checkbox" name="destacado" value="1"> 
                        </label>
                        
                        <label> Visible   &nbsp&nbsp 
                            <input type="hidden" name="visible" value="0">
                            <input type="checkbox" name="visible" value="1"> 
                        </label>

                    </div>
                    <div class="span4">

                        <label>Pais</label>
                        <input type="text" name="pais" class="span3" placeholder="Pais de Orígen" required>
                       
                        <label>Descripción</label>
                        <textarea name="descripcion" class="span4" rows="6" placeholder="Descripción del producto" required></textarea>
                        <input type="file" name="fileUpload"  size="40" />
                        <input type="submit" class="btn btn-primary"  value="Añadir Producto">
                  

                    </div>
                    <div class="span4">
                        <div class="alert alert-info">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <p><strong>Creación de Productos</strong></p>
                            <p>Recuerde que el precio del producto está en Euros y si tiene decimales
                                debe de expresarlo con punto y no con coma
                            <p><strong>Ejemplo:</strong> 1.2</p>
                            <strong style="color: greenyellow"> Formato imágenes: jpg y png, cualquier otra imágen será ignorada.</strong>
                        </div>


                    </div>
               </s:form>

            </div>



           

        </div>
    </div>





    <br>
    <div class="container">
        <footer>
            Copyright @Supermarket 2013
             <div id="selected" hidden>Selected Gate is:</div>
        </footer>
    </div>

   


 
    <script src="js/bootbox.min.js"></script>
    <script type="text/javascript">

    function confirmar(e)
    {
        bootbox.confirm("¿Estas seguro que desea cambiar la visibilidad de este producto?", function(confirmed) {
            if (confirmed)
            {
                
                $.ajax({
                    type: "GET",
                    url: 'MostrarProductos.action?id=' + e,
                    data: {},
                    dataType: "html",
                    success: function()
                    {
                        location.href="VerProductosAdmin.action"
                    }
                 });
            }
        });
    }
    function eliminar(e)
    {
        bootbox.confirm("¿Seguro que desea eliminar este producto?", function(confirmed) {
            if (confirmed)
            {
                
                $.ajax({
                    type: "GET",
                    url: 'BorrarProducto.action?id=' + e,
                    data: {},
                    dataType: "html",
                    success: function()
                    {
                        location.href="VerProductosAdmin.action";
                        
                    }
                 });

            }
        });
    }
    
    function change()
{
    var e = document.getElementById("seccionCombo");//get the combobox
    var selGate = e.options[e.selectedIndex].value;//get selected value
    //you can also do use                  ^.text =>to get the text instead
    document.getElementById("selected").innerHTML = "Selected Gate is: "+selGate;
                                     //^^ set the text to the selected value
  if (selGate == "Alimentacion"){
   var myobject = {
    Aperitivos : 'Aperitivos',
    Conservas : 'Conservas',
    Chucherias : 'Chucherias varias',
    PastaArroz : 'Pasta y Arroz'
    };
  }
  
   if (selGate == "ProductosFrescos"){
   var myobject = {
    Panaderia : 'Panadería',
    Pescaderia : 'Pescadería',
    Carniceria : 'Carnicería',
    Fruteria : 'Frutería'
    };
  }
  
   if (selGate == "Bebidas"){
   var myobject = {
    Refrescos : 'Refrescos',
    Alcohol : 'Alcohol',
    Zumo : 'Zumo'
    };
  }
  
     if (selGate == "Congelados"){
   var myobject = {
    Helados : 'Helados',
    ParaFreir : 'Para Freir',
    CongeladosVarios : 'Congelados Varios'
    };
  }
  
     if (selGate == "Farmacia"){
   var myobject = {
    HigieneDental : 'Higiene Dental',
    Botiquin : 'Botiquín',
    ComplementosAlimenticios : 'Complementos Alimenticios'
    };
  }
  
    if (selGate == "Mascotas"){
   var myobject = {
    AlimentacionMascotas : 'Alimentación',
    HigieneMascotas : 'Higiene',
    AccesoriosMascotas : 'Accesorios'
    };
  }
  
  if (selGate == "Cocina"){
   var myobject = {
    Congeladores : 'Congeladores',
    Hornos : 'Hornos',
    Lavavajillas : 'Lavavajillas',
    Microondas: 'Microondas'
    
    };
  }
  
   if (selGate == "Hogar"){
   var myobject = {
    Aspiradoras : 'Aspiradoras',
    Planchas : 'Planchas',
    Calefactores : 'Calefactores'
    };
  }
  
   if (selGate == "Hogar"){
   var myobject = {
    Aspiradoras : 'Aspiradoras',
    Planchas : 'Planchas',
    Calefactores : 'Calefactores'
    };
  }
  
   if (selGate == "AparatosCocina"){
   var myobject = {
    Batidoras : 'Batidoras',
    Freidoras : 'Freidoras',
    Cafeteras : 'Cafeteras'
    };
  }
  
   if (selGate == "Otros"){
   var myobject = {
    Lavadoras : 'Lavadoras',
    Frigorificos : 'Frigoríficos',
    AireAcondicionado : 'Aire Acondicionado'
    };
  }
  
  if (selGate == "Televisiones"){
   var myobject = {
    LCD : 'LCD',
    LED : 'LED',
    Plasma : 'Plasma'
    };
  }
  
   if (selGate == "Informatica"){
   var myobject = {
    PCSobremesa : 'PC Sobremesa',
    Portatil : 'PC portátil',
    Accesorios : 'Accesorios'
    };
  }
  
   if (selGate == "Videojuegos"){
   var myobject = {
    Consolas : 'Consolas',
    PS3 : 'PS3',
    Wii : 'Wii'
    };
  }
  
   if (selGate == "Telefonia"){
   var myobject = {
    MovilesLibres : 'Móviles Libres',
    NavegadoresGPS : 'Navegadores GPS'
    };
  }
  if (selGate == "SinDeterminar"){
   var myobject = {
    SinCategoria : 'Sin Categoría'
    };
  }
  
  
  
  
var select = document.getElementById("categoriaCombo");
select.options.length = 0;
for(index in myobject) {
    select.options[select.options.length] = new Option(myobject[index], index);
}


}
    </script>
</body>
</html>
