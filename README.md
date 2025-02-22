# Coworking_ACME

## 📌 Contexto del Problema
CoworkTech es una empresa emergente que ha creado una red de espacios de coworking en distintas ciudades. Estos espacios permiten a profesionales independientes, startups y empresas alquilar oficinas privadas, salas de reuniones o escritorios compartidos por horas o días.

📅 **El problema:** Actualmente, las reservas se gestionan de forma manual mediante llamadas y correos electrónicos, lo que genera confusión, sobrecargas de trabajo y errores en la asignación de espacios.

## 📌 Solución Problema
✔ Consultar los espacios disponibles 🏢
✔ Reservar espacios en función de la disponibilidad 📆
✔ Gestionar las reservas fácilmente ✅

## Requerimientos 🌐

- **1️⃣ 🏢 Gestión de Espacios de Coworking:**
Cada espacio debe contener:
📌 Nombre (Ej: "Sala Azul", "Oficina 3A")

📌 Tipo (oficina privada, sala de reuniones, escritorio compartido)

📌 Capacidad máxima

📌 Disponibilidad (activo/inactivo)

-**2️⃣ 📆 Reservas de Espacios:**
  Los usuarios podrán reservar espacios disponibles. Cada reserva debe contener:
📌 ID del espacio reservado

📌 Fecha de la reserva

📌 Hora de inicio y fin

📌 Estado de la reserva:
  🔹 Pendiente (cuando se crea)
  🔹 Confirmada (cuando es aceptada por el sistema)
  🔹 Cancelada (cuando el usuario decide no asistir)

-**3️⃣ 🌐 API REST y Consumo de Datos:**
📢 La API debe exponer los siguientes endpoints:

📌 Espacios de coworking:
  🔹 Listar todos los espacios
  🔹 Filtrar por tipo o disponibilidad
  🔹Crear, modificar y eliminar espacios

-**4️⃣ 🚨 Validación y Manejo de Errores:**
✅ Se deben validar los datos antes de almacenarlos:
🔹 Fechas y horarios deben ser coherentes.
🔹 No se pueden reservar espacios en el mismo horario si ya están ocupados.

-**5️⃣ 📖 Documentación con Swagger:**

📌 La API debe estar documentada con Swagger/OpenAPI e incluir:
	✔ Descripción de cada endpoint
	✔ Parámetros requeridos
	✔ Posibles respuestas y códigos de error

 -**6️⃣ 🗄 Persistencia de Datos con PostgreSQL:**
 
 📌 Base de datos relacional utilizando PostgreSQL con Spring Data JPA.
🔹 Tablas requeridas:

📌 Espacios (con las características mencionadas)

  📌 Reservas (con restricciones para evitar solapamientos)
  
📢 Importante:
📌 Los estudiantes deben diseñar la estructura SQL completa, definiendo las tablas y relaciones necesarias.

📌 Deben insertar al menos 10 registros de prueba por cada entidad.

## Funcionalidades 🔨

- *Espacios:*
✔ Registrar nuevos espacios
✔ Consultar la lista de espacios disponibles
✔ Modificar espacios existentes
✔ Eliminar espacios

-*Reservas:*
✔ Crear una nueva reserva
✔ Consultar todas las reservas activas
✔ Modificar una reserva (cambiar horario o cancelar)
✔ Eliminar reservas

## 🛠️ Tecnologías Usadas
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **Swagger (API Documentation)**
- **Maven (Dependency Management)**
- **HTML & CSS**
- **JavaScript**

--

## 🚀 Installation & Setup

### Requerimientos
- Java 17
- Maven 3.x
- PostgreSQL o MySQL base de datos
- API Client como Postman or Thunder Client (opcional)

### Pasos para ejecutar el proyecto
1. **Clonar el Repositorio:**
   ```sh
   git clone https://github.com/AlexisH28/Coworking_ACME
   ```

2. **Configurar la Base de Datos:**
   - Abrir `application.properties` o `application.yml`
   - Implementar tu base de datoe:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/coworking-acme
     spring.datasource.username=tu_db_user
     spring.datasource.password=tu_db_password
     ```

3. **Correr la Aplicación:**
   ```sh
   mvn spring-boot:run
   ```

4. **Acceso API Documentation:**
   - Abrir Swagger UI in tu navegador: `http://localhost:8081/swagger-ui.html`

## 📌 API Endpoints & Usage

### 2️⃣ Espacios
| Metodo | Endpoint               | Descripcion                |
|--------|-----------------------|-----------------------------|
| GET    | `/users/{id}`          | Get user profile            |
| PUT    | `/users/{id}`          | Update user profile         |
| GET    | `/users/{id}/followers` | Get followers of a user     |
| GET    | `/users/{id}/following` | Get users the user follows  |

### 3️⃣ Reservas
| Metodo | Endpoint          | Descripcion                       |
|--------|------------------|-------------------------------------|
| GET    | `/posts`          | Get all posts                      |
| GET    | `/posts/{id}`     | Get a specific post                |
| POST   | `/posts`          | Create a new post                  |
| PUT    | `/posts/{id}`     | Edit a post                        |
| DELETE | `/posts/{id}`     | Delete a post                      |

## ER Diagram
![modelo_relacional](https://github.com/user-attachments/assets/b0d79b9b-7a02-4161-80b5-acb18ee7aebe)

--

> [!TIP]
> ## Contribución 👥

¡Me encantaría recibir tus contribuciones! Si deseas contribuir a este proyecto, por favor sigue estos pasos:

- Haz un fork del proyecto.
- Crea una nueva rama `(git checkout -b feature/nueva-funcionalidad)`.
- Realiza tus cambios y haz commit `(git commit -am 'Añadir nueva funcionalidad')`.
- Empuja la rama `(git push origin feature/nueva-funcionalidad)`.
- Abre un Pull Request.

--

> [!NOTE]
> ## Contacto 🧑‍💻

Hecho por [Alexis Hernández](https://github.com/AlexisH28)[Alvaro Martínez](https://github.com/alvaroMartinez13)

Alexis Rafael Hernández Tocora -- (alexismar1228@gmail.com)
Alvaro Andres Martínez Alcina -- (alvaro.martinezalcina13@gmail.com)
