package servlet;

import biz.UserBiz;
import dao.AnswerDao;
import dao.QuestionDao;
import dao.UserDao;
import entity.Answer;
import entity.ObjectType;
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
 * Created by m on 2015/6/21.
 */
public class ExploreServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		UserDao userDao=new UserDao();
		UserBiz userBiz=new UserBiz();
		QuestionDao questionDao=new QuestionDao();
		AnswerDao answerDao=new AnswerDao();


		//main
		List<Question> question_list=new ArrayList<Question>();
		List<Answer> answer_list=new ArrayList<Answer>();
		//Question or Answer
		List object_list=new ArrayList();
		List<Integer> object_type=new ArrayList<Integer>();

		List<ObjectType> objectTypeList=new ArrayList<ObjectType>();

		try {
			question_list=questionDao.questionListAllByTime();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Question question:question_list){
			ObjectType objectType=new ObjectType();
			if(question.getAnswerCount()>0){
				Answer answer= null;
				try {
					answer = answerDao.answerListAllByQuestionId(question.getQuestionID()).get(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				objectType.setObject(answer);
				objectType.setType(1);
//				object_list.add(answer);
//				object_type.add(1);
			}
			else{
//				object_list.add(question);
//				object_type.add(0);

				objectType.setObject(question);
				objectType.setType(0);
			}
			objectTypeList.add(objectType);
		}

//		request.setAttribute("object_list",object_list);
//		request.setAttribute("object_type",object_type);
		request.setAttribute("objectType_list",objectTypeList);




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

		String path="explore.jsp";
		request.getRequestDispatcher(path).forward(request, response);





	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
	}
}
