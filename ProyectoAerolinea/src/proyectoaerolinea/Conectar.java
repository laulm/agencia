/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoaerolinea;
import static Metodos.Conexion_BD.clase;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Ángel
 */
public class Conectar {
    Connection conectar=null;

    
    
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/agencia_viajes", "root", "");
            
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        return conectar;
    }
}
