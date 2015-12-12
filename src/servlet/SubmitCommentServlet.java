package servlet;

import biz.CommentBiz;
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
public class SubmitCommentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		CommentBiz commentBiz=new CommentBiz();

		String answer_id=request.getParameter("answer_id");
		int answerID=0;
		String user_id=request.getParameter("user_id");
		int userID=0;
		String username=request.getParameter("username");
		String head_url=request.getParameter("head_url");
		String content=request.getParameter("content");

		try{
			answerID=Integer.parseInt(answer_id);
			userID=Integer.parseInt(user_id);
			username=new String(username.getBytes("utf-8"),"utf-8");
			head_url=new String(head_url.getBytes("utf-8"),"utf-8");
			content=new String(content.getBytes("utf-8"),"utf-8");
		}catch (Exception e){
			e.printStackTrace();
		}

		Timestamp commentDate=new Timestamp(new Date().getTime());

		try {
			commentBiz.addComment(answerID,userID,username,head_url,content,commentDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONObject object=new JSONObject();
		object.put("date",commentDate.toString());

		PrintWriter out=response.getWriter();
		out.print(object.toString());

		out.close();







	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
