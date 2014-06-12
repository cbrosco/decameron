package decameron;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class StoryCreationServlet
 */
@WebServlet("/StoryCreationServlet")
public class StoryCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryCreationServlet() {
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
		try{
			Integer.parseInt(request.getParameter("numberOfLocs"));
		}catch(NumberFormatException e) {
			session.setAttribute("error", ErrorTypes.NOT_A_POSITIVE_INT);
			RequestDispatcher dispatch = request.getRequestDispatcher("admin.jsp");
			dispatch.forward(request, response);
			return;
			
		}
		Story st= new Story(Integer.parseInt(request.getParameter("num")), Integer.parseInt(request.getParameter("giorno")), request.getParameter("teller"), request.getParameter("extra"));
		session.setAttribute("story", st);
		session.setAttribute("numLocations", request.getParameter("numberOfLocs"));
		RequestDispatcher dispatch = request.getRequestDispatcher("location.jsp");
		dispatch.forward(request, response);
	}

}
