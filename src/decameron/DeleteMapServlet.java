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
 * Servlet implementation class DeleteMapServlet
 */
@WebServlet("/DeleteMapServlet")
public class DeleteMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMapServlet() {
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
		session.setAttribute("story", null);
		int storyId= Integer.parseInt(request.getParameter("mapToDelete"));
		if(storyId == Story.MULTI_STORY){
			RequestDispatcher dispatch = request.getRequestDispatcher("deleteFailed.jsp");
			dispatch.forward(request, response);
			return;
		}
		Story st= new Story(storyId);
		boolean deleted= st.deleteStory();
		if(deleted){
			RequestDispatcher dispatch = request.getRequestDispatcher("deleteSuccess.jsp");
			dispatch.forward(request, response);
		} else{
			RequestDispatcher dispatch = request.getRequestDispatcher("deleteBad.jsp");
			dispatch.forward(request, response);
		}
	}

}
