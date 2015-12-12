package test;

import dao.HotUserDao;
import entity.User;

import java.util.List;

/**
 * Created by m on 2015/6/7.
 */
public class TestHotUserDao {
	public static void main(String[] args){
//		testUserListById();
//		testUserList();
	}


	public static void testUserListById(){
		int id=1;
		User u=new HotUserDao().hotUserListById(id);
		System.out.println(u.getUserID()+","+u.getUsername()+","+u.getHeadURL());
	}

	public static void testUserList(){
		List<User> list=new HotUserDao().hotUserListAll();
		for(User u:list){
			System.out.println(u.getUserID()+","+u.getUsername()+","+u.getHeadURL());
		}
	}



}
