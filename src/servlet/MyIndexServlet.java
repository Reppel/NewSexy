package servlet;

import biz.UserBiz;
import dao.AnswerDao;
import dao.FollowDao;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2015/6/25.
 */
public class MyIndexServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		UserDao userDao=new UserDao();
		UserBiz userBiz=new UserBiz();
		QuestionDao questionDao=new QuestionDao();
		AnswerDao answerDao=new AnswerDao();
		FollowDao followDao=new FollowDao();

		List<User> followingUser=new ArrayList<User>();
		List<Question> questionList=new ArrayList<Question>();

		HttpSession session=request.getSession();
		Integer userID= (Integer) session.getAttribute("userID");

		try {
			questionList=questionDao.questionListAllByTime();
			followingUser=followDao.listFollowing(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<ObjectType> objectTypeList=new ArrayList<ObjectType>();
		//获得用户关注的人列表

	    for(int i=0;i<questionList.size();i++){
			ObjectType objectType=new ObjectType();
			Question question=questionList.get(i);
			if(question.getAnswerCount()>0){
				Answer answer= null;
				try {
					answer = answerDao.answerListAllByQuestionIdLimit(question.getQuestionID(),0,1).get(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for(int j=0;j<followingUser.size();j++){
					User user=followingUser.get(j);
					if(answer.getUserID()==user.getUserID()){
						objectType.setObject(answer);
						objectType.setType(1);
					}
				}
			}else{
				for(int j=0;j<followingUser.size();j++){
					User user=followingUser.get(j);
					if(question.getUserID()==user.getUserID()){
						objectType.setObject(question);
						objectType.setType(0);
					}
				}
			}
			objectTypeList.add(objectType);
		}

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

		String path="my_index.jsp";
		request.getRequestDispatcher(path).forward(request, response);



	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
