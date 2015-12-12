package servlet;

import dao.CommentDao;
import entity.Comment;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/24.
 */
public class LoadCommentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		CommentDao commentDao=new CommentDao();
		List<Comment> commentList=new ArrayList<Comment>();


		String answer_id=request.getParameter("answer_id");
		int id=0;
		try{
			id=Integer.parseInt(answer_id);
		}catch (Exception e){
			e.printStackTrace();
		}


		try {
			commentList=commentDao.commentListByAnswerId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONArray jsonArray=new JSONArray();
		jsonArray.put(commentList);

		PrintWriter out=response.getWriter();
		out.print(jsonArray.toString());
		System.out.println(jsonArray.toString());
		out.close();



	}
}
