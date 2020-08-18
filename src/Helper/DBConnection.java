package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	Connection c=null;
	public DBConnection() {}
	public Connection conDB() {
		try {
			this.c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Kutuphane?user=root&password=Traeh,2133.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
		
	}

}
