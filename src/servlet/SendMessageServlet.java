package servlet;

import dao.MessageDao;
import entity.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by m on 2015/6/25.
 */
public class SendMessageServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		MessageDao messageDao=new MessageDao();

		String send_id=request.getParameter("from_id");
		String sender_username=request.getParameter("from_username");
		String sender_head=request.getParameter("from_head");
		String receive_id=request.getParameter("to_id");
		String receiver_username=request.getParameter("to_username");
		String receiver_head=request.getParameter("to_head");
		String content=request.getParameter("content");
		int sender=0;
		int receiver=0;
		try{
			sender=Integer.parseInt(send_id);
			receiver=Integer.parseInt(receive_id);
		}catch (Exception e){
			e.printStackTrace();
		}


		Timestamp messageDate=new Timestamp(new Date().getTime());
		Message message=new Message();
		message.setSendUserID(sender);
		message.setSendUsername(sender_username);
		message.setSendHeadURL(sender_head);
		message.setReceiveUserID(receiver);
		message.setReceiveUsername(receiver_username);
		message.setReceiveHeadURL(receiver_head);
		message.setMessageContent(content);
		message.setMessageDate(messageDate);
		message.setReadStatus(0);

		try {
			messageDao.MessageSave(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

	}
}
