package servlet;

import biz.AnswerBiz;
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
 * Created by m on 2015/6/24.
 */
public class SubmitAnswerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		AnswerBiz answerBiz=new AnswerBiz();

		String question_id=request.getParameter("question_id");
		int questionID=0;
		String question_title=request.getParameter("question_title");
		String user_id=request.getParameter("user_id");
		int userID=0;
		String username=request.getParameter("username");
		String head_url=request.getParameter("head_url");
		String sign=request.getParameter("sign");
		String content=request.getParameter("content");
		String picture=request.getParameter("picture");

		try{
			questionID=Integer.parseInt(question_id);
			userID=Integer.parseInt(user_id);
		}catch (Exception e){
			e.printStackTrace();
		}


		Timestamp answerDate=new Timestamp(new Date().getTime());

		int answerID= 0;
		try {
			answerID = answerBiz.addAnswer(questionID,question_title,userID,username,head_url,sign,content,picture,answerDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONObject object=new JSONObject();
		object.put("answerID",answerID);
		object.put("date",answerDate.toString());

		PrintWriter out=response.getWriter();
		out.print(object.toString());
		out.close();







	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
