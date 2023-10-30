/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studi_kasus;


import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

/**
 *
 * @author asus
 */


public class Biodata extends JFrame {

    private DataTable dt = new DataTable();

    public Biodata() {
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    
        JLabel labelHeader = new JLabel("Form Biodata", JLabel.CENTER);
        labelHeader.setBounds(0, 20, 350, 10);

        JLabel labelNama = new JLabel("Nama: ");
        labelNama.setBounds(15, 40, 350, 10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelHP = new JLabel("Nomor HP: ");
        labelHP.setBounds(15, 100, 350, 10);

        JTextField textFieldHP = new JTextField();
        textFieldHP.setBounds(15, 120, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 160, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(15, 180, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 210, 350, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        

        JLabel labelAlamat = new JLabel("Alamat: ");
        labelAlamat.setBounds(15, 240, 350, 30);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 270, 350, 100);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 380, 100, 40);

        JButton buttonUbah = new JButton("Ubah");
        buttonUbah.setBounds(125, 380, 100, 40);
        
        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(235, 380, 100, 40);

        JButton buttonFile = new JButton("Simpan ke File");
        buttonFile.setBounds(345, 380, 150, 40);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15,
                440,
                500,
                200);

      
        BiodataTableModel tableModel = new BiodataTableModel();
        table.setModel(tableModel);

       
        button.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";

                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                    radioButton1.setSelected(false);
                }
                if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                    radioButton2.setSelected(false);
                }

               
                String nama = textFieldNama.getText();
                String telepon = textFieldHP.getText();
                String alamat = txtOutput.getText();

                if (nama.equalsIgnoreCase("") && telepon.equalsIgnoreCase("") && alamat.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(Biodata.this, "Nama, telepon dan alamat belum terisi", "Perhatian",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    if (nama.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(Biodata.this, "Nama belum terisi", "Perhatian",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (telepon.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(Biodata.this, "Telepon belum terisi", "Perhatian",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (alamat.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(Biodata.this, "Alamat belum terisi", "Perhatian",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                int confirmation = JOptionPane.showConfirmDialog(Biodata.this,
                        "Apakah anda yakin ingin menyimpan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    tableModel.add(new ArrayList<>(Arrays.asList(nama, telepon, jenisKelamin, alamat)));
                    dt.setData(new ArrayList<>(Arrays.asList(nama, telepon, jenisKelamin, alamat)));
                    JOptionPane.showMessageDialog(Biodata.this, "Data tersimpan", "Perhatian",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else { 
                    JOptionPane.showMessageDialog(Biodata.this, "Data tidak tersimpan", "Perhatian",
                            JOptionPane.ERROR_MESSAGE);
                }
                
                textFieldNama.setText("");
                textFieldHP.setText("");
                txtOutput.setText("");
            }
        });

        buttonUbah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                String newValue = (String) table.getModel().getValueAt(row, column);

                int confirmation = JOptionPane.showConfirmDialog(Biodata.this,
                        "Apakah anda yakin ingin mengubah data?",
                        "Form Biodata",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    tableModel.setValueAt(newValue, row, column);
                    dt.setDataRow(row, column, newValue);
                    JOptionPane.showMessageDialog(Biodata.this, "Data berhasil diubah", "Perhatian",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (int i = 0; i < dt.getSize(row); i++) {
                        tableModel.setValueAt(dt.getData(row, i),
                                row,
                                i);
                    }
                }
            }
        });

      
        buttonHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int confirmation = JOptionPane.showConfirmDialog(Biodata.this,
                        "Apakah anda yakin ingin menghapus data?",
                        "Form Biodata",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    tableModel.remove(row);
                    dt.remove(row);

                    JOptionPane.showMessageDialog(Biodata.this, "Data berhasil dihapus", "Perhatian",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

       buttonFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); 
                fileChooser.setDialogTitle("Tentukan directory file yang akan disimpan"); 
                
                int userSelection = fileChooser.showSaveDialog(Biodata.this);
                
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath(); 
                    DataTable.saveTableData(table, filePath + ".txt"); 
                    JOptionPane.showMessageDialog(Biodata.this, "Data berhasil disimpan ke file", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(Biodata.this,
                        "Apakah anda yakin ingin keluar aplikasi?.",
                        "Form Biodata",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        for (int j = 0; j < tableModel.getColCount(i); j++) {
                            tableModel.setValueAt(dt.getData(i, j),
                                    i,
                                    j);
                        }
                    }
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        
        this.add(labelHeader);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelHP);
        this.add(textFieldHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(txtOutput);
        this.add(button);
        this.add(buttonUbah);
        this.add(buttonHapus);
        this.add(buttonFile);
        this.add(scrollableTable);

      
        this.setSize(550, 1000);
        this.setLayout(null);
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Biodata A = new Biodata();
                A.setVisible(true);
            }
        });
    }
}