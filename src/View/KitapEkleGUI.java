package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Yardimci;
import Model.Kullanici;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class KitapEkleGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_kitapAdi;
	private JTextField fld_yazari;
	static Kullanici kullanici = new Kullanici();
	String date = "beklemede";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapEkleGUI frame = new KitapEkleGUI(kullanici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KitapEkleGUI(Kullanici kullanici) {
		setTitle("Kitap Ekle");
		setBackground(Color.PINK);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl1 = new JLabel("Yeni kitap bilgilerini girin.");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(29, 25, 248, 30);
		contentPane.add(lbl1);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(KitapEkleGUI.class.getResource("/images/book-100.png")));
		label_1.setBounds(420, 0, 100, 80);
		contentPane.add(label_1);

		JLabel lblKitapAdi = new JLabel(" Kitap Adi:");
		lblKitapAdi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKitapAdi.setBackground(new Color(255, 182, 193));
		lblKitapAdi.setBounds(10, 51, 510, 30);
		contentPane.add(lblKitapAdi);

		fld_kitapAdi = new JTextField();
		fld_kitapAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fld_kitapAdi.setColumns(10);
		fld_kitapAdi.setBounds(10, 77, 510, 30);
		contentPane.add(fld_kitapAdi);

		JLabel lblCreated = new JLabel(" 2020 - Created by Serap Ercel");
		lblCreated.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblCreated.setBounds(209, 359, 113, 13);
		contentPane.add(lblCreated);

		fld_yazari = new JTextField();
		fld_yazari.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fld_yazari.setColumns(10);
		fld_yazari.setBounds(10, 141, 510, 30);
		contentPane.add(fld_yazari);

		JLabel lblYazari = new JLabel(" Yazari:");
		lblYazari.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYazari.setBackground(new Color(255, 182, 193));
		lblYazari.setBounds(10, 117, 390, 30);
		contentPane.add(lblYazari);

		JLabel lblOkunmaZamani = new JLabel(" Okunma Zamani:");
		lblOkunmaZamani.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOkunmaZamani.setBackground(new Color(255, 182, 193));
		lblOkunmaZamani.setBounds(10, 170, 510, 30);
		contentPane.add(lblOkunmaZamani);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 198, 510, 36);

		contentPane.add(select_date);

		JButton btn_ekle = new JButton("Ekle");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		select_date.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					date = sdf.format(select_date.getDate());
				}
			}

		});
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fld_kitapAdi.getText().length() != 0 && fld_yazari.getText().length() != 0) {
						if (kullanici.kitapEkle(kullanici.getId(), fld_kitapAdi.getText(), fld_yazari.getText(), date
								)) {
							KitaplikGUI kitaplikGUI;
							kitaplikGUI = new KitaplikGUI(kullanici);
							Yardimci.showMsg("basarili");
							kitaplikGUI.kitapModeliGuncelle();
							kitaplikGUI.setVisible(true);
							dispose();

						} else
							Yardimci.showMsg("hata");
					} else {
						Yardimci.showMsg("bos alan");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_ekle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_ekle.setBounds(146, 244, 239, 36);
		contentPane.add(btn_ekle);

	}
}
