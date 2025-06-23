package me.migueldrivet.appimc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.migueldrivet.appimc.dao.UsuarioDAO;
import me.migueldrivet.appimc.model.Usuario;

import java.io.IOException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String edadStr = request.getParameter("edad");
        String sexo = request.getParameter("sexo");
        String estaturaStr = request.getParameter("estatura");
        String password = request.getParameter("password");

        // Validaciones
        int edad = Integer.parseInt(edadStr);
        double estatura = Double.parseDouble(estaturaStr);

        if (edad < 15 || estatura < 1.0 || estatura > 2.5) {
            request.setAttribute("mensaje", "Edad o estatura no válida. Edad mínima: 15 años. Estatura entre 1.0 y 2.5 metros.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        if (dao.usuarioExiste(usuario)) {
            request.setAttribute("mensaje", "El nombre de usuario ya está registrado.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setUsuario(usuario);
        nuevo.setEdad(edad);
        nuevo.setSexo(sexo);
        nuevo.setEstatura(estatura);
        nuevo.setPassword(password);

        boolean exito = dao.insertarUsuario(nuevo);

        if (exito) {
            // Registro exitoso → Redirige al login o índice
            response.sendRedirect("index.jsp?registro=exitoso");
        } else {
            request.setAttribute("mensaje", "Hubo un error al registrar. Inténtalo de nuevo.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}