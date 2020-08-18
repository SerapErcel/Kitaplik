package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Yardimci;
import Model.Kullanici;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class KitaplikGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_kitaplik;
	static private Kullanici kullanici = new Kullanici();
	private DefaultTableModel kitapModel = null;
	private Object[] kitapData = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitaplikGUI frame = new KitaplikGUI(kullanici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KitaplikGUI(Kullanici kullanici) throws SQLException {
		kitapModel = new DefaultTableModel();
		Object[] colKitapName = new Object[3];
		colKitapName[0] = "Kitap Adi";
		colKitapName[1] = "Yazar";
		colKitapName[2] = "Okunma Zamani";
		kitapModel.setColumnIdentifiers(colKitapName);
		kitapData = new Object[3];
		for (int i = 0; i < kullanici.getKitapList().size(); i++) {
			kitapData[0] = kullanici.getKitapList().get(i).getKitapAdi();
			kitapData[1] = kullanici.getKitapList().get(i).getKitapYazari();
			kitapData[2] = kullanici.getKitapList().get(i).getOkunmaZamani();
			kitapModel.addRow(kitapData);
		}

		setResizable(false);
		setTitle("K\u0131taplik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_icon1 = new JLabel();
		lbl_icon1.setIcon(new ImageIcon(KitaplikGUI.class.getResource("/images/logo.png")));
		lbl_icon1.setBounds(10, 0, 100, 100);
		contentPane.add(lbl_icon1);

		JLabel lbl_hosgeldiniz = new JLabel(kullanici.getKullaniciAdi().toUpperCase() + " Kitapligi");
		lbl_hosgeldiniz.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lbl_hosgeldiniz.setBounds(74, 23, 349, 45);
		contentPane.add(lbl_hosgeldiniz);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 1015, 533);
		contentPane.add(scrollPane);

		JButton btnAlintilar = new JButton("Alintilar");
		btnAlintilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_kitaplik.getSelectedRow() != -1) {
					try {
						AlintilarGUI alintilar = new AlintilarGUI(kullanici,
								kullanici.getKitapList().get(table_kitaplik.getSelectedRow()));
						alintilar.setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else
					Yardimci.showMsg("hata");
			}
		});
		btnAlintilar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAlintilar.setBounds(423, 24, 115, 45);
		btnAlintilar.setVisible(false);
		contentPane.add(btnAlintilar);

		table_kitaplik = new JTable(kitapModel);
		table_kitaplik.setBorder(null);
		table_kitaplik.setFillsViewportHeight(true);
		table_kitaplik.setBackground(new Color(245, 255, 250));
		table_kitaplik.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table_kitaplik);

		table_kitaplik.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					if (table_kitaplik.getSelectedRow() != -1) {
						if (!kullanici
								.getAlintiList(kullanici.getKitapList().get(table_kitaplik.getSelectedRow()).getId())
								.isEmpty())
							btnAlintilar.setVisible(true);
						else
							btnAlintilar.setVisible(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		table_kitaplik.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {
					String selectKitapAdi = table_kitaplik.getValueAt(table_kitaplik.getSelectedRow(), 0).toString();
					String selectYazar = table_kitaplik.getValueAt(table_kitaplik.getSelectedRow(), 1).toString();
					String selectOkunmaZamani = table_kitaplik.getValueAt(table_kitaplik.getSelectedRow(), 2)
							.toString();
					int selectIndex = table_kitaplik.getSelectedRow();
					if (selectIndex != -1) {
						try {
							if (kullanici.kitapGuncelle(selectKitapAdi, selectYazar, selectOkunmaZamani,
									kullanici.getKitapList().get(selectIndex).getId())) {
							} else
								Yardimci.showMsg("hata");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("2020 - Created by Serap Ercel");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(442, 638, 151, 13);
		contentPane.add(lblNewLabel);

		JButton btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					KitapEkleGUI kitapEkle = new KitapEkleGUI(kullanici);

					kitapEkle.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				dispose();
			}
		});
		btnKitapEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKitapEkle.setBounds(800, 24, 117, 45);
		contentPane.add(btnKitapEkle);

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(KitaplikGUI.class.getResource("/images/book-love.png")));
		label.setBounds(927, 0, 98, 100);
		contentPane.add(label);

		JButton btnKitapSil = new JButton("Kitap Sil");
		btnKitapSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectIndex = table_kitaplik.getSelectedRow();
				if (selectIndex != -1) {
					if (Yardimci.confirm("emin misin")) {
						try {
							if (kullanici.kitapSil(kullanici.getKitapList().get(selectIndex).getId(),kullanici.getId())) {
								kitapModeliGuncelle();
								table_kitaplik.clearSelection();
							} else
								Yardimci.showMsg("hata");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				} else
					Yardimci.showMsg("kitap sec");
			}
		});
		btnKitapSil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKitapSil.setBounds(675, 24, 115, 45);
		contentPane.add(btnKitapSil);

		JButton btnAlintiEkle = new JButton("Alinti Ekle");
		btnAlintiEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectIndex = table_kitaplik.getSelectedRow();
				AlintiEkleGUI alintiGUI;
				if (selectIndex != -1) {
					try {
						alintiGUI = new AlintiEkleGUI(kullanici, kullanici.getKitapList().get(selectIndex));
						alintiGUI.setVisible(true);
						table_kitaplik.clearSelection();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else
					Yardimci.showMsg("kitap sec");
			}

		});
		btnAlintiEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAlintiEkle.setBounds(548, 24, 117, 45);
		contentPane.add(btnAlintiEkle);

	}

	public void kitapModeliGuncelle() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_kitaplik.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < kullanici.getKitapList().size(); i++) {
			kitapData[0] = kullanici.getKitapList().get(i).getKitapAdi();
			kitapData[1] = kullanici.getKitapList().get(i).getKitapYazari();
			kitapData[2] = kullanici.getKitapList().get(i).getOkunmaZamani();
			kitapModel.addRow(kitapData);
		}

	}
}
