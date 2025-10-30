
# Segunda tarea - Spring Boot 

## Información del Proyecto

- **Nombre:** CLASE NRO 2 CRUD
- **Versión de Spring Boot:** 3.x.x
- **Java:** 17
- **Build Tool:** Maven
## Estructura del Proyecto
```
├───.idea
├───.mvn
│   └───wrapper
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───crud
│   │   │           └───tarea2
│   │   │               ├───controller
│   │   │               ├───entity
│   │   │               ├───exception
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
```
## Comandos para Levantar, para o reinciar

### Levantar contenedores

```
docker-compose up -d
```

``up → crea y arranca los contenedores definidos en docker-compose.yml.``

``-d → detached, es decir, en segundo plano (no bloquea la terminal).``



### Detener contenedores

```
docker-compose down
```

``Esto detiene y elimina los contenedores creados por docker-compose up.``
``No borra las imágenes ni volúmenes por defecto.``

``detener sin eliminar:``

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

Opción A: reiniciar todos los contenedores que están corriendo:

```
docker-compose restart
```

Opción B: si cambiaste algo en la configuración o Dockerfile y quieres reconstruir:

```
docker-compose up -d --build
```

``Esto reconstruye las imágenes y luego levanta los contenedores.``

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

``Muestra los contenedores activos, puertos expuestos, estado y nombres.``

```
docker@ubuntu:~/cursoSpringKafka$ docker-compose ps
WARN[0000] /home/docker/cursoSpringKafka/docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion
NAME          IMAGE                COMMAND                  SERVICE    CREATED          STATUS          PORTS
postgres_db   postgres:15-alpine   "docker-entrypoint.s…"   postgres   57 minutes ago   Up 57 minutes   0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp
docker@ubuntu:~/cursoSpringKafka$
```



## Metodos publicados

| Método HTTP | Endpoint         | Descripción                                                                        | Ejemplo de uso                                  |
| ----------- | ---------------- | ---------------------------------------------------------------------------------- | ----------------------------------------------- |
| **GET**     | `/products`      | Lista todos los productos o los filtra por nombre (si se pasa el parámetro `name`) | `GET /products`<br>`GET /products?name=celular` |
| **GET**     | `/products/{id}` | Obtiene un producto por su ID                                                      | `GET /products/5`                               |
| **POST**    | `/products`      | Crea un nuevo producto (recibe un JSON en el body)                                 | `POST /products` con body JSON                  |
| **PUT**     | `/products/{id}` | Actualiza un producto existente por ID                                             | `PUT /products/5` con body JSON                 |
| **DELETE**  | `/products/{id}` | Elimina un producto por su ID                                                      | `DELETE /products/5`                            |
