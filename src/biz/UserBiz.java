package biz;

import dao.HotUserDao;
import dao.UserDao;
import entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by leilixia on 2015/6/7.
 */
public class UserBiz {
	private UserDao userDao;
	private HotUserDao hotUserDao;
	public UserBiz(){
		userDao=new UserDao();
		hotUserDao=new HotUserDao();
	}

	public void userRegist(String email,String username,String password) throws SQLException {
		User user=new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setHeadURL("images/0.jpg");
		user.setSign("Smart is the new sexy.");
		user.setFollowerCount(0);
		user.setFollowingCount(0);
		userDao.userSave(user);
	}

	public User userLogin(String email,String password) throws SQLException {
		User compare=userDao.userSearchByEmail(email);
//		return (compare.getPassword().equals(password)?true:false);
		if((compare!=null)&&(compare.getPassword().equals(password))){
			return compare;
		}
		else{
			compare.setUserID(0);
			return compare;
		}
	}



	public void userUpdate(int id,String headURL,String password,String sign) throws SQLException {
		System.out.println("userBiz.userUpdate!");
		User user=userDao.userSearch(id);
		if(user!=null&&user.getUserID()!=0){
			user.setHeadURL(headURL);
			if(password!=null&&password!=""){
				user.setPassword(password);
			}
			user.setSign(sign);
			userDao.userUpdate(user);
		}
	}

	public List<User> userListAll() throws SQLException {
		return userDao.userListAll();
	}

	public List<User> hotUserListAll(){
		return hotUserDao.hotUserListAll();
	}

	public void userDelete(int id) throws SQLException {
		userDao.userDelete(id);
//		hotUserDao.hotUserDelete(id);

	}
	public User userListByUserId(int id) throws SQLException {
		return userDao.userListByUserId(id);
	}




}
