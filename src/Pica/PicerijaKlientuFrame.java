package Pica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class PicerijaKlientuFrame {
	
	public static JFrame galvenaisLogs;
	
	public static void pasutisanasPanelis() {
		galvenaisLogs = new JFrame("Pasūtījums uz vietas");
		galvenaisLogs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		galvenaisLogs.setSize(1300, 800);
		galvenaisLogs.setLocationRelativeTo(null);
		galvenaisLogs.setLayout(null); 
		galvenaisLogs.setResizable(false);
		galvenaisLogs.setAlwaysOnTop(true);

		Picerija.DefaultDati();
		
		JLabel virsraksts = new JLabel("JAUNS PASŪTĪJUMS");
		virsraksts.setFont(new Font("Arial", Font.BOLD, 22));
		virsraksts.setBounds(50, 20, 300, 40);
		galvenaisLogs.add(virsraksts);

		JLabel lblPica = new JLabel("Izvēlieties picu:");
		lblPica.setBounds(50, 80, 150, 25);
		galvenaisLogs.add(lblPica);
		
		JComboBox<Pica> comboPica = new JComboBox<>(Picerija.picasSaraksts.toArray(new Pica[0]));
		comboPica.setBounds(200, 80, 300, 30);
		galvenaisLogs.add(comboPica);

		JLabel lblIzmers = new JLabel("Picas izmērs:");
		lblIzmers.setBounds(50, 130, 150, 25);
		galvenaisLogs.add(lblIzmers);
		
		JComboBox<String> comboIzmers = new JComboBox<>(Picerija.picasIzmeri);
		comboIzmers.setBounds(200, 130, 150, 30);
		galvenaisLogs.add(comboIzmers);

		JLabel lblPiedevas = new JLabel("Papildus piedevas (0.80€):");
		lblPiedevas.setFont(new Font("Arial", Font.BOLD, 14));
		lblPiedevas.setBounds(50, 180, 250, 25);
		galvenaisLogs.add(lblPiedevas);

		ArrayList<JCheckBox> piedevuKastes = new ArrayList<>();
		int y = 210;
		for (String piedeva : Picerija.visasPiedevas) {
			JCheckBox cb = new JCheckBox(piedeva);
			cb.setBounds(50, y, 200, 20);
			galvenaisLogs.add(cb);
			piedevuKastes.add(cb);
			y += 30;
		}

		JCheckBox chkMalina = new JCheckBox("Sieraina maliņa (+1.50€)");
		chkMalina.setBounds(300, 210, 200, 25);
		galvenaisLogs.add(chkMalina);

		JLabel lblDzerieni = new JLabel("Dzērieni:");
		lblDzerieni.setBounds(300, 280, 100, 25);
		galvenaisLogs.add(lblDzerieni);

		JComboBox<UzkodasDzerieni> comboDzerieni = new JComboBox<>(Picerija.dzerieni.toArray(new UzkodasDzerieni[0]));
		comboDzerieni.setBounds(300, 310, 200, 30);
		galvenaisLogs.add(comboDzerieni);
		
		JLabel cenaLbl = new JLabel("Kopā apmaksai: 0.00€");
		cenaLbl.setFont(new Font("Arial", Font.BOLD, 24));
		cenaLbl.setForeground(new Color(44, 62, 80));
		cenaLbl.setBounds(50, 520, 400, 40);
		galvenaisLogs.add(cenaLbl);

		JButton btnPirkt = new JButton("Pirkt");
		btnPirkt.setBounds(50, 580, 500, 50);
		btnPirkt.setBackground(new Color(39, 174, 96));
		btnPirkt.setForeground(Color.WHITE);
		btnPirkt.setFont(new Font("Arial", Font.BOLD, 16));
		galvenaisLogs.add(btnPirkt);

		ActionListener cenuAtjaunot = e -> {
			Pica izveleta = (Pica) comboPica.getSelectedItem();
			if (izveleta != null) {
				Pica kalk = new Pica(izveleta.getNosaukums(), izveleta.getPamataCena());
				kalk.setIzmers((String) comboIzmers.getSelectedItem());
				kalk.setSierainaMalina(chkMalina.isSelected());
				for (JCheckBox cb : piedevuKastes) {
					if (cb.isSelected()) kalk.pievienotPiedevu(cb.getText());
				}
				double galaCena = kalk.aprekinatGalaCenu();
				cenaLbl.setText("Kopā apmaksai: " + String.format("%.2f", galaCena) + "€");
			}
		};

		comboPica.addActionListener(cenuAtjaunot);
		comboIzmers.addActionListener(cenuAtjaunot);
		chkMalina.addActionListener(cenuAtjaunot);
		for (JCheckBox cb : piedevuKastes) cb.addActionListener(cenuAtjaunot);
		
		cenuAtjaunot.actionPerformed(null);

		btnPirkt.addActionListener(e -> {
			try (PrintWriter pw = new PrintWriter(new FileWriter("Pasutijumi.txt", true))) {
				pw.println(" PICA UZ VIETAS ");
				pw.println("PICA: " + comboPica.getSelectedItem() + " [" + comboIzmers.getSelectedItem() + "]");
				
				String piedevas = "";
				for(JCheckBox cb : piedevuKastes) if(cb.isSelected()) piedevas += cb.getText() + " ";
				pw.println("PIEDEVAS: " + (piedevas.isEmpty() ? "Nav" : piedevas));
				
				pw.println("SUMMA: " + cenaLbl.getText().replace("Kopā apmaksai: ", ""));
				pw.println("----------------------\n");

				JOptionPane.showMessageDialog(galvenaisLogs, "Pasūtījums pieņemts! Pica būs gatava drīz.");
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(galvenaisLogs, "Kļūda kases sistēmā!");
			}
		});

		galvenaisLogs.setVisible(true);
	}
}