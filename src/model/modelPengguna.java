/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import mvc.koneksi;

/**
 *
 * @author ASUS
 */
public class modelPengguna {
    
    private Connection koneksi;
    
    public modelPengguna() {
        koneksi = new koneksi().getKoneksi();
    }
    
    public DefaultTableModel bacaTabelPengguna() {
        String query = "SELECT p.id, b.nama, p.username, p.password FROM pengguna p JOIN bio b WHERE p.level = 'user';";
        String namaKolom[] = {"ID", "Nama", "Username", "Password"};
        DefaultTableModel tabel = new DefaultTableModel(null, namaKolom);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object data[] = new Object[4];
                
                data[0] = rs.getInt(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                
                tabel.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return tabel;
    }
    
    public String login(String username, String password) {
        String query = "SELECT level FROM pengguna WHERE username=? AND password=?;";
        String tingkatan = "kosong";
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tingkatan = rs.getString(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return tingkatan;
    }
    
    public boolean hapusPengguna() {
        return true;
    }
    
    public boolean daftar(String username, String password, String nama, String email) {
        String query = "INSERT INTO pendaftar(nama, username, password, email) VALUES( ?, ?, ?, ?);";
        boolean hasil = false;
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nama);
            st.setString(2, username);
            st.setString(3, password);
            st.setString(4, email);
            int insert = st.executeUpdate();
            if (insert > 0) {
                hasil = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return hasil;
    }
    
    
}
