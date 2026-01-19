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

public class PicerijaKlientuFrame {
    
    public static JFrame galvenaisLogs;
    
    public static void KlientaPanelis() {
        galvenaisLogs = new JFrame("Klientu Kase");
        galvenaisLogs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        galvenaisLogs.setResizable(false);
        galvenaisLogs.setSize(1300, 800);
        galvenaisLogs.setLocationRelativeTo(null); 
        galvenaisLogs.setAlwaysOnTop(true); 

        galvenaisLogs.setLayout(new BorderLayout());

        CardLayout cl = new CardLayout();
        JPanel main = new JPanel(cl);

        JPanel kasesPanel = KasesPanelis();
        main.add(kasesPanel, "KASE");

        JPanel izv = IzvelesPanelis(cl, main);
        
        galvenaisLogs.add(izv, BorderLayout.WEST);
        galvenaisLogs.add(main, BorderLayout.CENTER);
        
        galvenaisLogs.setVisible(true);
    }
    
    static JPanel IzvelesPanelis(CardLayout cl, JPanel main) {
        JPanel izv = new JPanel();
        izv.setLayout(null); 
        izv.setPreferredSize(new Dimension(200, 800));
        izv.setBackground(Color.DARK_GRAY);
        
        JButton kase = new JButton("Jauns pasūtījums");
        kase.setBounds(20, 350, 160, 50);
        kase.setFocusable(false);
        kase.addActionListener(e -> cl.show(main, "KASE"));
        
        izv.add(kase);
        return izv;
    }

    static JPanel KasesPanelis() {
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
        
        JLabel izmersLbl = new JLabel("Izmērs:");
        izmersLbl.setBounds(320, 30, 100, 25);
        panel.add(izmersLbl);

        JComboBox<String> izmeruCombo = new JComboBox<>(Picerija.picasIzmeri);
        izmeruCombo.setBounds(320, 60, 150, 30);
        panel.add(izmeruCombo);
        
        // Piedevas
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

        // Mērces, Uzkodas, Dzērieni
        JLabel merceLbl = new JLabel("Mērce:");
        merceLbl.setBounds(320, 110, 100, 25);
        panel.add(merceLbl);
        JComboBox<String> mercesCombo = new JComboBox<>(Picerija.visasMerces.toArray(new String[0]));
        mercesCombo.setBounds(320, 140, 150, 30);
        panel.add(mercesCombo);
        
        JLabel uzkodasLbl = new JLabel("Uzkodas:");
        uzkodasLbl.setBounds(550, 30, 100, 25);
        panel.add(uzkodasLbl);
        JComboBox<Uzkodas> uzkodasCombo = new JComboBox<>(Picerija.Uzkodas.toArray(new Uzkodas[0]));
        uzkodasCombo.setBounds(550, 60, 150, 30);
        panel.add(uzkodasCombo);
        
        JLabel dzerieniLbl = new JLabel("Dzērieni:");
        dzerieniLbl.setBounds(750, 30, 100, 25);
        panel.add(dzerieniLbl);
        JComboBox<Dzerieni> dzerieniCombo = new JComboBox<>(Picerija.dzerieni.toArray(new Dzerieni[0]));
        dzerieniCombo.setBounds(750, 60, 150, 30);
        panel.add(dzerieniCombo);

        JCheckBox chkMalina = new JCheckBox("Sieraina maliņa (+1.50€)");
        chkMalina.setBounds(320, 190, 200, 25);
        chkMalina.setBackground(Color.WHITE);
        panel.add(chkMalina);

        JLabel kopejaCenaLbl = new JLabel("Kopā: 0.00€");
        kopejaCenaLbl.setFont(new Font("Arial", Font.BOLD, 18));
        kopejaCenaLbl.setBounds(720, 480, 250, 45); 
        panel.add(kopejaCenaLbl);
        
        // calc
        ActionListener cenasAtjaunotajs = e -> {
            double kopejaSumma = 0;
            Pica sablons = (Pica) picasCombo.getSelectedItem();
            
            if (sablons != null) {
 
                Pica kalkulators = new Pica(sablons.getNosaukums(), sablons.getPamataCena(), sablons.getPamataPiedevas());
                kalkulators.setIzmers((String) izmeruCombo.getSelectedItem());
                kalkulators.setSierainaMalina(chkMalina.isSelected());
                
                for (JCheckBox cb : piedevuKastes) {
                    if (cb.isSelected()) kalkulators.pievienotPapildusPiedevu(cb.getText());
                }
                kopejaSumma += kalkulators.aprekinatGalaCenu();
            }

            Uzkodas u = (Uzkodas) uzkodasCombo.getSelectedItem();
            if (u != null) kopejaSumma += u.getCena();

            Dzerieni d = (Dzerieni) dzerieniCombo.getSelectedItem();
            if (d != null) kopejaSumma += d.getCena();
            
            kopejaCenaLbl.setText("Kopā: " + String.format("%.2f", kopejaSumma) + "€");
        };

        picasCombo.addActionListener(cenasAtjaunotajs);
        izmeruCombo.addActionListener(cenasAtjaunotajs);
        chkMalina.addActionListener(cenasAtjaunotajs);
        uzkodasCombo.addActionListener(cenasAtjaunotajs);
        dzerieniCombo.addActionListener(cenasAtjaunotajs);
        for (JCheckBox cb : piedevuKastes) cb.addActionListener(cenasAtjaunotajs);
    
        cenasAtjaunotajs.actionPerformed(null);

        // pirkt
        JButton pirktPoga = new JButton("PIRKT");
        pirktPoga.setBounds(300, 500, 400, 45);
        pirktPoga.setBackground(new Color(39, 174, 96));
        pirktPoga.setForeground(Color.WHITE);
        pirktPoga.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(pirktPoga);

        pirktPoga.addActionListener(e -> {
            double summa = Double.parseDouble(kopejaCenaLbl.getText().replace("Kopā: ", "").replace("€", "").replace(",", "."));
            if (Player.Nauda < summa) {
                JOptionPane.showMessageDialog(galvenaisLogs, "Nepietiek naudas!", "Kļūda", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Player.Nauda -= summa;

            Pica sablons = (Pica) picasCombo.getSelectedItem();
            Pica galaPica = new Pica(sablons.getNosaukums(), sablons.getPamataCena(), sablons.getPamataPiedevas());
            galaPica.setIzmers((String) izmeruCombo.getSelectedItem());
            for (JCheckBox cb : piedevuKastes) {
                if(cb.isSelected()) galaPica.pievienotPapildusPiedevu(cb.getText());
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter("Pasutijumi.txt", true))) {
                pw.println("PASŪTĪJUMS UZ VIETAS");
                pw.println("Pica: " + galaPica.toString());
                pw.println("Sastāvs: " + galaPica.getSastavs());
                pw.println("Uzkoda: " + uzkodasCombo.getSelectedItem());
                pw.println("Dzēriens: " + dzerieniCombo.getSelectedItem());
                pw.println("SUMMA: " + String.format("%.2f", summa) + "€");
                pw.println("------------------------------------------\n");
                
                JOptionPane.showMessageDialog(galvenaisLogs, "Pica veiksmīgi nopirkta!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(galvenaisLogs, "Kļūda saglabājot failā!");
            }
        });

        return panel;
    }
}