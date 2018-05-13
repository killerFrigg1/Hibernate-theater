import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/hbtheater_tracker?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "hbtheater";
		String pass = "hbtheater";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);

			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection succesful!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}