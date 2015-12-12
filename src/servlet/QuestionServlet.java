package servlet;

import biz.UserBiz;
import dao.AnswerDao;
import dao.CommentDao;
import dao.QuestionDao;
import entity.Answer;
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
 * Created by m on 2015/6/22.
 */
public class QuestionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		QuestionDao questionDao=new QuestionDao();
		AnswerDao answerDao=new AnswerDao();
		CommentDao commentDao=new CommentDao();
		UserBiz userBiz=new UserBiz();

		List<Answer> answerList=new ArrayList<Answer>();

		String questionID=request.getParameter("questionID");
		int id=Integer.parseInt(questionID);

		Question question= null;
		try {
			question = questionDao.questionListByQuestionId(id);
			answerList=answerDao.answerListAllByQuestionIdLimit(id, 0, 3);
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		for(Answer answer:answerList){
//			answer.setCommentList(commentDao.commentListByAnswerId(id));
//		}

		request.setAttribute("question",question);
		request.setAttribute("answer_list",answerList);


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


		String path="questions.jsp";
		request.getRequestDispatcher(path).forward(request, response);




	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
}
