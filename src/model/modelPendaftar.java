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
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import mvc.koneksi;

/**
 *
 * @author ASUS
 */
public class modelPendaftar {

    Connection koneksi;

    public modelPendaftar() {
        koneksi = new koneksi().getKoneksi();
    }

    public DefaultTableModel bacaTabelPendaftar() {
        String query = "SELECT id, nama, username, password, email, persetujuan FROM pendaftar;";
        String namaKolom[] = {"ID", "Nama", "Username", "Password", "Email", "Persetujuan"};
        DefaultTableModel tabel = new DefaultTableModel(null, namaKolom);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object data[] = new Object[6];

                data[0] = rs.getInt(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);

                tabel.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return tabel;
    }

    public int tolakPendaftar(int id) {
        String query = "UPDATE pendaftar SET persetujuan=? WHERE id=?";
        int hasil = -1;
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, "ditolak");
            st.setInt(2, id);
            hasil = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return hasil;
    }

    public boolean terimaPendaftar(int id) {
        String query = "UPDATE pendaftar SET persetujuan=? WHERE id=?;";

        String queryInsert1 = "INSERT INTO pengguna(username, password, level) VALUES((SELECT username FROM pendaftar WHERE id=?), (SELECT password FROM pendaftar WHERE id=?), 'user');";
        String queryInsert2 = "INSERT INTO bio(nama, email, id_pengguna) VALUES((SELECT nama FROM pendaftar WHERE id=?), (SELECT email FROM pendaftar WHERE id=?), ?);";

        boolean hasil = false;

        try {
            /**
             * update tabel pendaftar menjadi diterima
             */
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, "diterima");
            st.setInt(2, id);
            int insert = st.executeUpdate();
            if (insert > 0) {
                hasil = true;
            }

            /**
             * insert ke tabel pengguna
             */
            PreparedStatement insertPengguna = koneksi.prepareStatement(queryInsert1, Statement.RETURN_GENERATED_KEYS);
            insertPengguna.setInt(1, id);
            insertPengguna.setInt(2, id);
            insertPengguna.executeUpdate();
            int idPengguna = 0;
            ResultSet idHasil = insertPengguna.getGeneratedKeys();

            idHasil.next();
            idPengguna = idHasil.getInt(1);

            /**
             * insert ke tabel bio
             */
            PreparedStatement insertBio = koneksi.prepareStatement(queryInsert2);
            insertBio.setInt(1, id);
            insertBio.setInt(2, id);
            insertBio.setInt(3, idPengguna);
            insertBio.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return hasil;
    }

}
