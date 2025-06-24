# Proyecto Calculadora de IMC (Índice de Masa Corporal)

Este proyecto es una aplicación web desarrollada en Java con JSP y Servlets que permite calcular el IMC del usuario, almacenar sus registros e historial, y mostrar los resultados clasificados.

## ⚙️ Tecnologías Utilizadas

- Java 17
- JSP y Servlets (Jakarta EE)
- HTML5, CSS3 (Bootstrap)
- MySQL 8.0
- GlassFish Server 5.1.0
- NetBeans IDE

## 🚀 Funcionalidades Principales

- Registro e inicio de sesión de usuarios.
- Validación de edad mínima (15 años) y estatura (entre 1 m y 2.5 m).
- Cálculo del IMC con clasificación automática.
- Guardado de cada cálculo en una base de datos.
- Consulta del historial de cálculos por usuario.
- Visualización con diseño responsivo usando Bootstrap.
- Servicio REST para obtener el historial de registros.

## 🗃️ Base de Datos

Base de datos: `appimc`  
Tabla: `registro_imc`  
Campos: `usuario`, `peso`, `estatura`, `imc`, `clasificacion`, `fecha`.

## 📦 Estructura del Proyecto

- `src/`: Código fuente (paquetes `servlets`, `dao`, `model`, `util`)
- `web/`: Archivos JSP y recursos estáticos.
- `README.md`: Descripción del proyecto.
- `.gitignore`: Archivos que no se deben subir a Git.

## 🧪 Demostración

El funcionamiento completo del sistema se demuestra en el archivo de video entregado junto con este repositorio.

## 👤 Autor

Miguel Drivet  
Computación Avanzada en Java  
Junio 2025

> Versión desarrollada por Drivet junio 2025.