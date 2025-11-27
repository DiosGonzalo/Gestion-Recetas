# API de Gestión de Recetas

Este proyecto es una API REST desarrollada con Spring Boot para la gestión de un sistema de recetas de cocina. Permite la administración completa de recetas, su clasificación por categorías y la gestión de los ingredientes necesarios.


## Tecnologías utilizadas

El proyecto está construido utilizando las siguientes tecnologías principales:

* **Java**: Lenguaje principal de desarrollo.
* **Spring Boot**: Framework para la creación de la API.
* **Swagger / OpenAPI**: Para la documentación interactiva y pruebas de los endpoints.
* **Lombok**: Para reducir el código repetitivo.

## Funcionalidades

El sistema se divide en tres módulos principales:

1.  **Recetas**: Gestión del ciclo de vida de una receta (crear, consultar, editar y borrar) y asignación de ingredientes.
2.  **Categorías**: Organización de las recetas (ej. Postres, Entrantes, Principales).
3.  **Ingredientes**: Base de datos de productos disponibles para ser utilizados en las recetas.

## Documentación de la API

A continuación se detallan los endpoints disponibles en la aplicación.

### Recetas
*Base URL:* `/recetas`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/recetas` | Obtiene el listado completo de recetas. |
| `GET` | `/recetas/{id}` | Busca una receta específica por su ID. |
| `GET` | `/recetas/{recetaId}` | Obtiene una receta detallada junto con sus ingredientes. |
| `PUT` | `/recetas/crear` | Crea una nueva receta. |
| `POST` | `/recetas/edit/{id}` | Edita los datos de una receta existente. |
| `DELETE` | `/recetas/delete/{id}` | Elimina una receta del sistema. |
| `POST` | `/recetas/{id}/ingredientes` | Añade un ingrediente a una receta existente. |

### Categorías
*Base URL:* `/categoria`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/categoria` | Lista todas las categorías disponibles. |
| `GET` | `/categoria/{id}` | Muestra la información de una categoría por ID. |
| `PUT` | `/categoria/create` | Crea una nueva categoría. |
| `POST` | `/categoria/edit/{id}` | Edita una categoría existente. |
| `DELETE` | `/categoria/delete/{id}` | Elimina una categoría. |

### Ingredientes
*Base URL:* `/ingredientes`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/ingredientes` | Lista todos los ingredientes registrados. |
| `PUT` | `/ingredientes/crear` | Registra un nuevo ingrediente en el sistema. |

## Códigos de Respuesta

La API devuelve los siguientes códigos de estado HTTP para indicar el resultado de las operaciones:

* **200 OK**: Solicitud procesada correctamente.
* **201 Created**: Recurso creado con éxito.
* **204 No Content**: Eliminación correcta.
* **400 Bad Request**: Error en los datos enviados (ej. validaciones fallidas).
* **404 Not Found**: El recurso solicitado no existe.
* **409 Conflict**: Conflicto lógico (ej. nombres duplicados).

## Ejecución y Pruebas

Si descargas y ejecutas el proyecto en local, puedes acceder a la documentación interactiva generada por Swagger UI. Esto te permitirá probar las peticiones directamente desde el navegador sin necesidad de herramientas externas.

Una vez iniciada la aplicación, accede a:
