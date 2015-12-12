package servlet;

import biz.QuestionBiz;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by m on 2015/6/25.
 */
public class SubmitQuestionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		QuestionBiz questionBiz=new QuestionBiz();

		String user_id=request.getParameter("user_id");
		String username=request.getParameter("username");
		String question_title=request.getParameter("question_title");
		String question_detail=request.getParameter("question_detail");
		int userID=0;

		try{
			userID=Integer.parseInt(user_id);
			username=new String(username.getBytes("utf-8"),"utf-8");
			question_title=new String(question_title.getBytes("utf-8"),"utf-8");
			question_detail=new String(question_detail.getBytes("utf-8"),"utf-8");
		}catch (Exception e){
			e.printStackTrace();
		}

		Timestamp questionDate=new Timestamp(new Date().getTime());

		int questionID= 0;
		try {
			questionID = questionBiz.addQuestion(userID,username,question_title,question_detail,questionDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONObject object=new JSONObject();
		object.put("questionID",questionID);

		PrintWriter out=response.getWriter();
		out.print(object.toString());
		out.close();


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
