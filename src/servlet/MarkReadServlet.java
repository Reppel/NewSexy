package servlet;

import dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by m on 2015/6/25.
 */
public class MarkReadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		MessageDao messageDao=new MessageDao();

		String message_id=request.getParameter("message_id");
		int MessageID=Integer.parseInt(message_id);

		try {
			messageDao.messageStatusUpdate(MessageID);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
