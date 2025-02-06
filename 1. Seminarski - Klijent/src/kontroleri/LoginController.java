/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Radnik;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListener();
    }

    private void addActionListener() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String ki = lf.getTxtusername().getText().trim();
                String pass = String.valueOf(lf.getPass().getPassword());

                Komunikacija.getInstance().Konekcija();
                Radnik ulogovani = Komunikacija.getInstance().logIn(ki, pass);

                if (ulogovani == null) {

                    JOptionPane.showMessageDialog(lf, "Prijava na sistem neuspesna",
                            "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(lf, "Prijava na sistem uspesna",
                            "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    lf.dispose();
                }

            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
