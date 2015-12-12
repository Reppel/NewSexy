package test;

import dao.UserDao;
import entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2015/6/6.
 */
public class TestUserDao {

	public static void main(String[] args){
//		testUserSave();
//		testUserUpdate();
//		testUserDelete();
//		testUserList();
//		testUserSearch();
//		testUserListById();
//		testExist();

	}

	public static void testUserSave() throws SQLException {
		User u=new User();
		u.setEmail("tom@abc.com");
		u.setUsername("test99");
		u.setPassword("12385");
		u.setHeadURL("images/2.jpg");
		u.setSign("签名哈哈哈");

		new UserDao().userSave(u);

	}

	public static void testUserUpdate() throws SQLException {
		User u=new User();
		UserDao dao=new UserDao();
		u.setUserID(2);
		u.setEmail(dao.userSearch(2).getEmail());
		u.setUsername(dao.userSearch(2).getUsername());
		u.setPassword(dao.userSearch(2).getPassword());
		u.setHeadURL(dao.userSearch(2).getHeadURL());
		u.setSign("我也要改一下签名");
		dao.userUpdate(u);
	}

	public static void testUserDelete() throws SQLException {
		int id=3;
		new UserDao().userDelete(3);
	}

	public static void testUserList() throws SQLException {
		List<User> list=new UserDao().userListAll();
		for(User u:list){
			System.out.println(u.getUserID()+","+u.getEmail()+","+u.getUsername()+","+u.getPassword()+","+u.getHeadURL()+u.getSign());
		}
	}

	public static void testUserSearch() throws SQLException {
		String name="测试test";
		int id=1;
		User u=new UserDao().userSearch(name);
		System.out.println(u.getUserID()+","+u.getEmail()+","+u.getUsername()+","+u.getPassword()+","+u.getHeadURL()+u.getSign());

	}

	public static void testUserListById() throws SQLException {
		int id=4;
		User u=new UserDao().userListByUserId(id);
		System.out.println(u.getUserID()+","+u.getEmail()+","+u.getUsername()+","+u.getPassword()+","+u.getHeadURL()+u.getSign());
	}

	public static void testExist() throws SQLException {
		String email="122@tom.com";
		String name="lalal";
		int result=new UserDao().isExisted(email,name);
		String print="";
	    switch (result){
			case -1:print="Bad request!";break;
			case 0:print="Not exist!";break;
			case 1:print="Exist!";break;
		}

		System.out.println(print);

	}

}
