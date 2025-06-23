package me.migueldrivet.appimc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import me.migueldrivet.appimc.model.Usuario;
import me.migueldrivet.appimc.dao.RegistroIMCDAO;
import me.migueldrivet.appimc.model.RegistroIMC;

@WebServlet(name = "CalcularIMC", value = "/CalcularIMC")
public class CalcularIMC extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            // 1. Captura y cálculo
            double peso = Double.parseDouble(request.getParameter("peso"));
            double estatura = usuario.getEstatura();
            double imc = peso / (estatura * estatura);

            // 2. Clasificación
            String clasificacion;
            if (imc < 18.5) clasificacion = "Bajo peso";
            else if (imc < 25) clasificacion = "Peso normal";
            else if (imc < 30) clasificacion = "Sobrepeso";
            else clasificacion = "Obesidad";

            // 3. Obtener fecha exacta en CDMX
            LocalDateTime ahoraCDMX = LocalDateTime.now().minusHours(1);
            Timestamp fechaAjustada = Timestamp.valueOf(ahoraCDMX);


            // 4. Crear y rellenar objeto
            RegistroIMC registro = new RegistroIMC(
                usuario.getUsuario(),
                peso,
                imc,
                fechaAjustada
            );
            registro.setEstatura(estatura);
            registro.setClasificacion(clasificacion);

            // 5. Guardar en BD
            RegistroIMCDAO dao = new RegistroIMCDAO();
            dao.insertarRegistro(registro);

            // 6. Obtener historial y preparar datos para JSP
            List<RegistroIMC> historial = dao.getHistorialByUsuario(usuario.getUsuario());
            request.setAttribute("historial", historial);

            request.setAttribute("peso", peso);
            request.setAttribute("estatura", estatura);
            request.setAttribute("imc", imc);
            request.setAttribute("clasificacion", clasificacion);

            request.getRequestDispatcher("resultado.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("imc.jsp");
        }
    }
}