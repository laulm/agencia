/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ángel
 */
public class Conexion_BD {
    public static String url="jdbc:mysql://localhost/agencia_viajes";
    public static String usuario="root";
    public static String contraseña="";
    public static String clase= "com.mysql.jdbc.Driver";
    
    public static Connection conectar(){
        Connection conexion=null;
                
        try {
            try {
                Class.forName(clase);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            conexion=(Connection) DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion a BD establecida");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la BD");
        }
        return conexion;
    }
    
}
