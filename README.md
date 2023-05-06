# BIOTEST
## ¿Que es BioTest?
Biotest es un proyecto, conformado por una aplicacion de escritorio, 
una aplicacion de android, una base de datos y un servidor PHP el cual permite a sus
usuarios aprender biologia jugando
## Funciones
### Aplicacion de android
   - Editar sus datos
   - Seleccionar un modo de juego
   - Jugar
   - Ver los resultados de una partida
   - Cerrar sesion
## Puesta en Marcha
## Base de datos
### Descargar xampp
Para poder utilizar este proyecto, es necesario descargar XAMPP. Puede descargar XAMPP desde la [página oficial de descarga](https://www.apachefriends.org/download.html).
## Importación de la base de datos

Dirijase a la siguiente ruta:

`./java/com.example.proyectofinalandroid/database/preguntas.sql`

Para crear la base de datos dirijase a la consola de comandos y escriba la siguiente sentencia:

```sql
CREATE DATABASE preguntas;
````
Ahora para importar el script con el contenido de la base de datos escriba el siguiente comando

```bash
mysql -u usuario -p base_de_datos < /ruta/a/mi/archivo.sql
````
### Configuracion
Para configurar el proyecto dirigase al siguiente archivo:

`.java/com.example.proyectofinalandroid/controller/baseDeDatos/constantes.java`

Sustituya la url del servidor en caso de que su servidor este en otra direccion
```java
String SERVER = "http://192.168.171.147/webserviceProyecto/";
````
Despues sustituya el email por el email desde el cual se van a mandar los correos
```java
String  EMAIL = "BioTestProyecto@gmail.com";
````











