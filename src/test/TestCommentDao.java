package test;

import dao.CommentDao;
import entity.Comment;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2015/6/8.
 */
public class TestCommentDao {
	public static void main(String[] args){
//		testCommentSave();
//		testCommentList();

	}

	public static void testCommentSave() throws SQLException {
	    Comment comment=new Comment();
		comment.setAnswerID(1);
		comment.setUserID(1);
		comment.setCommentDetail("my second comment!");
		Calendar calendar=Calendar.getInstance();
		Timestamp timestamp=new Timestamp(new Date().getTime());
		comment.setCommentDate(timestamp);

		new CommentDao().CommentSave(comment);

	}


	public static void testCommentList() throws SQLException {
		List<Comment> list=new CommentDao().commentListByAnswerId(1);
		for(Comment c:list){
		    System.out.println(c.getCommentID() + "," + c.getAnswerID() + "," + c.getUserID() + "," + c.getCommentDetail() + "," + c.getCommentDate());
		}
	}
}
