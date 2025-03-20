Task Manager API es una aplicación backend desarrollada con Spring Boot que permite a los usuarios gestionar sus tareas de forma segura mediante autenticación con JWT.

🚀 Características

CRUD de tareas: Crear, leer, actualizar y eliminar tareas.

Autenticación y autorización: Manejo de usuarios y roles con Spring Security y JWT.

Asociación usuario-tareas: Cada usuario solo puede acceder a sus propias tareas.

Persistencia: Uso de PostgreSQL con Spring Data JPA.

🛠 Tecnologías utilizadas

Java 17

Spring Boot 3

Spring Security (JWT)

PostgreSQL

Maven

🔧 Instalación y configuración

1️⃣ Clonar el repositorio

2️⃣ Configurar la base de datos

Modifica el archivo application.properties con tus credenciales de PostgreSQL:

3️⃣ Ejecutar la aplicación

La API estará disponible en http://localhost:8080.

🔐 Autenticación y uso de la API

1️⃣ Registrar un usuario

Body (JSON):

2️⃣ Iniciar sesión

Body (JSON):

Respuesta:

3️⃣ Operaciones con tareas

Todas las solicitudes requieren el token JWT en el header Authorization:

✅ Obtener tareas del usuario

➕ Crear una tarea

Body (JSON):

✏️ Actualizar una tarea

🗑️ Eliminar una tarea
