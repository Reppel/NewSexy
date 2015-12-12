package biz;

import dao.AnswerDao;
import dao.CommentDao;
import dao.QuestionDao;
import dao.UserDao;
import entity.Answer;
import entity.Comment;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by m on 2015/6/24.
 */
public class CommentBiz {
	private UserDao userDao;
	private QuestionDao questionDao;
	private AnswerDao answerDao;
	private CommentDao commentDao;

	public CommentBiz(){
		questionDao=new QuestionDao();
		answerDao=new AnswerDao();
		commentDao=new CommentDao();
	}

	public void addComment(int answerID,int userID,String username,String headURL,String commentDetail,Timestamp commentDate) throws SQLException {
		Comment comment=new Comment();
		comment.setAnswerID(answerID);
		comment.setUserID(userID);
		comment.setUsername(username);
		comment.setHeadURL(headURL);
		comment.setCommentDetail(commentDetail);
		comment.setCommentDate(commentDate);
		commentDao.CommentSave(comment);

		Answer answer=answerDao.answerSearchByAnswerId(answerID);
		answer.setCommentCount(answer.getCommentCount()+1);
		answerDao.answerUpdateCount(answer);



	}


}
