/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.userHome;

/**
 *
 * @author ASUS
 */
public class pengguna extends user {
    
    private userHome view;
    
    public pengguna() {
        view = new userHome();
        view.setVisible(true);
        
        view.logoutListener(new logout(view));
    }
}
