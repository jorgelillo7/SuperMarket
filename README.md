# SuperMarket
Proyecto fin de grado 2012-2013, Universidad de Alcalá de Henares, Jorge Lillo Cobacho

SuperMarket es una aplicación básica que simula la web de cualquier supermercado actual. En ella, se muestra mediante una serie de filtros o por el buscador de la barra de navegación los distintos productos que ofrece. Además, estos productos se pueden agregar a la cesta de la compra y procesar el pedido.
Para poder cerrar el pedido, debemos “loguearnos” en la aplicación. En caso de que no se tenga usuario, se podrá registrar sin problema.
Al margen de los clientes, existen los administradores. Al iniciar sesión les trasladará a su módulo correspondiente dónde podrán gestionar los productos, los pedidos de los usuarios y a los propios usuarios.

Probado en:
- Postgresql 9.1
- Netbeans 7.2 (probado con JDK 1.6)
- Librerias NetBeans: Struts 2.3.4 Core Libraries y PostgreSQL JDBC Driver (Para más información, consulte el Anexo 2 del pdf de la memoria -> /docs/TFG Struts2.pdf)
- Servidor Apache Tomcat 7.0.27

Al entrar nos encontramos con:
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/1-%20index.PNG)

Podemos realizar búsquedas de productos desde la barra superior, o seleccionando alguna de las categorías y subcategorías de la barra lateral:

![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/2-%20busqueda_productos.PNG)


Los productos que deseemos podemos ir añadiendolos a la cesta:
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/3-%20cesta.PNG)


Pero para comprar debemos iniciar sesión: (si no estamos registrados, debemos registrarnos antes)
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/4-%20pedido.PNG)


Podemos consultar el estado de nuestros pedidos en la pantalla de "Mis pedidos":
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/5-%20mis_pedidos.PNG)


Desde el apartado "Mi cuenta" se pueden modificar los datos del usuario:

![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/6-%20mi_cuenta.PNG)


Los administradores se encarga de suministrar el contenido y controlar la web:

![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/7-%20admin.PNG)


- Crud de usuario:
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/8-%20admin_clientes.PNG)


- Gestión de productos:
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/9-%20admin_gesti%C3%B3n_productos.PNG)
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/10-%20admin_nuevo_producto.PNG)


- Gestión de pedidos:
![alt tag](https://github.com/jorgelillo7/SuperMarket/blob/master/docs/img/11-admin-pedidos.PNG)


*Al tratarse de una web prototipo es el propio administrador el encargado de cambiar el estado del producto, esta tarea en producción sería delegada en un servicio de terceros o personal encargado de certificar cada fase.*





