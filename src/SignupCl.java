

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignupCl
 */
@WebServlet("/SignupCl")
public class SignupCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupCl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SignupCl-----service");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String passwordagain=request.getParameter("passwordagain");
		String email=request.getParameter("email");
		username=username.trim();
		password=password.trim();
		passwordagain=passwordagain.trim();
		email=email.trim();
		HttpSession httpSession=request.getSession();
		httpSession.setMaxInactiveInterval(1);				
		if (username==null || username.equals("")) {		//判断注册信息是否为空，若有任意一个信息为空则返回重新登录
			httpSession.setAttribute("info", "usernamenull");
			response.sendRedirect("/WebSite/Signup");
		} else if (password==null || password.equals("")) {
			httpSession.setAttribute("info", "passwordnull");
			response.sendRedirect("/WebSite/Signup");
		} else if (passwordagain==null || passwordagain.equals("")) {
			httpSession.setAttribute("info", "passwordagainnull");
			response.sendRedirect("/WebSite/Signup");
		} else if (email==null || email.equals("")) {
			httpSession.setAttribute("info", "emailnull");
			response.sendRedirect("/WebSite/Signup");
		} else if (!password.equals(passwordagain)) {
			httpSession.setAttribute("info", "passworderror");
			response.sendRedirect("/WebSite/Signup");
		} else if (email.indexOf("@")<=0 || email.indexOf(".com")<=0 || email.indexOf("@") != email.lastIndexOf("@") || email.indexOf(".com") != email.lastIndexOf(".com") || !email.endsWith(".com") || email.indexOf("@") >= email.indexOf(".com")) {		//判断email的格式是否正确
			httpSession.setAttribute("info", "emailerror");
			response.sendRedirect("/WebSite/Signup");
		} else {											//所有注册信息都不为空为空，进入用户名和邮箱验证			
			Connection connection=null;
			Statement statement=null;
			ResultSet resultSet=null;
			String sql=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","root");
				statement=connection.createStatement();
				sql="select username ,  email from users where username = '"+username+"' or email = '"+email+"'  limit 1;";
				print(sql);
				resultSet=statement.executeQuery(sql);
				if (resultSet.next()) {				//用户名或邮箱已存在
					System.out.println("username or email exist");
					String dbusername=resultSet.getString(1);
					String dbemail=resultSet.getString(2);
					System.out.println("dbusername-------"+dbusername);
					System.out.println("dbemail"+dbemail);
					if (username.equals(dbusername)) {
						httpSession.setAttribute("info", "usernameexist");						
					} else if (email.equals(dbemail)) {
						httpSession.setAttribute("info", "emailexist");
					}
					response.sendRedirect("/WebSite/Signup");					
				} else {		//用户名和邮箱可用
					System.out.println("details useable");
					sql="insert into users (username,password,email,grade) values ('"+username+"','"+password+"','"+email+"',5);";
					print(sql);
					statement.executeUpdate(sql);
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter printWriter=response.getWriter();
					printWriter.println("<html>");
					printWriter.println("<head>");
					printWriter.println("<title>注册成功</title>");
					printWriter.println("<meta http-equiv=refresh content=\"5;url=/WebSite/Login\">");
					printWriter.println("</head>");
					printWriter.println("<body>");
					printWriter.println("<center>");	
					printWriter.println("<h1>"+username+" 用户注册成功，请返回<a href=/WebSite/Login>登陆页面</a>重新登录</h1>");
					printWriter.println("</center>");
					printWriter.println("</body>");
					printWriter.println("</html>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("/WebSite/ErrorPage.jsp");
			} finally {
				try {
					if (resultSet!=null) {
						resultSet.close();
					}
					if (statement!=null) {
						statement.close();
					}
					if (connection!=null) {
						connection.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("/WebSite/ErrorPage.jsp");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Signup-----doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
	
	private void print(String string) {
		System.out.println(string);
	}

}
