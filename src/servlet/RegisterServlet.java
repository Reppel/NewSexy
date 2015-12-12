package servlet;

import biz.UserBiz;
import dao.UserDao;
import entity.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by m on 2015/6/9.
 */
public class RegisterServlet extends HttpServlet {

	public boolean isExisted(String email,String username) throws SQLException {
		int result=new UserDao().isExisted(email, username);
		if(result==1){
			return true;
		}
		else{
		    return false;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		try{
			email=new String(email.getBytes("ISO-8859-1"),"utf-8");
			username=new String(username.getBytes("ISO-8859-1"),"utf-8");
			password=new String(password.getBytes("ISO-8859-1"),"utf-8");
			if((email!="")&&(username!="")&&(password!="")&&(!isExisted(email,username))){
				new UserBiz().userRegist(email,username,password);
			}

		}catch (Exception e){
			e.printStackTrace();
		}


		User user= null;
		try {
			user = new UserDao().userSearchByEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String path="exploreServlet";
		HttpSession session=request.getSession();
		session.setAttribute("username", user.getUsername());
		session.setAttribute("email",user.getEmail());
		session.setAttribute("userID", user.getUserID());
		session.setAttribute("headURL",user.getHeadURL());
		session.setAttribute("sign",user.getSign());
		session.setAttribute("user_state","login");

		request.getRequestDispatcher(path).forward(request,response);



	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		JSONObject jsonObject=new JSONObject();
		String email=request.getParameter("email");
		String username=request.getParameter("username");

		try{
			email=new String(email.getBytes("ISO-8859-1"),"utf-8");
			username=new String(username.getBytes("ISO-8859-1"),"utf-8");
		}catch (Exception e){
			e.printStackTrace();
		}


		if((email!="")||(username!="")){
			try {
				if(isExisted(email,username)){
					jsonObject.put("flag",1);
				}
				else{
					jsonObject.put("flag",0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		PrintWriter out=response.getWriter();
		out.print(jsonObject.toString());
		out.close();
	}
}
