package com.tuyweb.aplicacionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aplicacionjdbc {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aplicacionjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/veterinaria2";
        Connection conexion; // donde se guarda la conexion
        Statement statement; // donde podemos guardar el onjeto para hacer consulta
        ResultSet rs; // donde guardamos los datos que se traen de la base de datos con un SELECT
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Antes de while");
            while (rs.next()) { //ciclo que valida si hay otra fila, y si hay fila la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuarios:"+rs.getString("nombre"));
            }
            
            
            //Insercion de datos
            statement.execute("INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `correo`) VALUES (NULL, 'alfonso', 'gil', 'alfonsogil@gmail.com');");
            
            rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Despues de insertar");
            while (rs.next()) { //ciclo que valida si hay otra fila, y si hay fila la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("nombre"));
            } 
            
            //Actualizacion
            statement.executeUpdate("UPDATE usuarios SET nombre='pedro' WHERE nombre= \"mateo\"");
            
            rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Despues de actualizar");
            while (rs.next()) { //ciclo que valida si hay otra fila, y si hay fila la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("nombre"));
            }
            
            statement.executeUpdate("DELETE FROM usuarios WHERE nombre= \"alfonso\"");
            
            rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Despues de eliminar");
            while (rs.next()) { //ciclo que valida si hay otra fila, y si hay fila la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("nombre"));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Aplicacionjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
