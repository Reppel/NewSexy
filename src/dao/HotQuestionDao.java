package dao;

import entity.HotQuestion;
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
public class HotQuestionDao {
	private JdbcUtil util;
	public HotQuestionDao(){
		util=new JdbcUtil();
	}

	public void hotQuestionSave(HotQuestion question){

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
		String sql="insert into table_hotquestion(question_id) values(?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1, question.getQuestionID());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public void hotQuestionUpdate(HotQuestion question){

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
		String sql="update table_hotquestion set question_id=? where hotquestion_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,question.getQuestionID());
			ps.setInt(2, question.getId());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public void hotQuestionDelete(int id){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="delete from table_hotquestion where question_id=?;";
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


	public Question hotQuestionListById(int id){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_hotquestion where hotquestion_id=?";
		PreparedStatement ps=null;
	    Question q=null;
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
			    q=new Question();
				q=new QuestionDao().questionSearch(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return q;

	}


	public List<Question> hotQuestionListAll(){
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_hotquestion";
		PreparedStatement ps=null;
		List<Question> list=new ArrayList<Question>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Question q=new Question();
				int id=rs.getInt("question_id");
				q=new QuestionDao().questionSearch(id);

				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}



}
