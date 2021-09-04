package com.company;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FichierRdv {
    private JPanel MainRdv;
    private JTextField txtrdv;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton chercherButton;
    private JTable table1;
    private JTextField txtid;




    public static void main(String[] args) {
        JFrame frame = new JFrame("FichierRdv");
        frame.setContentPane(new FichierRdv().MainRdv);
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
            pst = con.prepareStatement("select * from rdv" +
                    "");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public FichierRdv() {
        connect();
        table_load();
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String date_de_rdv;

                date_de_rdv = txtrdv.getText();


                try {
                    pst = con.prepareStatement("insert into rdv(date_de_rdv)values(?)");
                    pst.setString(1, date_de_rdv);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                   txtrdv.setText("");

                  txtrdv.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }







            }
        });


        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String idpatient =txtid.getText();

                    pst = con.prepareStatement("select date_de_rdv from rdv where idpatient= ?");
                    pst.setString(1, idpatient);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String date_de_rdv = rs.getString(1);


                        txtrdv.setText( date_de_rdv );


                    }
                    else
                    {
                        txtrdv.setText("");

                        JOptionPane.showMessageDialog(null,"Invalid  ");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });


        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idpatient;

                idpatient = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from rdv  where idpatient = ?");

                    pst.setString(1, idpatient);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtrdv.setText("");

                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }








            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idpatient,date_de_rdv;

               date_de_rdv= txtrdv.getText();

                idpatient = txtid.getText();


                try {
                    pst = con.prepareStatement("update rdv set    date_de_rdv= ? where idpatient = ?");
                    pst.setString(1, date_de_rdv);
                    pst.setString(2, idpatient);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtrdv.setText("");

                    txtrdv.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

















            }
        });
    }















}
