<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="me.migueldrivet.appimc.model.RegistroIMC" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #1e1e2f;
            color: white;
        }

        .container {
            margin-top: 60px;
        }

        .resultado {
            border: 2px solid #00ffc8;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 60px;
            background-color: #000000;
            text-align: center;
        }

        .custom-table {
            background-color: #000000;
            color: white;
            border-collapse: collapse;
            width: 100%;
            border-radius: 10px;
            overflow: hidden;
        }

        .custom-table thead {
            background-color: #0f0f0f;
        }

        .custom-table thead th {
            color: #00ffc8;
            font-weight: bold;
            padding: 10px;
        }

        .custom-table tbody tr {
            background-color: #000000;
        }

        .custom-table tbody tr:nth-child(even) {
            background-color: #111111;
        }

        .custom-table tbody tr:hover {
            background-color: #222222;
        }

        .custom-table td {
            padding: 10px;
        }

        .btn-volver {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center mb-4">Resultado de tu IMC</h2>

    <div class="resultado">
        <%
            Double peso = (Double) request.getAttribute("peso");
            Double estatura = (Double) request.getAttribute("estatura");
            Double imc = (Double) request.getAttribute("imc");
            String clasificacion = (String) request.getAttribute("clasificacion");

            if (peso != null && estatura != null && imc != null && clasificacion != null) {
        %>
        <p>Tu peso es: <strong><%= peso %> kg</strong></p>
        <p>Tu estatura es: <strong><%= estatura %> m</strong></p>
        <p>Tu IMC es: <strong><%= String.format("%.2f", imc) %></strong></p>
        <p>Clasificación: <strong><%= clasificacion %></strong></p>
        <%
            } else {
        %>
        <p>No se pudo calcular el IMC. Intenta de nuevo.</p>
        <%
            }
        %>
    </div>

    <h3 class="text-center">Historial de registros</h3>
    <table class="custom-table text-center">
        <thead>
        <tr>
            <th>Fecha</th>
            <th>Peso</th>
            <th>Estatura</th>
            <th>IMC</th>
            <th>Clasificación</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RegistroIMC> historial = (List<RegistroIMC>) request.getAttribute("historial");

            if (historial != null && !historial.isEmpty()) {
                java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("America/Mexico_City"));

                for (RegistroIMC reg : historial) {
        %>
        <tr>
            <td><%= sdf.format(reg.getFecha()) %></td>
            <td><%= reg.getPeso() %></td>
            <td><%= reg.getEstatura() %></td>
            <td><%= String.format("%.2f", reg.getImc()) %></td>
            <td><%= reg.getClasificacion() != null ? reg.getClasificacion() : "" %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="5">No hay registros aún</td></tr>
        <%
            }
        %>
        </tbody>
    </table>

    <div class="text-center btn-volver">
        <a href="imc.jsp" class="btn btn-light">Volver</a>
    </div>
</div>
</body>
</html>