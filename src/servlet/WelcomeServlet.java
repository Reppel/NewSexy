package servlet;

import biz.UserBiz;
import dao.QuestionDao;
import entity.Question;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/9.
 */
public class WelcomeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> userList=new ArrayList<User>();
		List<Question> questionList=new ArrayList<Question>();
		List<User> user_list=new ArrayList<User>();
		List<Question> question_list=new ArrayList<Question>();
	    UserBiz userBiz=new UserBiz();
		try {
			userList=userBiz.userListAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			questionList=new QuestionDao().questionListAllByCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		for(int i=0;i<8;i++){
			user_list.add(userList.get(i));
			question_list.add(questionList.get(i));
		}



		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		request.setAttribute("user_list", user_list);
		request.setAttribute("question_list",question_list);
		String path="login.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

//		System.out.println("WelcomeServlet!");


	}
}
