package test;

import dao.FollowDao;
import entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2015/6/16.
 */
public class TestFollowDao {
	public  static void main(String[] args){
//		testGetFollower();
//		testGetFollowing();
	}

	public static void testGetFollower() throws SQLException {
		int id=5;
		List<User> list=new FollowDao().listFollower(id);
		System.out.println("粉丝数： " + list.size());
		for(User user:list){
			System.out.println("id:"+user.getUserID()+" , username:"+user.getUsername());
		}
	}

	public static void testGetFollowing() throws SQLException {
		int id=5;
		List<User> list=new FollowDao().listFollowing(id);
		System.out.println("关注的人： " + list.size());
		for(User user:list){
			System.out.println("id:"+user.getUserID()+" , username:"+user.getUsername());
		}
	}



}
