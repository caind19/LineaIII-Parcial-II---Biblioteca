# LineaIII-Parcial-II---Biblioteca

# BiblioApp - Aplicativo de Administración de Tablas Relacionadas con Base de Datos 
para la gestión de una bibliteca.
- Linea de profundización III
- Cain David Martinez Cardona
- 901N

# Descripción

**BibliotecaApp** es una aplicación desarrollada en Android Studio utilizando **Jetpack Compose** y **Room**. Creada por [tu nombre], esta aplicación permite gestionar los registros de una biblioteca, incluyendo libros, autores, préstamos y miembros. La interfaz es amigable e intuitiva, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las diferentes entidades. Además, cuenta con validaciones y soporte para orientación vertical y horizontal.

# Funcionalidades Principales

## HomeScreen.kt

- **Pantalla Principal**: Navegación hacia las diferentes secciones (Libros, Autores, Préstamos y Miembros) a través de botones estilizados y un diseño agradable con degradados y sombreados.
    - Navegación intuitiva a las pantallas de registro.
    - Interfaz responsive y compatible con orientación horizontal.

## LibroScreen.kt

- **Gestión de Libros**: Permite agregar, editar, listar y eliminar libros.
    - **Agregar Libro**: Registro de nuevos libros con título, género y autor.
    - **Editar Libro**: Actualización de un libro existente proporcionando su ID.
    - **Eliminar Libro**: Eliminación de libros por su ID.
    - **Listar Libros**: Visualización de todos los libros registrados.

## AutorScreen.kt

- **Gestión de Autores**: Formulario para la manipulación de autores, donde se ingresan el nombre, apellido y nacionalidad. 
    - **Agregar Autor**: Registro de nuevos autores.
    - **Editar Autor**: Actualización de un autor existente proporcionando su ID.
    - **Eliminar Autor**: Elimina un autor proporcionando su ID.
    - **Listar Autores**: Muestra todos los autores registrados.

## PrestamoScreen.kt

- **Gestión de Préstamos**: Permite el registro de préstamos de libros, estableciendo la relación entre miembros y libros.
    - **Agregar Préstamo**: Registro de un nuevo préstamo con fecha de inicio y devolución.
    - **Editar Préstamo**: Actualización de un préstamo existente proporcionando su ID.
    - **Eliminar Préstamo**: Posibilidad de eliminar préstamos por su ID.
    - **Listar Préstamos**: Muestra todos los préstamos registrados, incluyendo el libro y miembro asociado.

## MiembroScreen.kt

- **Gestión de Miembros**: Permite registrar, editar, listar y eliminar miembros de la biblioteca.
    - **Agregar Miembro**: Registro de un nuevo miembro con su nombre, apellido y fecha de inscripción.
    - **Editar Miembro**: Actualización de los datos de un miembro proporcionando su ID.
    - **Eliminar Miembro**: Posibilidad de eliminar un miembro por su ID.
    - **Listar Miembros**: Muestra todos los miembros registrados.

# Room Database

- **Persistencia de Datos**: **Room** maneja la base de datos local de la aplicación, almacenando la información de libros, autores, miembros y préstamos de manera eficiente. 
- Las relaciones entre las entidades están bien definidas:
    - Libros relacionados con autores.
    - Préstamos relacionados con libros y miembros.

# Diseño de Interfaz

- **Interfaz Amigable y Responsiva**: La aplicación está diseñada para ser accesible y fácil de usar, tanto en orientación vertical como horizontal, con el uso de scrolls en pantallas horizontales.
- **Formularios de Fácil Uso**: Cada formulario está organizado para que la interacción sea clara y fluida. Los botones de acción están resaltados con colores agradables y un estilo visual consistente.

# IDE y Lenguaje Utilizados

- **Android Studio**
- **Kotlin**

## |¡Gestiona libros, autores, miembros y préstamos de manera sencilla y eficiente con BibliotecaApp!|