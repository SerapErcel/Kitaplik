package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class KayitOlGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_kullaniciAdi;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KayitOlGUI frame = new KayitOlGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KayitOlGUI() {
		setTitle("Kayit Ekrani");
		setBackground(Color.PINK);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Kullanici Adi:");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBackground(new Color(255, 182, 193));
		label.setBounds(10, 64, 510, 30);
		contentPane.add(label);

		fld_kullaniciAdi = new JTextField();
		fld_kullaniciAdi.setToolTipText("Sisteme giris yaparken kullanacaginiz adinizi giriniz.");
		fld_kullaniciAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fld_kullaniciAdi.setColumns(10);
		fld_kullaniciAdi.setBounds(10, 93, 510, 36);
		contentPane.add(fld_kullaniciAdi);

		JLabel label_1 = new JLabel("Parola:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBackground(new Color(255, 182, 193));
		label_1.setBounds(10, 133, 510, 30);
		contentPane.add(label_1);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Sisteme giris yapacak parolanizi giriniz.");
		passwordField.setBounds(10, 161, 510, 36);
		contentPane.add(passwordField);

		JButton btnKayitOl = new JButton("  Kayit Ol");
		btnKayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_kullaniciAdi.getText().length() != 0 && passwordField.getText().length() != 0
						&& passwordField_1.getText().length() != 0) {
					if (passwordField.getText().equals(passwordField_1.getText())) {
						Kullanici kull = new Kullanici();
						kull.setKullaniciAdi(fld_kullaniciAdi.getText());
						kull.setParola(passwordField.getText());
						kullaniciEkle(kull);
						Anasayfa a = new Anasayfa();
						a.setVisible(true);
						dispose();
					} else {
						Yardimci.showMsg("hatali bilgi girisi");
					}
				} else {
					Yardimci.showMsg("bos alan");
				}
			}
		});
		btnKayitOl.setIcon(new ImageIcon(KayitOlGUI.class.getResource("/images/reading.png")));
		btnKayitOl.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKayitOl.setBounds(176, 300, 156, 49);
		contentPane.add(btnKayitOl);

		JLabel label_2 = new JLabel("2020 - Created by Serap Ercel");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label_2.setBounds(208, 359, 114, 13);
		contentPane.add(label_2);

		JLabel lblParolaYeniden = new JLabel("Parola Yeniden:");
		lblParolaYeniden.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblParolaYeniden.setBackground(new Color(255, 182, 193));
		lblParolaYeniden.setBounds(10, 207, 510, 30);
		contentPane.add(lblParolaYeniden);

		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("Parolanizi tekrar yaziniz.");
		passwordField_1.setBounds(10, 235, 510, 36);
		contentPane.add(passwordField_1);

		JLabel lblNewLabel = new JLabel("Kayit islemi icin bilgilerinizi girin.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(23, 22, 248, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(KayitOlGUI.class.getResource("/images/logo.png")));
		lblNewLabel_1.setBounds(450, 0, 72, 100);
		contentPane.add(lblNewLabel_1);

	}

	public void kullaniciEkle(Kullanici kull) {
		try {
			Connection con = conn.conDB();
			String sorgu = "INSERT INTO kullanicilar (kullaniciAdi, kullaniciParola) VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(sorgu);
			ps.setString(1, kull.getKullaniciAdi());
			ps.setString(2, kull.getParola());
			int sonuc = ps.executeUpdate();
			if (sonuc == 1) {
				Yardimci.showMsg("basarili");
			} else
				Yardimci.showMsg("hata");
			ps.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
