

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Welcome -----service");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession httpSession=request.getSession(true);
		String username=(String) httpSession.getAttribute("username");
		if (username ==null) {
			httpSession.setAttribute("info", "usernameerror");
			response.sendRedirect("/WebSite/Login");
		}
		PrintWriter printWriter = response.getWriter();
//		printWriter.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\" errorPage=\"/WebSite/ErrorPage\"%>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<title>登陆 成功</title>");	
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<center>");
		printWriter.println("<h1>"+username+",欢迎登陆</h1>");
		printWriter.println("<form action=/WebSite/Login method=post>");
		printWriter.println("<input type=hidden name=safelogout value=safelogout>");
		printWriter.println("<input type=submit value=安全退出>");
		printWriter.println("</form>");
		printWriter.println("<img src=/WebSite/images/psb.gif><br>");
		printWriter.println("</center>");
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Welcome -----service");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
