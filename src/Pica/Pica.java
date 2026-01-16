package Pica;

import java.util.ArrayList;

public class Pica {
    private String nosaukums;
    private double pamataCena;
    private String izmers; 
    private String merce;
    private boolean sierainaMalina;
    private ArrayList<String> piedevas = new ArrayList<>();

    public Pica(String nosaukums, double pamataCena) {
        this.nosaukums = nosaukums;
        this.pamataCena = pamataCena;
        this.izmers = "Vidējā";
    }

    public double aprekinatGalaCenu() {
        double galaCena = pamataCena;
        
        if (izmers.equals("Lielā")) galaCena += 2.0;
        if (izmers.equals("Mazā")) galaCena -= 1.5;
        
        galaCena += piedevas.size() * 0.80;

        if (sierainaMalina) galaCena += 1.50;
        
        return galaCena;
    }


    public void setIzmers(String izmers) { 
    	this.izmers = izmers; 
    }
    
    public void setMerce(String merce) {
    	this.merce = merce; 
    }
    
    public void setSierainaMalina(boolean ir) {
    	this.sierainaMalina = ir; 
    }
    
    public String getNosaukums() {
    	return nosaukums;
    }
    
    public double getPamataCena() {
    	return pamataCena;
    }
    
    public void pievienotPiedevu(String piedeva) {
    	this.piedevas.add(piedeva); 
    }
    
    @Override
    public String toString() {
        return nosaukums + " [" + izmers + "]";
    }

}