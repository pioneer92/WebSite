

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<title>�û���½</title>");				
		printWriter.println("</head>");
		printWriter.println("<body bgcolor=#CED3FF>");
		printWriter.println("<center>");	
		printWriter.println("<h1>������</h1>");
		printWriter.println("<a href=???>�����û�</a><br>");
		printWriter.println("<a href=???>����û�</a><br>");
		printWriter.println("<a href=???>�����û�</a><br>");
		printWriter.println("<a href=???>��ȫ�˳�</a><br>");
		printWriter.println("</center>");
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
