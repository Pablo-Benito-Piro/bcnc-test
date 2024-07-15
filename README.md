# Prueba BCNC Group - Pablo Benito Piró

# Ejecución

He utilizado el IDE IntelliJ por lo que explicaré su ejecución desde este IDE.
Para ejecutar el proyecto se puede hacer de diferentes maneras, explicaré 2:
La primera entrando dentro de la clase 'BcncTestApplication.kt' y en la clase se encontrará el método main con una
flecha Verde, lo pulsamos y nos saldrá un desplegable y seleccionamos la opción Run y el nombre de la clase.

IMPORTANTE: no tener levantado nada en el puerto 8080 debido a que usa ese puerto para levantarse.
![imagen](https://github.com/user-attachments/assets/e2d30da4-ff79-4c03-9882-394754d9b441)


![imagen](https://github.com/user-attachments/assets/358db8b0-dd5b-4120-b6c3-a4b157e4ba05)


La otra manera es crear una Run/Debug Configuration, si pinchamos en la flecha al lado del nombre nos saldrá un
desplegable

![imagen](https://github.com/user-attachments/assets/4372f97a-b12e-49f7-a7a3-d254cb86efdf)

![imagen](https://github.com/user-attachments/assets/0bfcc8cb-1c39-48b3-bbdb-b0bd4613fb02)

Desde ahí podremos elegir una configuración ya existente o crear una nueva para crear una seleccionaremos la marcada en
azul

![imagen](https://github.com/user-attachments/assets/09e937f6-ed4d-454d-b006-4065996549d0)


Seleccionaremos la opción Spring boot, se tendrá que rellenar los huecos como la imagen, en este creacion tambien se
podrá  añadir perfiles como por ejemplo (local, develop) pero en este caso no es necesario.

![imagen](https://github.com/user-attachments/assets/4372f97a-b12e-49f7-a7a3-d254cb86efdf)

Una vez creado, seleccionamos la flecha verde y se ejecutará, y en la consola se abrirá y saldrán estos mensajes

![imagen](https://github.com/user-attachments/assets/e7037a44-59f3-403e-a3d4-3aa1a256db30)

Una vez levantado podremos lanzar peticiones a la api para ello podremos utilizar cualquier navegador o Postman un
software que facilita la visualización de las respuestas y el lanzamiento de peticiones.

![imagen](https://github.com/user-attachments/assets/19e9629a-6656-4856-98ec-adc21a6c7546)

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

![imagen](https://github.com/user-attachments/assets/6f622873-cf43-4103-b6fc-e4e845146c39)

Si seleccionamos la carpeta donde se encuentran todos los test y le damos a run se ejecutarán todos los test que haya dentro

![imagen](https://github.com/user-attachments/assets/491e927d-8ea0-49a4-8f43-7f1b97f34e67)

Si seleccionamos la clase que queremos ejecutar los test, podemos seleccionar  en la flecha verde al lado del nombre de la clase o en test correspondiente para ejecutarlo dando al run

![imagen](https://github.com/user-attachments/assets/4c00b519-5014-483d-97ce-aee3a134a57e)




