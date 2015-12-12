package servlet;

import dao.AnswerDao;
import dao.FollowDao;
import dao.QuestionDao;
import dao.UserDao;
import entity.Answer;
import entity.FollowRelation;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by m on 2015/6/16.
 */
public class UserProfileServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String path="";

		String user_id=request.getParameter("userID");
		int id=0;
		List<Question> question_list=new ArrayList<Question>();
		List<Answer> answer_list=new ArrayList<Answer>();
		List<User> followerList=new ArrayList<User>();
		List<User> follower_list=new ArrayList<User>();
		List<User> followingList=new ArrayList<User>();
		List<User> following_list=new ArrayList<User>();
		QuestionDao questionDao=new QuestionDao();
		AnswerDao answerDao=new AnswerDao();
		FollowDao followDao=new FollowDao();

		try{
			user_id=new String(user_id.getBytes("ISO-8859-1"),"utf-8");
			id=Integer.parseInt(user_id);
		}catch (Exception e){
			e.printStackTrace();
		}

		User user= null;
		try {
			user = new UserDao().userListByUserId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if((user!=null)&&(user.getUserID()!=0)){
			try {
				question_list=questionDao.questionListAllByUserId(id);
				answer_list=answerDao.answerListAllByUserId(id);
				followerList=followDao.listFollower(id);
				followingList=followDao.listFollowing(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}



			//log information
			Logger logger=Logger.getLogger("UserProfileServlet");
			logger.setLevel(Level.INFO);

			for(Answer answer:answer_list){
				try {
					answer.setQuestionTitle(questionDao.questionSearch(answer.getQuestionID()).getQuestionTitle());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(followerList.size()>6){
				for(int i=0;i<6;i++){
					follower_list.add(i,followerList.get(i));
				}
			}
			else{
				follower_list=followerList;
			}

			if(followingList.size()>6){
				for (int i=0;i<6;i++){
					following_list.add(i,followingList.get(i));
				}
			}
			else{
				following_list=followingList;
			}

			HttpSession session=request.getSession();
			Integer me_id=(Integer)session.getAttribute("userID");

			FollowRelation relation= null;

			int relation_flag=-1;
			int reverse_flag=1;
			try {
				relation = new FollowDao().getRelation(me_id,id);
				FollowRelation reverse_relation=new FollowDao().getRelation(id,me_id);
				if(relation==null&&reverse_relation==null){
					relation_flag=-1;
				}else if(relation!=null){
					relation_flag=relation.getRelation();
				}else if(reverse_relation!=null){
					reverse_flag=-1;
					switch (reverse_relation.getRelation()){
						case 0:relation_flag=1;break;
						case 1:relation_flag=0;break;
						default:break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}



			request.setAttribute("relation",relation_flag);
		    request.setAttribute("reverse_flag",reverse_flag);


			request.setAttribute("user", user);
			request.setAttribute("question_list",question_list);
			request.setAttribute("answer_list",answer_list);
			request.setAttribute("follower_list",follower_list);
			request.setAttribute("following_list",following_list);
			path="user_profile.jsp";
		}


		request.getRequestDispatcher(path).forward(request, response);


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
}
