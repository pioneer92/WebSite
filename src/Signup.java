

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
		printWriter.println("<title>�û�ע��</title>");				
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<center>");
		if (info != null) {
			if (info.endsWith("usernamenull")) {
				printWriter.println("<h1>�û�������Ϊ��</h1>");
			} else if (info.equals("passwordnull")) {
				printWriter.println("<h1>���벻��Ϊ��</h1>");
			} else if (info.equals("passwordagainnull")) {
				printWriter.println("<h1>ȷ�����벻��Ϊ��</h1>");
			} else if (info.equals("emailnull")) {
				printWriter.println("<h1>���䲻��Ϊ��</h1>");
			} else if (info.equals("passworderror")) {
				printWriter.println("<h1>�����������</h1>");
			} else if (info.endsWith("usernameexist")) {
				printWriter.println("<h1>�û����Ѵ���</h1>");
			} else if (info.endsWith("emailexist")) {
				printWriter.println("<h1>�����Ѵ���</h1>");
			} else if (info.endsWith("emailerror")) {
				printWriter.println("<h1>�����ʽ����</h1>");
			}
			httpSession.setMaxInactiveInterval(0);
		}
		printWriter.println("<h1>�������˻���Ϣ</h1>");
		printWriter.println("<form action=/WebSite/SignupCl method=post>");
		printWriter.println("�û�����<input type=text name=username><br>");
		printWriter.println("���룺<input type=password name=password><br>");
		printWriter.println("ȷ�����룺<input type=password name=passwordagain><br>");
		printWriter.println("���䣺<input type=text name=email><br>");
		printWriter.println("<input type=submit value=ע��>");
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
