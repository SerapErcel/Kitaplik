package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import Helper.*;
import Model.Kullanici;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.UIManager;

public class Anasayfa extends JFrame {

	private JPanel contentPane;
	private JTextField fld_kullaniciAdi;
	private JPasswordField fld_password;
	private DBConnection conn = new DBConnection();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anasayfa frame = new Anasayfa();
					frame.setVisible(true);
					frame.setTitle("Kutuphane Anasayfasi");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Anasayfa() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_kullaniciAdi = new JLabel("Kullanici Adi:");
		lbl_kullaniciAdi.setBackground(new Color(255, 182, 193));
		lbl_kullaniciAdi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_kullaniciAdi.setBounds(18, 107, 510, 30);
		contentPane.add(lbl_kullaniciAdi);

		fld_kullaniciAdi = new JTextField();
		fld_kullaniciAdi.setToolTipText("Kullanici adinizi giriniz.");
		fld_kullaniciAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fld_kullaniciAdi.setBounds(18, 147, 510, 36);
		contentPane.add(fld_kullaniciAdi);
		fld_kullaniciAdi.setColumns(10);

		JLabel lbl_parola = new JLabel("Parola:");
		lbl_parola.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_parola.setBackground(new Color(255, 182, 193));
		lbl_parola.setBounds(18, 193, 510, 30);
		contentPane.add(lbl_parola);

		fld_password = new JPasswordField();
		fld_password.setToolTipText("Kullanici parolanizi giriniz.");
		fld_password.setBounds(18, 233, 510, 36);
		contentPane.add(fld_password);

		JButton btn_KayitOl = new JButton("Kayit Ol");
		btn_KayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KayitOlGUI kayit = new KayitOlGUI();
				kayit.setVisible(true);
				dispose();
			}
		});
		btn_KayitOl.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_KayitOl.setBounds(18, 291, 240, 37);
		contentPane.add(btn_KayitOl);

		JButton btn_GirisYap = new JButton("Giris Yap");
		btn_GirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_kullaniciAdi.getText().length() != 0 && fld_password.getText().length() != 0) {
					try {
						Connection con = conn.conDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM kutuphane.kullanicilar");
						boolean kontrol = false;
						while (rs.next()) {
							if (fld_kullaniciAdi.getText().equals(rs.getString("kullaniciAdi"))
									&& fld_password.getText().equals(rs.getString("kullaniciParola"))) {
								kontrol = true;
								Kullanici kull = new Kullanici();
								kull.setId(rs.getInt("kullaniciID"));
								kull.setKullaniciAdi(rs.getString("kullaniciAdi"));
								kull.setParola(rs.getString("kullaniciParola"));
								KitaplikGUI kitaplikGUI = new KitaplikGUI(kull);
								kitaplikGUI.setVisible(true);
								dispose();
							}
						}
						if (!kontrol) {
							Yardimci.showMsg("hatali bilgi girisi");
						}

						rs.close();
						st.close();
						con.close();

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Yardimci.showMsg("bos alan");
				}
			}
		});
		btn_GirisYap.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_GirisYap.setBounds(288, 291, 240, 37);
		contentPane.add(btn_GirisYap);

		JLabel lblNewLabel = new JLabel("2020 - Created by Serap Ercel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(172, 359, 202, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Anasayfa.class.getResource("/images/logo.png")));
		lblNewLabel_1.setBounds(32, 22, 64, 64);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("K\u0130TAPLIK 1.0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 27));
		lblNewLabel_2.setBounds(118, 22, 210, 64);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Anasayfa.class.getResource("/images/book_emoji_big.png")));
		label.setBounds(364, 9, 128, 128);
		contentPane.add(label);

	
	}
}
