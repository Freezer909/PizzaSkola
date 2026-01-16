package Pica;

import java.awt.*;
import java.util.regex.Pattern;

import javax.swing.*;

public class PasutisanasFrame {
		
	public static JFrame pasutisanasPanels;
	public static String ievade;
	
	public static String virknesParbaude(String zinojums, String noklusejums) {
		 String virkne;
		 do {
			 virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
			 if(virkne == null)
				 return null;
		 }while(!Pattern.matches("^[\\p{L} .]+$", virkne));
		 
		return virkne;
	}
	
	 public static int skaitlaParbaude(String zinojums) {
		 
		  int skaitlis;
		  
		  while(true) {
			  ievade = JOptionPane.showInputDialog(null, zinojums, 
					  "datu ievade", JOptionPane.INFORMATION_MESSAGE);
			  if (ievade == null)
				  return -1;
			  
			  try {
				  skaitlis = Integer.parseInt(ievade);
				  return skaitlis;
			  }catch(NumberFormatException e) {
				  JOptionPane.showMessageDialog(null, 
						  "netika ievadīts vesels skaitlis",
						  "Nekorekti dati", JOptionPane.ERROR_MESSAGE);
			  }
			  
		  }
		}
	
	public static void pasutisanasPanelis() {
		
	    pasutisanasPanels = new JFrame("Pasūtīšanas Panelis");
	    pasutisanasPanels.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    pasutisanasPanels.setResizable(false);
	    pasutisanasPanels.setSize(1300, 800);
	    pasutisanasPanels.setLocationRelativeTo(null); 
	    pasutisanasPanels.setAlwaysOnTop(true); 
	    
	    // Use BorderLayout only for the main structure
	    pasutisanasPanels.setLayout(new BorderLayout());

	    CardLayout cl = new CardLayout();
	    JPanel main = new JPanel(cl);
	    
	    // Create the sub-panels
	    JPanel adresePanel = AdresePanelis();
	    JPanel pasutitPanel = PasutitPanelis();
	    JPanel vesturePanel = VesturePanelis();
	    
	    main.add(adresePanel, "ADRESE");
	    main.add(pasutitPanel, "PASUTIT");
	    main.add(vesturePanel, "VESTURE");
	    
	    // Create the sidebar
	    JPanel izv = IzvelesPanelis(cl, main);
	    
	    pasutisanasPanels.add(izv, BorderLayout.WEST);
	    pasutisanasPanels.add(main, BorderLayout.CENTER);
	    
	    pasutisanasPanels.setVisible(true);
	}
	
	private static JPanel IzvelesPanelis(CardLayout cl, JPanel main) {
		JPanel izv = new JPanel();
		izv.setLayout(null); // Turned off automatic layout
		izv.setPreferredSize(new Dimension(200, 800));
		izv.setBackground(Color.DARK_GRAY);
		
		// Buttons
		JButton adrese = new JButton("Ievadīt adresi");
		JButton pasutit = new JButton("Pasūtīt picu");
		JButton vesture = new JButton("Apskatīties vēsturi");
		
		// Styling and Placement using setBounds(x, y, width, height)
		int bW = 160; // Button Width
		int bH = 50;  // Button Height
		int bX = 20;  // Button X (left margin)
		
		adrese.setBounds(bX, 280, bW, bH);
		pasutit.setBounds(bX, 350, bW, bH);
		vesture.setBounds(bX, 420, bW, bH);
		
		adrese.setFocusable(false);
		pasutit.setFocusable(false);
		vesture.setFocusable(false);

		// Logic to switch screens
		adrese.addActionListener(e -> cl.show(main, "ADRESE"));
		pasutit.addActionListener(e -> cl.show(main, "PASUTIT"));
		vesture.addActionListener(e -> cl.show(main, "VESTURE"));
		
		izv.add(adrese);
		izv.add(pasutit);
		izv.add(vesture);
		
		return izv;
	}

	private static JPanel AdresePanelis() {
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    panel.setBackground(new Color(240, 240, 240));

	    // --- VĀRDS ---
	    JLabel Vards = new JLabel("Vārds:");
	    Vards.setBounds(100, 120, 100, 30);
	    JTextField VardaIevad = new JTextField();
	    VardaIevad.setBounds(200, 120, 300, 30);
	    JLabel VardaKluda = new JLabel("");
	    VardaKluda.setBounds(200, 150, 300, 20);
	    VardaKluda.setForeground(Color.RED);
	    VardaKluda.setFont(new Font("Arial", Font.PLAIN, 10));

	    // --- TELEFONS ---
	    JLabel Tel = new JLabel("Telefons:");
	    Tel.setBounds(100, 170, 100, 30);
	    JTextField TelIevad = new JTextField();
	    TelIevad.setBounds(200, 170, 300, 30);
	    JLabel TelKluda = new JLabel("");
	    TelKluda.setBounds(200, 200, 300, 20);
	    TelKluda.setForeground(Color.RED);
	    TelKluda.setFont(new Font("Arial", Font.PLAIN, 10));

	    // --- ADRESE ---
	    JLabel Adr = new JLabel("Adrese:");
	    Adr.setBounds(100, 220, 100, 30);
	    JTextField AdreseIevad = new JTextField(); // Pārdēvēju uz skaidrāku nosaukumu
	    AdreseIevad.setBounds(200, 220, 300, 30);
	    JLabel AdreseKluda = new JLabel("");
	    AdreseKluda.setBounds(200, 250, 300, 20);
	    AdreseKluda.setForeground(Color.RED);
	    AdreseKluda.setFont(new Font("Arial", Font.PLAIN, 10));

	    // --- POGA (Tagad atkal augstāk, jo e-pasts ir prom) ---
	    JButton PievienotPoga = new JButton("Pievienot");
	    PievienotPoga.setBounds(200, 300, 120, 40);
	    PievienotPoga.setBackground(new Color(46, 204, 113));
	    PievienotPoga.setForeground(Color.WHITE);

	    PievienotPoga.addActionListener(e -> {
	        // Reset kļūdas
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

	        // Adreses pārbaude
	        if (adrese.isEmpty()) {
	            AdreseKluda.setText("Adrese ir obligāta piegādei!");
	            hasError = true;
	        }

	        if (!hasError) {
	            System.out.println("Dati veiksmīgi sagatavoti: " + vards + ", " + tel + ", " + adrese);
	            // Šeit nāks faila rakstīšana
	        }
	    });

	    panel.add(Vards); panel.add(VardaIevad); panel.add(VardaKluda);
	    panel.add(Tel); panel.add(TelIevad); panel.add(TelKluda);
	    panel.add(Adr); panel.add(AdreseIevad); panel.add(AdreseKluda);
	    panel.add(PievienotPoga);

	    return panel;
	}

	private static JPanel PasutitPanelis() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		JLabel temp = new JLabel("Šeit būs picu izvēle (Izmanto setLayout(null) šeit arī)");
		temp.setBounds(50, 50, 400, 30);
		panel.add(temp);
		
		return panel;
	}
	
	private static JPanel VesturePanelis() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel temp = new JLabel("Šeit būs pasūtījumu vēsture");
		temp.setBounds(50, 50, 300, 30);
		panel.add(temp);
		
		return panel;
	}
}