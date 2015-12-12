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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by m on 2015/6/21.
 */
public class EditProfileServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Logger logger=Logger.getLogger("LoginServlet");
		logger.setLevel(Level.INFO);

		HttpSession session=request.getSession();

		Integer id= (Integer) session.getAttribute("userID");
		String headURL=request.getParameter("image");
		String password=request.getParameter("password");
		String sign=request.getParameter("sign");

		try{
			headURL=new String(headURL.getBytes("ISO-8859-1"),"utf-8");
//			headURL="images/heads/"+headURL;
			sign=new String(sign.getBytes("ISO-8859-1"),"utf-8");
		}catch (Exception e){
			e.printStackTrace();
		}

		logger.info("headURL:"+headURL+" ;password:"+password+"; sign:"+sign);

		try {
			new UserBiz().userUpdate(id,headURL,password,sign);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("succeed!");

		session.setAttribute("headURL",headURL);
		session.setAttribute("sign",sign);


		String path="userProfileServlet?userID="+id+"";
		request.getRequestDispatcher(path).forward(request,response);


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Logger logger=Logger.getLogger("LoginServlet");
		logger.setLevel(Level.INFO);

		HttpSession session=request.getSession();

		Integer id= (Integer) session.getAttribute("userID");
		logger.info("userID from session:"+id);


		String password=request.getParameter("password");

		User compare= null;
		try {
			compare = new UserDao().userListByUserId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject object=new JSONObject();
	    if((compare!=null)&&(compare.getUserID()!=0)){
			if(compare.getPassword().equals(password)){
				object.put("flag",1);
			}
			else{
				object.put("flag",0);
			}
		}

		PrintWriter out=response.getWriter();
		out.print(object.toString());
		out.close();


	}




}
