<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h3 class="mb-0">Registro de Usuario</h3>
        </div>
        <div class="card-body">
            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="registrar" method="post">
                <div class="mb-3">
                    <label class="form-label">Nombre completo:</label>
                    <input type="text" name="nombre" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Nombre de usuario:</label>
                    <input type="text" name="usuario" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Edad:</label>
                    <input type="number" name="edad" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Sexo:</label>
                    <select name="sexo" class="form-select" required>
                        <option value="" selected disabled>Selecciona una opción</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Femenino">Femenino</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Estatura (en metros):</label>
                    <input type="number" name="estatura" step="0.01" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña:</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Registrarse</button>
                <a href="index.jsp" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>