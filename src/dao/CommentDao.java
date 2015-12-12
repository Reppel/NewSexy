package dao;

import entity.Comment;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/8.
 */
public class CommentDao {
	private JdbcUtil util;
	public CommentDao(){
		util=new JdbcUtil();
	}


	public void CommentSave(Comment comment) throws SQLException {

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
		String sql="insert into table_comment(comment_id,answer_id,user_id,username,head_url,comment_detail,comment_date) values(?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1, comment.getCommentID());
			ps.setInt(2, comment.getAnswerID());
			ps.setInt(3, comment.getUserID());
			ps.setString(4,comment.getUsername());
			ps.setString(5,comment.getHeadURL());
			ps.setString(6, comment.getCommentDetail());
			ps.setTimestamp(7, comment.getCommentDate());

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

	public List<Comment> commentListByAnswerId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_comment where answer_id=? ORDER BY comment_date";
		PreparedStatement ps=null;
		List<Comment> list=new ArrayList<Comment>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){

				Comment c=new Comment();
				c.setCommentID(rs.getInt("comment_id"));
				c.setAnswerID(rs.getInt("answer_id"));
				c.setUserID(rs.getInt("user_id"));
				c.setUsername(rs.getString("username"));
				c.setHeadURL(rs.getString("head_url"));
				c.setCommentDetail(rs.getString("comment_detail"));
				c.setCommentDate(rs.getTimestamp("comment_date"));


				list.add(c);
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
}
