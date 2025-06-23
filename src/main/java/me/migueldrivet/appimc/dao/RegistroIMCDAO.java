package me.migueldrivet.appimc.dao;

import me.migueldrivet.appimc.model.RegistroIMC;
import me.migueldrivet.appimc.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroIMCDAO {

    public boolean insertarRegistro(RegistroIMC registro) {
    String sql = "INSERT INTO registro_imc (usuario, peso, estatura, imc, clasificacion, fecha) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, registro.getUsuario());
        ps.setDouble(2, registro.getPeso());
        ps.setDouble(3, registro.getEstatura());
        ps.setDouble(4, registro.getImc());
        ps.setString(5, registro.getClasificacion());
        ps.setTimestamp(6, registro.getFecha());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
public List<RegistroIMC> obtenerHistorial(int usuarioId) {
    List<RegistroIMC> historial = new ArrayList<>();
    String sql = "SELECT peso, imc, fecha FROM registro_imc WHERE usuario = ? ORDER BY fecha DESC";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, String.valueOf(usuarioId));  // Convertir int a String
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RegistroIMC registro = new RegistroIMC();
            registro.setPeso(rs.getDouble("peso"));
            registro.setImc(rs.getDouble("imc"));
            registro.setFecha(rs.getTimestamp("fecha"));
            historial.add(registro);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return historial;
}


    public List<RegistroIMC> getHistorialByUsuario(String usuarioId) {
    List<RegistroIMC> lista = new ArrayList<>();
    String sql = "SELECT * FROM registro_imc WHERE usuario = ? ORDER BY fecha DESC";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usuarioId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RegistroIMC registro = new RegistroIMC();
            registro.setPeso(rs.getDouble("peso"));
            registro.setEstatura(rs.getDouble("estatura"));
            registro.setImc(rs.getDouble("imc"));
            registro.setClasificacion(rs.getString("clasificacion"));
            registro.setFecha(rs.getTimestamp("fecha"));
            lista.add(registro);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}
}