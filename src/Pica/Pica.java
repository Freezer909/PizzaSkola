package Pica;

import java.util.ArrayList;

public class Pica {
    private String nosaukums;
    private double pamataCena;
    private String izmers; // Mazā, Vidējā, Lielā
    
    public Pica(String nosaukums, double pamataCena) {
        this.nosaukums = nosaukums;
        this.pamataCena = pamataCena;
        this.izmers = "Vidējā"; // Default
    }

    // Šī metode ir svarīga JComboBox - tā nosaka, ko lietotājs redz sarakstā
    @Override
    public String toString() {
        return nosaukums + " (" + pamataCena + "€)";
    }

    // Getteri un Setteri
    public String getNosaukums() { return nosaukums; }
    public double getPamataCena() { return pamataCena; }
}