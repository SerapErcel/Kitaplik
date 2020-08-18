package Model;

public class Kitap {
	private int id;
	private String kitapAdi;
	private String kitapYazari;
	private String okunmaZamani;

	public Kitap(int id,String kitapAdi, String kitapYazari, String okunmaZamani) {
		super();
		this.id=id;
		this.kitapAdi = kitapAdi;
		this.kitapYazari = kitapYazari;
		this.okunmaZamani = okunmaZamani;
	}
	public Kitap() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getKitapAdi() {
		return kitapAdi;
	}

	public void setKitapAdi(String kitapAdi) {
		this.kitapAdi = kitapAdi;
	}

	public String getKitapYazari() {
		return kitapYazari;
	}

	public void setKitapYazari(String kitapYazari) {
		this.kitapYazari = kitapYazari;
	}

	public String getOkunmaZamani() {
		return okunmaZamani;
	}

	public void setOkunmaZamani(String okunmaZamani) {
		this.okunmaZamani = okunmaZamani;
	}

}
