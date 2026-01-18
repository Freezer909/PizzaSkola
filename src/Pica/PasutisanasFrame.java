package Pica;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.*;
import entity.Player;

public class PasutisanasFrame {
    
    public static String KlientaVards = "Nav zināms";
    public static String KlientaTel = "Nav zināms";
    public static String KlientaAdrese = "Nav zināms";
    public static JFrame pasutisanasPanels;

    public static void pasutisanasPanelis() {
        pasutisanasPanels = new JFrame("Pasūtīšanas Panelis");
        pasutisanasPanels.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pasutisanasPanels.setResizable(false);
        pasutisanasPanels.setSize(1300, 800);
        pasutisanasPanels.setLocationRelativeTo(null); 
        pasutisanasPanels.setAlwaysOnTop(true); 
        pasutisanasPanels.setLayout(new BorderLayout());

        CardLayout cl = new CardLayout();
        JPanel main = new JPanel(cl);

        JPanel adresePanel = AdresePanelis();
        JPanel pasutitPanel = PasutitPanelis();

        main.add(adresePanel, "ADRESE");
        main.add(pasutitPanel, "PASUTIT");

        JPanel izv = IzvelesPanelis(cl, main);
        
        pasutisanasPanels.add(izv, BorderLayout.WEST);
        pasutisanasPanels.add(main, BorderLayout.CENTER);
        
        pasutisanasPanels.setVisible(true);
    }
    
    private static JPanel IzvelesPanelis(CardLayout cl, JPanel main) {
        JPanel izv = new JPanel();
        izv.setLayout(null); 
        izv.setPreferredSize(new Dimension(200, 800));
        izv.setBackground(Color.DARK_GRAY);
        
        JButton adrese = new JButton("Ievadīt adresi");
        JButton pasutit = new JButton("Pasūtīt picu");
        
        adrese.setBounds(20, 280, 160, 50);
        pasutit.setBounds(20, 350, 160, 50);
        
        adrese.setFocusable(false);
        pasutit.setFocusable(false);
        
        adrese.addActionListener(e -> cl.show(main, "ADRESE"));
        pasutit.addActionListener(e -> cl.show(main, "PASUTIT"));
        
        izv.add(adrese);
        izv.add(pasutit);
        
        return izv;
    }

    private static JPanel AdresePanelis() {

    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	panel.setBackground(new Color(240, 240, 240));

    	JLabel Vards = new JLabel("Vārds:");
    	Vards.setBounds(100, 120, 100, 30);

    	JTextField VardaIevad = new JTextField();
    	VardaIevad.setBounds(200, 120, 300, 30);

    	JLabel VardaKluda = new JLabel("");
    	VardaKluda.setBounds(200, 150, 300, 20);
    	VardaKluda.setForeground(Color.RED);
    	VardaKluda.setFont(new Font("Arial", Font.PLAIN, 10));

    	JLabel Tel = new JLabel("Telefons:");
    	Tel.setBounds(100, 170, 100, 30);

    	JTextField TelIevad = new JTextField();
    	TelIevad.setBounds(200, 170, 300, 30);

    	JLabel TelKluda = new JLabel("");
    	TelKluda.setBounds(200, 200, 300, 20);
    	TelKluda.setForeground(Color.RED);
    	TelKluda.setFont(new Font("Arial", Font.PLAIN, 10));

    	JLabel Adr = new JLabel("Adrese:");
    	Adr.setBounds(100, 220, 100, 30);
    	
    	JTextField AdreseIevad = new JTextField();
    	AdreseIevad.setBounds(200, 220, 300, 30);
    	
    	JLabel AdreseKluda = new JLabel("");
    	AdreseKluda.setBounds(200, 250, 300, 20);
    	AdreseKluda.setForeground(Color.RED);
    	AdreseKluda.setFont(new Font("Arial", Font.PLAIN, 10));

    	JButton PievienotPoga = new JButton("Pievienot");

    	PievienotPoga.setBounds(200, 300, 120, 40);
    	PievienotPoga.setBackground(new Color(46, 204, 113));
    	PievienotPoga.setForeground(Color.WHITE);
    	PievienotPoga.addActionListener(e -> {

    	VardaKluda.setText("");
    	TelKluda.setText("");
    	AdreseKluda.setText("");

    	String vards = VardaIevad.getText().trim();
    	String tel = TelIevad.getText().trim();
    	String adrese = AdreseIevad.getText().trim();
    	boolean hasError = false;
    	// Vārda pārbaude

    	if (vards.isEmpty()) {
    	VardaKluda.setText("Obligāti jāizpilda!");
    	hasError = true;

    	} else if (!vards.matches("^[\\p{L} .]+$")) {
    	VardaKluda.setText("Nedrīkst ievadīt simbolus/skaitļus!");
    	hasError = true;

    	}
    	// Telefona pārbaude

    	if (tel.isEmpty()) {
    	TelKluda.setText("Obligāti jāizpilda!");
    	hasError = true;
    	
    	} else if (!tel.matches("\\d+")) {
    		
    	TelKluda.setText("Nedrīkst ievadīt burtus!");
    	hasError = true;

    	}

    	if (adrese.isEmpty()) {
    	AdreseKluda.setText("Adrese ir obligāta piegādei!");
    	hasError = true;
    	}

    	if (!hasError) {
    		
    	KlientaVards = vards;
    	KlientaTel = tel;
    	KlientaAdrese = adrese;

    	try {
    	FileWriter fw = new FileWriter("Adrese.txt", true);
    	PrintWriter pw = new PrintWriter(fw);
    	pw.println(vards+", "+tel+", "+adrese);
    	pw.println("-------------------------------\n");
    	JOptionPane.showMessageDialog(pasutisanasPanels, "Adrese veiksmigi pievienota!");
    	pw.close();

    	}catch(IOException ex) {
    	JOptionPane.showMessageDialog(null,
    	"Kļūda ierakstot failā", "Kluda",
    	JOptionPane.ERROR_MESSAGE);

    	}

    }

    	});

    	panel.add(Vards); panel.add(VardaIevad); panel.add(VardaKluda);
    	panel.add(Tel); panel.add(TelIevad); panel.add(TelKluda);
    	panel.add(Adr); panel.add(AdreseIevad); panel.add(AdreseKluda);
    	panel.add(PievienotPoga);

    	return panel;
    }

    private static JPanel PasutitPanelis() {
        Picerija.DefaultDati();
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        // Pica
        JLabel picaLbl = new JLabel("Izvēlieties picu:");
        picaLbl.setBounds(50, 30, 150, 25);
        panel.add(picaLbl);

        JComboBox<Pica> picasCombo = new JComboBox<>(Picerija.picasSaraksts.toArray(new Pica[0]));
        picasCombo.setBounds(50, 60, 250, 30);
        panel.add(picasCombo);
        
        // Izmērs
        JLabel izmersLbl = new JLabel("Izmērs:");
        izmersLbl.setBounds(320, 30, 100, 25);
        panel.add(izmersLbl);

        JComboBox<String> izmeruCombo = new JComboBox<>(Picerija.picasIzmeri);
        izmeruCombo.setBounds(320, 60, 150, 30);
        panel.add(izmeruCombo);
        
        // Piedeva
        JLabel piedevuLbl = new JLabel("Papildus piedevas (0.80€/gab):");
        piedevuLbl.setBounds(50, 110, 250, 25);
        panel.add(piedevuLbl);

        ArrayList<JCheckBox> piedevuKastes = new ArrayList<>();
        int yPos = 140;

        for (String piedeva : Picerija.papildusPiedevas) { 
            JCheckBox cb = new JCheckBox(piedeva);
            cb.setBounds(50, yPos, 150, 20);
            cb.setBackground(Color.WHITE);
            panel.add(cb);
            piedevuKastes.add(cb);
            yPos += 25;
        }

        // Mērces
        JLabel merceLbl = new JLabel("Mērce:");
        merceLbl.setBounds(320, 110, 100, 25);
        panel.add(merceLbl);

        JComboBox<String> mercesCombo = new JComboBox<>(Picerija.visasMerces.toArray(new String[0]));
        mercesCombo.setBounds(320, 140, 150, 30);
        panel.add(mercesCombo);
        
        // Uzkodas
        JLabel Uzkodas = new JLabel("Uzkodas:");
        Uzkodas.setBounds(550, 30, 100, 25);
        panel.add(Uzkodas);

        JComboBox<Uzkodas> UzkodasCombo = new JComboBox<>(Picerija.Uzkodas.toArray(new Uzkodas[0]));
        UzkodasCombo.setBounds(550, 60, 150, 30);
        panel.add(UzkodasCombo);
        
        // Dzērieni
        JLabel Dzerieni = new JLabel("Dzērieni:");
        Dzerieni.setBounds(750, 30, 100, 25);
        panel.add(Dzerieni);

        JComboBox<Dzerieni> DzerieniCombo = new JComboBox<>(Picerija.dzerieni.toArray(new Dzerieni[0]));
        DzerieniCombo.setBounds(750, 60, 150, 30);
        panel.add(DzerieniCombo);

        // Maliņa
        JCheckBox chkMalina = new JCheckBox("Sieraina maliņa (+1.50€)");
        chkMalina.setBounds(320, 190, 200, 25);
        chkMalina.setBackground(Color.WHITE);
        panel.add(chkMalina);

        // Cenas
        JLabel kopejaCenaLbl = new JLabel("Cena: 0.00€");
        kopejaCenaLbl.setFont(new Font("Arial", Font.BOLD, 18));
        kopejaCenaLbl.setBounds(720, 480, 250, 45); 
        panel.add(kopejaCenaLbl);
        
        JLabel piegadeLbl = new JLabel("+ Piegāde: 2.00€");
        piegadeLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        piegadeLbl.setForeground(Color.GRAY);
        piegadeLbl.setBounds(825, 480, 150, 25);
        panel.add(piegadeLbl);

        // kalk
        ActionListener cenasAtjaunotajs = e -> {
            Pica sablons = (Pica) picasCombo.getSelectedItem();
            double kopejaSumma = 0;

            if (sablons != null) {
            	Pica kalkulators = new Pica(sablons.getNosaukums(), sablons.getPamataCena(), sablons.getPamataPiedevas());
                kalkulators.setIzmers((String) izmeruCombo.getSelectedItem());
                kalkulators.setSierainaMalina(chkMalina.isSelected());
                
                for (JCheckBox cb : piedevuKastes) {
                    if (cb.isSelected()) kalkulators.pievienotPapildusPiedevu(cb.getText());
                }
                
                kopejaSumma += kalkulators.aprekinatGalaCenu();
            }

            // Uzkodas, Dzērieni
            Uzkodas izveletaUzkoda = (Uzkodas) UzkodasCombo.getSelectedItem();
            if (izveletaUzkoda != null) kopejaSumma += izveletaUzkoda.getCena();

            Dzerieni izveletaisDzeriens = (Dzerieni) DzerieniCombo.getSelectedItem();
            if (izveletaisDzeriens != null) kopejaSumma += izveletaisDzeriens.getCena();

            kopejaSumma += 2.00;
            
            kopejaCenaLbl.setText("Kopā: " + String.format("%.2f", kopejaSumma) + "€");
        };

        picasCombo.addActionListener(cenasAtjaunotajs);
        izmeruCombo.addActionListener(cenasAtjaunotajs);
        chkMalina.addActionListener(cenasAtjaunotajs);
        UzkodasCombo.addActionListener(cenasAtjaunotajs);
        DzerieniCombo.addActionListener(cenasAtjaunotajs);
        for (JCheckBox cb : piedevuKastes) cb.addActionListener(cenasAtjaunotajs);
    
        cenasAtjaunotajs.actionPerformed(null);

        JButton pirktPoga = new JButton("PIRKT");
        pirktPoga.setBounds(300, 500, 400, 45);
        pirktPoga.setBackground(new Color(39, 174, 96));
        pirktPoga.setForeground(Color.WHITE);
        pirktPoga.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(pirktPoga);

        pirktPoga.addActionListener(e -> {
            if (KlientaVards.equals("Nav zināms") || KlientaAdrese.isEmpty()) {
                JOptionPane.showMessageDialog(pasutisanasPanels, "Lūdzu, vispirms ievadiet adresi!", "Kļūda", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            double summa = Double.parseDouble(kopejaCenaLbl.getText().replace("Kopā: ", "").replace("€", "").replace(",", "."));
            if (Player.Nauda < summa) {
                JOptionPane.showMessageDialog(pasutisanasPanels, "Nepietiek naudas!", "Kļūda", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Player.Nauda -= summa;


            Pica mainigaPica = (Pica) picasCombo.getSelectedItem();
            Pica galaPica = new Pica(mainigaPica.getNosaukums(), mainigaPica.getPamataCena(), mainigaPica.getPamataPiedevas());
            galaPica.setIzmers((String) izmeruCombo.getSelectedItem());
            for (JCheckBox cb : piedevuKastes) {
                if(cb.isSelected()) galaPica.pievienotPapildusPiedevu(cb.getText());
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter("Pasutijumi.txt", true))) {
                pw.println("PASŪTĪJUMS KLIENTAM: " + KlientaVards);
                pw.println("Adrese: " + KlientaAdrese);
                pw.println("Pica: " + galaPica.toString());
                pw.println("Sastāvs: " + galaPica.getSastavs());
                pw.println("SUMMA: " + String.format("%.2f", summa) + "€");
                pw.println("------------------------------------------\n");
                
                JOptionPane.showMessageDialog(pasutisanasPanels, "Pasūtījums pieņemts! Paldies!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(pasutisanasPanels, "Kļūda failā!");
            }
        });

        return panel;
    }
}