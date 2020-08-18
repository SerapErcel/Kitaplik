package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.*;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AlintiEkleGUI extends JFrame {

	private JPanel contentPane;
	private static Kullanici kullanici;
	private static Kitap kitap;
	private JTextField fld_kitapAdi;
	DBConnection conn = new DBConnection();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlintiEkleGUI frame = new AlintiEkleGUI(kullanici, kitap);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {

							KitaplikGUI kitaplik;
							try {
								kitaplik = new KitaplikGUI(kullanici);
								kitaplik.setVisible(true);
								frame.setVisible(false);
								frame.dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlintiEkleGUI(Kullanici kullanici, Kitap kitap) {
		setTitle("Alinti Ekleme");
		setResizable(false);
		
		setBounds(100, 100, 542, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label2=new JLabel();
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		label2.setBackground(new Color(255,182,193));
		label2.setBounds(10, 20, 436, 30);
		label2.setText(kitap.getKitapAdi().toUpperCase());
		contentPane.add(label2);
		
		JLabel lblAlintiMetni = new JLabel(" Alinti Metni");
		lblAlintiMetni.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlintiMetni.setBackground(new Color(240, 255, 255));
		lblAlintiMetni.setBounds(10, 64, 510, 30);
		contentPane.add(lblAlintiMetni);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 510, 200);
		contentPane.add(scrollPane);
		
		JTextArea txt_Alinti = new JTextArea();
		txt_Alinti.setFont(new Font("Verdana", Font.ITALIC, 14));
		txt_Alinti.setLineWrap(true);
		scrollPane.setViewportView(txt_Alinti);
		
		JLabel label_1 = new JLabel(" 2020 - Created by Serap Ercel");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label_1.setBounds(209, 359, 113, 13);
		contentPane.add(label_1);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alinti alinti=new Alinti(kullanici.getId(),kitap.getId(),txt_Alinti.getText());
				
				if(!alintiEkle(alinti))
					Yardimci.showMsg("karakter siniri");
				
				dispose();
			}
		});
		btnEkle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEkle.setBounds(146, 313, 239, 36);
		contentPane.add(btnEkle);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AlintiEkleGUI.class.getResource("/images/shelf.png")));
		lblNewLabel.setBounds(456, 20, 64, 56);
		contentPane.add(lblNewLabel);
		
		
	}

	public boolean alintiEkle(Alinti alinti) {
		boolean kontrol = false;
		try {
			Connection con = conn.conDB();
			String sorgu = "INSERT INTO alintilar (kullaniciID, kitapID, alinti) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sorgu);
			ps.setInt(1, alinti.getKullaniciId());
			ps.setInt(2, alinti.getKitapID());
			ps.setString(3, alinti.getAlinti());
			int sonuc = ps.executeUpdate();
			if (sonuc == 1) {
				Yardimci.showMsg("basarili");
				kontrol = true;

			} else
				Yardimci.showMsg("hata");

			ps.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return kontrol;
	}
}
