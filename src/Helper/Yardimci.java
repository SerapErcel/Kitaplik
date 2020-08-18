package Helper;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Yardimci {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayir");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");

	}

	public static void showMsg(String str) {
		optionPaneChangeButtonText();
		String msg = null;
		switch (str) {
		case "bos alan":
			msg = "Lutfen tum alanlarý doldurunuz!";
			break;
		case "hatali bilgi girisi":
			msg = "Girdiginiz bilgileri kontrol ediniz!";
			break;
		case "basarili":
			msg = "Ýsleminiz gerceklesti!";
			break;
		case "hata":
			msg = "Bir sorun ile karsilasildi!";
			break;
		case "kitap sec":
			msg="Tablodan kitap seciniz!";
			break;
		case "karakter siniri":
			msg="En fazla 250 karakter girebilirsiniz!";
			break;
		default:
			msg = str;
			break;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		optionPaneChangeButtonText();
		String msg = null;
		switch (str) {
		case "emin misin":
			msg = "Islemi gerceklesitirmek istediginize emin misiniz?";
			break;
		default:
			msg = str;
			break;
		}
		int i=JOptionPane.showConfirmDialog(null, msg,"Dikkat!",JOptionPane.YES_NO_OPTION);
		if(i==0)
			return true;
		else
			return false;
	}
}
