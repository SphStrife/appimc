package me.migueldrivet.appimc.model;

public class Usuario {
    private int id;
    private String nombre;
    private String usuario;
    private int edad;
    private String sexo;
    private double estatura;
    private String password;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String usuario, int edad, String sexo, double estatura, String password) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.edad = edad;
        this.sexo = sexo;
        this.estatura = estatura;
        this.password = password;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public double getEstatura() { return estatura; }
    public void setEstatura(double estatura) { this.estatura = estatura; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}