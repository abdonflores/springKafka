
# Tercera tarea - Spring Boot / KAFKA

## Información del Proyecto

- **Nombre:** Clase 3 Refuerzo de arquitectura, validaciones y relación 1:N
- **Versión de Spring Boot:** 3.x.x
- **Java:** 17
- **Build Tool:** Maven


# Estructura y Nuevos Archivos

|Capa|Archivo|Descripción|
|-----------|-----------------|----------------------------------------------------------------------------------|
|Entidad|Category.java|Nueva entidad para la categoría.|
|Entidad|Product.java|Modificado para incluir la relación con Category.|
|DTO|CategoryDTO.java|DTO para la transferencia de datos de Category.|
|DTO|ProductDTO.java|DTO para la transferencia de datos de Product.|
|Mapper|ProductMapper.java|Responsable de Product ProductDTO.|
|Mapper|CategoryMapper.java|Responsable de Category   CategoryDTO.|
|Repositorio|CategoryRepository.java|Repositorio Spring Data para Category.|
|Servicio|CategoryService.java|Lógica de negocio para Category y conversiones.|
|Servicio|ProductService.java|Modificado para usar DTOs y gestionar la relación.|
|Controlador|CategoryController.java|CRUD y endpoint para productos por categoría. Sin lógica.|
|Controlador|ProductController.java|Modificado para usar DTOs. Sin lógica.|
|Excepciones|GlobalExceptionHandler.java|Modificado para manejo uniforme de errores, validaciones y conflictos.|
|Excepciones|DataConflictException.java|Nueva excepción para errores de integridad/conflicto (e.j., categoría duplicada o con productos asociados).|
|Excepciones|ErrorResponse.java|Modificado para incluir code y path.|
|Validación|ResourceNotFoundException.java|Sin cambios, pero necesario.|
|Validación|src/main/resources/ValidationMessages.properties|Archivo con mensajes de validación legibles.|


# Estructura del Proyecto

```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───crud
│   │   │           └───tarea3
│   │   │               ├───controller
│   │   │               ├───dto
│   │   │               ├───entity
│   │   │               ├───exception
│   │   │               ├───mapper
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates

```


# Metodos publicados

| Método HTTP | Endpoint         | Descripción                                                                        | 
| ----------- | ---------------- | ---------------------------------------------------------------------------------- | 
| **GET**     | `/products`      | Lista todos los productos o los filtra por nombre (si se pasa el parámetro `name`) | 
| **GET**     | `/products/{id}` | Obtiene un producto por su ID                                                      | 
| **POST**    | `/products`      | Crea un nuevo producto (recibe un JSON en el body)                                 | 
| **PUT**     | `/products/{id}` | Actualiza un producto existente por ID                                             | 
| **DELETE**  | `/products/{id}` | Elimina un producto por su ID                                                      | 
|**GET**	|/api/categories	|Obtener todas las categorías.|
|**GET**	|/api/categories/{id}	|Obtener una categoría por su ID.|
|**POST**	|/api/categories	|Crear una nueva categoría (pasa CategoryDTO).|
|**PUT**|/api/categories/{id}	|Actualizar una categoría existente (pasa CategoryDTO).|
|**DELETE**|/api/categories/{id}	|Eliminar una categoría.|
|**GET**|/api/categories/{id}/products	|Obtener todos los productos asociados a una categoría específica.|




## Comandos docker
 

```
docker-compose stop
```
```

docker@ubuntu:~/cursoSpringKafka$ docker-compose stop
WARN[0000] /home/docker/cursoSpringKafka/docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion
[+] Stopping 1/1
 ✔ Container postgres_db  Stopped                                                                                                                                                                                     0.3s
docker@ubuntu:~/cursoSpringKafka$ 

```

### Reiniciar contenedores 
```
docker-compose restart
```

 
```
docker@ubuntu:~/cursoSpringKafka$ docker-compose restart
WARN[0000] /home/docker/cursoSpringKafka/docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion
[+] Restarting 1/1
 ✔ Container postgres_db  Started                                                                                                                                                                                     0.8s
docker@ubuntu:~/cursoSpringKafka$

```

### Ver el estado de los contenedores

```
docker-compose ps
 

```
docker@ubuntu:~/cursoSpringKafka$ docker-compose ps
WARN[0000] /home/docker/cursoSpringKafka/docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion
NAME          IMAGE                COMMAND                  SERVICE    CREATED          STATUS          PORTS
postgres_db   postgres:15-alpine   "docker-entrypoint.s…"   postgres   57 minutes ago   Up 57 minutes   0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp
docker@ubuntu:~/cursoSpringKafka$
```
