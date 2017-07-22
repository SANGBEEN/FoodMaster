package kr.co.fm.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize=1024*1024*10, maxRequestSize=1024*1024*100, fileSizeThreshold = 1024 * 1024 * 512)
public class FrontController extends HttpServlet {

	private HandlerMapping mappings = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	
		String configName = config.getInitParameter("configName");
		mappings = new HandlerMapping(configName);
	}

	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		uri = uri.substring(context.length());
		System.out.println("요청 uri : " + uri);

		try {
			Controller controller = mappings.getController(uri);
			String callPage = controller.handleRequest(request, response);
			
			if(callPage.indexOf("redirect:") != -1){
				callPage = callPage.substring(callPage.indexOf(":")+1);
				response.sendRedirect(callPage);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
				System.out.println("dispatcher"+dispatcher);
				dispatcher.forward(request, response);
			}
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
}











