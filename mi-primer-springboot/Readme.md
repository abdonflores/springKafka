
# Mi Primer Proyecto Spring Boot

## Información del Proyecto

- **Nombre:** mi-primer-springboot
- **Versión de Spring Boot:** 3.x.x
- **Java:** 17
- **Build Tool:** Maven
## Estructura del Proyecto

```
mi-primer-springboot/
├── .idea/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
├── target/
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```
### `.idea`
Carpeta generada por **IntelliJ IDEA**. Contiene configuraciones del IDE (preferencias, rutas de SDK, etc.).  
**No forma parte del código fuente** y normalmente se ignora en el control de versiones.

### `.mvn`
Carpeta opcional usada por el **Maven Wrapper** para configuraciones personalizadas (como `jvm.config`).  
No siempre está presente y puede estar vacía.

### `src`
Carpeta principal del código fuente y recursos. Sigue la estructura estándar de Maven:

- `src/main/java` → Código Java de la aplicación (controladores, servicios, entidades, etc.).
- `src/main/resources` → Archivos de configuración (`application.properties`, `application.yml`), plantillas, archivos estáticos, etc.
- `src/test/java` → Código de pruebas unitarias e integración.
- `src/test/resources` → Recursos específicos para las pruebas.

### `target`
Carpeta generada automáticamente por Maven al compilar o empaquetar el proyecto. Contiene:
- Clases compiladas (`.class`)
- El archivo JAR/WAR ejecutable
- Reportes de pruebas
---
## 📄 Archivos importantes
### `.gitattributes`
Define cómo Git maneja ciertos archivos (finales de línea, codificación, etc.), especialmente útil en entornos multiplataforma.
### `.gitignore`
Lista de archivos y carpetas que **Git debe ignorar**, como:
- `target/`
- `.idea/`
- Archivos de logs o temporales
### `HELP.md`
Archivo de ayuda generado por **Spring Initializr**. Puede eliminarse o personalizarse según las necesidades del proyecto.

### `mvnw` y `mvnw.cmd`
**Maven Wrapper**:
- `mvnw` → para sistemas Unix/Linux/macOS
- `mvnw.cmd` → para Windows

Permiten ejecutar Maven sin tenerlo instalado globalmente. Ejemplo:  
```bash
./mvnw spring-boot:run


## Dependencias Principales
El archivo `pom.xml` (Project Object Model) es el archivo de configuración central de cualquier proyecto Maven. Es un archivo XML que contiene toda la información necesaria para construir tu proyecto: dependencias, plugins, configuraciones de compilación, información del proyecto y mucho más.

**Funciones principales:**
- Define la identidad única de tu proyecto
- Lista todas las dependencias (librerías) que tu proyecto necesita
- Configura cómo se debe compilar y empaquetar tu aplicación
- Especifica plugins para tareas especiales
- Hereda configuraciones de proyectos padre

Maven lee este archivo para saber exactamente qué necesita tu proyecto y cómo construirlo.

---
```bash
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

## Conceptos Aprendidos
### ¿Qué es Spring Boot?

Spring Boot es un framework de desarrollo de aplicaciones Java que simplifica enormemente la creación de aplicaciones empresariales basadas en Spring. Fue diseñado para eliminar la complejidad de configuración que tradicionalmente requería Spring Framework.

**Características principales:**
- **Configuración automática**: Configura automáticamente tu aplicación basándose en las dependencias que agregues
- **Servidor embebido**: Incluye servidores como Tomcat, Jetty o Undertow, eliminando la necesidad de desplegar archivos WAR
- **Dependencias "starter"**: Agrupa dependencias comunes en paquetes fáciles de usar
- **Sin código generado ni XML**: Minimiza la configuración mediante anotaciones y convenciones
- **Listo para producción**: Incluye métricas, health checks y configuración externalizada

Spring Boot permite crear aplicaciones "stand-alone" que pueden ejecutarse con un simple `java -jar`, ideal para microservicios y aplicaciones cloud-native.

### ¿Qué es Maven?

Maven es una herramienta de gestión y construcción de proyectos Java desarrollada por Apache. Funciona como un sistema automatizado que maneja todo el ciclo de vida del proyecto: desde la compilación hasta el empaquetado y despliegue.

**Funciones principales:**
- **Gestión de dependencias**: Descarga automáticamente las librerías que tu proyecto necesita desde repositorios centrales
- **Estructura estándar**: Define una estructura de directorios convencional para todos los proyectos
- **Build automatizado**: Compila, prueba y empaqueta tu aplicación con comandos simples
- **Gestión del ciclo de vida**: Define fases claras: compile, test, package, install, deploy

**Archivo central: pom.xml**
El archivo `pom.xml` (Project Object Model) es el corazón de un proyecto Maven. Define:
- Información del proyecto (groupId, artifactId, version)
- Dependencias del proyecto
- Plugins y su configuración
- Propiedades del build

Ejemplo de uso:
```bash
mvn clean install    # Limpia, compila, prueba y empaqueta
mvn spring-boot:run  # Ejecuta la aplicación Spring Boot
```

### ¿Qué significa "Tomcat started on port 8080"?

Este mensaje indica que el servidor web embebido Apache Tomcat se ha iniciado correctamente y está escuchando peticiones HTTP en el puerto 8080.

**Desglosando el mensaje:**
- **Tomcat**: Es el servidor de aplicaciones web/servlet container que Spring Boot incluye por defecto
- **Started**: El servidor se inició exitosamente y está listo para recibir peticiones
- **Port 8080**: Es el puerto de red donde el servidor está escuchando (8080 es el puerto por defecto para desarrollo)

**Qué significa en la práctica:**
- Puedes acceder a tu aplicación en `http://localhost:8080`
- Todos los endpoints que definas en tu aplicación estarán disponibles bajo esta URL base
- Por ejemplo: `http://localhost:8080/api/usuarios`

**Cómo cambiar el puerto:**
Puedes modificar el puerto en `application.properties`:
```properties
server.port=9090
```

### ¿Para qué sirve la anotación @SpringBootApplication?

`@SpringBootApplication` es una anotación "meta" que combina tres anotaciones esenciales de Spring en una sola, simplificando la configuración inicial de la aplicación.

**Anotaciones que incluye:**

1. **@Configuration**: Indica que la clase puede contener definiciones de beans (componentes) que serán gestionados por Spring
2. **@EnableAutoConfiguration**: Activa la configuración automática de Spring Boot basándose en las dependencias presentes en el classpath
3. **@ComponentScan**: Habilita el escaneo automático de componentes, configuraciones y servicios en el paquete actual y sus subpaquetes

**Uso típico:**
```java
@SpringBootApplication
public class MiAplicacionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiAplicacionApplication.class, args);
    }
}
```

**Lo que hace automáticamente:**
- Escanea y registra todos los `@Controller`, `@Service`, `@Repository` y otros componentes
- Configura automáticamente elementos como datasources, JPA, seguridad, etc.
- Establece valores por defecto sensatos para una aplicación lista para ejecutar
- Inicializa el contexto de Spring y arranca el servidor embebido

Esta anotación debe colocarse en la clase principal de tu aplicación, preferiblemente en el paquete raíz del proyecto para asegurar que todos los componentes sean escaneados correctamente.
