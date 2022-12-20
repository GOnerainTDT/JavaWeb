package Web.dataAccess;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FriendDAO {
	private DataAccessManager manager;

	public FriendDAO(DataAccessManager manager) {
		this.manager = manager;
	}

	public FriendSet loadFriendsFromXMLFile() {
		FriendSet friends = new FriendSet();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db = factory.newDocumentBuilder();
			Document xmldoc = db.parse(manager.openXMLFile());
			Element root = xmldoc.getDocumentElement();
			FriendPO friend;
			Node objNode, attrNode;

			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				objNode = root.getChildNodes().item(i);
				if (objNode.getNodeName().equals("friend")) {
					friend = new FriendPO();
					for (int j = 0; j < objNode.getChildNodes().getLength(); j++) {
						attrNode = objNode.getChildNodes().item(j);
						if (attrNode.getNodeName().equals("name")) {
							friend.setName(attrNode.getFirstChild()
									.getNodeValue());
						}
						if (attrNode.getNodeName().equals("phone")) {
							friend.setPhone(attrNode.getFirstChild()
									.getNodeValue());
						}
						if (attrNode.getNodeName().equals("email")) {
							friend.setEmail(attrNode.getFirstChild()
									.getNodeValue());
						}
						if (attrNode.getNodeName().equals("address")) {
							friend.setAddress(attrNode.getFirstChild()
									.getNodeValue());
						}
					}
					friends.add(friend);
				}
			}

		} catch (ParserConfigurationException error) {
			error.printStackTrace();
		} catch (SAXException error2) {
			error2.printStackTrace();
		} catch (FileNotFoundException error3) {
			error3.printStackTrace();
		} catch (IOException error4) {
			error4.printStackTrace();
		}

		return friends;
	}

	public void writeFriendsToXmlFile(FriendSet friends) {
		TransformerFactory transFactory = TransformerFactory.newInstance();

		try {

			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("indent", "yes");
			DOMSource source = new DOMSource();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db = factory.newDocumentBuilder();
			Document xmldoc = db.newDocument();
			Element root = xmldoc.createElement("friends");

			Element theFriend, theAttr;
			for (FriendPO friend : friends) {
				// --- 新建一个朋友信息 ----
				theFriend = xmldoc.createElement("friend");
				theAttr = xmldoc.createElement("name");
				theAttr.setTextContent(friend.getName());
				theFriend.appendChild(theAttr);

				theAttr = xmldoc.createElement("phone");
				theAttr.setTextContent(friend.getPhone());
				theFriend.appendChild(theAttr);

				theAttr = xmldoc.createElement("email");
				theAttr.setTextContent(friend.getEmail());
				theFriend.appendChild(theAttr);

				theAttr = xmldoc.createElement("address");
				theAttr.setTextContent(friend.getAddress());
				theFriend.appendChild(theAttr);
				root.appendChild(theFriend);
			}
			xmldoc.appendChild(root);
			source.setNode(xmldoc);
			StreamResult result = new StreamResult();
			FileOutputStream stream = new FileOutputStream(
					manager.openXMLFile());
			result.setOutputStream(stream);
			transformer.transform(source, result);
			stream.close();
		} catch (ParserConfigurationException error) {
			error.printStackTrace();
		} catch (TransformerConfigurationException error1) {
			error1.printStackTrace();
		} catch (TransformerException error2) {
			error2.printStackTrace();
		} catch (FileNotFoundException error3) {
			error3.printStackTrace();
		} catch (IOException error4) {
			error4.printStackTrace();
		}
	}

	public FriendSet loadFriendsFromExcelFile() {
		FriendSet friends = new FriendSet();
		try {
			FileInputStream inputStream = new FileInputStream(
					manager.openExcelFile());
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			int rowstart = hssfSheet.getFirstRowNum();
			int rowEnd = hssfSheet.getLastRowNum();
			FriendPO friend;
			for (int i = rowstart + 1; i <= rowEnd; i++) {
				HSSFRow row = hssfSheet.getRow(i);
				if (null == row)
					continue;
				friend = new FriendPO();
				HSSFCell cell = row.getCell(0);
				friend.setName(cell.getStringCellValue());
				cell = row.getCell(1);
				friend.setPhone(cell.getStringCellValue());
				cell = row.getCell(2);
				friend.setEmail(cell.getStringCellValue());
				cell = row.getCell(3);
				friend.setAddress(cell.getStringCellValue());
				friends.add(friend);
			}
			inputStream.close();
		} catch (FileNotFoundException error) {
			System.out.println("excel file cannot find");
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
		return friends;
	}

	public void writeFriendsToExcelFile(FriendSet friends) {
		HSSFWorkbook workbook = null;
		workbook = new HSSFWorkbook();

		int columeCount = 4;

		HSSFSheet sheet = workbook.createSheet("通讯录");

		HSSFRow headRow = sheet.createRow(0);
		String[] titleArray = { "姓名", "电话", "电子邮箱", "地址ַ" };
		for (int m = 0; m <= columeCount - 1; m++) {
			HSSFCell cell = headRow.createCell(m);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(m, 7000);
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			short color = HSSFColor.RED.index;
			font.setColor(color);
			style.setFont(font);
			cell.setCellStyle(style);
			cell.setCellValue(titleArray[m]);

		}
		int index = 0;

		for (FriendPO friend : friends) {
			HSSFRow row = sheet.createRow(index + 1);
			for (int n = 0; n <= columeCount - 1; n++)
				row.createCell(n);
			row.getCell(0).setCellValue(friend.getName());
			row.getCell(1).setCellValue(friend.getPhone());
			row.getCell(2).setCellValue(friend.getEmail());
			row.getCell(3).setCellValue(friend.getAddress());
			index++;
		}

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					manager.openExcelFile());
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addFriend(FriendPO friend) {
		try {
			String sqlStr = "insert into friend(name,phone,email,address) values(?,?,?,?)";
			PreparedStatement prepStmt = manager.getConnection()
					.prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, friend.getName());
			prepStmt.setString(2, friend.getPhone());
			prepStmt.setString(3, friend.getEmail());
			prepStmt.setString(4, friend.getAddress());
			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("add Friend error:" + e);
		}
	}

	public void addFriends(FriendSet friends) {
		for (FriendPO friend : friends) {
			addFriend(friend);
		}

	}

	public void deleteFriend(FriendPO friend) {
		try {
			String sqlStr = "delete from friend where name=?";
			PreparedStatement prepStmt = manager.getConnection()
					.prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, friend.getName());
			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("deletefriend error:" + e);
		}
	}

	public void deleteFriends(FriendSet friends) {
		for (FriendPO friend : friends) {
			deleteFriend(friend);
		}

	}

	public void updateFriend(FriendPO friend) {
		try {
			String sqlStr = "update friend set phone=?,email=?,address=? "
					+ " where name=?";
			PreparedStatement prepStmt = manager.getConnection()
					.prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, friend.getPhone());
			prepStmt.setString(2, friend.getEmail());
			prepStmt.setString(3, friend.getAddress());
			prepStmt.setString(4, friend.getName());
			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("updatefriend error:" + e);
		}
	}

	public void updateFriends(FriendSet friends) {
		for (FriendPO friend : friends) {
			updateFriend(friend);
		}

	}

	public FriendPO queryFriendByName(String name) {
		FriendPO friend = null;
		try {
			if (manager.getConnection() != null) {
				String sqlStr = "SELECT * FROM friend  where name=?";
				PreparedStatement prepStmt = manager.getConnection()
						.prepareStatement(sqlStr);
				prepStmt.setString(1, name);
				ResultSet rs = prepStmt.executeQuery();
				if (rs.next()) {
					friend = new FriendPO();
					friend.setName(rs.getString("name"));
					friend.setPhone(rs.getString("phone"));
					friend.setEmail(rs.getString("email"));
					friend.setAddress(rs.getString("address"));
				}
			}

		} catch (Exception e) {
			System.out.println("queryfriend error:" + e);
		}

		return friend;

	}

	public FriendSet queryFriends() {
		FriendSet friends = new FriendSet();
		FriendPO friend;
		try {
			if (manager.getConnection() != null) {
				Statement stmt = manager.getConnection().createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT * FROM friend order by name");
				while (rs.next()) {
					friend = new FriendPO();
					friend.setName(rs.getString("name"));
					friend.setPhone(rs.getString("phone"));
					friend.setEmail(rs.getString("email"));
					friend.setAddress(rs.getString("address"));
					friends.add(friend);
				}
			}

		} catch (Exception e) {
			System.out.println("queryfriend error:" + e);
		}

		return friends;
	}

}
