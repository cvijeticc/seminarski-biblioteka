
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Komunikacija;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author andri
 */
public class Posiljalac {
    private Socket socket;

    public Posiljalac(Socket socket) {
        this.socket = socket;
    }
    
    public void posalji(Object obj){//ne znamo da li ce da bude Zahtev ili Odg
        try {
        
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(obj);
        oos.flush();
        
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
    
}
