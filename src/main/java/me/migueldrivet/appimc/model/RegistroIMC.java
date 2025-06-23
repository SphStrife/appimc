package me.migueldrivet.appimc.model;

import java.sql.Timestamp;

public class RegistroIMC {
    private int id;
    private String usuario;
    private double peso;
    private double imc;
    private Timestamp fecha;

    public RegistroIMC() {}

    public RegistroIMC(String usuario, double peso, double imc, Timestamp fecha) {
        this.usuario = usuario;
        this.peso = peso;
        this.imc = imc;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }

    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
    
    private double estatura;
private String clasificacion;

// Getter y Setter de estatura
public double getEstatura() {
    return estatura;
}

public void setEstatura(double estatura) {
    this.estatura = estatura;
}

// Getter y Setter de clasificacion
public String getClasificacion() {
    return clasificacion;
}

public void setClasificacion(String clasificacion) {
    this.clasificacion = clasificacion;
}

}