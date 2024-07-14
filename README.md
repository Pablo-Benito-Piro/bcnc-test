# Prueba BCNC Group - Pablo Benito Piró

# Ejecución

He utilizado el IDE IntelliJ por lo que explicaré su ejecución desde este IDE.
Para ejecutar el proyecto se puede hacer de diferentes maneras, explicaré 2:
La primera entrando dentro de la clase 'BcncTestApplication.kt' y en la clase se encontrará el método main con una
flecha Verde, lo pulsamos y nos saldrá un desplegable y seleccionamos la opción Run y el nombre de la clase.

IMPORTANTE: no tener levantado nada en el puerto 8080 debido a que usa ese puerto para levantarse.
![img_2.png](img_2.png)

![img_3.png](img_3.png)

La otra manera es crear una Run/Debug Configuration, si pinchamos en la flecha al lado del nombre nos saldrá un
desplegable

![img_4.png](img_4.png)

![img_5.png](img_5.png)

Desde ahí podremos elegir una configuración ya existente o crear una nueva para crear una seleccionaremos la marcada en
azul

![img_6.png](img_6.png)

Seleccionaremos la opción Spring boot, se tendrá que rellenar los huecos como la imagen, en este creacion tambien se
podrá  añadir perfiles como por ejemplo (local, develop) pero en este caso no es necesario.

![img_4.png](img_4.png)

Una vez creado, seleccionamos la flecha verde y se ejecutará, y en la consola se abrirá y saldrán estos mensajes

![img_7.png](img_7.png)

Una vez levantado podremos lanzar peticiones a la api para ello podremos utilizar cualquier navegador o Postman un
software que facilita la visualización de las respuestas y el lanzamiento de peticiones.

Actualmente, la Api tiene 3 endpoint para Photos:

Devuelve todas las fotos del Json PLaceHolder

- http://localhost:8080/photo

Devuelve todas las fotos con ID que pongas (en este caso Id 1)

- http://localhost:8080/photo/1

Devuelve todas las fotos correspondientes al Id del Album (en este caso AlbumId 1)

- http://localhost:8080/photo/album/1

Y 4 endpoint para Album:

Devuelve todos los álbumes

- http://localhost:8080/album

Devuelve todos los álbumes con sus fotos correspondientes a cada álbum en caso de ser true, si es falso solo devuelve
los álbumes

- http://localhost:8080/album/all/true

Devuelve todos los álbumes con ID que pongas (en este caso Id 1)

- http://localhost:8080/album/1

Devuelve todos los álbumes del usuario con ID que pongas (en este caso Id 1)

- http://localhost:8080/album/user/1

Explicación:
Aunque puede llegar a tardar la devolución de los datos, decidí crear el endpoint (http://localhost:8080/album/all/true)
que recibe todos los álbumes con sus respectivas fotos
por si se quiere hacer un listado, una paginación o recibir todos los datos para actualizar una posible base de datos

Al principio, los endpoint de Photo y Album pensaba dejarlas las rutas de la siguiente manera debido a que me parecía más
adecuada y más coherente, pero debido a problemas con Swagger Open Api al crear la documentación por un error que si dos
endpoint con mismo método y diferentes parámetros se juntan en uno mismo, lo decidí cambiar, ya que se requería documentación en Swagger para la prueba y queda más claro.

- http://localhost:8080/photo
- http://localhost:8080/photo/1
- http://localhost:8080/photo?=albumId=1
- http://localhost:8080/album
- http://localhost:8080/album/true
- http://localhost:8080/album/1
- http://localhost:8080/album?userId=1

# Test

A la hora de ejecutar los test hay diferentes maneras puedes ejecutarlos test por test, clase por clase o todos a la vez.

