Proyecto Backend con Spring Boot

Este proyecto es un **backend desarrollado con Spring Boot 3**, que expone una API REST para la gestión de **usuarios, órdenes y productos**.  

Características principales
- Arquitectura basada en **Controlador – Servicio – Repositorio**.  
- Datos de prueba cargados desde un archivo **JSON** en memoria.  
- Integración con **MongoDB** mediante Spring Data.  
- **Unit Tests** implementados para controladores, servicios y modelos.  
- Endpoints REST para:  
  - Obtener lista de usuarios.  
  - Consultar órdenes de un usuario.  
  - Listar productos de una orden.  

Estructura del proyecto

src/main/java/org/proyectdemo/
├── controller # Controladores REST
├── model # Entidades: Usuario, Orden, Producto
├── repository # Repositorio de datos (MongoDB)
├── service # Lógica de negocio
└── Application # Clase principal de Spring Boot

src/test/java/org/proyectdemo/
├── model
│ ├── OrdenTest
│ ├── ProductoTest
│ └── UsuarioTest
├── UsuarioControllerTest
├── UsuarioServiceMongoDbTest
└── UsuarioServiceTest


Ejemplo de Endpoints
- `GET /usuarios/picker` → Lista de usuarios simplificada.  
- `GET /usuarios/{id}/ordenes/picker` → Órdenes de un usuario.  
- `GET /usuarios/{idUsuario}/ordenes/{idOrden}/productos` → Productos de una orden.  

Tecnologías utilizadas
- Java 17 
- Spring Boot 3  
- Spring Web  
- Spring Data MongoDB  
- Maven  
- JUnit 5 (para tests)  
- Mockito (para mocks en los tests)  

 Unit Tests
El proyecto cuenta con pruebas unitarias para asegurar la calidad del código y el correcto funcionamiento de los componentes:  

- Modelos:`OrdenTest`, `ProductoTest`, `UsuarioTest`  
- Controladores: `UsuarioControllerTest`  
- Servicios: `UsuarioServiceTest`, `UsuarioServiceMongoDbTest`  

Para ejecutar los tests:  
```bash
mvn test

Arquitectura del Proyecto

flowchart TD
    C[Cliente / Frontend] -->|HTTP REST| A[Controladores REST]
    A --> B[Servicios]
    B --> D[Repositorio]
    D --> M[(MongoDB)]
    B --> J[Datos JSON de prueba]

Ejecucion
mvn spring-boot:run

Ejecucion de Test
mvn test

El servicio quedará disponible en:
 http://localhost:8080

Contacto
Celular: +54 9 341 586-3212

Email: bpstyga@gmail.com

LinkedIn: Bruno Pstyga

 GitHub: @brunopstyga










