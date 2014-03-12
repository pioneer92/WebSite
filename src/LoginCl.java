

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCl
 */
@WebServlet("/LoginCl")
public class LoginCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LoginCl -----doGet");
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		HttpSession httpSession=request.getSession();
		httpSession.setMaxInactiveInterval(1);		
		if (username==null || username.equals("")) {	//用户名为空，返回重新登录
			httpSession.setAttribute("info", "usernamenull");
			response.sendRedirect("/WebSite/Login");
		} else if (password==null || password.equals("")) {		//密码为空，返回重新登录
			httpSession.setAttribute("info", "passwordnull");
			response.sendRedirect("/WebSite/Login");
		} else {			//用户名和密码验证			
			UserBeanCl userBeanCl =new UserBeanCl();
			int check=userBeanCl.checkUser(username, password);
			switch (check) {
				case 0:			//用户名不存在
					System.out.println(username);
					System.out.println(password);
					httpSession.setAttribute("info", "usernameerror");
					response.sendRedirect("/WebSite/Login");
					break;
					
				case 1:			//密码错误
					httpSession.setAttribute("info", "passworderror");
					response.sendRedirect("/WebSite/Login");
					break;
					
				case 2:			//用户名和密码都正确
					String userkeep	=request.getParameter("userkeep");
					System.out.println("userkeep------"+userkeep);
					if (userkeep!=null) {
						Cookie cookie=new Cookie("username", username);
						cookie.setMaxAge(14*24*3600);
						response.addCookie(cookie);
					}
					httpSession.setAttribute("username", username);
					response.sendRedirect("/WebSite/Main");
					break;

				default:
					break;
			}			
//			Connection connection=null;
//			Statement statement=null;
//			ResultSet resultSet=null;
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","root");
//				statement=connection.createStatement();
//				resultSet=statement.executeQuery("select password from users where username = '"+username+"' limit 1;");
//				if (resultSet.next()) {					//用户名存在
//					String dbpassword=resultSet.getString(1);
//					if (dbpassword!=null && password.equals(dbpassword)) {		//密码匹配
//						String userkeep	=request.getParameter("userkeep");
//						System.out.println("userkeep------"+userkeep);
//						if (userkeep!=null) {
//							Cookie cookie=new Cookie("username", username);
//							cookie.setMaxAge(14*24*3600);
//							response.addCookie(cookie);
//						}
//						httpSession.setAttribute("username", username);
//						response.sendRedirect("/WebSite/Welcome");
//					} else {													//密码不匹配
//						httpSession.setAttribute("info", "passworderror");
//						response.sendRedirect("/WebSite/Login");
//					}
//				} else {								//用户名不存在
//					System.out.println(username);
//					System.out.println(password);
//					httpSession.setAttribute("info", "usernameerror");
//					response.sendRedirect("/WebSite/Login");
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				response.sendRedirect("/WebSite/ErrorPage.jsp");
//			} finally {
//				try {
//					if (resultSet!=null) {
//						resultSet.close();
//					}
//					if (statement!=null) {
//						statement.close();
//					}
//					if (connection!=null) {
//						connection.close();
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					response.sendRedirect("/WebSite/ErrorPage.jsp");
//				}
//			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
