package konfiguracija;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija = new Properties();
        try {
//            konfiguracija.load(new FileInputStream(
//                    "C:\\Users\\andri\\Documents\\Andrija\\NetBeansProjects\\4. Seminarski\\1. Seminarski - Klijent\\config\\config.properties.txt"
//));
        konfiguracija.load(new FileInputStream("config/config.properties.txt"));

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }
}
