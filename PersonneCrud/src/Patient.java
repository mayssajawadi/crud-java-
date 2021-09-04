import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Patient {


    private JPanel Main;
    private JTextField txtnom;
    private JTextField txtprenom;
    private JTextField txtadresse;
    private JTextField txtnum;
    private JTable table1;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtid;

    private JScrollPane table_1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Personne");

        frame.setContentPane(new Patient().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        JButton patient = new JButton("MenuPrincipal");


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
            pst = con.prepareStatement("select * from personne");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }









    public Patient() {

        connect();
            table_load();
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String nom, prenom, num_telephone, adresse;

                    nom = txtnom.getText();
                    prenom = txtprenom.getText();
                    num_telephone = txtnum.getText();
                    adresse = txtadresse.getText();

                    try {
                        pst = con.prepareStatement("insert into personne(nom,prenom,num_telephone,adresse)values(?,?,?,?)");
                        pst.setString(1, nom);
                        pst.setString(2, prenom);
                        pst.setString(3, num_telephone);
                        pst.setString(4, adresse);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                        table_load();
                        txtnom.setText("");
                        txtprenom.setText("");
                        txtnum.setText("");
                        txtadresse.setText("");
                        txtnom.requestFocus();
                    }

                    catch (SQLException e1)
                    {

                        e1.printStackTrace();
                    }







                }
            });
            //Search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String empid = txtid.getText();

                    pst = con.prepareStatement("select nom,prenom,num_telephone,adresse from personne where id = ?");
                    pst.setString(1, empid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String nom = rs.getString(1);
                        String prenom = rs.getString(2);
                        String num_telephone = rs.getString(3);
                        String adresse = rs.getString(4);

                        txtnom.setText(nom);
                        txtprenom.setText(prenom);
                        txtnum.setText(num_telephone);
                        txtadresse.setText(adresse);

                    }
                    else
                    {
                        txtnom.setText("");
                        txtprenom.setText("");
                        txtnum.setText("");
                        txtadresse.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid  ");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });


        //update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String empid,nom, prenom, num_telephone, adresse;

                nom = txtnom.getText();
                prenom = txtprenom.getText();
                num_telephone = txtnum.getText();
                adresse = txtadresse.getText();
                empid = txtid.getText();


                try {
                    pst = con.prepareStatement("update personne set nom = ?,prenom = ?,num_telephone = ?,adresse = ? where id = ?");
                    pst.setString(1, nom);
                    pst.setString(2, prenom);
                    pst.setString(3, num_telephone);
                    pst.setString(4, adresse);
                    pst.setString(5, empid);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtnom.setText("");
                    txtprenom.setText("");
                    txtnum.setText("");
                    txtadresse.setText("");
                    txtnom.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });


        //delate
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String empid;

                empid = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from personne  where id = ?");

                    pst.setString(1, empid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtnom.setText("");
                    txtprenom.setText("");
                    txtnum.setText("");
                    txtadresse.setText("");
                    txtnom.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
    }


}
