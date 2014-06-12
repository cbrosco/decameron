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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		int userId= Users.validateCredentials(request.getParameter("username"), request.getParameter("password"));
		if(userId == -1){
			session.setAttribute("error", ErrorTypes.INVALID_USER);
			RequestDispatcher dispatch = request.getRequestDispatcher("signIn.jsp");
			dispatch.forward(request, response);
		}else{
			Users usr= new Users(userId);
			session.setAttribute("user", usr);
			String mapId= request.getParameter("mapToDelete");
			String tryingToSave= request.getParameter("saving");
			if(tryingToSave != null){
				RequestDispatcher dispatch = request.getRequestDispatcher("SaveMapServlet");
				dispatch.forward(request, response);
				return;	
			}
			if(mapId != null){
				int map= Integer.parseInt(mapId);
				if(map != Story.MULTI_STORY){
					RequestDispatcher dispatch = request.getRequestDispatcher("mapAndInfo.jsp?storyID=" + mapId);
					dispatch.forward(request, response);
					return;
				}	
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
			
		}
	}

}
