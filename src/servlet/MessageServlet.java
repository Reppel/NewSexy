package servlet;

import dao.MessageDao;
import entity.Message;
import entity.ObjectType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/25.
 */
public class MessageServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		MessageDao messageDao=new MessageDao();

		HttpSession session=request.getSession();
		Integer userID= (Integer) session.getAttribute("userID");

		List<Message> messageList=new ArrayList<Message>();
		List<ObjectType> objectTypeList=new ArrayList<ObjectType>();
		try {
			messageList=messageDao.messageListAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Message message:messageList){
			ObjectType objectType=new ObjectType();
			if(message.getSendUserID()==userID){
				objectType.setObject(message);
				objectType.setType(1);
			}else if(message.getReceiveUserID()==userID){
				objectType.setObject(message);
				objectType.setType(0);
			}
			objectTypeList.add(objectType);
		}

		request.setAttribute("objectType_list",objectTypeList);

		String path="message.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
}
