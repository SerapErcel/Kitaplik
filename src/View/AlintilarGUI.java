package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AlintilarGUI extends JFrame {

	private JPanel contentPane;
	private static Kullanici kullanici = new Kullanici();
	private static Kitap kitap = new Kitap();
	private String metin = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlintilarGUI frame = new AlintilarGUI(kullanici, kitap);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							try {

								KitaplikGUI kitaplik = new KitaplikGUI(kullanici);
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
	public AlintilarGUI(Kullanici kullanici, Kitap kitap) {
		setTitle("Kitap Alintilari");
		setResizable(false);
		setBounds(100, 100, 535, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Alintilar hatirlamaniza yardimci olur.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(34, 10, 350, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AlintilarGUI.class.getResource("/images/book-love.png")));
		lblNewLabel_1.setBounds(394, 0, 100, 76);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 500, 275);
		contentPane.add(scrollPane);

		JTextArea txt_alintilar = new JTextArea();
		txt_alintilar.setEditable(false);
		txt_alintilar.setFont(new Font("Verdana", Font.ITALIC, 14));
		txt_alintilar.setBackground(new Color(245, 255, 250));
		txt_alintilar.setLineWrap(true);
		try {
			for (int i = 0; i < kullanici.getAlintiList(kitap.getId()).size(); i++) {
				if (!kullanici.getAlintiList(kitap.getId()).get(i).getAlinti().equals(null))
					metin += " " + kullanici.getAlintiList(kitap.getId()).get(i).getAlinti()
							+ "\n	-------------------------------- \n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			txt_alintilar.setText(metin);

		}

		scrollPane.setViewportView(txt_alintilar);

		JLabel label = new JLabel(" 2020 - Created by Serap Ercel");
		label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label.setBounds(209, 359, 113, 13);
		contentPane.add(label);

		JLabel fld_kitapAdi = new JLabel("");
		fld_kitapAdi.setText(kitap.getKitapAdi());
		fld_kitapAdi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		fld_kitapAdi.setBounds(10, 44, 350, 32);
		contentPane.add(fld_kitapAdi);
	}
}
