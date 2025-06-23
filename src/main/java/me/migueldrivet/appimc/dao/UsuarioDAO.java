package me.migueldrivet.appimc.dao;

import me.migueldrivet.appimc.model.Usuario;
import me.migueldrivet.appimc.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean usuarioExiste(String usuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, usuario, edad, sexo, estatura, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getUsuario());
            stmt.setInt(3, u.getEdad());
            stmt.setString(4, u.getSexo());
            stmt.setDouble(5, u.getEstatura());
            stmt.setString(6, u.getPassword());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
        public Usuario validarLogin(String usuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setEdad(rs.getInt("edad"));
                u.setSexo(rs.getString("sexo"));
                u.setEstatura(rs.getDouble("estatura"));
                u.setPassword(rs.getString("password"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        
        public Usuario validar(String usuario, String password) {
    Usuario user = null;
    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";

    try {
        Connection conn = Conexion.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            user = new Usuario();
            user.setId(rs.getInt("id"));
            user.setNombre(rs.getString("nombre"));
            user.setUsuario(rs.getString("usuario"));
            user.setEdad(rs.getInt("edad"));
            user.setSexo(rs.getString("sexo"));
            user.setEstatura(rs.getDouble("estatura"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return user;
}


}