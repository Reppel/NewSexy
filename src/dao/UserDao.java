package dao;

import entity.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilixia on 2015/6/6.
 */
public class UserDao {
	private JdbcUtil util;
	public UserDao(){
		util=new JdbcUtil();
	}


	public void userSave(User user) throws SQLException {

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
		String sql="insert into table_user(email,username,password,head_url,sign,follower_count,following_count) values(?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getHeadURL());
			ps.setString(5, user.getSign());
			ps.setInt(6,user.getFollowerCount());
			ps.setInt(7,user.getFollowingCount());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}


	}


	public void userUpdate(User user) throws SQLException {

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
		String sql="update table_user set password=?,head_url=?,sign=? where user_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getHeadURL());
			ps.setString(3, user.getSign());

			ps.setInt(4, user.getUserID());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}


	public void userUpdateFollower(User user) throws SQLException {

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
		String sql="update table_user set follower_count=? where user_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,user.getFollowerCount());
			ps.setInt(2,user.getUserID());


			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}

	public void userUpdateFollowing(User user) throws SQLException {

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
		String sql="update table_user set following_count=? where user_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,user.getFollowingCount());
			ps.setInt(2,user.getUserID());


			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}



	public List<User> userListAll() throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_user ORDER BY follower_count DESC";
		PreparedStatement ps=null;
		List<User> list=new ArrayList<User>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setUserID(rs.getInt("user_id"));
				u.setEmail(rs.getString("email"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setHeadURL(rs.getString("head_url"));
				u.setSign(rs.getString("sign"));
				u.setFollowerCount(rs.getInt("follower_count"));
				u.setFollowingCount(rs.getInt("following_count"));


				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}

		return list;

	}

	public void userDelete(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="delete from table_user where user_id=?";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}


	public User userListByUserId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_user where user_id=?";
		PreparedStatement ps=null;
		User u=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				u=new User();
				u.setUserID(rs.getInt("user_id"));
			    u.setEmail(rs.getString("email"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setHeadURL(rs.getString("head_url"));
				u.setSign(rs.getString("sign"));
				u.setFollowerCount(rs.getInt("follower_count"));
				u.setFollowingCount(rs.getInt("following_count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}

		return u;

	}


	public User userSearch(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_user;";
		ResultSet rs=null;
		PreparedStatement ps=null;
		User u=new User();
		try {

			ps=conn.prepareStatement(sql);

			rs=ps.executeQuery();
			while(rs.next()){
				if(id==rs.getInt("user_id")){
					u.setUserID(rs.getInt("user_id"));
					u.setEmail(rs.getString("email"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setHeadURL(rs.getString("head_url"));
					u.setSign(rs.getString("sign"));
					u.setFollowerCount(rs.getInt("follower_count"));
					u.setFollowingCount(rs.getInt("following_count"));

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}

		return u;
	}


	public User userSearch(String name) throws SQLException {
		if(name!=null){
			Connection conn=util.getConnection();
			do{
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(conn==null);
			String sql="select * from table_user;";
			ResultSet rs=null;
			PreparedStatement ps=null;
			User u=new User();
			try {

				ps=conn.prepareStatement(sql);

				rs=ps.executeQuery();
				while(rs.next()){
//					System.out.println(rs.getString("userName"));
					if(name.equals(rs.getString("username"))){
						u.setUserID(rs.getInt("user_id"));
					    u.setEmail(rs.getString("email"));
						u.setUsername(rs.getString("username"));
						u.setPassword(rs.getString("password"));
						u.setHeadURL(rs.getString("head_url"));
						u.setSign(rs.getString("sign"));
						u.setFollowerCount(rs.getInt("follower_count"));
						u.setFollowingCount(rs.getInt("following_count"));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps != null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
				if(rs!=null){
					rs.close();
				}
			}
			return u;
		}
		User u=new User();
		u.setUserID(0);
		return u;
	}


	public User userSearchByEmail(String email) throws SQLException {
		if(email!=null){
			Connection conn=util.getConnection();
			do{
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(conn==null);
			String sql="select * from table_user;";
			ResultSet rs=null;
			PreparedStatement ps=null;
			User u=new User();
			try {

				ps=conn.prepareStatement(sql);

				rs=ps.executeQuery();
				while(rs.next()){
//					System.out.println(rs.getString("userName"));
					if(email.equals(rs.getString("email"))){
						u.setUserID(rs.getInt("user_id"));
						u.setEmail(rs.getString("email"));
						u.setUsername(rs.getString("username"));
						u.setPassword(rs.getString("password"));
						u.setHeadURL(rs.getString("head_url"));
						u.setSign(rs.getString("sign"));
						u.setFollowerCount(rs.getInt("follower_count"));
						u.setFollowingCount(rs.getInt("following_count"));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps != null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
				if(rs!=null){
					rs.close();
				}
			}
			return u;
		}
		User u=new User();
		u.setUserID(0);
		return u;
	}


	public List<User> userSearchByKey(String key) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_user ORDER BY follower_count DESC;";
		ResultSet rs=null;
		PreparedStatement ps=null;
		List<User> list=new ArrayList<User>();
		try {

			ps=conn.prepareStatement(sql);

			rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getString("username").contains(key)){
					User u=new User();
					u.setUserID(rs.getInt("user_id"));
					u.setEmail(rs.getString("email"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setHeadURL(rs.getString("head_url"));
					u.setSign(rs.getString("sign"));
					u.setFollowerCount(rs.getInt("follower_count"));
					u.setFollowingCount(rs.getInt("following_count"));

					list.add(u);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}
		return list;
	}


    /**
	 * result = -1 bad request
	 * result = 0  not exist
	 * result = 1  exist
	 *
	 * */
	public int isExisted(String email,String name) throws SQLException {
		int result=-1;
		if((email!="")||(name!="")){
			Connection conn=util.getConnection();
			do{
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(conn==null);
			String sql="select * from table_user where email=? or username=?;";
			ResultSet rs=null;
			PreparedStatement ps=null;
			User u=new User();
			try {

				ps=conn.prepareStatement(sql);
				ps.setString(1, email);
				ps.setString(2,name);
				rs=ps.executeQuery();


				//exist
				if(rs.next()){
					result=1;
				}
				//not exist
				else{
				    result=0;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps != null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
				if(rs!=null){
					rs.close();
				}
			}
			return result;
		}

		return result;
	}



}
