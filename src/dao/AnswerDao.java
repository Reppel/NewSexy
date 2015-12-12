package dao;

import entity.Answer;
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
public class AnswerDao {
	private JdbcUtil util;
	public AnswerDao(){
		util=new JdbcUtil();
	}

	public void AnswerSave(Answer answer) throws SQLException {

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
		String sql="insert into table_answer(answer_id,question_id,question_title,user_id,username,head_url,sign,answer_detail,answer_picture,answer_date,comment_count) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1, answer.getAnswerID());
			ps.setInt(2, answer.getQuestionID());
			ps.setString(3, answer.getQuestionTitle());
			ps.setInt(4, answer.getUserID());
			ps.setString(5, answer.getUsername());
			ps.setString(6,answer.getHeadURL());
			ps.setString(7,answer.getSign());
			ps.setString(8, answer.getAnswerDetail());
			ps.setString(9,answer.getPictureURL());
			ps.setTimestamp(10, answer.getAnswerDate());
			ps.setInt(11,answer.getCommentCount());

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


	public List<Answer> answerListAll() throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_answer";
		PreparedStatement ps=null;
		List<Answer> list=new ArrayList<Answer>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Answer a=new Answer();
				a.setAnswerID(rs.getInt("answer_id"));
				a.setQuestionID(rs.getInt("question_id"));
				a.setQuestionTitle(rs.getString("question_title"));
				a.setUserID(rs.getInt("user_id"));
				a.setUsername(rs.getString("username"));
				a.setHeadURL(rs.getString("head_url"));
				a.setSign(rs.getString("sign"));
				a.setAnswerDetail(rs.getString("answer_detail"));
				a.setPictureURL(rs.getString("answer_picture"));
				a.setAnswerDate(rs.getTimestamp("answer_date"));
				a.setCommentCount(rs.getInt("comment_count"));


				list.add(a);
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


	public List<Answer> answerListAllByUserId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_answer where user_id=? ORDER BY answer_date DESC";
		PreparedStatement ps=null;
		List<Answer> list=new ArrayList<Answer>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Answer a=new Answer();
				a.setAnswerID(rs.getInt("answer_id"));
				a.setQuestionID(rs.getInt("question_id"));
				a.setQuestionTitle(rs.getString("question_title"));
				a.setUserID(rs.getInt("user_id"));
				a.setUsername(rs.getString("username"));
				a.setHeadURL(rs.getString("head_url"));
				a.setSign(rs.getString("sign"));
				a.setAnswerDetail(rs.getString("answer_detail"));
				a.setPictureURL(rs.getString("answer_picture"));
				a.setAnswerDate(rs.getTimestamp("answer_date"));
				a.setCommentCount(rs.getInt("comment_count"));



				list.add(a);
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


	public List<Answer> answerListAllByQuestionId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_answer where question_id=? ORDER BY answer_date DESC";
		PreparedStatement ps=null;
		List<Answer> list=new ArrayList<Answer>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Answer a=new Answer();
				a.setAnswerID(rs.getInt("answer_id"));
				a.setQuestionID(rs.getInt("question_id"));
				a.setQuestionTitle(rs.getString("question_title"));
				a.setUserID(rs.getInt("user_id"));
				a.setUsername(rs.getString("username"));
				a.setHeadURL(rs.getString("head_url"));
				a.setSign(rs.getString("sign"));
				a.setAnswerDetail(rs.getString("answer_detail"));
				a.setPictureURL(rs.getString("answer_picture"));
				a.setAnswerDate(rs.getTimestamp("answer_date"));
				a.setCommentCount(rs.getInt("comment_count"));


				list.add(a);
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

	public List<Answer> answerListAllByQuestionIdLimit(int id,int start,int limit) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_answer where question_id=? ORDER BY answer_date DESC LIMIT "+start+","+limit;
		PreparedStatement ps=null;
		List<Answer> list=new ArrayList<Answer>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Answer a=new Answer();
				a.setAnswerID(rs.getInt("answer_id"));
				a.setQuestionID(rs.getInt("question_id"));
				a.setQuestionTitle(rs.getString("question_title"));
				a.setUserID(rs.getInt("user_id"));
				a.setUsername(rs.getString("username"));
				a.setHeadURL(rs.getString("head_url"));
				a.setSign(rs.getString("sign"));
				a.setAnswerDetail(rs.getString("answer_detail"));
				a.setPictureURL(rs.getString("answer_picture"));
				a.setAnswerDate(rs.getTimestamp("answer_date"));
				a.setCommentCount(rs.getInt("comment_count"));


				list.add(a);
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

	public Answer answerSearchByAnswerId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_answer where answer_id=? limit 1";
		PreparedStatement ps=null;
		Answer a=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				a=new Answer();
				a.setAnswerID(rs.getInt("answer_id"));
				a.setQuestionID(rs.getInt("question_id"));
				a.setQuestionTitle(rs.getString("question_title"));
				a.setUserID(rs.getInt("user_id"));
				a.setUsername(rs.getString("username"));
				a.setHeadURL(rs.getString("head_url"));
				a.setSign(rs.getString("sign"));
				a.setAnswerDetail(rs.getString("answer_detail"));
				a.setPictureURL(rs.getString("answer_picture"));
				a.setAnswerDate(rs.getTimestamp("answer_date"));
				a.setCommentCount(rs.getInt("comment_count"));

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

		return a;

	}

	public Answer answerSearchByQuestionAndUserId(int question_id,int user_id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_answer where question_id=? and user_id=? ORDER BY answer_date DESC limit 1";
		PreparedStatement ps=null;
		Answer a=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, question_id);
			ps.setInt(2,user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				a=new Answer();
				a.setAnswerID(rs.getInt("answer_id"));
				a.setQuestionID(rs.getInt("question_id"));
				a.setQuestionTitle(rs.getString("question_title"));
				a.setUserID(rs.getInt("user_id"));
				a.setUsername(rs.getString("username"));
				a.setHeadURL(rs.getString("head_url"));
				a.setSign(rs.getString("sign"));
				a.setAnswerDetail(rs.getString("answer_detail"));
				a.setPictureURL(rs.getString("answer_picture"));
				a.setAnswerDate(rs.getTimestamp("answer_date"));
				a.setCommentCount(rs.getInt("comment_count"));

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

		return a;

	}


	public void answerUpdateCount(Answer answer) throws SQLException {

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
		String sql="update table_answer set comment_count=? where answer_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,answer.getCommentCount());
			ps.setInt(2,answer.getAnswerID());

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


}
