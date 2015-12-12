package servlet;

import dao.FollowDao;
import dao.UserDao;
import entity.FollowRelation;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by m on 2015/6/24.
 */
public class FollowServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		FollowDao followDao=new FollowDao();
		UserDao userDao=new UserDao();


		String from_id=request.getParameter("from_id");
		String to_id=request.getParameter("to_id");
		String new_relation=request.getParameter("new_relation");
		String reverse_flag=request.getParameter("reverse_flag");
		int fromID=0;
		int toID=0;
		int relation=0;
		int flag=0;

		try{
			fromID=Integer.parseInt(from_id);
			toID=Integer.parseInt(to_id);
			relation=Integer.parseInt(new_relation);
			flag=Integer.parseInt(reverse_flag);
		}catch (Exception e){
			e.printStackTrace();
		}

		System.out.println("fromID:"+fromID+" toID:"+toID+" relation:"+relation+" flag:"+flag);
		FollowRelation old_relation=null;
		//若正序
		if(flag==1){

			FollowRelation followRelation=new FollowRelation();
			followRelation.setFromUserID(fromID);
			followRelation.setToUserID(toID);
			followRelation.setRelation(relation);

			try {
				old_relation=followDao.getRelation(fromID,toID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//更新
			if(old_relation!=null){
				try {
					followDao.relationUpdate(followRelation);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{  //新建
				try {
					followDao.followRelationSave(followRelation);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}else if(flag==-1){//反序
			FollowRelation followRelation=new FollowRelation();
			followRelation.setFromUserID(toID);
			followRelation.setToUserID(fromID);
			switch (relation){
				case -1:followRelation.setRelation(-1);break;
				case 0:followRelation.setRelation(1);break;
				case 1:followRelation.setRelation(0);break;
				case 2:followRelation.setRelation(2);break;
			}
			try {
				followDao.relationUpdate(followRelation);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		User from_user= null;
		try {
			from_user = userDao.userListByUserId(fromID);
			User to_user=userDao.userListByUserId(toID);
			from_user.setFollowerCount(followDao.listFollower(fromID).size());
			from_user.setFollowingCount(followDao.listFollowing(fromID).size());
			to_user.setFollowerCount(followDao.listFollower(toID).size());
			to_user.setFollowingCount(followDao.listFollowing(toID).size());
			userDao.userUpdateFollower(from_user);
			userDao.userUpdateFollowing(from_user);
			userDao.userUpdateFollower(to_user);
			userDao.userUpdateFollowing(to_user);
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
}
