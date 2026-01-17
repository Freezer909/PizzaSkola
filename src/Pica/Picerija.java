package Pica;

import java.util.ArrayList;

public class Picerija {
    public static ArrayList<Pica> picasSaraksts = new ArrayList<>();
    public static ArrayList<String> visasPiedevas = new ArrayList<>();
    public static ArrayList<String> visasMerces = new ArrayList<>();
    public static String[] picasIzmeri = {"Mazā", "Vidējā", "Lielā"};
    
    // JAUNIE SARAKSTI
    public static ArrayList<UzkodasDzerieni> papildusEdieni = new ArrayList<>();
    public static ArrayList<UzkodasDzerieni> dzerieni = new ArrayList<>();

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


        if (papildusEdieni.isEmpty()) {
            papildusEdieni.add(new UzkodasDzerieni("Frī kartupeļi", 2.50));
            papildusEdieni.add(new UzkodasDzerieni("Vistas nageti", 3.80));
        }

        if (dzerieni.isEmpty()) {
            dzerieni.add(new UzkodasDzerieni("Coca-Cola", 1.50));
            dzerieni.add(new UzkodasDzerieni("Fanta", 1.50));
            dzerieni.add(new UzkodasDzerieni("Ūdens", 1.00));
        }
    }
}