import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			String file = "E:\\cx\\workspace\\WebSite\\src\\Login.txt";
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(this.getServletContext()
					.getAttribute("visitTime").toString());
			fileWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			String file = "E:\\cx\\workspace\\WebSite\\src\\Login.txt";
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String count = bufferedReader.readLine();
			bufferedReader.close();
			fileReader.close();
			this.getServletContext().setAttribute("visitTime", count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String safelogout = request.getParameter("safelogout");
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("username")) {
					if (safelogout != null) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					} else {
						HttpSession httpSession = request.getSession(true);
						httpSession.setAttribute("username", cookie.getValue());
						response.sendRedirect("/WebSite/Welcome");
					}
				}
			}
		}

		HttpSession httpSession = request.getSession();
		String info = (String) httpSession.getAttribute("info");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		// printWriter.println("<%@ page language=\"java\" pageEncoding=\"UTF-8\" errorPage=\"/WebSite/ErrorPage\"%>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<title>用户登陆</title>");
		printWriter.println("</head>");
		printWriter.println("<body bgcolor=#CED3FF>");
		printWriter.println("<center>");
		if (info != null && info.equals("usernamenull")) {
			printWriter.println("<h1>用户名输入不能为空</h1>");
		} else if (info != null && info.equals("passwordnull")) {
			printWriter.println("<h1>密码输入不能为空</h1>");
		} else if (info != null && info.equals("usernameerror")) {
			printWriter.println("<h1>用户名输入错误</h1>");
		} else if (info != null && info.equals("passworderror")) {
			printWriter.println("<h1>密码输入错误</h1>");
		}
		printWriter.println("<h1>用户登陆</h1>");
		printWriter.println("<form action=/WebSite/LoginCl method=post>");
		printWriter.println("用户名：<input type=text name=username><br>");
		printWriter.println("密码：<input type=password name=password><br>");
		printWriter
				.println("<input type=checkbox name=userkeep value=2>两周内自动登录<br>");
		printWriter.println("<input type=submit value=登陆>");
		printWriter.println("<a href=/WebSite/Signup>注册</a>");
		printWriter.println("</form>");
		int time = Integer.parseInt(this.getServletContext()
				.getAttribute("visitTime").toString());
		time++;
		this.getServletContext().setAttribute("visitTime", time + "");
		printWriter.println("<h1>该网页被访问了 " + time + " 次</h1>");
		printWriter.println("</center>");
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

}
