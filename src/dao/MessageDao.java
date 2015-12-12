package dao;

import entity.Message;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/25.
 */
public class MessageDao {
	private JdbcUtil util;
	public MessageDao(){
		util=new JdbcUtil();
	}

	public void MessageSave(Message message) throws SQLException {

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
		String sql="insert into table_message(send_userid,send_username,send_head,receive_userid,receive_username,receive_head,message_content,message_date,read_status) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1, message.getSendUserID());
			ps.setString(2, message.getSendUsername());
			ps.setString(3,message.getSendHeadURL());
			ps.setInt(4, message.getReceiveUserID());
			ps.setString(5, message.getReceiveUsername());
			ps.setString(6,message.getReceiveHeadURL());
			ps.setString(7, message.getMessageContent());
			ps.setTimestamp(8,message.getMessageDate());
			ps.setInt(9,message.getReadStatus());

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

	public void messageStatusUpdate(int id) throws SQLException{
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
		String sql="update table_message set read_status=1 where message_id=?";

		PreparedStatement ps=null;
		try {

			ps=conn.prepareStatement(sql);

			ps.setInt(1,id);

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

	public List<Message> messageListAll() throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_message ORDER BY message_date DESC";
		PreparedStatement ps=null;
		List<Message> list=new ArrayList<Message>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Message m=new Message();
				m.setId(rs.getInt("message_id"));
				m.setSendUserID(rs.getInt("send_userid"));
				m.setSendUsername(rs.getString("send_username"));
				m.setSendHeadURL(rs.getString("send_head"));
				m.setReceiveUserID(rs.getInt("receive_userid"));
				m.setReceiveUsername(rs.getString("receive_username"));
				m.setReceiveHeadURL(rs.getString("receive_head"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageDate(rs.getTimestamp("message_date"));
				m.setReadStatus(rs.getInt("read_status"));

				list.add(m);
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


	public List<Message> messageListAllBySenderId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_message where send_userid=? ORDER BY message_date DESC";
		PreparedStatement ps=null;
		List<Message> list=new ArrayList<Message>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Message m=new Message();
				m.setId(rs.getInt("message_id"));
				m.setSendUserID(rs.getInt("send_userid"));
				m.setSendUsername(rs.getString("send_username"));
				m.setSendHeadURL(rs.getString("send_head"));
				m.setReceiveUserID(rs.getInt("receive_userid"));
				m.setReceiveUsername(rs.getString("receive_username"));
				m.setReceiveHeadURL(rs.getString("receive_head"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageDate(rs.getTimestamp("message_date"));
				m.setReadStatus(rs.getInt("read_status"));


				list.add(m);
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

	public List<Message> messageListAllByReceiverId(int id) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_message where reveive_userid=? ORDER BY message_date DESC";
		PreparedStatement ps=null;
		List<Message> list=new ArrayList<Message>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			while (conn==null);
			rs=ps.executeQuery();
			while (rs.next()) {
				Message m=new Message();
				m.setId(rs.getInt("message_id"));
				m.setSendUserID(rs.getInt("send_userid"));
				m.setSendUsername(rs.getString("send_username"));
				m.setSendHeadURL(rs.getString("send_head"));
				m.setReceiveUserID(rs.getInt("receive_userid"));
				m.setReceiveUsername(rs.getString("receive_username"));
				m.setReceiveHeadURL(rs.getString("receive_head"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageDate(rs.getTimestamp("message_date"));
				m.setReadStatus(rs.getInt("read_status"));


				list.add(m);
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

	public List<Message> messageListAllByTwo(int sender,int receiver) throws SQLException {
		Connection conn=util.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from table_message where send_userid=? and reveive_userid=? ORDER BY message_date DESC";
		PreparedStatement ps=null;
		List<Message> list=new ArrayList<Message>();
		ResultSet rs=null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setInt(1,sender);
			ps.setInt(2,receiver);

			while (conn==null);
			rs=ps.executeQuery();
			while(rs.next()){
				Message m=new Message();
				m.setId(rs.getInt("message_id"));
				m.setSendUserID(rs.getInt("send_userid"));
				m.setSendUsername(rs.getString("send_username"));
				m.setSendHeadURL(rs.getString("send_head"));
				m.setReceiveUserID(rs.getInt("receive_userid"));
				m.setReceiveUsername(rs.getString("receive_username"));
				m.setReceiveHeadURL(rs.getString("receive_head"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageDate(rs.getTimestamp("message_date"));
				m.setReadStatus(rs.getInt("read_status"));

				list.add(m);
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
