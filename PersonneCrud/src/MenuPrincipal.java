import com.company.FichierPatient;
import com.company.FichierRdv;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {


    JFrame frame=new JFrame();
    JLabel label =new JLabel("Bienvenue");

    private JPanel MainMenu;

    private JButton gestionRendez_vousButton;
    private static JButton gestionPatientButton;
    private JButton gestionMedecinButton;
    private JButton gestionFichiersPatientsButton;


    public MenuPrincipal() {
        gestionPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {








            }
        });
    }

    public static void main(String[] args) {
        Patient patient=new Patient();
        Medecinn medecinn=new Medecinn();
        FichierRdv fichierRdv=new FichierRdv();
        FichierPatient fichierPatient=new FichierPatient();




        JFrame frame = new JFrame("MenuPrincipal");
        frame.setContentPane(new MenuPrincipal().MainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);




    }


}
