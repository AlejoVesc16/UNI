/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author micol
 */
class OperacionesBD {
    
    public static Statement stmObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
    
    public static Connection getConnetion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String db_url= "jdbc:mysql://localhost:3306/students?zeroDateTimeBehavior=convertToNull",
                    db_username="root",
                    db_password="";
            connObj=DriverManager.getConnection(db_url,db_username,db_password);
            
        }catch (ClassNotFoundException ex){
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connObj;
    }

    static ArrayList getListaEstudiantes() {
        ArrayList listaEstudiantes = new ArrayList();
        try {
            stmObj=getConnetion().createStatement();
            resultSetObj=stmObj.executeQuery("select * from student");
            while(resultSetObj.next()){
                EstudianteBean est0bj=new EstudianteBean();
                est0bj.setCedula(resultSetObj.getInt("cedula"));
                est0bj.setNombre(resultSetObj.getString("nombre"));
                est0bj.setCorreo(resultSetObj.getString("correo"));
                est0bj.setContraseña(resultSetObj.getString("contraseña"));
                est0bj.setSexo(resultSetObj.getString("sexo"));
                est0bj.setDireccion(resultSetObj.getString("direccion"));
                listaEstudiantes.add(est0bj);

            }
            System.out.println("Cantidad de estudiantes consultados"+listaEstudiantes.size());
            connObj.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEstudiantes;
    }
    
}
