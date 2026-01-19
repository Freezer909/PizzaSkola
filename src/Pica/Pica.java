package Pica;

import java.util.ArrayList;
import java.util.Arrays;

public class Pica {
    String nosaukums;
    double pamataCena;
    String izmers; 
    boolean sieraMalina;
    
    String[] pamataPiedevas; 
    
    ArrayList<String> papildusPiedevas = new ArrayList<>();

    public Pica(String nosaukums, double pamataCena, String[] pamataPiedevas) {
        this.nosaukums = nosaukums;
        this.pamataCena = pamataCena;
        this.pamataPiedevas = pamataPiedevas; 
        this.izmers = "Vidējā";
    }

    public double aprekinatGalaCenu() {
        double galaCena = pamataCena;
        
        if (izmers.equals("Lielā")) galaCena += 2.0;
        if (izmers.equals("Mazā")) galaCena -= 1.5;
        
        galaCena += papildusPiedevas.size() * 0.80;

        if (sieraMalina) galaCena += 1.50;
        
        return galaCena;
    }

    public void pievienotPapildusPiedevu(String piedeva) {
        papildusPiedevas.add(piedeva);
    }
    
    public String[] getPamataPiedevas() {
    	return pamataPiedevas;
    	}
    
    public String getNosaukums() { 
    	return nosaukums; 
    	}
    
    public double getPamataCena() {
    	return pamataCena; 
    	}
    
    public void setIzmers(String izmers) { 
    	this.izmers = izmers; 
    	}
    
    public void setSierainaMalina(boolean ir) {
    	this.sieraMalina = ir; 
    	}
    
    public String getSastavs() {

        String baze = Arrays.toString(pamataPiedevas);
        String papildus = papildusPiedevas.isEmpty() ? "" : " + Papildus: " + papildusPiedevas.toString();
        return baze + papildus;
    }

    @Override
    public String toString() {
        return nosaukums + " [" + izmers + "]";
    }
}