package com.company;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FichierPatient {
    private JPanel Mainfichier;
    private JTextField txtAvis;
    private JTextField txtMedicament;
    private JTextField txtIdPatient;
    private JTextField txtIdMedecin;
    private JButton AjouterButton;
    private JButton chercherButton;
    private JButton SupprimerButton;
    private JButton modifierButton;
   // private JScrollPane table_1;
    private JTable table1;



    public static void main(String[] args) {
        JFrame frame = new JFrame("FichierPatient");
        frame.setContentPane(new FichierPatient().Mainfichier);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    Connection con;
    PreparedStatement pst;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/AppGestionCabinet", "root", "");
            System.out.println("Successs");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();

        }
    }

    void table_load(){
        try
        {
            pst = con.prepareStatement("select * from fichepatient");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

public FichierPatient() {
    connect();
    table_load();


    AjouterButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String IdPatient, IdMedecin, Avis, Medicament;

            IdPatient = txtIdPatient.getText();
            IdMedecin = txtIdMedecin.getText();
            Avis = txtAvis.getText();
            Medicament = txtMedicament.getText();

            try {
                pst = con.prepareStatement("insert into FichePatient(IdPatient, IdMedecin, Avis, Medicament)values(?,?,?,?)");
                pst.setString(1, IdPatient);
                pst.setString(2, IdMedecin);
                pst.setString(3, Avis);
                pst.setString(4, Medicament);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                table_load();
                txtIdPatient.setText("");
                txtIdMedecin.setText("");
                txtAvis.setText("");
                txtMedicament.setText("");
                txtIdPatient.requestFocus();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }


        }
    });
    chercherButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String IdPatient = txtIdPatient.getText();

                pst = con.prepareStatement("select IdMedecin,Avis,Medicament from fichepatient where IdPatient = ?");
                pst.setString(1, IdPatient);
                ResultSet rs = pst.executeQuery();

                if (rs.next() == true) {
                    String IdMedecin = rs.getString(1);
                    String Avis = rs.getString(2);
                    String Medicament = rs.getString(3);


                    txtIdMedecin.setText(IdMedecin);
                    txtAvis.setText(Avis);
                    txtMedicament.setText(Medicament);


                } else {
                    txtIdMedecin.setText("");
                    txtAvis.setText("");
                    txtMedicament.setText("");

                    JOptionPane.showMessageDialog(null, "Invalid  ");

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    });
    SupprimerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String IdPatient;

            IdPatient = txtIdPatient.getText();

            try {
                pst = con.prepareStatement("delete from fichepatient where IdPatient= ?");

                pst.setString(1, IdPatient);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                table_load();
                txtIdMedecin.setText("");
                txtAvis.setText("");
                txtMedicament.setText("");

                txtIdMedecin.requestFocus();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }

        }
    });


    modifierButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String IdPatient, IdMedecin, Avis, Medicament;

            IdMedecin= txtIdMedecin.getText();
            Avis= txtAvis.getText();
            Medicament =txtMedicament.getText();
            IdPatient = txtIdPatient.getText();


            try {
                pst = con.prepareStatement("update fichepatient set IdMedecin = ?,Avis = ?,Medicament =? where IdPatient = ?");
                pst.setString(1,  IdMedecin);
                pst.setString(2,  Avis);
                pst.setString(3,Medicament);
                pst.setString(4,  IdPatient );



                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                table_load();
                txtIdMedecin.setText("");
                txtAvis.setText("");
                txtMedicament.setText("");

                txtIdMedecin.requestFocus();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    });


}



}
