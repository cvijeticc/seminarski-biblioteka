/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
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
            //implementacija te metode se nalazi na dnu LoginForme
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String ki = lf.getTxtusername().getText().trim();//korisnicko ime
                String pass = String.valueOf(lf.getPass().getPassword());
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

//                if (!ki.matches(emailRegex)) {
//                    JOptionPane.showMessageDialog(null, "Korisničko ime mora biti u formi e-mail adrese!");
//                    return;
//                }

                Komunikacija.getInstance().Konekcija();
                Radnik ulogovani = Komunikacija.getInstance().logIn(ki, pass);

                if (ulogovani == null) {

                    JOptionPane.showMessageDialog(lf, "Prijava na sistem neuspesna",
                            "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    Cordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Prijava na sistem uspesna",
                            "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Cordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }

            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
