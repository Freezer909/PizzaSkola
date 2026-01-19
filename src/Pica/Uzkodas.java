package Pica;

public class Uzkodas {
    String nosaukums;
    double cena;

    public Uzkodas(String nosaukums, double cena) {
        this.nosaukums = nosaukums;
        this.cena = cena;
    }

    public String getNosaukums() {
    	return nosaukums; 
    	}
    
    public double getCena() {
    	return cena; 
    	}
    
    @Override
    public String toString() {
        return nosaukums + " (" + String.format("%.2f", cena) + "€)";
    }
}