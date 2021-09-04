import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Medecinn {
    private JPanel Mainmed;
    private JTextField txtnom;
    private JTextField txtprenom;
    private JTextField txtadresse;
    private JTextField txtnum;
    private JTextField txtspecialite;
    private JTable table1;
    private JTextField txtid;
    private JButton save;
    private JButton update;
    private JButton Suppprimer;
    private JButton Chercher;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Medecinn");
        frame.setContentPane(new Medecinn().Mainmed);
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
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }


    void table_load() {
        try {
            pst = con.prepareStatement("select * from medicin");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Medecinn() {
        connect();
        table_load();
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nom, prenom, adresse, num_telephone, specialite;

                nom = txtnom.getText();
                prenom = txtprenom.getText();
                adresse = txtadresse.getText();
                num_telephone = txtnum.getText();
                specialite = txtspecialite.getText();

                try {
                    pst = con.prepareStatement("insert into medicin(nom,prenom,adresse,num_telephone,specialite)values(?,?,?,?,?)");
                    pst.setString(1, nom);
                    pst.setString(2, prenom);
                    pst.setString(3, adresse);
                    pst.setString(4, num_telephone);
                    pst.setString(5, specialite);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtnom.setText("");
                    txtprenom.setText("");
                    txtadresse.setText("");
                    txtnum.setText("");
                    txtspecialite.setText("");
                    txtnom.requestFocus();
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }


            }
        });


        Suppprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String medid;

                medid = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from medicin  where id = ?");

                    pst.setString(1, medid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtnom.setText("");
                    txtprenom.setText("");
                    txtadresse.setText("");
                    txtnum.setText("");
                    txtspecialite.setText("");
                    txtnom.requestFocus();
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nom, prenom, adresse,num_telephone,specialite,medid;

                nom = txtnom.getText();
                prenom = txtprenom.getText();
                adresse = txtadresse.getText();
                num_telephone = txtnum.getText();
                specialite = txtspecialite.getText();
                medid = txtid.getText();


                try {
                    pst = con.prepareStatement("update medicin set nom = ?,prenom = ?,adresse = ?,num_telephone = ? ,specialite =? where id = ?");
                    pst.setString(1, nom);
                    pst.setString(2, prenom);
                    pst.setString(3, adresse);
                    pst.setInt(4,Integer.parseInt(num_telephone) );
                    pst.setString(5, specialite);
                    pst.setInt(6, Integer.parseInt(medid));


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtnom.setText("");
                    txtprenom.setText("");
                    txtadresse.setText("");
                    txtnum.setText("");
                    txtspecialite.setText("");
                    txtnom.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String medid = txtid.getText();

                    pst = con.prepareStatement("select nom,prenom,num_telephone,adresse,specialite from medicin where id = ?");
                    pst.setString(1, medid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String nom = rs.getString(1);
                        String prenom = rs.getString(2);
                        String adresse = rs.getString(3);
                        String num_telephone = rs.getString(4);
                        String specialite = rs.getString(5);

                        txtnom.setText(nom);
                        txtprenom.setText(prenom);
                        txtadresse.setText(adresse);
                        txtnum.setText(num_telephone);

                        txtspecialite.setText(specialite);

                    }
                    else
                    {
                        txtnom.setText("");
                        txtprenom.setText("");
                        txtadresse.setText("");
                        txtnum.setText("");

                        txtspecialite.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid  ");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });

    }
}












