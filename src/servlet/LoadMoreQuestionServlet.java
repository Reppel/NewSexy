package servlet;

import dao.AnswerDao;
import entity.Answer;
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
public class LoadMoreQuestionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		AnswerDao answerDao=new AnswerDao();

		List<Answer> answerList=new ArrayList<Answer>();

		String question_id=request.getParameter("question_id");
		String str_count=request.getParameter("showed_count");
		int count=0;
		int id=0;
		try{
			id=Integer.parseInt(question_id);
			count=Integer.parseInt(str_count);

		}catch (Exception e){
			e.printStackTrace();
		}

		try {
			answerList=answerDao.answerListAllByQuestionIdLimit(id,count,3);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONArray jsonArray=new JSONArray();
		jsonArray.put(answerList);
//		for(Answer answer:answerList){
//			JSONObject object=new JSONObject();
//			object.put("answer",answer);
//			jsonArray.put(object);
//		}


		PrintWriter out=response.getWriter();
		out.print(jsonArray.toString());
		out.close();





	}
}
