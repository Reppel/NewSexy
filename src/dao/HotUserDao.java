package dao;

import entity.HotUser;
import entity.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/7.
 */
public class HotUserDao {
	private JdbcUtil util;
	public HotUserDao(){
		util=new JdbcUtil();
	}


	public void hotUserSave(HotUser user){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="insert into table_hotuser(user_id,follower_count) values(?,?);";
//		System.out.println(sql);
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);

			ps.setInt(1, user.getUserID());
			ps.setInt(2,user.getFollowerCount());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void hotUserUpdate(HotUser user){

		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		// 3 String sql
		String sql="update table_hotuser set user_id=?,follower_count=? where hotuser_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,user.getUserID());
			ps.setInt(2,user.getFollowerCount());
			ps.setInt(3, user.getId());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void hotUserDelete(int id){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="delete from table_hotuser where user_id=?;";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public User hotUserListById(int id){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_hotuser where hotuser_id=?";
		PreparedStatement ps=null;
		User u=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				u=new User();
				u=new UserDao().userSearch(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;

	}


	public List<User> hotUserListAll(){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_hotuser order by follower_count DESC ";
		PreparedStatement ps=null;
		List<User> list=new ArrayList<User>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=new User();
				int id=rs.getInt("user_id");
			    u=new UserDao().userSearch(id);

				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}









}
