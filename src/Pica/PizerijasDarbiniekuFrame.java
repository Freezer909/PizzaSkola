package Pica;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class PizerijasDarbiniekuFrame {

	public static void raditDarbiniekaPaneli() {
		JFrame DarbiniekuPanelis = new JFrame("Darbinieku Panelis: Piedāvājuma Rediģēšana");
		DarbiniekuPanelis.setSize(1300, 800);
		DarbiniekuPanelis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DarbiniekuPanelis.setLocationRelativeTo(null);


		JPanel saturaPanelis = new JPanel();
		saturaPanelis.setLayout(null);
		saturaPanelis.setPreferredSize(new Dimension(500, 850)); 

		
		JLabel lblPicaV = new JLabel("PIEVIENOT PICU");
		lblPicaV.setFont(new Font("Arial", Font.BOLD, 14));
		lblPicaV.setBounds(30, 20, 200, 25);
		saturaPanelis.add(lblPicaV);

		JTextField txtPicasNos = new JTextField("");
		txtPicasNos.setBounds(30, 50, 150, 25);
		saturaPanelis.add(txtPicasNos);

		JTextField txtPicasCena = new JTextField("");
		txtPicasCena.setBounds(190, 50, 80, 25);
		saturaPanelis.add(txtPicasCena);

		JButton btnPievienotPicu = new JButton("Pievienot");
		btnPievienotPicu.setBounds(280, 50, 120, 25);
		saturaPanelis.add(btnPievienotPicu);


		JSeparator sep1 = new JSeparator();
		sep1.setBounds(20, 100, 480, 2);
		saturaPanelis.add(sep1);

		JLabel lblPiedevaV = new JLabel("PIEVIENOT PIEDEVU VAI MĒRCI");
		lblPiedevaV.setFont(new Font("Arial", Font.BOLD, 14));
		lblPiedevaV.setBounds(30, 110, 300, 25);
		saturaPanelis.add(lblPiedevaV);

		JTextField txtPiedeva = new JTextField("");
		txtPiedeva.setBounds(30, 140, 240, 25);
		saturaPanelis.add(txtPiedeva);

		JButton btnPievienotPiedevu = new JButton("Pievienot Piedevu");
		btnPievienotPiedevu.setBounds(280, 140, 150, 25);
		saturaPanelis.add(btnPievienotPiedevu);

		JTextField txtMerce = new JTextField("");
		txtMerce.setBounds(30, 175, 240, 25);
		saturaPanelis.add(txtMerce);

		JButton btnPievienotMerci = new JButton("Pievienot Mērci");
		btnPievienotMerci.setBounds(280, 175, 150, 25);
		saturaPanelis.add(btnPievienotMerci);


		JSeparator sep2 = new JSeparator();
		sep2.setBounds(20, 220, 480, 2);
		saturaPanelis.add(sep2);

		JLabel lblEdiensV = new JLabel("PIEVIENOT PAPILDU ĒDIENU (Uzkodas)");
		lblEdiensV.setFont(new Font("Arial", Font.BOLD, 14));
		lblEdiensV.setBounds(30, 230, 300, 25);
		saturaPanelis.add(lblEdiensV);

		JTextField txtEdienaNos = new JTextField("");
		txtEdienaNos.setBounds(30, 260, 150, 25);
		saturaPanelis.add(txtEdienaNos);

		JTextField txtEdienaCena = new JTextField("");
		txtEdienaCena.setBounds(190, 260, 80, 25);
		saturaPanelis.add(txtEdienaCena);

		JButton btnPievienotEdienu = new JButton("Pievienot");
		btnPievienotEdienu.setBounds(280, 260, 120, 25);
		saturaPanelis.add(btnPievienotEdienu);


		JSeparator sep3 = new JSeparator();
		sep3.setBounds(20, 310, 480, 2);
		saturaPanelis.add(sep3);

		JLabel lblDzeriensV = new JLabel("PIEVIENOT DZĒRIENU");
		lblDzeriensV.setFont(new Font("Arial", Font.BOLD, 14));
		lblDzeriensV.setBounds(30, 320, 300, 25);
		saturaPanelis.add(lblDzeriensV);

		JTextField txtDzerienaNos = new JTextField("");
		txtDzerienaNos.setBounds(30, 350, 150, 25);
		saturaPanelis.add(txtDzerienaNos);

		JTextField txtDzerienaCena = new JTextField("");
		txtDzerienaCena.setBounds(190, 350, 80, 25);
		saturaPanelis.add(txtDzerienaCena);

		JButton btnPievienotDzerienu = new JButton("Pievienot");
		btnPievienotDzerienu.setBounds(280, 350, 120, 25);
		saturaPanelis.add(btnPievienotDzerienu);


		btnPievienotPicu.addActionListener(e -> {
			try {
				String nos = txtPicasNos.getText();
				double cena = Double.parseDouble(txtPicasCena.getText());
				Picerija.picasSaraksts.add(new Pica(nos, cena));
				JOptionPane.showMessageDialog(DarbiniekuPanelis, "Pica '" + nos + "' pievienota!");
			} catch (Exception ex) { 
				JOptionPane.showMessageDialog(DarbiniekuPanelis, "Kļūda picas cenas formātā!"); 
			}
		});

		btnPievienotPiedevu.addActionListener(e -> {
			String piedeva = txtPiedeva.getText();

			Picerija.visasPiedevas.add(piedeva);
			JOptionPane.showMessageDialog(DarbiniekuPanelis, "Piedeva '" + piedeva + "' pievienota!");
		});

		btnPievienotMerci.addActionListener(e -> {
			String merce = txtMerce.getText();
			Picerija.visasMerces.add(merce);
			JOptionPane.showMessageDialog(DarbiniekuPanelis, "Mērce '" + merce + "' pievienota!");
		});

		btnPievienotEdienu.addActionListener(e -> {
			try {
				String nos = txtEdienaNos.getText();
				double cena = Double.parseDouble(txtEdienaCena.getText());
				Picerija.Uzkodas.add(new Uzkodas(nos, cena));
				JOptionPane.showMessageDialog(DarbiniekuPanelis, "Uzkoda '" + nos + "' pievienota!");
			} catch (Exception ex) { 
				JOptionPane.showMessageDialog(DarbiniekuPanelis, "Kļūda uzkodas cenas formātā!"); 
			}
		});

		btnPievienotDzerienu.addActionListener(e -> {
			try {
				String nos = txtDzerienaNos.getText();
				double cena = Double.parseDouble(txtDzerienaCena.getText());
				Picerija.dzerieni.add(new Dzerieni(nos, cena));
				JOptionPane.showMessageDialog(DarbiniekuPanelis, "Dzēriens '" + nos + "' pievienots!");
			} catch (Exception ex) { 
				JOptionPane.showMessageDialog(DarbiniekuPanelis, "Kļūda dzēriena cenas formātā!"); 
			}
		});

		JScrollPane scrollPane = new JScrollPane(saturaPanelis);
		DarbiniekuPanelis.add(scrollPane);
		DarbiniekuPanelis.setVisible(true);
	}
}