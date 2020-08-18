package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.*;

public class Kullanici {
	private static int id;
	private String kullaniciAdi;
	private String parola;
	DBConnection conn = new DBConnection();

	Connection con = conn.conDB();
	Statement st = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public Kullanici() {
	}

	public Kullanici(int id, String kullaniciAdi, String parola) {
		this.id = id;
		this.kullaniciAdi = kullaniciAdi;
		this.parola = parola;
	}

	public boolean kitapEkle(int kullaniciId, String kitapAdi, String yazar, String okunmaZamani) {
		String sorgu = "INSERT INTO kitaplik" + "(kullaniciID,kitapAdi,kitapYazari,okunmaZamani) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1, kullaniciId);
			preparedStatement.setString(2, kitapAdi);
			preparedStatement.setString(3, yazar);
			preparedStatement.setString(4, okunmaZamani);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

	public boolean kitapSil(int kitapID,int kullaniciID) {
		String sorgu1 = "DELETE FROM kitaplik WHERE id= " + kitapID;
		String sorgu2="DELETE FROM alintilar WHERE kullaniciID= "+kullaniciID+" AND kitapID="+kitapID+"";
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu2);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(sorgu1);
			preparedStatement.executeUpdate();
			key = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean kitapGuncelle(String kitapAdi, String yazari, String okunmaZamani, int kitapID) {
		String sorgu = "UPDATE kitaplik SET kitapAdi=?, kitapYazari=?, okunmaZamani=? WHERE id =?";
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, kitapAdi);
			preparedStatement.setString(2, yazari);
			preparedStatement.setString(3, okunmaZamani);
			preparedStatement.setInt(4, kitapID);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public ArrayList<Kitap> getKitapList() throws SQLException {
		ArrayList<Kitap> list = new ArrayList<>();
		Kitap obj;
		try {
			con = conn.conDB();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kitaplik WHERE kullaniciID= '" + getId() + "'");
			while (rs.next()) {
				obj = new Kitap(rs.getInt("id"), rs.getString("kitapAdi"), rs.getString("kitapYazari"),
						rs.getString("okunmaZamani"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}

	public ArrayList<Alinti> getAlintiList(int kitapid) throws SQLException {
		ArrayList<Alinti> list = new ArrayList<>();
		Alinti obj;
		try {
			con = conn.conDB();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM alintilar WHERE kullaniciID= '" + getId() + "' AND kitapID= '"+kitapid+"'");
			while (rs.next()) {
				obj = new Alinti(rs.getInt("kullaniciID"),rs.getInt("kitapID"), rs.getString("alinti"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
	


}
