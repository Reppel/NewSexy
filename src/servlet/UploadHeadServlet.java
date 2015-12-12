package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by m on 2015/6/21.
 */
public class UploadHeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	UploadHeadServlet(){
//		super();
//	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload servletUpload = new ServletFileUpload(factory);
		servletUpload.setFileSizeMax(1024*50*1024);


		try{

			List<FileItem> fileItems = servletUpload.parseRequest(new ServletRequestContext(request));
			FileItem item = fileItems.get(0);
			String imgUrlString = item.getName();
//   		String fileSuf = imgUrlString.substring(imgUrlString.lastIndexOf("."));


			if (imgUrlString!=null && !(imgUrlString.equals(""))){
				String path = this.getServletContext().getRealPath("images/heads");
				System.out.println("path: "+path);

				File file = new File(path + "/" +imgUrlString);
				item.write(file);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
	}
}
