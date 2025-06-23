package me.migueldrivet.appimc.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("javaee8")
public class JavaEE8Resource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "¡Hola desde JavaEE8Resource!";
    }
}
