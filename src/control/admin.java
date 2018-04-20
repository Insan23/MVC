/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.modelPendaftar;
import model.modelPengguna;
import view.adminDaftarPengguna;
import view.adminHome;

/**
 *
 * @author ASUS
 */
public class admin extends user {

    private adminHome viewHome;
    private adminDaftarPengguna viewDaftar;
    private modelPendaftar mPendaftar;
    private modelPengguna mPengguna;

    private int IDPendaftar;
    private int IDPengguna;

    public admin() {
        viewHome = new adminHome();
        viewDaftar = new adminDaftarPengguna();
        mPendaftar = new modelPendaftar();
        mPengguna = new modelPengguna();
        viewHome.setVisible(true);

        viewHome.terimaListener(new terima());
        viewHome.tolakListener(new tolak());
        viewHome.tabelListener(new tabelPendaftarListener());
        viewHome.logoutListener(new logout(viewHome));
        viewHome.swapPilihan("tutup");

        viewDaftar.logoutListener(new logout(viewDaftar));
        bacaTabel();
    }

    private void bacaTabel() {
        viewHome.setTabel(mPendaftar.bacaTabelPendaftar());
        viewDaftar.setTabel(mPengguna.bacaTabelPengguna());
    }
    
    private class terima implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int pilihan = JOptionPane.showConfirmDialog(viewHome, "Terima Pendaftar dengan ID" + IDPendaftar + "?", "Konfirmasi", JOptionPane.YES_NO_CANCEL_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                if (mPendaftar.terimaPendaftar(IDPendaftar)) {
                    JOptionPane.showMessageDialog(viewHome, "Sukses");
                }
            } else if (pilihan == JOptionPane.NO_OPTION) {
                viewHome.swapPilihan("tutup");
                IDPendaftar = -1;
            } else if (pilihan == JOptionPane.CANCEL_OPTION) {

            }
            bacaTabel();
        }
    }

    private class tolak implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int pilihan = JOptionPane.showConfirmDialog(viewHome, "Terima Pendaftar dengan ID" + IDPendaftar + "?", "Konfirmasi", JOptionPane.YES_NO_CANCEL_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                if (mPendaftar.tolakPendaftar(IDPendaftar) > 0) {
                    JOptionPane.showMessageDialog(viewHome, "Sukses");
                }
            } else if (pilihan == JOptionPane.NO_OPTION) {
                viewHome.swapPilihan("tutup");
                IDPendaftar = -1;
            } else if (pilihan == JOptionPane.CANCEL_OPTION) {

            }
            bacaTabel();
        }

    }

    private class daftarPengguna implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewHome.setVisible(false);
            viewDaftar.setVisible(true);
        }

    }

    private class hapus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class kembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewHome.setVisible(true);
            viewDaftar.setVisible(false);
        }
    }

    private class tabelPendaftarListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            IDPendaftar = viewHome.ambilID(viewHome.getBarisTerpilih());
            viewHome.swapPilihan("buka");
        }

        //<editor-fold defaultstate="collapsed" desc="unused">
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        //</editor-fold>

    }

    private class tabelPenggunaListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        //<editor-fold defaultstate="collapsed" desc="unused">
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        //</editor-fold>
    }
}
