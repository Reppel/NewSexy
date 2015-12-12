package biz;

import dao.FollowDao;
import dao.UserDao;

/**
 * Created by m on 2015/6/25.
 */
public class FollowBiz {
	FollowDao followDao;
	UserDao userDao;
	FollowBiz(){
		followDao=new FollowDao();
		userDao=new UserDao();
	}

	public void updateRelation(){

	}

	public void addRelation(){

	}


}
