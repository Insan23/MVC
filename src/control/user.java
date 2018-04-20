/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.modelPengguna;
import view.adminDaftarPengguna;
import view.adminHome;
import view.daftar;
import view.login;
import view.userHome;

/**
 *
 * @author ASUS
 */
public class user {

    private modelPengguna model;
    private login viewLogin;
    private daftar viewDaftar;

    public user() {
        //konstruktor kosong
    }

    public user(login viewLogin, daftar viewDaftar, modelPengguna model) {
        this.model = model;
        this.viewLogin = viewLogin;
        this.viewDaftar = viewDaftar;
        this.viewLogin.setVisible(true);

        this.viewLogin.daftarListener(new daftarViewListener());
        this.viewLogin.loginListener(new loginListener());

        this.viewDaftar.kembaliListener(new kembali());
        this.viewDaftar.daftarListener(new daftarListener());
    }

    public class logout implements ActionListener {

        private JFrame view;

        public logout(JFrame v) {
            view = v;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int pilihan = JOptionPane.showConfirmDialog(view, "Yakin Ingin Logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                login viewLogin = new login();
                modelPengguna model = new modelPengguna();
                daftar viewDaftar = new daftar();
                new user(viewLogin, viewDaftar, model);
                
                view.dispose();
            } else {
                //empty
            }
        }

    }

    private class daftarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = viewDaftar.getNama();
            String username = viewDaftar.getUsername();
            String password = viewDaftar.getPassword();
            String email = viewDaftar.getEmail();

            if (model.daftar(username, password, nama, email)) {
                JOptionPane.showMessageDialog(viewDaftar, "Pendaftaran Berhasil");
                viewLogin.setVisible(true);
                viewDaftar.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(viewDaftar, "Pendaftaran Gagal");
            }
        }

    }

    private class daftarViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewLogin.setVisible(false);
            viewDaftar.setVisible(true);
        }

    }

    private class kembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewLogin.setVisible(true);
            viewDaftar.setVisible(false);
        }
    }

    private class loginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = viewLogin.getUsername();
            String password = viewLogin.getPassword();

            String tingkatan = model.login(username, password);
            if (tingkatan.equalsIgnoreCase("user")) {
                new control.pengguna();
                viewLogin.dispose();
            } else if (tingkatan.equalsIgnoreCase("admin")) {
                new control.admin();
                viewLogin.dispose();
            } else {
                JOptionPane.showMessageDialog(viewLogin, "Username atau Password Salah");
            }
        }

    }
}
