/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import control.user;
import model.modelPengguna;
import view.daftar;
import view.login;

/**
 *
 * @author ASUS
 */
public class MVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new user(new login(), new daftar(), new modelPengguna());
    }
    
}
