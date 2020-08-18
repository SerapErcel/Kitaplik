package Model;

public class Alinti {
	private int kullaniciId;
	private int kitapID;
	private String alinti;
	public Alinti() {}
	
	public Alinti(int kullaniciId, int kitapID, String alinti) {
		super();
		this.kullaniciId = kullaniciId;
		this.kitapID = kitapID;
		this.alinti = alinti;
	}

	public int getKitapID() {
		return kitapID;
	}
	public void setKitapID(int kitapID) {
		this.kitapID = kitapID;
	}
	public int getKullaniciId() {
		return kullaniciId;
	}
	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}
	public String getAlinti() {
		return alinti;
	}
	public void setAlinti(String alinti) {
		this.alinti = alinti;
	}

}
