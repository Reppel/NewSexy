package dao;

import entity.FollowRelation;
import entity.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/16.
 */
public class FollowDao {
	private JdbcUtil util;
	public FollowDao(){
		util=new JdbcUtil();
	}

	public void followRelationSave(FollowRelation relation) throws SQLException {

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
		String sql="insert into table_follow(from_userid,to_userid,relation) values(?,?,?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1, relation.getFromUserID());
			ps.setInt(2, relation.getToUserID());
			ps.setInt(3, relation.getRelation());

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



	public void relationUpdate(FollowRelation relation) throws SQLException {

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
		String sql="update table_follow set relation=? where (from_userid=? and to_userid=?)";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,relation.getRelation());
			ps.setInt(2,relation.getFromUserID());
			ps.setInt(3,relation.getToUserID());

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


	public FollowRelation getRelation(int from_id,int to_id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_follow where from_userid=? and to_userid=?";
		PreparedStatement ps=null;
		FollowRelation relation=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, from_id);
			ps.setInt(2,to_id);

			rs=ps.executeQuery();
			if(rs.next()){
				relation=new FollowRelation();
				relation.setFromUserID(rs.getInt("from_userid"));
				relation.setToUserID(rs.getInt("to_userid"));
				relation.setRelation(rs.getInt("relation"));
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

		return relation;

	}


	public void relationDelete(int from,int to) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="delete from table_follow where from_userid=? and to_userid=?";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, from);
			ps.setInt(2,to);
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


	public List<User> listFollower(int user_id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql1="select * from table_follow where (from_userid=? and (relation=1 or relation=2))";
		String sql2="select * from table_follow where (to_userid=? and (relation=0 or relation=2))";
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		List<User> list=new ArrayList<User>();
		ResultSet rs1=null;
		ResultSet rs2=null;
		try {

			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1,user_id);

			while (conn==null);
			rs1=ps1.executeQuery();
			while(rs1.next()){
				User u=new User();
				int id=rs1.getInt("to_userid");
				u=new UserDao().userSearch(id);

				list.add(u);
			}


			ps2=conn.prepareStatement(sql2);
			ps2.setInt(1,user_id);
			while(conn==null);
			rs2=ps2.executeQuery();
			while(rs2.next()){
				User u=new User();
				int id=rs2.getInt("from_userid");
				u=new UserDao().userSearch(id);

				list.add(u);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps1!= null){
				ps1.close();
			}
			if(ps2!=null){
				ps2.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs1!=null){
				rs1.close();
			}
			if(rs2!=null){
				rs2.close();
			}
		}

		return list;

	}


	public List<User> listFollowing(int user_id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql1="select * from table_follow where (from_userid=? and (relation=0 or relation=2))";
		String sql2="select * from table_follow where (to_userid=? and (relation=1 or relation=2))";
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		List<User> list=new ArrayList<User>();
		ResultSet rs1=null;
		ResultSet rs2=null;
		try {

			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1,user_id);

			while (conn==null);
			rs1=ps1.executeQuery();
			while(rs1.next()){
				User u=new User();
				int id=rs1.getInt("to_userid");
				u=new UserDao().userSearch(id);

				list.add(u);
			}


			ps2=conn.prepareStatement(sql2);
			ps2.setInt(1,user_id);
			while(conn==null);
			rs2=ps2.executeQuery();
			while(rs2.next()){
				User u=new User();
				int id=rs2.getInt("from_userid");
				u=new UserDao().userSearch(id);

				list.add(u);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps1 != null){
				ps1.close();
			}
			if(ps2!=null){
				ps2.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs1!=null){
				rs1.close();
			}
			if(rs2!=null){
				rs2.close();
			}
		}

		return list;

	}



}
