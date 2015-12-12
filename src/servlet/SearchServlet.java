package servlet;

import biz.UserBiz;
import dao.QuestionDao;
import dao.UserDao;
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
 * Created by m on 2015/6/25.
 */
public class SearchServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		QuestionDao questionDao=new QuestionDao();
		UserDao userDao=new UserDao();
		UserBiz userBiz=new UserBiz();

		String key=request.getParameter("key");
		try{
			key=new String(key.getBytes("ISO-8859-1"),"utf-8");
		}catch (Exception e){
			e.printStackTrace();
		}

		List<Question> questionList=new ArrayList<Question>();
		List<User> userList=new ArrayList<User>();

		try {
			questionList=questionDao.searchQuestionByKey(key);
			userList=userDao.userSearchByKey(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		request.setAttribute("key",key);
		request.setAttribute("question_list",questionList);
		request.setAttribute("user_list",userList);


		//hot
		List<User> hotUserList=new ArrayList<User>();
		List<User> hot_user_list=new ArrayList<User>();
		List<Question> hotQuestionList=new ArrayList<Question>();
		List<Question> hot_question_list=new ArrayList<Question>();
		try {
			hotUserList=userBiz.userListAll();
			hotQuestionList=questionDao.questionListAllByCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}



		for(int i=0;i<5;i++){
			if(i<3){
				hot_user_list.add(hotUserList.get(i));
			}
			hot_question_list.add(hotQuestionList.get(i));
		}

		request.setAttribute("hot_user_list",hot_user_list);
		request.setAttribute("hot_question_list",hot_question_list);


		String path="search.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
}
