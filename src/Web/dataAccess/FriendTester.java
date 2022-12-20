package Web.dataAccess;

import java.sql.Statement;

public class FriendTester {
	private DataAccessManager dbManager;
	private FriendDAO dao;

	public FriendTester() {
		dbManager = new DataAccessManager();
		dao = new FriendDAO(dbManager);
	}

	public void closeConnection() {
		dbManager.closeConnection();
	}

	private void createTable() {
		try {
			Statement stmt = dbManager.getConnection().createStatement();
			stmt.execute("create table friend(name varchar(12) primary key,phone varchar(20),"
					+ "" + " email varchar(20),address varchar(30))");
			System.out.println("Created table friend");
		} catch (Exception e) {
			System.out.println("Created table friend error:" + e);
		}
	}

	private void dropTable() {
		try {
			Statement stmt = dbManager.getConnection().createStatement();
			stmt.execute("drop table friend");
			System.out.println("drop table friend");
		} catch (Exception e) {
			System.out.println("drop table friend error:" + e);
		}
	}

	private void initExcelFile() {
		FriendSet friends = new FriendSet();
		FriendPO friend = new FriendPO();
		friend.setName("刘云");
		friend.setPhone("13999999999");
		friend.setEmail("765431@qq.com");
		friend.setAddress("东北大学浑南校区信息馆118");
		friends.add(friend);

		friend = new FriendPO();
		friend.setName("李勇");
		friend.setPhone("13888889999");
		friend.setEmail("456789@qq.com");
		friend.setAddress("东北大学浑南校区建筑馆118312");
		friends.add(friend);

		friend = new FriendPO();
		friend.setName("宋涛");
		friend.setPhone("13888888888");
		friend.setEmail("123456@qq.com");
		friend.setAddress("东北大学浑南校区管理馆118518");
		friends.add(friend);
		dao.writeFriendsToExcelFile(friends);
		System.out.println("=============初始化Excel文件=====================");
		System.out.println(" 姓名 " + "             电话  "
				+ "                                              电子信箱 "
				+ "                                                     地址 ");
		System.out
				.println("--------------------------------------------------------------- ");
		if (friends != null && friends.size() > 0) {
			for (FriendPO one : friends) {
				System.out.println(one.toString());
			}
		}
	}

	private FriendSet testLoadFriendsFromExcelFile() {
		System.out
				.println("=============加载Excel文件得到通讯录信息=====================");
		System.out.println(" 姓名 " + "             电话  "
				+ "                                              电子信箱 "
				+ "                                                     地址 ");
		System.out
				.println("--------------------------------------------------------------- ");
		FriendSet friends = dao.loadFriendsFromExcelFile();
		if (friends != null && friends.size() > 0) {
			for (FriendPO one : friends) {
				System.out.println(one.toString());
			}
		}
		return friends;
	}

	private void testWriteFriendsToXMLFile(FriendSet friends) {
		System.out.println("=============把通讯录信息写入XML文件=====================");
		System.out.println(" 姓名 " + "             电话  "
				+ "                                              电子信箱 "
				+ "                                                     地址 ");
		System.out
				.println("--------------------------------------------------------------- ");
		if (friends != null && friends.size() > 0) {
			for (FriendPO one : friends) {
				System.out.println(one.toString());
			}
		}
		dao.writeFriendsToXmlFile(friends);

	}

	private FriendSet testLoadFriendsFromXMLFile() {
		System.out.println("=============加载XML文件得到通讯录信息=====================");
		System.out.println(" 姓名 " + "             电话  "
				+ "                                              电子信箱 "
				+ "                                                     地址 ");
		System.out
				.println("--------------------------------------------------------------- ");
		FriendSet friends = dao.loadFriendsFromXMLFile();
		if (friends != null && friends.size() > 0) {
			for (FriendPO one : friends) {
				System.out.println(one.toString());
			}
		}
		return friends;
	}

	private void testQueryRecords() {
		System.out.println(" 姓名 " + "             电话  "
				+ "                                              电子信箱 "
				+ "                                                     地址 ");
		System.out
				.println("--------------------------------------------------------------- ");
		FriendSet friends = dao.queryFriends();
		if (friends != null && friends.size() > 0) {
			for (FriendPO one : friends) {
				System.out.println(one.toString());
			}
		}
	}

	private void testInsertRecord() {
		FriendPO friend = new FriendPO();
		friend.setName("天才");
		friend.setPhone("18888888888");
		friend.setEmail("888888@qq.com");
		friend.setAddress("东北大学南湖校区综合楼818");
		dao.addFriend(friend);
		System.out.println();
		System.out.println("==========增加天才后通讯录信息==========");
		testQueryRecords();

	}

	private void testUpdateRecord() {
		FriendPO friend = dao.queryFriendByName("天才");
		friend.setAddress("东北大学浑南校区生命楼118");
		dao.updateFriend(friend);
		System.out.println();
		System.out.println("==========天才更新地址后通讯录信息==========");
		testQueryRecords();
	}

	private void testBactchAddRecords(FriendSet friends) {
		dao.addFriends(friends);
		System.out.println();
		System.out
				.println("=============向数据库中批量增加如下通讯录信息=====================");
		testQueryRecords();
	}

	private void testDeleteRecord() {
		FriendPO friend = dao.queryFriendByName("天才");
		dao.deleteFriend(friend);
		System.out.println();
		System.out.println("==========天才转学后的通讯录信息==========");
		testQueryRecords();
	}

	public static void main(String arg[]) {
		FriendTester daoTester = new FriendTester();
		daoTester.initExcelFile();
		FriendSet friends = daoTester.testLoadFriendsFromExcelFile();
		daoTester.testWriteFriendsToXMLFile(friends);
		friends = daoTester.testLoadFriendsFromXMLFile();
		daoTester.dropTable();
		daoTester.createTable();
		daoTester.testBactchAddRecords(friends);
		daoTester.testQueryRecords();
		 daoTester.testInsertRecord();
		 daoTester.testQueryRecords();
		daoTester.testUpdateRecord();
		daoTester.testQueryRecords();
		daoTester.testDeleteRecord();
		daoTester.testQueryRecords();
		daoTester.closeConnection();
	}
}
