package Pica;

import java.util.ArrayList;

public class Picerija {
    public static ArrayList<Pica> picasSaraksts = new ArrayList<>();
    public static ArrayList<String> visasPiedevas = new ArrayList<>();
    public static ArrayList<String> visasMerces = new ArrayList<>();
    public static String[] picasIzmeri = {"Mazā", "Vidējā", "Lielā"};
    
    // JAUNIE SARAKSTI
    public static ArrayList<Uzkodas> Uzkodas = new ArrayList<>();
    public static ArrayList<Dzerieni> dzerieni = new ArrayList<>();

    public static void DefaultDati() {
        if (picasSaraksts.isEmpty()) {
            picasSaraksts.add(new Pica("Margarita", 7.50));
            picasSaraksts.add(new Pica("Studentu", 6.00));
        }

        if (visasPiedevas.isEmpty()) {
            visasPiedevas.add("Siers");
            visasPiedevas.add("Peperoni");
        }

        if (visasMerces.isEmpty()) {
            visasMerces.add("Tomātu mērce");
            visasMerces.add("Ķiploku mērce");
        }


        if (Uzkodas.isEmpty()) {
        	Uzkodas.add(new Uzkodas("Frī kartupeļi", 2.50));
        	Uzkodas.add(new Uzkodas("Vistas nageti", 3.80));
        	Uzkodas.add(new Uzkodas("Bez", 0.0));
        }

        if (dzerieni.isEmpty()) {
        	dzerieni.add(new Dzerieni("Coca-Cola", 1.50));
            dzerieni.add(new Dzerieni("Fanta", 1.50));
            dzerieni.add(new Dzerieni("Ūdens", 1.00));
            dzerieni.add(new Dzerieni("Bez", 0.00));
        }
    }
}