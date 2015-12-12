package dao;

import entity.Question;
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
public class QuestionDao {
	private JdbcUtil util;
	public QuestionDao(){
		util=new JdbcUtil();
	}


	public void QuestionSave(Question question) throws SQLException {

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
		String sql="insert into table_question(user_id,username,question_title,question_detail,question_date,answer_count) values(?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1, question.getUserID());
			ps.setString(2, question.getUsername());
			ps.setString(3, question.getQuestionTitle());
			ps.setString(4, question.getQuestionDetail());
			ps.setTimestamp(5, question.getQuestionDate());
			ps.setInt(6,question.getAnswerCount());

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


	public void questionUpdate(Question question) throws SQLException {

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
		String sql="update table_question set question_title=?,question_detail=? where question_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setString(1, question.getQuestionTitle());
			ps.setString(2, question.getQuestionDetail());

			ps.setInt(3, question.getQuestionID());

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


	public void questionUpdateCount(Question question) throws SQLException {

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
		String sql="update table_question set answer_count=? where question_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,question.getAnswerCount());
			ps.setInt(2,question.getQuestionID());

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


	public void questionDelete(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="delete from table_question where question_id=?";
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


	public List<Question> questionListAllByCount() throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question ORDER BY answer_count DESC";
		PreparedStatement ps=null;
		List<Question> list=new ArrayList<Question>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Question q=new Question();
				q.setQuestionID(rs.getInt("question_id"));
				q.setUserID(rs.getInt("user_id"));
				q.setUsername(rs.getString("username"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionDetail(rs.getString("question_detail"));
				q.setQuestionDate(rs.getTimestamp("question_date"));
				q.setAnswerCount(rs.getInt("answer_count"));


				list.add(q);
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

	public List<Question> questionListAllByTime() throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question ORDER BY question_date DESC";
		PreparedStatement ps=null;
		List<Question> list=new ArrayList<Question>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Question q=new Question();
				q.setQuestionID(rs.getInt("question_id"));
				q.setUserID(rs.getInt("user_id"));
				q.setUsername(rs.getString("username"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionDetail(rs.getString("question_detail"));
				q.setQuestionDate(rs.getTimestamp("question_date"));
				q.setAnswerCount(rs.getInt("answer_count"));


				list.add(q);
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


	public List<Question> questionListAllByUserId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question where user_id=? ORDER BY question_date DESC";
		PreparedStatement ps=null;
		List<Question> list=new ArrayList<Question>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Question q=new Question();
				q.setQuestionID(rs.getInt("question_id"));
				q.setUserID(rs.getInt("user_id"));
				q.setUsername(rs.getString("username"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionDetail(rs.getString("question_detail"));
				q.setQuestionDate(rs.getTimestamp("question_date"));
				q.setAnswerCount(rs.getInt("answer_count"));

				list.add(q);
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

	public Question questionListByQuestionId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question where question_id=? limit 1";
		PreparedStatement ps=null;
		Question q=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				q=new Question();
				q.setQuestionID(rs.getInt("question_id"));
				q.setUserID(rs.getInt("user_id"));
				q.setUsername(rs.getString("username"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionDetail(rs.getString("question_detail"));
				q.setQuestionDate(rs.getTimestamp("question_date"));
				q.setAnswerCount(rs.getInt("answer_count"));

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

		return q;

	}

	public Question questionSearchByTitle(String title) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question where question_title=? ORDER BY question_date DESC limit 1";
		PreparedStatement ps=null;
		Question q=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setString(1, title);
			rs=ps.executeQuery();
			if(rs.next()){
				q=new Question();
				q.setQuestionID(rs.getInt("question_id"));
				q.setUserID(rs.getInt("user_id"));
				q.setUsername(rs.getString("username"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionDetail(rs.getString("question_detail"));
				q.setQuestionDate(rs.getTimestamp("question_date"));
				q.setAnswerCount(rs.getInt("answer_count"));

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

		return q;

	}


	public Question questionSearch(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question;";
		ResultSet rs=null;
		PreparedStatement ps=null;
        Question q=new Question();
		try {

			ps=conn.prepareStatement(sql);

			rs=ps.executeQuery();
			while(rs.next()){
				if(id==rs.getInt("question_id")){
					q.setQuestionID(rs.getInt("question_id"));
					q.setUserID(rs.getInt("user_id"));
					q.setUsername(rs.getString("username"));
					q.setQuestionTitle(rs.getString("question_title"));
					q.setQuestionDetail(rs.getString("question_detail"));
					q.setQuestionDate(rs.getTimestamp("question_date"));
					q.setAnswerCount(rs.getInt("answer_count"));


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
		return q;
	}

	public List<Question> searchQuestionByKey(String key) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_question ORDER BY answer_count DESC;";
		ResultSet rs=null;
		PreparedStatement ps=null;
		List<Question> list=new ArrayList<Question>();
		try {

			ps=conn.prepareStatement(sql);

			rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getString("question_title").contains(key)){
					Question q=new Question();
					q.setQuestionID(rs.getInt("question_id"));
					q.setUserID(rs.getInt("user_id"));
					q.setUsername(rs.getString("username"));
					q.setQuestionTitle(rs.getString("question_title"));
					q.setQuestionDetail(rs.getString("question_detail"));
					q.setQuestionDate(rs.getTimestamp("question_date"));
					q.setAnswerCount(rs.getInt("answer_count"));

					list.add(q);
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





}
