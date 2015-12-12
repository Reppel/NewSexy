package servlet;

import biz.UserBiz;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by m on 2015/6/15.
 */
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String path="";

		String email=request.getParameter("email");
		String password=request.getParameter("password");

		Logger logger=Logger.getLogger("LoginServlet");
		logger.setLevel(Level.INFO);

		HttpSession session=request.getSession();

		try{
			email=new String(email.getBytes("ISO-8859-1"),"utf-8");
			password=new String(password.getBytes("ISO-8859-1"),"utf-8");
		    User user=new UserBiz().userLogin(email,password);
			if(user.getUserID()!=0){
				request.setAttribute("user", user);

				session.setAttribute("username", user.getUsername());
				session.setAttribute("email",user.getEmail());
				session.setAttribute("userID", user.getUserID());
				session.setAttribute("headURL",user.getHeadURL());
				session.setAttribute("sign",user.getSign());
				session.setAttribute("user_state","login");

				//log information
				logger.info("username:"+user.getUsername());
				logger.info("email:"+user.getEmail());
				logger.info("userID:"+user.getUserID());
				logger.info("headURL:"+user.getHeadURL());
				logger.info("sign:"+user.getSign());

				path="myIndexServlet";

			}
			else{
				path="welcomeServlet";
				logger.info("path:"+path);
				session.setAttribute("user_state","failed");
				session.removeAttribute("username");
				session.removeAttribute("userID");
				session.removeAttribute("email");
				session.removeAttribute("headURL");
				session.removeAttribute("sign");

//				JSONObject object=new JSONObject();
//				object.put("login_result","failed");
//				PrintWriter out=response.getWriter();
//				out.print(object.toString());
//				out.close();

			}
		}catch (Exception e){
			e.printStackTrace();
		}

		request.getRequestDispatcher(path).forward(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
}
