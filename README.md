# VuelosGlobales
#### Desarrollado por Santiago Sandoval Torres
+ Desarrollado en JAVA 8 
+ Base de datos relacional MySQL 
+ Vistas -> Salida en terminal

+ Arquitecturas: 
    + Para el desarrollo óptimo de esta aplicación se decidió trabajar con vertical slice y arquitectura hexagonal ¿Cómo se implementó? Basicamente tenemos **3** grandes paquetes que son Flight Plane y User, cada uno contiene carpetas con entidades que permiten hacer frente a los casos de uso.
    + Para cada entidad, se usa la arquitectura de puertos y adaptadores, esto, separando las funcionalidades y responsabilidades a través de los paquetes Domain[Models, Ports], Application[Services], Infrastructure[Controllers,Repositories]
    + ### ¿Cómo se comunican entre sí?
        + El beneficio de el uso de estas arquitecturas, nos permite separar en una capa muy separada del resto a las entidades, se definen y apartir de allí fluye todo. Las clases que están en el -core- de la funcionalidad, realizan sus contratos a través de los puertos de entrada, los cuales, definen qué información y/o acciones se necesitan del exterior -DATABASES u otro servicio-. Allí aparecen los ports de salida, que son las firmas de cómo se va a conseguir esta información y es directamente el que se encarga de la comunicación y solicitudes con los servicios externos. 

+ Login
    + Identificacion de Rol
        + Menus / Interacciones correspondientes a su rol
        