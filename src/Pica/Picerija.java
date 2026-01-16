package Pica;

import java.util.ArrayList;

public class Picerija {

    public static ArrayList<Pica> picasSaraksts = new ArrayList<>();
    
    public static String[] dzerieniSaraksts = {"Ūdens", "Pepsi", "Fanta", "Sula", "Cola"};
    public static String[] uzkodasSaraksts = {"Frī kartupeļi", "Sīpolu gredzeni", "Mērce (ķiploku)"};


    public static void GatavasPicas() {
        if (picasSaraksts.isEmpty()) {
            picasSaraksts.add(new Pica("Margarita", 7.50));
            picasSaraksts.add(new Pica("Studentu", 6.00));
            picasSaraksts.add(new Pica("Havaju", 8.50));
            picasSaraksts.add(new Pica("Lauku", 9.00));
        }
    }
}