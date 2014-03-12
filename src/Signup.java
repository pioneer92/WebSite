

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Signup------service");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession httpSession=request.getSession();
//		String info=request.getParameter("info");
		String info=(String) httpSession.getAttribute("info");
		PrintWriter printWriter = response.getWriter();
//		printWriter.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\" errorPage=\"/WebSite/ErrorPage\"%>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<title>用户注册</title>");				
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<center>");
		if (info != null) {
			if (info.endsWith("usernamenull")) {
				printWriter.println("<h1>用户名不能为空</h1>");
			} else if (info.equals("passwordnull")) {
				printWriter.println("<h1>密码不能为空</h1>");
			} else if (info.equals("passwordagainnull")) {
				printWriter.println("<h1>确认密码不能为空</h1>");
			} else if (info.equals("emailnull")) {
				printWriter.println("<h1>邮箱不能为空</h1>");
			} else if (info.equals("passworderror")) {
				printWriter.println("<h1>密码输入错误</h1>");
			} else if (info.endsWith("usernameexist")) {
				printWriter.println("<h1>用户名已存在</h1>");
			} else if (info.endsWith("emailexist")) {
				printWriter.println("<h1>邮箱已存在</h1>");
			} else if (info.endsWith("emailerror")) {
				printWriter.println("<h1>邮箱格式错误</h1>");
			}
			httpSession.setMaxInactiveInterval(0);
		}
		printWriter.println("<h1>请输入账户信息</h1>");
		printWriter.println("<form action=/WebSite/SignupCl method=post>");
		printWriter.println("用户名：<input type=text name=username><br>");
		printWriter.println("密码：<input type=password name=password><br>");
		printWriter.println("确认密码：<input type=password name=passwordagain><br>");
		printWriter.println("邮箱：<input type=text name=email><br>");
		printWriter.println("<input type=submit value=注册>");
		printWriter.println("</form>");
		printWriter.println("</center>");
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Signup------doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
