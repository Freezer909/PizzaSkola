package Pica;

import java.util.ArrayList;

public class Picerija {
    public static ArrayList<Pica> picasSaraksts = new ArrayList<>();
    
    public static ArrayList<String> visasPiedevas = new ArrayList<>();
    public static ArrayList<String> visasMerces = new ArrayList<>();
    public static String[] picasIzmeri = {"Mazā", "Vidējā", "Lielā"};

    public static void inicializetDatus() {
        if (picasSaraksts.isEmpty()) {
            picasSaraksts.add(new Pica("Margarita", 7.50));
            picasSaraksts.add(new Pica("Studentu", 6.00));
        }

        if (visasPiedevas.isEmpty()) {
            visasPiedevas.add("Siers");
            visasPiedevas.add("Peperoni");
            visasPiedevas.add("Sēnes");
            visasPiedevas.add("Bekons");
        }

        if (visasMerces.isEmpty()) {
            visasMerces.add("Tomātu mērce");
            visasMerces.add("Ķiploku mērce");
            visasMerces.add("BBQ mērce");
        }
    }
    
    public static void pievienotJaunuPiedevu(String nosaukums) {
        if(!visasPiedevas.contains(nosaukums)) {
            visasPiedevas.add(nosaukums);
        }
    }
}