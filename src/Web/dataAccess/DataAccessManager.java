package Web.dataAccess;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataAccessManager {
	private Connection dbConnection = null;
	// private String jdbcDriver="org.apache.derby.jdbc.ClientDriver";
	// private String dbUrl =
	// "jdbc:derby://localhost:1527/addressBook;create=true";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/javaCourse";
	private String dbUser = "root";
	private String dbPassword = "manager";
	private File excelFile = null;

	private File xmlFile = null;
	private String filePath = "src/Web.dataAccess/";

	public DataAccessManager() {
		super();

	}

	public DataAccessManager(String filePath) {
		this.filePath = filePath;

	}

	public DataAccessManager(String jdbcDriver, String dbUrl, String dbUser,
			String dbPassword) {
		this.jdbcDriver = jdbcDriver;

	}

	public File openExcelFile() {
		if (excelFile == null) {
			try {
				excelFile = new File(filePath + "addressBook.xls");

			} catch (Exception e) {
				System.out.println("open excel file error:" + e);
			}
		}
		return excelFile;
	}

	public File openXMLFile() {
		if (xmlFile == null) {
			try {
				xmlFile = new File(filePath + "addressBook.xml");

			} catch (Exception e) {
				System.out.println("open xml file error:" + e);
			}
		}
		return xmlFile;
	}

	public Connection getConnection() {
		if (dbConnection == null) {
			try {
				Class.forName(jdbcDriver).newInstance();// ������
				dbConnection = DriverManager.getConnection(dbUrl, dbUser,
						dbPassword);
			} catch (Exception e) {
				System.out.println("open databse connection error:" + e);
			}
		}
		return dbConnection;
	}

	public void closeConnection() {
		try {
			dbConnection.close();
		} catch (Exception e) {
			System.out.println("close databse connection error:" + e);
		}
	}

}
