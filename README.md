# Gestión de Recetas — API REST (Spring Boot)

API REST desarrollada con Spring Boot para gestionar recetas, categorías e ingredientes.  
Incluye uso de DTOs para desacoplar la capa de datos, documentación Swagger y manejo de errores con ProblemDetail (RFC 7807).

---

## 1. Resumen Técnico

- API REST (Tipo B) para administración de recetas.
- CRUD de Categorías, Recetas e Ingredientes.
- Relación Muchos-a-Muchos entre Receta e Ingrediente con información adicional (cantidad y unidad).
- Uso intensivo de records como DTOs para peticiones y respuestas.
- Swagger/OpenAPI para documentación.
- Manejo de excepciones estandarizado.

---

## 2. Tecnologías

| Tecnología | Uso |
|-----------|------|
| Java 17+ | Lenguaje |
| Spring Boot | Framework REST |
| Spring Data JPA | Persistencia |
| H2 | Base de datos en memoria |
| springdoc-openapi | Swagger |
| Lombok | Eliminación de boilerplate |

---

## 3. Modelo de Datos

### Entidades Principales
- **Categoría**: nombre, descripción.
- **Receta**: nombre, tiempo de preparación, dificultad, categoría.
- **Ingrediente**: nombre.
- **Receta_Ingrediente**: tabla intermedia con cantidad y unidad.

---

# 4. DTOs (Data Transfer Objects)

A continuación se explica **qué hace cada DTO**, cuál es su propósito y en qué endpoints se utiliza.

---

## 4.1 DTOs de Categorías

### **CrearCategoriaCmd**
**Tipo:** DTO de entrada  
**Usado en:** `POST /categorias`

Representa los datos necesarios para crear una nueva categoría.  
Incluye método `toEntity()` para convertirlo a entidad `Categoria`.

Campos:
- `nombre`: nombre único de la categoría.
- `descripcion`: texto descriptivo.

---

### **CategoriaResponse**
**Tipo:** DTO de salida  
**Usado en:** respuestas de `GET`, `POST`, `PUT`.

Convierte una entidad `Categoria` en un DTO seguro para envío al cliente.

Campos:
- `id`
- `nombre`
- `descripcion`

---

## 4.2 DTOs de Ingredientes

### **IngredienteCmd**
**Tipo:** DTO de entrada  
**Usado en:** `POST /ingredientes`

Recibe únicamente el nombre del ingrediente.  
Convierte los datos a una entidad `Ingrediente`.

Campos:
- `nombre`

---

### **IngredienteResponse**
**Tipo:** DTO de salida  
**Usado en:** respuestas de `GET /ingredientes`

Representa un ingrediente junto con las recetas en las que aparece.

Campos:
- `id`
- `nombre`
- `recetas`: lista de objetos `Receta_Ingrediente`

---

## 4.3 DTOs para Relación Receta – Ingrediente

### **AñadirIngredienteCmd**
**Tipo:** DTO de entrada  
**Usado en:** `POST /recetas/{id}/ingredientes`

Sirve para asignar un ingrediente a una receta.  
Genera una entidad `Receta_Ingrediente` usando el ingrediente y la receta cargados.

Campos:
- `ingredienteId`
- `cantidad`
- `unidad`

---

### **IngredienteCantCmd**
**Tipo:** DTO de salida  
**Usado en:** listados de ingredientes dentro de una receta.

Representa un ingrediente dentro de una receta incluyendo la cantidad y la unidad.

Campos:
- `id` (del ingrediente)
- `nombre`
- `cantidad`
- `unidad`

---

### **IngredienteInReceta**
**Tipo:** DTO de salida  
**Usado en:** `GET /recetas/{id}`

Devuelve una receta completa **incluyendo su lista de ingredientes** ya convertidos a `IngredienteCantCmd`.

Campos:
- `id`
- `nombre`
- `tiempoPreparacionMin`
- `dificultad`
- `categoriaNombre`
- `ingredientes`: lista con cantidades y unidades

---

## 4.4 DTOs de Recetas

### **RecetaCmd**
**Tipo:** DTO de entrada  
**Usado en:** `POST /recetas`, `PUT /recetas/{id}`

Recoge los datos necesarios para crear o actualizar una receta.  
Convierte sus campos a entidad `Receta`.

Campos:
- `nombre`
- `tiempoPreparacionMin`
- `dificultad`

---

### **RecetaResponse**
**Tipo:** DTO de salida  
**Usado en:** respuestas de `GET`, `POST`, `PUT`.

Representa una receta sin incluir detalles de ingredientes (para versiones resumidas).

Campos:
- `id`
- `nombre`
- `tiempoPreparacionMin`
- `dificultad`

---

# 5. Endpoints de la API
### Categorías
| Método | Ruta | Función |
|--------|------|---------|
| GET | /categorias | Listar |
| GET | /categorias/{id} | Obtener |
| POST | /categorias | Crear |
| PUT | /categorias/{id} | Actualizar |
| DELETE | /categorias/{id} | Eliminar |

### Recetas
| Método | Ruta |
|--------|------|
| GET | /recetas |
| GET | /recetas/{id} |
| POST | /recetas |
| PUT | /recetas/{id} |
| DELETE | /recetas/{id} |

### Ingredientes y asignación
| Método | Ruta | Función |
|--------|------|---------|
| GET | /ingredientes | Listar |
| POST | /ingredientes | Crear ingrediente |
| POST | /recetas/{id}/ingredientes | Asignar ingrediente |

---

## 6. Códigos de Respuesta

| Código | Significado |
|--------|-------------|
| 200 / 201 | Correcto |
| 400 | Petición inválida |
| 404 | No encontrado |
| 409 | Conflicto lógico |

---

## 7. Ejecución

Compilar:
```bash
mvn clean package
