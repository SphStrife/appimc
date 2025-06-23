<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="me.migueldrivet.appimc.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Calcular IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-light">
<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center">
        <h2>Bienvenido, <span class="text-info"><%= usuario.getNombre() %></span></h2>
        <a href="logout" class="btn btn-outline-danger">Cerrar sesión</a>
    </div>

    <hr class="border-light">

    <form action="CalcularIMC" method="post" class="mt-4">
        <!-- Peso -->
        <div class="mb-3">
            <label for="peso" class="form-label">Ingresa tu peso en kilogramos:</label>
            <input type="number" name="peso" id="peso" step="0.01" min="1" class="form-control" required>
        </div>

        <!-- Estatura -->
        <div class="mb-3">
            <label for="estatura" class="form-label">Ingresa tu estatura en metros:</label>
            <input type="number" name="estatura" id="estatura" step="0.01" min="1" max="2.5" class="form-control" required>
        </div>

        <!-- Botón -->
        <div class="d-grid">
            <button type="submit" class="btn btn-success">Calcular IMC</button>
        </div>
    </form>
    
</div>
</body>
</html>