package me.migueldrivet.appimc.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.core.Context;
import java.util.List;
import me.migueldrivet.appimc.dao.RegistroIMCDAO;
import me.migueldrivet.appimc.model.RegistroIMC;
import me.migueldrivet.appimc.model.Usuario;

@Path("/historial")
public class IMCHistorialResource {

    private RegistroIMCDAO dao = new RegistroIMCDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerHistorial(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Sesión no válida").build();
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int usuarioId = usuario.getId();
        List<RegistroIMC> historial = dao.obtenerHistorial(usuarioId);


        return Response.ok(historial).build();
    }
}