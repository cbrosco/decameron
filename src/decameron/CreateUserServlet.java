package decameron;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession session = request.getSession(true);
		Users usr= (Users)session.getAttribute("user");
		if(usr== null)System.out.println("Why is user null?");
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		boolean admin= (request.getParameter("makeAdmin") != null);
		if(username != null && !username.isEmpty() && password != null && !password.isEmpty()){
			Users newUsr= new Users(username, password, admin); //this puts user in DB so do not delete
			RequestDispatcher dispatch = request.getRequestDispatcher("userCreated.jsp");
			dispatch.forward(request, response);
			return;
		}
		session.setAttribute("error", ErrorTypes.NEW_USER_INFORMATION_MISSING);
		RequestDispatcher dispatch = request.getRequestDispatcher("userpage.jsp?userID=" + usr.getId());
		dispatch.forward(request, response);
		return;
	}

}
