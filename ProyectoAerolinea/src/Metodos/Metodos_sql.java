/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoaerolinea.Consultar_Usuario;

/**
 *
 * @author Ángel
 */
public class Metodos_sql {

    public static Conexion_BD nueva1 = new Conexion_BD();

    public static PreparedStatement sentencia_preparada;

    public static ResultSet resultado;

    public static String sql;

    public static int resultado_numero = 0;
    /* Metodos para CRUD de Usuario*/
    public int crearUsuario(){
        return 0;
    }
    public int leerUsuario(){
        return 0;
    }
    public int actualizarUsuario(String Nombre, String Apellidos, String CURP, String Domicilio, String Celular, String Correo, String Contraseña, String Fecha_nac, String Sexo, String id_paquete){
        try {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/agencia_viajes","root","");
        Statement stmt=con.createStatement();
        //ResultSet rs=stmt.executeQuery("SELECT * FROM usuarios WHERE ID"+txtCURP.getText());
        int rs = stmt.executeUpdate("UPDATE usuarios SET (Nombre, Apellidos, CURP, Domicilio, Celular, Correo, Contraseña, Fecha_Nac, sexo, id_paquete) VALUES (?,?,?,?,?,?,?,?,?,?)");
        //("SELECT * FROM usuarios WHERE CURP LIKE '"+txtCURP.getText()+"%' ORDER BY CURP DESC");
                
        
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("No se puedo actualizar");
        }
        System.out.println("USUARIO CON CURP "+CURP+" ELIMINAR");
        return 1;
    }
    public int eliminarUsuario(String CURP) throws SQLException{
        try {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/agencia_viajes","root","");
        Statement stmt=con.createStatement();
        //ResultSet rs=stmt.executeQuery("SELECT * FROM usuarios WHERE ID"+txtCURP.getText());
        int rs = stmt.executeUpdate("DELETE FROM usuarios WHERE CURP = '"+CURP+"'");
        //("SELECT * FROM usuarios WHERE CURP LIKE '"+txtCURP.getText()+"%' ORDER BY CURP DESC");
                
        
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("NO SE PUDO ELIMINAR");
        }
        System.out.println("USUARIO CON CURP"+CURP+"ELIMINAR");
        return 1;
    }
    
    
    public int Guardar(String Nombre, String Apellidos, String CURP, String Domicilio, String Celular, String Correo, String Contraseña, String Fecha_nac, String Sexo, String id_paquete) {
        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = ("INSERT INTO usuarios (Nombre, Apellidos, CURP, Domicilio, Celular, Correo, Contraseña, Fecha_Nac, sexo, id_paquete) VALUES (?,?,?,?,?,?,?,?,?,?)");

        try {
            conexion = Conexion_BD.conectar();
            //usuario new_usuario = new usuario();
           // new_usuario.
            sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
            sentencia_preparada.setString(1, Nombre);
            sentencia_preparada.setString(2, Apellidos);
            sentencia_preparada.setString(3, CURP);
            sentencia_preparada.setString(4, Domicilio);
            sentencia_preparada.setString(5, Celular);
            sentencia_preparada.setString(6, Correo);
            sentencia_preparada.setString(7, Contraseña);
            sentencia_preparada.setString(8, Fecha_nac);
            sentencia_preparada.setString(9, Sexo);
            sentencia_preparada.setString(10, id_paquete);

            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_sql.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public static String Buscar_Nombre(String Correo) {
        String busqueda_nombre = "";
        Connection conexion = null;

        try {
            conexion = Conexion_BD.conectar();
            String sentencia_buscar = ("SELECT Nombre, Apellidos FROM usuarios WHERE Correo='" + Correo + "'");
            sentencia_preparada=conexion.prepareStatement(sentencia_buscar);
            resultado=sentencia_preparada.executeQuery();
            
            
            if (resultado.next()) {
                String Nombre=resultado.getString("Nombre");
                String Apellidos=resultado.getString("Apellidos");
                busqueda_nombre=(Nombre+" "+Apellidos);                                
            }
            
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return busqueda_nombre;
    }
    
    
    public static String Buscar_Usuario_Registrado(String Correo, String Contraseña){
        String busqueda_usuario="";
        Connection conexion=null;
        
        
        try {
            conexion=Conexion_BD.conectar();
            String sentencia_buscar_usuario=("SELECT Nombre, Correo, Contraseña FROM usuarios WHERE Correo= '"+Correo+"' && Contraseña= '"+Contraseña+"'");
            sentencia_preparada=conexion.prepareStatement(sentencia_buscar_usuario);
            resultado=sentencia_preparada.executeQuery();
            
            if (resultado.next()) {
                busqueda_usuario="Usuario ENCONTRADO";
            }else {
                busqueda_usuario="Usuario NO ENCONTRADO";
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return busqueda_usuario;
    }
}
