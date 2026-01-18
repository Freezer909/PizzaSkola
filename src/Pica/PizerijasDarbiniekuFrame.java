package Pica;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import entity.Player;

public class PizerijasDarbiniekuFrame {

    public static void raditDarbiniekaPaneli() {
        JFrame darbiniekuLogs = new JFrame("Darbinieku Panelis");
        darbiniekuLogs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        darbiniekuLogs.setSize(1300, 800);
        darbiniekuLogs.setLocationRelativeTo(null);
        darbiniekuLogs.setLayout(new BorderLayout());

        CardLayout cl = new CardLayout();
        JPanel mainContent = new JPanel(cl);

        JPanel redigetPanel = izveidotRedigesanasPaneli();
        JPanel darbaPanel = izveidotDarbaPaneli();

        mainContent.add(redigetPanel, "REDIGET");
        mainContent.add(darbaPanel, "DARBS");

        JPanel izvelne = izveidotIzvelnesPaneli(cl, mainContent);

        darbiniekuLogs.add(izvelne, BorderLayout.WEST);
        darbiniekuLogs.add(mainContent, BorderLayout.CENTER);

        darbiniekuLogs.setVisible(true);
    }

    private static JPanel izveidotIzvelnesPaneli(CardLayout cl, JPanel main) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 800));
        panel.setBackground(Color.DARK_GRAY);

        JButton btnRediget = new JButton("Rediģēt ēdienkarti");
        JButton btnDarbs = new JButton("Strādāt");

        btnRediget.setBounds(20, 280, 160, 50);
        btnDarbs.setBounds(20, 350, 160, 50);

        btnRediget.setFocusable(false);
        btnDarbs.setFocusable(false);

        btnRediget.addActionListener(e -> cl.show(main, "REDIGET"));
        btnDarbs.addActionListener(e -> cl.show(main, "DARBS"));

        panel.add(btnRediget);
        panel.add(btnDarbs);

        return panel;
    }

    private static JPanel izveidotRedigesanasPaneli() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        JLabel virsraksts = new JLabel("ĒDIENKARTES REDIĢĒŠANA");
        virsraksts.setFont(new Font("Arial", Font.BOLD, 22));
        virsraksts.setBounds(50, 30, 400, 40);
        panel.add(virsraksts);

        // --- Picas pievienošana ---
        JLabel lblPicaVirsraksts = new JLabel("PIEVIENOT PICU");
        lblPicaVirsraksts.setFont(new Font("Arial", Font.BOLD, 14));
        lblPicaVirsraksts.setBounds(50, 90, 200, 25);
        panel.add(lblPicaVirsraksts);

        JLabel lblNosaukums = new JLabel("Nosaukums:");
        lblNosaukums.setBounds(50, 120, 100, 25);
        panel.add(lblNosaukums);

        JTextField txtPicasNos = new JTextField();
        txtPicasNos.setBounds(50, 145, 200, 30);
        panel.add(txtPicasNos);

        JLabel lblCena = new JLabel("Cena (€):");
        lblCena.setBounds(260, 120, 80, 25);
        panel.add(lblCena);

        JTextField txtPicasCena = new JTextField();
        txtPicasCena.setBounds(260, 145, 80, 30);
        panel.add(txtPicasCena);

        JLabel lblSastavs = new JLabel("Sastāvdaļas (piem. Siers, Šķiņķis...):");
        lblSastavs.setBounds(50, 185, 250, 25);
        panel.add(lblSastavs);

        JTextField txtPicasSastavs = new JTextField();
        txtPicasSastavs.setBounds(50, 210, 290, 30);
        panel.add(txtPicasSastavs);

        JButton btnPievienotPicu = new JButton("Pievienot");
        btnPievienotPicu.setBounds(360, 145, 120, 95);
        btnPievienotPicu.setBackground(new Color(52, 152, 219));
        btnPievienotPicu.setForeground(Color.WHITE);
        btnPievienotPicu.addActionListener(e -> {
            try {
                String nos = txtPicasNos.getText();
                double cena = Double.parseDouble(txtPicasCena.getText());
                String[] sastavs = txtPicasSastavs.getText().split(",");
                for(int i=0; i<sastavs.length; i++) sastavs[i] = sastavs[i].trim();
                Picerija.picasSaraksts.add(new Pica(nos, cena, sastavs));
                JOptionPane.showMessageDialog(null, "Pica '" + nos + "' pievienota!");
                txtPicasNos.setText(""); txtPicasCena.setText(""); txtPicasSastavs.setText("");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Kļūda picas datos!"); }
        });
        panel.add(btnPievienotPicu);

        // --- Piedevas ---
        JLabel lblPiedevaVirsraksts = new JLabel("PIEVIENOT PAPILDU PIEDEVU");
        lblPiedevaVirsraksts.setFont(new Font("Arial", Font.BOLD, 14));
        lblPiedevaVirsraksts.setBounds(50, 280, 300, 25);
        panel.add(lblPiedevaVirsraksts);

        JLabel lblPiedevaNos = new JLabel("Piedevas nosaukums:");
        lblPiedevaNos.setBounds(50, 305, 200, 25);
        panel.add(lblPiedevaNos);

        JTextField txtPiedeva = new JTextField();
        txtPiedeva.setBounds(50, 330, 290, 30);
        panel.add(txtPiedeva);

        JButton btnPiedeva = new JButton("Pievienot");
        btnPiedeva.setBounds(360, 330, 120, 30);
        btnPiedeva.addActionListener(e -> {
            Picerija.papildusPiedevas.add(txtPiedeva.getText());
            JOptionPane.showMessageDialog(null, "Piedeva pievienota!");
            txtPiedeva.setText("");
        });
        panel.add(btnPiedeva);

        // --- Uzkodas ---
        JLabel lblUzkodaVirsraksts = new JLabel("PIEVIENOT UZKODU");
        lblUzkodaVirsraksts.setFont(new Font("Arial", Font.BOLD, 14));
        lblUzkodaVirsraksts.setBounds(50, 400, 200, 25);
        panel.add(lblUzkodaVirsraksts);

        JLabel lblUzkodaNos = new JLabel("Nosaukums:");
        lblUzkodaNos.setBounds(50, 425, 100, 25);
        panel.add(lblUzkodaNos);

        JTextField txtUzkodaNos = new JTextField();
        txtUzkodaNos.setBounds(50, 450, 200, 30);
        panel.add(txtUzkodaNos);

        JLabel lblUzkodaCena = new JLabel("Cena (€):");
        lblUzkodaCena.setBounds(260, 425, 80, 25);
        panel.add(lblUzkodaCena);

        JTextField txtUzkodaCena = new JTextField();
        txtUzkodaCena.setBounds(260, 450, 80, 30);
        panel.add(txtUzkodaCena);

        JButton btnUzkoda = new JButton("Pievienot");
        btnUzkoda.setBounds(360, 450, 120, 30);
        btnUzkoda.addActionListener(e -> {
            try {
                Picerija.Uzkodas.add(new Uzkodas(txtUzkodaNos.getText(), Double.parseDouble(txtUzkodaCena.getText())));
                JOptionPane.showMessageDialog(null, "Uzkoda pievienota!");
                txtUzkodaNos.setText(""); txtUzkodaCena.setText("");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Kļūda!"); }
        });
        panel.add(btnUzkoda);

        return panel;
    }

    private static JPanel izveidotDarbaPaneli() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel virsraksts = new JLabel("DARBA ZONA");
        virsraksts.setFont(new Font("Arial", Font.BOLD, 22));
        virsraksts.setBounds(50, 30, 200, 40);
        panel.add(virsraksts);

        JLabel apraksts = new JLabel("Spiediet pogu, lai strādātu un pelnītu naudu:");
        apraksts.setBounds(50, 80, 400, 25);
        panel.add(apraksts);

        JButton btnStradat = new JButton("STRĀDĀT (+5.00 €)");
        btnStradat.setFont(new Font("Arial", Font.BOLD, 20));
        btnStradat.setBounds(50, 120, 400, 100);
        btnStradat.setBackground(new Color(46, 204, 113));
        btnStradat.setForeground(Color.WHITE);
        btnStradat.setFocusable(false);

        btnStradat.addActionListener(e -> {
            Player.Nauda += 5.0;
        });

        panel.add(btnStradat);

        return panel;
    }
}