package me.migueldrivet.appimc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import me.migueldrivet.appimc.dao.UsuarioDAO;
import me.migueldrivet.appimc.model.Usuario;

import java.io.IOException;

@WebServlet(name = "UsuarioController", urlPatterns = {"/registrar", "/login", "/logout"})
public class UsuarioController extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/registrar":
                registrarUsuario(request, response);
                break;
            case "/login":
                loginUsuario(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String sexo = request.getParameter("sexo");
        double estatura = Double.parseDouble(request.getParameter("estatura"));
        String password = request.getParameter("password");

        if (edad < 15 || estatura < 1 || estatura > 2.5) {
            request.setAttribute("error", "Edad o estatura no válidas");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        if (usuarioDAO.usuarioExiste(usuario)) {
            request.setAttribute("error", "El nombre de usuario ya está en uso");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setEdad(edad);
        nuevoUsuario.setSexo(sexo);
        nuevoUsuario.setEstatura(estatura);
        nuevoUsuario.setPassword(password);

        boolean exito = usuarioDAO.insertarUsuario(nuevoUsuario);

        if (exito) {
            request.setAttribute("mensaje", "Registro exitoso. Inicia sesión.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Hubo un error al registrar al usuario");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

    private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        Usuario u = usuarioDAO.validarLogin(usuario, password);
        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", u);
            response.sendRedirect("imc.jsp");
        } else {
            request.setAttribute("error", "Usuario o password incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/logout".equals(path)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}