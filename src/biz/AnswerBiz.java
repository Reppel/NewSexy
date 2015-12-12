package biz;

import dao.AnswerDao;
import dao.CommentDao;
import dao.QuestionDao;
import entity.Answer;
import entity.Question;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by m on 2015/6/24.
 */
public class AnswerBiz {
	private QuestionDao questionDao;
	private AnswerDao answerDao;
	private CommentDao commentDao;

	public AnswerBiz(){
		questionDao=new QuestionDao();
		answerDao=new AnswerDao();
		commentDao=new CommentDao();
	}

	public int addAnswer(int questionID,String questionTitle,int userID,String username,String headURL,String sign,String answerDetail,String answerPicture,Timestamp answerDate) throws SQLException {
		Answer answer=new Answer();
		answer.setQuestionID(questionID);
		answer.setQuestionTitle(questionTitle);
		answer.setUserID(userID);
		answer.setUsername(username);
		answer.setHeadURL(headURL);
		answer.setSign(sign);
		answer.setAnswerDetail(answerDetail);
		answer.setPictureURL(answerPicture);
		answer.setAnswerDate(answerDate);

		answerDao.AnswerSave(answer);
		Question question=questionDao.questionSearch(questionID);
		question.setAnswerCount(question.getAnswerCount()+1);
		questionDao.questionUpdateCount(question);

		return answerDao.answerSearchByQuestionAndUserId(questionID,userID).getAnswerID();
	}

}
