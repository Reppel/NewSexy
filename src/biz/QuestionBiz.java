package biz;

import dao.AnswerDao;
import dao.CommentDao;
import dao.QuestionDao;
import entity.Question;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by m on 2015/6/24.
 */
public class QuestionBiz {
	private QuestionDao questionDao;
	private AnswerDao answerDao;
	private CommentDao commentDao;

	public QuestionBiz(){
		questionDao=new QuestionDao();
		answerDao=new AnswerDao();
		commentDao=new CommentDao();
	}

    public int addQuestion(int userID,String username,String questionTitle,String quetsionDetail,Timestamp questionDate) throws SQLException {
		Question question=new Question();
		question.setUserID(userID);
		question.setUsername(username);
		question.setQuestionTitle(questionTitle);
		question.setQuestionDetail(quetsionDetail);
		question.setQuestionDate(questionDate);
		question.setAnswerCount(0);

		questionDao.QuestionSave(question);
		int questionID=questionDao.questionSearchByTitle(questionTitle).getQuestionID();

		return questionID;
	}
}
