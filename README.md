# API-Rest
Tarea Asincronica de API Rest:
* Se utiliza base de datos h2 con url=jdbc:h2:mem:testdb
* En el puerto 8080
* Utilizo la anotación: @LazyCollection(LazyCollectionOption.FALSE) en las clases Persona y Libro, ya que la  forma en que Hibernate maneja las relaciones entre entidades no me permitia hacer una carga de datos correcta en mi clase principal.
* En caso de no querer usar la carga de datos a traves de la clase principal comentar las lineas de codigo de la misma y las lineas @LazyCollection(LazyCollectionOption.FALSE) en las clases correspondientes.