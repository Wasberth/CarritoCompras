/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author PORTO
 */
public class AtributosRegistro {
    
    public static String usuario;
    public static String password;
    
    public AtributosRegistro(String usuario, String password){
        AtributosRegistro.usuario = usuario;
        AtributosRegistro.password = password;
        System.out.println("AtributosRegistro");
        System.out.println(AtributosRegistro.usuario);
        System.out.println(AtributosRegistro.password);
        
    }
    
}
